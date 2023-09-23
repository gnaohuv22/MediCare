/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.UserDAO;
import Models.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hoang
 */

public class UserLoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("user-login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email, password, error;
        email = request.getParameter("email");
        password = request.getParameter("password");
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        UserDAO ud = new UserDAO();

        HttpSession session = request.getSession();
//        String loginValue = "";
        session.setAttribute("email", email);
//        session.setAttribute("password", password);
        // Case 1: Both inputs email and password is valid:
        if (email != null && password != null && !email.equals("") && !password.equals("")) {
            // Check if the User exists in DB:
            // return null -> login fail -> (return to the login page):
            User u = ud.login(email, password);
            if (u == null) {
                System.out.println("Login fail!");
                error = "Email or password is wrong!";
                request.setAttribute("error", error);
                session.setAttribute("loginValue", "false");
                request.getRequestDispatcher("user-login.jsp").forward(request, response);
                // return a valid object -> login success -> (forward to home page):
            } else {
                session.setAttribute("email", email);
                session.setAttribute("name", u.getName());
                System.out.println("Login successfully!");
                session.setAttribute("loginValue", "true");
                response.sendRedirect("user-home");
            }
            // Case 2: Login with Google Account:
            // Case 3: One or both of inputs are invalid -> (return to the login page): 
        } else {
            error = "Email and password must be valid!";
            request.setAttribute("error", error);
            session.setAttribute("loginValue", "false");
            request.getRequestDispatcher("user-login.jsp").forward(request, response);
        }
    }
}
