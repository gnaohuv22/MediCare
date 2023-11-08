/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import Models.User;

/**
 *
 * @author tubinh
 */
public class UserRegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterUserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterUserServlet at " + request.getContextPath() + "</h1>");
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
        request.removeAttribute("confirmSuccess");
        request.getRequestDispatcher("user-register.jsp").forward(request, response);
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
        String birthDate = (request.getParameter("birthDate") == null || request.getParameter("birthDate").isEmpty() ? "" : request.getParameter("birthDate"));
        String gender = request.getParameter("gender");
        String address = (request.getParameter("address") == null || request.getParameter("address").isEmpty() ? "" : request.getParameter("address"));
        String phone = (request.getParameter("phone") == null || request.getParameter("phone").isEmpty() ? "" : request.getParameter("phone"));

        Date currentDate = new Date();
        java.sql.Timestamp createdAt = new java.sql.Timestamp(currentDate.getTime());

        User u = new User(email, password, name, birthDate, gender, address, phone, createdAt.toString());
        System.out.println("Real time: " + createdAt.toString());
        System.out.println("Gender: " + gender);

        UserDAO ud = new UserDAO();

        if (ud.registerUser(u)) {
            System.out.println("Register success!");
            request.setAttribute("errorMessage", "Register successfully. You will be directed to the Login page.");
            request.setAttribute("confirmSuccess", "1");
            request.getRequestDispatcher("user-register.jsp").forward(request, response);
        } else {
            request.removeAttribute("confirmSuccess");
            request.setAttribute("errorMessage", "Register not successful. Your username is already existed.");
            request.setAttribute("email", email);
            request.setAttribute("name", name);
            request.setAttribute("birthDate", birthDate);
            request.setAttribute("gender", gender);
            request.setAttribute("address", address);
            request.setAttribute("phone", phone);
            request.getRequestDispatcher("user-register.jsp").forward(request, response);

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
