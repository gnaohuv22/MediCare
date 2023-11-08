/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.ReviewDAO;
import Models.Doctor;
import Models.Reviews;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hoang
 */
public class DoctorReviewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("doctorLoggedIn") == null) {
            session.setAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            response.sendRedirect("user-login");
        } else {
            
            
            Doctor d = (Doctor) session.getAttribute("d");
            ReviewDAO rd = new ReviewDAO();
            
            ArrayList<Reviews> reviewList = rd.getReviewsByDoctorId(d.getId());
            Set<Double> uniqueRatingsSet = new HashSet<>();
            Set<String> uniqueServicesSet = new HashSet<>();
            
            for (Reviews r : reviewList) {
                double rating = Double.parseDouble(r.getRating());
                uniqueRatingsSet.add(Math.floor(rating));
                uniqueServicesSet.add(r.getAppointment().getSt().getNametag());
            }
            List<Double> uniqueRatings = new ArrayList<>(uniqueRatingsSet);
            Collections.sort(uniqueRatings);
            
            
            
            request.setAttribute("uniqueServicesSet", uniqueServicesSet);
            request.setAttribute("uniqueRatings", uniqueRatings);
            request.setAttribute("reviewList", reviewList);
            request.getRequestDispatcher("doctor-review.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
