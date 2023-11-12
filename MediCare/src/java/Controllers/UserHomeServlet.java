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
        String pathInfo = request.getPathInfo();
        String value = request.getParameter("value");
        System.out.println("value: " + pathInfo);
        HttpSession session = request.getSession();
        if (value != null) {
            session.removeAttribute("email");
            session.removeAttribute("loginValue");
        }

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
        ArrayList<News> pages = nd.getTopLevelMenu();

        NewsCategoryDAO ncd = new NewsCategoryDAO();
        ArrayList<NewsCategory> categories = ncd.getNewsCategoryList();
        ArrayList<NewsCategory> navbar = ncd.getNavigationBar();
        ArrayList<NewsCategory> contacts = ncd.getContacts();
        ArrayList<NewsCategory> references = ncd.getReferences();
        ArrayList<NewsCategory> snsList = ncd.getSocial();
        ArrayList<NewsCategory> profileMenu = ncd.getProfileMenu();
        ArrayList<NewsCategory> profileSidebar = ncd.getProfileSidebar();
        
        ArrayList<NewsCategory> subMenu = ncd.getAllSubMenu();
        session.setAttribute("pages", pages);
        session.setAttribute("profileSidebar", profileSidebar);
        session.setAttribute("profileMenu", profileMenu);
        session.setAttribute("navbar", navbar);
        session.setAttribute("contacts", contacts);
        session.setAttribute("references", references);
        session.setAttribute("snsList", snsList);
        session.setAttribute("subMenu", subMenu);
        session.setAttribute("topNews", topNews);
        session.setAttribute("categories", categories);
        session.setAttribute("bannerList", bannerList);
        session.setAttribute("bannerDetailsList", bannerDetailsList);
        session.setAttribute("trendDoctors", trendDoctors);
        session.setAttribute("listUser", listUser);
        session.setAttribute("topReviewList", topReviewList);

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