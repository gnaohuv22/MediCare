package Controllers;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import DAL.AwardsDAO;
import DAL.DoctorDAO;
import DAL.EducationDAO;
import DAL.ExperienceDAO;
import DAL.RatingStarDAO;
import DAL.ResearchPapersDAO;
import DAL.ReviewsDAO;
import DAL.ServiceTagDAO;
import Models.Awards;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.Doctor;
import Models.Education;
import Models.Experience;
import Models.RatingStar;
import Models.ResearchPapers;
import Models.Reviews;
import Models.ServiceTag;
import jakarta.servlet.http.HttpSession;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class UserDoctorDetailServlet extends HttpServlet {

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
            out.println("<title>Servlet DoctorDetailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DoctorDetailServlet at " + request.getContextPath() + "</h1>");
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
        String doctorId = request.getParameter("doctorId");
        HttpSession session = request.getSession();
        session.removeAttribute("numberOfRatings");
        session.removeAttribute("overallRating");

        DoctorDAO dd = new DoctorDAO();
        Doctor d = dd.getDoctorByDoctorId(doctorId);
        if (d != null) {
            ServiceTagDAO std = new ServiceTagDAO();
            ArrayList<ServiceTag> services = std.getServiceTagsByDoctorId(doctorId);

            ReviewsDAO rvd = new ReviewsDAO();
            ArrayList<Reviews> reviews = rvd.getReviewsByDoctorId(doctorId);
            int numberOfReviews = rvd.numberOfReviews(doctorId);
            int numberOfRatings = rvd.numberOfRatings(doctorId);

            ExperienceDAO ed = new ExperienceDAO();
            ArrayList<Experience> experiences = ed.getExperiencesOfDoctorByDoctorId(doctorId);

            AwardsDAO awd = new AwardsDAO();
            ArrayList<Awards> awards = awd.getAwardsOfDoctorByDoctorId(doctorId);

            EducationDAO edd = new EducationDAO();
            ArrayList<Education> education = edd.getEducationOfDoctorByDoctorId(doctorId);

            ResearchPapersDAO rpd = new ResearchPapersDAO();
            ArrayList<ResearchPapers> papers = rpd.getResearchPapersOfDoctorByDoctorId(doctorId);

            RatingStarDAO rsd = new RatingStarDAO();
            ArrayList<RatingStar> ratingstars = rsd.getRatingStarInfoByDoctorId(doctorId);
            double overallRating_raw = rsd.overallRating(ratingstars);
            DecimalFormat df = new DecimalFormat("#.#"); // Định dạng để chỉ hiển thị 1 số sau dấu thập phân
            String overallRating = df.format(overallRating_raw);
            
            request.setAttribute("doctor", d);
            request.setAttribute("servicesOfdoctor", services);
            request.setAttribute("reviews", reviews);
            request.setAttribute("numberOfReviews", numberOfReviews);
            request.setAttribute("numberOfRatings", numberOfRatings);
            request.setAttribute("experiences", experiences);
            request.setAttribute("awards", awards);
            request.setAttribute("education", education);
            request.setAttribute("papers", papers);
            request.setAttribute("ratingstars", ratingstars);
            request.setAttribute("overallRating", overallRating);
        }
        request.getRequestDispatcher("user-doctor-detail.jsp").forward(request, response);
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
        processRequest(request, response);
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
