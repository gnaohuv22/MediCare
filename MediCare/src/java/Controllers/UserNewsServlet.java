/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.NewsCategoryDAO;
import DAL.NewsDAO;
import DAL.SubLevelMenuDAO;
import Models.News;
import Models.NewsCategory;
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

//        if (pathInfo == null) {
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
        System.out.println("pathInfo: " + pathInfo);
        String[] pathParts;
        if (pathInfo != null) {
            pathParts = pathInfo.split("/");
            if (pathParts.length == 3) {
                NewsDAO nd = new NewsDAO();
                //            String categorySlug = pathParts[1];
                String newsSlug = pathParts[2];
                News n = nd.getNewsFromSlug(newsSlug);
                if (n == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                ArrayList<News> related = nd.getNewestExcept(n.getId());
                request.setAttribute("n", n);
                request.setAttribute("related", related);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/user-news.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            NewsDAO nd = new NewsDAO();
            NewsCategoryDAO ncd = new NewsCategoryDAO();

            News centeredNews = nd.getNewest(1).get(0);
            if (centeredNews != null) {
                request.setAttribute("centeredNews", centeredNews);

                ArrayList<NewsCategory> topLevel = ncd.getTopLevelCategory();
                ArrayList<NewsCategory> subLevel = ncd.getSubLevelCategory();
                ArrayList<News> news = nd.getNewestExcept(centeredNews.getId());

                request.setAttribute("topLevel", topLevel);
                request.setAttribute("subLevel", subLevel);
                request.setAttribute("parentIds", ncd.getParentCategoryNumber());
                request.setAttribute("news", news);
            } 
            request.getRequestDispatcher("/user-news-general.jsp").forward(request, response);
            }
        }

        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        }
    }
