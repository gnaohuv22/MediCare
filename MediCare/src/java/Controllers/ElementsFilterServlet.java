/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import Models.*;
import DAL.*;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
@WebFilter("/*")
public class ElementsFilterServlet implements Filter {
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        NewsCategoryDAO ncd = new NewsCategoryDAO();
        ArrayList<NewsCategory> categories = ncd.getNewsCategoryList();
        ArrayList<NewsCategory> navbar = ncd.getNavigationBar();
        ArrayList<NewsCategory> contacts = ncd.getContacts();
        ArrayList<NewsCategory> references = ncd.getReferences();
        ArrayList<NewsCategory> snsList = ncd.getSocial();
        ArrayList<NewsCategory> profileMenu = ncd.getProfileMenu();
        ArrayList<NewsCategory> profileSidebar = ncd.getProfileSidebar();
        ArrayList<NewsCategory> subMenu = ncd.getAllSubMenu();

        session.setAttribute("profileSidebar", profileSidebar);
        session.setAttribute("profileMenu", profileMenu);
        session.setAttribute("navbar", navbar);
        session.setAttribute("contacts", contacts);
        session.setAttribute("references", references);
        session.setAttribute("snsList", snsList);
        session.setAttribute("subMenu", subMenu);
        session.setAttribute("categories", categories);

        chain.doFilter(request, response); // Continue with the rest of the filter chain
    }
}
