/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Employee;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import DAL.EmployeeDAO;
import DAL.SubLevelMenuDAO;


//@WebServlet(name = "LoginEmployeeServlet", urlPatterns = {"/admin-login-employee"})
public class AdminLoginEmployee extends HttpServlet {
    private final String EMPLOYEE_LOGIN="admin-screen/admin-login.jsp";
    private final String EMPLOYEE_HOME_PAGE="admin-home-page";
    private final String LOGIN_FAIL = "Đăng nhập không thành công!";
    private final String LOGIN_EMPTY_EMAIL_ERROR = "Chưa nhập thông tin email";
    private final String LOGIN_EMPTY_PASSWORD_ERROR = "Chưa nhập thông tin mật khẩu";
    private final String LOGIN_EMAIL_ERROR = "Tài khoản không khớp!";
    private final String LOGIN_PASSWORD_ERROR = "Mật khẩu sai!";
    private final String ADMIN_ROLE = "1";
    private final String NON_ADMIN_ROLE = "2";
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
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            RequestDispatcher rd = request.getRequestDispatcher(EMPLOYEE_LOGIN);
            rd.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        String password = request.getParameter("password");
        //check if input is empty
        if (email.isEmpty()){
            request.setAttribute("MESSAGE", LOGIN_FAIL+" "+LOGIN_EMPTY_EMAIL_ERROR);
            request.getRequestDispatcher(EMPLOYEE_LOGIN).forward(request, response);
        }
        if (password.isEmpty()){
            request.setAttribute("MESSAGE", LOGIN_FAIL+" "+LOGIN_EMPTY_PASSWORD_ERROR);
            request.getRequestDispatcher(EMPLOYEE_LOGIN).forward(request, response);
        }
        session.setAttribute("EMAIL", email);
        EmployeeDAO dao = new EmployeeDAO();
        Employee emp = dao.getEmployeeByEmail(email);
        // check employee is exist
        if (emp == null){
            request.setAttribute("MESSAGE", LOGIN_FAIL+" "+LOGIN_EMAIL_ERROR);
            request.getRequestDispatcher(EMPLOYEE_LOGIN).forward(request, response);
        }
        else{
            String storedPassword = emp.getPassword();
            boolean check = PasswordEncryption.comparePasswords(password, storedPassword);
            //check login and get info employee
            if (check == false) {
                request.setAttribute("MESSAGE",LOGIN_FAIL+" "+LOGIN_PASSWORD_ERROR);
                RequestDispatcher rd = request.getRequestDispatcher(EMPLOYEE_LOGIN);
                rd.forward(request, response);
            } 
            else {
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
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
