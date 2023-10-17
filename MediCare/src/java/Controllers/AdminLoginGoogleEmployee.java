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

/**
 *
 * @author DELL
 */
@WebServlet(name="LoginGoogleEmployee", urlPatterns={"/login-google-employee-controller"})
public class AdminLoginGoogleEmployee extends HttpServlet {
   private static final long serialVersionUID = 1L;
    private final String EMPLOYEE_LOGIN="admin-screen/admin-login.jsp";
    private final String EMPLOYEE_PAGE="admin-home-page";
    private final String LOGIN_FAIL = "Đăng nhập không thành công!";
    private final String LOGIN_GOOGLE_EMAIL_ERROR = "Tài khoản google không khớp!";
    private final String LOGIN_GOOGLE_ERROR = "Không đăng nhập bằng google được!";

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
            response.sendRedirect(EMPLOYEE_PAGE);
//            RequestDispatcher dis = request.getRequestDispatcher(EMPLOYEE_PAGE);
//            dis.forward(request, response);
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
