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


@WebServlet(name = "LoginEmployeeServlet", urlPatterns = {"/admin-login-employee"})
public class AdminLoginEmployee extends HttpServlet {
    private final String EMPLOYEE_LOGIN="admin-screen/admin-login.jsp";
    private final String EMPLOYEE_PAGE="admin-home-page";
    private final String LOGIN_FAIL = "Đăng nhập không thành công!";
    private final String LOGIN_EMPTY_EMAIL_ERROR = "Chưa nhập thông tin email";
    private final String LOGIN_EMPTY_PASSWORD_ERROR = "Chưa nhập thông tin mật khẩu";
    private final String LOGIN_EMAIL_ERROR = "Tài khoản không khớp!";
    private final String LOGIN_PASSWORD_ERROR = "Mật khẩu sai!";
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
                response.sendRedirect(EMPLOYEE_PAGE);
            }
        }
        
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
