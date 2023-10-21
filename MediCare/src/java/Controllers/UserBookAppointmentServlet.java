/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AppointmentsDAO;
import DAL.BranchDAO;
import DAL.FamilyProfileDAO;
import DAL.ScheduleDetailDAO;
import DAL.ServiceTagDAO;
import DAL.UserDAO;
import Models.Branch;
import Models.FamilyProfile;
import Models.ServiceTag;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author tubinh
 */
public class UserBookAppointmentServlet extends HttpServlet {

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
            out.println("<title>Servlet UserBookingAppointmentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserBookingAppointmentServlet at " + request.getContextPath() + "</h1>");
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

        BranchDAO bd = new BranchDAO();
        ArrayList<Branch> branches = bd.getAllBranches();

        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTimeInMillis());

        ServiceTagDAO std = new ServiceTagDAO();
        ArrayList<ServiceTag> services = std.getAllServiceTags();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        UserDAO ud = new UserDAO();
        User u = ud.login(email);
        if (u != null) {
            FamilyProfileDAO fpd = new FamilyProfileDAO();
            ArrayList<FamilyProfile> profiles = (ArrayList<FamilyProfile>) fpd.getFamilyProfileListByUserOwnerIdForBooking(u.getId());
            
            System.out.println("doGET - to Booking Appointment:");
            for (FamilyProfile profile : profiles) {
                System.out.println(profile);
            }
            request.setAttribute("profiles", profiles);
            request.setAttribute("ownerId", u.getId());
        }

        request.setAttribute("branches", branches);
        request.setAttribute("services", services);
        request.setAttribute("currentDate", currentDate);

        request.getRequestDispatcher("user-book-appointment.jsp").forward(request, response);
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
        String branchId, serviceId, doctorId, date, slotId;
        branchId = request.getParameter("branchId");
        serviceId = request.getParameter("serviceId");
        doctorId = request.getParameter("doctorId");
        date = request.getParameter("booking-calendar");
        slotId = request.getParameter("booking-slot");
        String patientName = request.getParameter("patientName");
        String gender = request.getParameter("gender");
        String birthDate = request.getParameter("birthDate");
        String phone = request.getParameter("phone");
        String emailPatient = request.getParameter("email");
        String symptoms = request.getParameter("description");
//        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        String password = (String) session.getAttribute("password");
        UserDAO ud = new UserDAO();
        User u = ud.login(email);

        java.util.Date currentDate = new java.util.Date();
        java.sql.Timestamp createdAt = new java.sql.Timestamp(currentDate.getTime());

        AppointmentsDAO ad = new AppointmentsDAO();
//        ad.test(u==null?null:u.getId());

        ScheduleDetailDAO sdd = new ScheduleDetailDAO();
        String startTime = sdd.getStartTimeBySlotId(slotId);

        String plannedAt = date + " " + startTime;
        if (ad.addNewAppointment(u == null ? null : u.getId(), branchId, serviceId, doctorId, plannedAt, slotId, createdAt.toString(),
                patientName, gender, birthDate, phone, emailPatient, symptoms, email, password) && sdd.setStatusForBookingSlot(slotId, doctorId, date)) {
            System.out.println("Add appointment to database successfully!");
            System.out.println("Planned at :.... = " + plannedAt);
        } else {
            System.out.println("Planned at :.... = " + plannedAt);
            System.out.println("Add appointment to database FAIL!!!");
        }
        System.out.println("email = " + email + " | branchId = " + branchId + " | serviceId = " + serviceId + " | doctorId = " + doctorId + " | date = " + date + " | slotId = " + slotId);
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("Yêu cầu đặt lịch của bạn thành công! Chúng tôi sẽ sớm liên hệ với bạn qua email để xác thực thông tin.");
        }
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
