/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.BranchDAO;
import DAL.DoctorDAO;
import DAL.NavigationItemDAO;
import DAL.ReviewsDAO;
import DAL.ServiceTagDAO;
import DAL.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import Models.Branch;
import Models.Doctor;
import Models.NavigationItem;
import Models.Reviews;
import Models.ServiceTag;
import Models.User;

/**
 *
 * @author tubinh
 */
public class UserHomeServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String value = request.getParameter("value");
        System.out.println("value: " + value);
        HttpSession session = request.getSession();
        if (value != null) {
            session.removeAttribute("email");
            session.removeAttribute("loginValue");
        }

        BranchDAO bd = new BranchDAO();
        ArrayList<Branch> branches = bd.getAllBranches();

        ServiceTagDAO sd = new ServiceTagDAO();
        ArrayList<ServiceTag> servicesTop10 = sd.getTop10ServiceTags();
        
        NavigationItemDAO nid = new NavigationItemDAO();
        ArrayList<NavigationItem> listNavigationItem = nid.getListNavigationItem();
        
        ReviewsDAO rd = new ReviewsDAO();
        ArrayList<Reviews> topReviewList = rd.getTopReviews();
        
        UserDAO ud = new UserDAO();
        ArrayList<User> listUser = ud.getAllUsers();
        
        DoctorDAO dd = new DoctorDAO();
        ArrayList<Doctor> trendDoctors = dd.getTrendingDoctors();
        
        session.setAttribute("trendDoctors", trendDoctors);
        session.setAttribute("listUser", listUser);
        session.setAttribute("topReviewList", topReviewList);
        session.setAttribute("branches", branches);
        session.setAttribute("servicesTop10", servicesTop10);
        session.setAttribute("listNavigationItem", listNavigationItem);
        request.getRequestDispatcher("user-home.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
