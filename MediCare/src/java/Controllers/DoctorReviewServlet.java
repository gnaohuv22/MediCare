/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.ReviewDAO;
import Models.Doctor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author hoang
 */
public class DoctorReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 5;
        HttpSession session = request.getSession();
        if (session.getAttribute("doctorLoggedIn") == null) {
            session.setAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            response.sendRedirect("user-login");
        } else {
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            Doctor d = (Doctor) session.getAttribute("d");
            ReviewDAO rd = new ReviewDAO();
            
            request.setAttribute("reviewList", rd.getReviewsByDoctorId(d.getId(), (page - 1) * recordsPerPage, recordsPerPage));
            
            int records = rd.getNumberRecord();
            System.out.println("records: " + records);
            int pages = (int) Math.ceil(records * 1.0 / recordsPerPage);

            request.setAttribute("currentPage", page);
            request.setAttribute("pages", pages);
            request.getRequestDispatcher("doctor-review.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
