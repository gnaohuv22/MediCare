/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AppointmentsDAO;
import DAL.BranchDAO;
import DAL.EmployeeDAO;
import DAL.NewsCategoryDAO;
import DAL.ServiceTagDAO;
import DAL.SubLevelMenuDAO;
import Models.AdminSidebarMenu;
import Models.Appointments;
import Models.Branch;
import Models.Doctor;
import Models.ServiceTag;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AdminSearchManageAppointment", urlPatterns = {"/admin-search-manage-appointments"})
public class AdminSearchManageAppointment extends HttpServlet {

    private final String STATISTIC_APPOINTMENT = "admin-appointments/admin-manage-appointments.jsp";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
    private final String NEED_LOGIN = "Bạn cần đăng nhập truy cập vào trang này";
    private final String NEED_ROLE = "Bạn cần có quyền để truy cập vào trang này";
    private final String[] ROLE = {"1", "2", "3"};

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminSearchManageAppointment</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminSearchManageAppointment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        EmployeeDAO edao = new EmployeeDAO();
        Models.Employee checkEmp = (Models.Employee) session.getAttribute("EMPLOYEE");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", NEED_LOGIN);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        } else {
            try {
                String empRole = checkEmp.getEmployeeRole().getId();
                //check the role of employee
                for (String roleNeed : ROLE) {
                    if (empRole.equals(roleNeed)) {
                        throw new Exception();
                    }
                }
                request.setAttribute("MESSAGE", NEED_ROLE);
                request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
            } catch (Exception e) {
            }
            SubLevelMenuDAO sd = new SubLevelMenuDAO();
            ArrayList<AdminSidebarMenu> statusAppointments = sd.getSubLevelMenuByContent("Trạng thái cuộc hẹn");
            String searchName = request.getParameter("searchName");
            request.setAttribute("searchName", searchName);
            String searchServiceTag = request.getParameter("searchServiceTag");
            request.setAttribute("searchServiceTag", searchServiceTag);
            String searchBranch = request.getParameter("");
            request.setAttribute("searchBranch", searchBranch);
            String searchStatus = request.getParameter("searchStatus");
            request.setAttribute("searchStatus", searchStatus);
            String searchStartDate = request.getParameter("searchStartDate");
            request.setAttribute("searchStartDate", searchStartDate);
            if (searchStartDate==null||searchStartDate.isEmpty()){
                searchStartDate = "2000-01-01";
            }
            String searchEndDate = request.getParameter("searchEndDate");
            request.setAttribute("searchEndDate", searchEndDate);
            long millis=System.currentTimeMillis();   
            java.sql.Date getDate=new java.sql.Date(millis);
            String sdate=""+getDate;
            if (searchEndDate==null||searchEndDate.isEmpty()){
                String[] split = sdate.split("-");
                searchEndDate = Integer.parseInt(split[0]) + 10 +"-"+split[1]+"-"+split[2]; 
            }
            Doctor doctor = new Doctor("", searchName, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
            ServiceTag sevice = new ServiceTag(searchServiceTag);
            Branch branch = new Branch(searchBranch, "", "", "");
            Appointments searchAppointment = new Appointments(doctor, sevice, searchStatus, branch);
            AppointmentsDAO ad = new AppointmentsDAO();
            ServiceTagDAO stdao = new ServiceTagDAO();
            BranchDAO bdao = new BranchDAO();
            NewsCategoryDAO ncdao = new NewsCategoryDAO();
            request.setAttribute("ALL_SERVICETAG", stdao.getAllServiceTags());
            request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
            request.setAttribute("ALL_STATUS", ncdao.getAllStatus());
            ArrayList<Appointments> appointments = ad.searchAllAppointment(searchAppointment, searchStartDate, searchEndDate);
            if (appointments != null) {
                for (Appointments appointment : appointments) {
                    System.out.println(appointment);
                    SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                    // Định dạng ngày tháng ban đầu
                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

                    // Định dạng ngày tháng mới (không có mili giây)
                    SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");

                    try {
                        java.util.Date date = inputFormat.parse(appointment.getPlannedAt());
                        String timeWithoutMillis = outputFormat.format(date);
                        appointment.setAppointmentTime(timeWithoutMillis);
                    } catch (ParseException e) {
                    }

                    try {
                        java.util.Date appointmentDate = inputDateFormat.parse(appointment.getAppointmentDay()); // Chuyển đổi chuỗi thành Date
                        String formattedDate = outputDateFormat.format(appointmentDate); // Chuyển đổi lại thành chuỗi
                        appointment.setAppointmentDay(formattedDate);
                    } catch (ParseException e) {
                        // Xử lý lỗi nếu định dạng chuỗi không khớp
                    }
                }
            }
            request.setAttribute("ALL_APPOINTMENT", appointments);
            request.setAttribute("statusAppointments", statusAppointments);
            request.getRequestDispatcher(STATISTIC_APPOINTMENT).forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
