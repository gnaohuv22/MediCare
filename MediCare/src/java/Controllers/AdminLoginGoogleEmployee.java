/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import Models.Employee;
import Models.GooglePojo;
import DAL.EmployeeDAO;
import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import DAL.GoogleUtils;
import DAL.SubLevelMenuDAO;

/**
 *
 * @author DELL
 */
@WebServlet(name="LoginGoogleEmployee", urlPatterns={"/login-google-employee-controller"})
public class AdminLoginGoogleEmployee extends HttpServlet {
   private static final long serialVersionUID = 1L;
    private final String EMPLOYEE_LOGIN="admin-screen/admin-login.jsp";
    private final String EMPLOYEE_HOME_PAGE="admin-home-page";
    private final String LOGIN_FAIL = "Đăng nhập không thành công!";
    private final String LOGIN_GOOGLE_EMAIL_ERROR = "Tài khoản google không khớp!";
    private final String LOGIN_GOOGLE_ERROR = "Không đăng nhập bằng google được!";
    private final String ADMIN_ROLE = "1";
    private final String EMPLOYEE_MANAGER = "3";
    private final String EMPLOYEE_PAGE = "admin-list-employee";
    private final String USER_MANAGER = "4";
    private final String USER_PAGE = "admin-list-user";
    private final String APPOINTMENT_MANAGER = "5";
    private final String APPOINTMENT_PAGE = "admin-list-appointments";
    private final String REVIEW_MANAGER = "6";
    private final String REVIEW_PAGE = "admin-list-review";
    private final String NEWS_MANAGER = "7";
    private final String NEWS_PAGE = "admin-list-news";
    private final String DOCTOR_MANAGER = "8";
    private final String DOCTOR_PAGE = "admin-doctors";

    public AdminLoginGoogleEmployee() {
        super();
    }
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginGoogleEmployee</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginGoogleEmployee at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            request.setAttribute("MESSAGE",LOGIN_FAIL+" "+LOGIN_GOOGLE_ERROR);
            request.getRequestDispatcher(EMPLOYEE_LOGIN).forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
//            request.setAttribute("id", googlePojo.getId());
//            request.setAttribute("name", googlePojo.getName());
            request.setAttribute("email", googlePojo.getEmail());
            HttpSession session = request.getSession();
            EmployeeDAO dao = new EmployeeDAO();
            Employee emp = dao.getEmployeeByEmail(googlePojo.getEmail());
            if (emp == null){
                request.setAttribute("MESSAGE",LOGIN_FAIL+" "+LOGIN_GOOGLE_EMAIL_ERROR);
                request.getRequestDispatcher(EMPLOYEE_LOGIN).forward(request, response);
            }
            session.setAttribute("EMPLOYEE", emp);
            SubLevelMenuDAO slmDao = new SubLevelMenuDAO();
            session.setAttribute("ADMIN_SIDEBAR_MENU", slmDao.getSidebarMenu());
            switch (emp.getEmployeeRole().getId()) {
                    case ADMIN_ROLE:
                        response.sendRedirect(EMPLOYEE_HOME_PAGE);
                        break;
                    case EMPLOYEE_MANAGER:
                        response.sendRedirect(EMPLOYEE_PAGE);
                        break;
                    case USER_MANAGER:
                        response.sendRedirect(USER_PAGE);
                        break;
                    case APPOINTMENT_MANAGER:
                        response.sendRedirect(APPOINTMENT_PAGE);
                        break;
                    case REVIEW_MANAGER:
                        response.sendRedirect(REVIEW_PAGE);
                        break;
                    case NEWS_MANAGER:
                        response.sendRedirect(NEWS_PAGE);
                        break;
                    case DOCTOR_MANAGER:
                        response.sendRedirect(DOCTOR_PAGE);
                        break;
                    default:
                        response.sendRedirect(EMPLOYEE_HOME_PAGE);
                        
                }
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
