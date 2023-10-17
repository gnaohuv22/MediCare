/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.NewsCategoryDAO;
import DAL.NewsDAO;
import Models.Breadcrumb;
import Models.News;
import Models.NewsCategory;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hoang
 */
public class UserNewsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        System.out.println("pathInfo: " + pathInfo);
        ArrayList<Breadcrumb> breadcrumbs = new ArrayList<>();
        breadcrumbs.add(new Breadcrumb("news", "Tin tức"));
        
        String[] pathParts;
        if (pathInfo != null) {
            pathParts = pathInfo.split("/");

            if (pathParts.length > 1) {
                NewsDAO nd = new NewsDAO();

                String categorySlug = pathParts[1];
                NewsCategoryDAO ncd = new NewsCategoryDAO();
                
                breadcrumbs.add(new Breadcrumb("news/" + categorySlug, ncd.getCategoryBySlug(categorySlug).getName()));
                
                NewsCategory nc = ncd.getCategoryBySlug(categorySlug);
                ArrayList<News> news = nd.getListNewsByCategory(Integer.parseInt(nc.getId()));
                ArrayList<NewsCategory> topLevel = ncd.getTopLevelSideBar();
                ArrayList<NewsCategory> subLevel = ncd.getSubLevelSideBar();

                request.setAttribute("topLevel", topLevel);
                request.setAttribute("subLevel", subLevel);
                request.setAttribute("parentIds", ncd.getParentCategoryNumber());
                request.setAttribute("news", news);
                request.setAttribute("nc", nc);
                request.setAttribute("breadcrumbs", breadcrumbs);
            }
            if (pathParts.length > 2) {
                NewsDAO nd = new NewsDAO();
                //            String categorySlug = pathParts[1];
                String newsSlug = pathParts[2];
                
                breadcrumbs.add(new Breadcrumb("news/" + newsSlug, nd.getNewsFromSlug(newsSlug).getTitle()));
                News n = nd.getNewsFromSlug(newsSlug);
                if (n == null) {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
                ArrayList<News> related = nd.getNewestExcept(3, n.getId());
                request.setAttribute("n", n);
                request.setAttribute("related", related);
                request.setAttribute("breadcrumbs", breadcrumbs);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/user-news.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/user-category.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            
            NewsDAO nd = new NewsDAO();
            NewsCategoryDAO ncd = new NewsCategoryDAO();

            News centeredNews = nd.getNewest(1).get(0);
            if (centeredNews != null) {
                request.setAttribute("centeredNews", centeredNews);

                ArrayList<NewsCategory> topLevel = ncd.getTopLevelSideBar();
                ArrayList<NewsCategory> subLevel = ncd.getSubLevelSideBar();
                ArrayList<News> news = nd.getNewestExcept(centeredNews.getId());

                request.setAttribute("topLevel", topLevel);
                request.setAttribute("subLevel", subLevel);
                request.setAttribute("parentIds", ncd.getParentCategoryNumber());
                request.setAttribute("news", news);
                request.setAttribute("breadcrumbs", breadcrumbs);

                request.getRequestDispatcher("/user-news-general.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NO_CONTENT);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
