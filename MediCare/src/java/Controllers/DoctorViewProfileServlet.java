/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AwardsDAO;
import DAL.DoctorDAO;
import DAL.EducationDAO;
import DAL.ExperienceDAO;
import DAL.RatingStarDAO;
import DAL.ResearchPapersDAO;
import DAL.ReviewsDAO;
import DAL.ServiceTagDAO;
import Models.Awards;
import Models.Doctor;
import Models.Education;
import Models.Experience;
import Models.RatingStar;
import Models.ResearchPapers;
import Models.Reviews;
import Models.ServiceTag;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class DoctorViewProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("doctorLoggedIn") == null) {
            session.setAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            response.sendRedirect("user-login");
        } else {
            Doctor d = (Doctor) session.getAttribute("d");
            String doctorId = d.getId();

            ServiceTagDAO std = new ServiceTagDAO();
            ReviewsDAO rvd = new ReviewsDAO();
            ExperienceDAO ed = new ExperienceDAO();
            AwardsDAO awd = new AwardsDAO();
            EducationDAO edd = new EducationDAO();
            ResearchPapersDAO rpd = new ResearchPapersDAO();
            RatingStarDAO rsd = new RatingStarDAO();

            ArrayList<ServiceTag> services = std.getServiceTagsByDoctorId(doctorId);
            ArrayList<Reviews> reviews = rvd.getReviewsByDoctorId(doctorId);
            int numberOfRatings = rvd.numberOfRatings(doctorId);
            ArrayList<Experience> experiences = ed.getExperiencesOfDoctorByDoctorId(doctorId);
            ArrayList<Awards> awards = awd.getAwardsOfDoctorByDoctorId(doctorId);
            ArrayList<Education> education = edd.getEducationOfDoctorByDoctorId(doctorId);
            ArrayList<ResearchPapers> papers = rpd.getResearchPapersOfDoctorByDoctorId(doctorId);
            ArrayList<RatingStar> ratingstars = rsd.getRatingStarInfoByDoctorId(doctorId);
            double overallRating_raw = rsd.overallRating(ratingstars);
            System.out.println("overallRating_raw: " + overallRating_raw);
            DecimalFormat df = new DecimalFormat("#.#"); // Định dạng để chỉ hiển thị 1 số sau dấu thập phân
            String overallRating = df.format(overallRating_raw);

            request.setAttribute("servicesOfdoctor", services);
            request.setAttribute("reviews", reviews);
            request.setAttribute("experiences", experiences);
            request.setAttribute("awards", awards);
            request.setAttribute("education", education);
            request.setAttribute("papers", papers);
//            request.setAttribute("numberOfRatings", numberOfRatings);
//            request.setAttribute("overallRating", overallRating);

            session.setAttribute("numberOfRatings", numberOfRatings);
            session.setAttribute("overallRating", overallRating);

            request.getRequestDispatcher("doctor-view-profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
