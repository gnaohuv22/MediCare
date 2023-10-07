/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.NewsDAO;
import Models.News;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author hoang
 */
public class UserNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String pathInfo = request.getPathInfo(); // /{slug}/{id}
//        System.out.println("Request URI: " + request.getRequestURI());
//        System.out.println("Query string: " + request.getQueryString());
//        String[] pathParts = pathInfo.split("-");
//        String idStr = pathParts[pathParts.length - 1];
//
//        System.out.println("getTitle: " + idStr);
//        NewsDAO nd = new NewsDAO();
//
//        News n = nd.getNewsById("5");
//
//        request.setAttribute("n", n);
//        request.getRequestDispatcher("user-news.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}