/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
@WebServlet(name = "UserLoginController", urlPatterns = {"/user-login"})
public class UserLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user-login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO ud = new UserDAO();
        if (ud.login(email, password)) {
            try (PrintWriter out = response.getWriter()) {
                out.println("<html><body>");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Login successful');");
                out.println("window.location.href = 'user-home.jsp';");
                out.println("</script>");
                out.println("</body></html>");
            }
        } else {
            request.setAttribute("email", email);
            request.setAttribute("msg", "Email or password is incorrect");
            request.getRequestDispatcher("user-login.jsp").forward(request, response);
        }
    }
}
