/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.*;
import Models.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.util.ArrayList;

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
        
        BannerDAO bnd = new BannerDAO();
        ArrayList<Banner> bannerList = bnd.getAllBanner();
        
        BannerDetailsDAO bdd = new BannerDetailsDAO();
        ArrayList<BannerDetails> bannerDetailsList = bdd.getAllBannerDetails();
        
        NewsDAO nd = new NewsDAO();
        ArrayList<News> topNews = nd.getTopNews();
        
        NewsCategoryDAO ncd = new NewsCategoryDAO();
        ArrayList<NewsCategory> categories = ncd.getNewsCategoryList();
        
        EmployeeDAO ed = new EmployeeDAO();
        ArrayList<Employee> employees = ed.getEmployeeList();
        
        session.setAttribute("topNews", topNews);
        session.setAttribute("categories", categories);
        session.setAttribute("employees", employees);
        session.setAttribute("bannerList", bannerList);
        session.setAttribute("bannerDetailsList", bannerDetailsList);
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
        try (PrintWriter out = response.getWriter()) {
            out.print("<h1>This page should not be found!!</h1>");
            out.print("<p>+1 easter egg</p>");
        }
    }
}
