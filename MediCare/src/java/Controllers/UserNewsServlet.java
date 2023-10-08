/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.NewsDAO;
import Models.News;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class UserNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        if (pathInfo == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length == 3) {
            NewsDAO nd = new NewsDAO();
//            String categorySlug = pathParts[1];
            String newsSlug = pathParts[2];
            
            News n = nd.getNewsFromSlug(newsSlug);
            
            if (n == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            ArrayList<News> related = nd.getNewest(n.getId());

            
            request.setAttribute("n", n);
            request.setAttribute("related", related);
            
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user-news.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}