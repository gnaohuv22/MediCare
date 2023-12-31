/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DoctorDAO;
import DAL.UserDAO;
import Models.Doctor;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import Models.User;

/**
 *
 * @author tubinh
 */
public class UserLoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginUserServlet at " + request.getContextPath() + "</h1>");
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
        if (session.getAttribute("loginValue") != null) {
            response.sendRedirect("user-home");
        } else if (session.getAttribute("doctorLoggedIn") != null) {
            response.sendRedirect("doctor-home");
        } else {
            request.getRequestDispatcher("user-login.jsp").forward(request, response);
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
        String email, password, error;
        email = request.getParameter("email");
        password = request.getParameter("password");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        UserDAO ud = new UserDAO();
        DoctorDAO dd = new DoctorDAO();

        HttpSession session = request.getSession();
        System.out.println("loginValue: " + session.getAttribute("loginValue"));
        System.out.println("doctorLoggedIn: " + session.getAttribute("doctorLoggedIn"));

//        String loginValue = "";
        session.setAttribute("email", email);
//        session.setAttribute("password", password);
        // Case 1: Both inputs email and password is valid:
        if (email != null && password != null && !email.equals("") && !password.equals("")) {
            // Check if the User exists in DB:
            //return null -> check if Doctor exist in DB
            // return null -> login fail -> (return to the login page):
            Object typeObj = request.getAttribute("type");
            String type = String.valueOf(typeObj);
            User u = null;
            Doctor d = null;
            if (typeObj == null
                    || type.isEmpty()
                    || !type.trim().equals("null")) {
                u = ud.login(email, password);
                d = dd.login(email, password);
            } else {
                d = dd.login(email, password);
            }
            if (u == null && d == null) {
                System.out.println("Login fail!");
                error = "Tài khoản hoặc mật khẩu không đúng!";
                request.setAttribute("error", error);
                session.setAttribute("loginValue", "false");
                request.getRequestDispatcher("user-login.jsp").forward(request, response);
                // return a valid object -> login success -> (forward to home page):
            } else if (d == null && u != null) {
                session.setAttribute("email", email);
                session.setAttribute("name", u.getName());
                session.setAttribute("loginValue", "true");
                response.sendRedirect("user-home");
            } else if (d != null && u == null) {
                session.setAttribute("d", d);
                session.setAttribute("doctorLoggedIn", "true");
                response.sendRedirect("doctor-home");
            }
        } else {
            error = "Tài khoản hoặc mật khẩu có thể không hợp lệ!";
            request.setAttribute("error", error);
            session.setAttribute("loginValue", "false");
            request.getRequestDispatcher("user-login.jsp").forward(request, response);
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
