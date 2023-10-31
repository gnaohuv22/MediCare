/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.UserDAO;
import Models.GooglePojo;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;

/**
 *
 * @author tubinh
 */
public class UserRegisterGoogleHandler extends HttpServlet {

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
            out.println("<title>Servlet UserRegisterGoogleHandler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserRegisterGoogleHandler at " + request.getContextPath() + "</h1>");
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
        String code = request.getParameter("code");
        HttpSession session = request.getSession();
        if (code == null || code.isEmpty()) {
            request.getRequestDispatcher("user-register.jsp").forward(request, response);
        } else {
            UserDAO ud = new UserDAO();
            String accessToken = GoogleUtils.getToken1(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo1(accessToken);
            String email = googlePojo.getEmail();
            if (email != null && !email.equals("")) {
                    request.setAttribute("id", googlePojo.getId());
                    request.setAttribute("name", googlePojo.getName());
                    request.setAttribute("email", googlePojo.getEmail());
                    System.out.println("id: " + googlePojo.getId());
                    System.out.println("name: " + googlePojo.getName());
                    System.out.println("email: " + email);
                if (ud.getUserRegistered(email) != null) {
                    request.setAttribute("error", "This email is already registered!");
                }
                request.getRequestDispatcher("user-register-google-account.jsp").forward(request, response);
            }
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
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String name = request.getParameter("name");

            Date currentDate = new Date();
            java.sql.Timestamp createdAt = new java.sql.Timestamp(currentDate.getTime());

            User u = new User(email, password, name, createdAt.toString());
            System.out.println("Real time: " + createdAt.toString());
            System.out.println("User: " + u);
            UserDAO ud = new UserDAO();

            if (ud.registerUserGoogleAccount(u)) {
                System.out.println("Register success!");
                request.setAttribute("errorMessage", "Register successfully. You will be directed to the Login page.");
                request.setAttribute("confirmSuccess", "1");
                request.getRequestDispatcher("user-register-google-account.jsp").forward(request, response);
            } else {
                request.removeAttribute("confirmSuccess");
                System.out.println("Register fail!");
                request.setAttribute("errorMessage", "Register not successful. Your username is already existed.");
                request.setAttribute("email", email);
                request.setAttribute("name", name);
                request.getRequestDispatcher("user-register-google-account.jsp").forward(request, response);
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
