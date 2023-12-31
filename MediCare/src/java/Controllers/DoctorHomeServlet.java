/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Models.*;
import DAL.*;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class DoctorHomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("doctor") != null) {
            session.setAttribute("doctorLoggedIn", true);
            
        } else {
            
        }
 
        NewsCategoryDAO ncd = new NewsCategoryDAO();
        NewsDAO nd = new NewsDAO();

        ArrayList<NewsCategory> navbar = ncd.getDoctorNavigationBar();
        ArrayList<NewsCategory> subMenu = ncd.getAllSubMenu();
        ArrayList<NewsCategory> contacts = ncd.getContacts();
        ArrayList<NewsCategory> references = ncd.getReferences();
        ArrayList<NewsCategory> snsList = ncd.getSocial();
        ArrayList<NewsCategory> doctorProfileMenu = ncd.getDoctorProfileMenu();
        ArrayList<News> pages = nd.getTopLevelMenu();
        ArrayList<News> topNews = nd.getTopNews();

        session.setAttribute("pages", pages);
        session.setAttribute("navbar", navbar);
        session.setAttribute("subMenu", subMenu);
        session.setAttribute("contacts", contacts);
        session.setAttribute("references", references);
        session.setAttribute("snsList", snsList);
        session.setAttribute("topNews", topNews);
        session.setAttribute("doctorProfileMenu", doctorProfileMenu);

        request.getRequestDispatcher("doctor-home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
