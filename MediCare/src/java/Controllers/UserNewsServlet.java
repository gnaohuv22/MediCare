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
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
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

        NewsDAO nd = new NewsDAO();
        NewsCategoryDAO ncd = new NewsCategoryDAO();

        ArrayList<NewsCategory> topLevel = ncd.getTopLevelSideBar();
        ArrayList<NewsCategory> subLevel = ncd.getSubLevelSideBar();
        List<Integer> parentIds = ncd.getParentCategoryNumber();

        request.setAttribute("topLevel", topLevel);
        request.setAttribute("subLevel", subLevel);
        request.setAttribute("parentIds", parentIds);

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            System.out.println("pathParts' length: " + pathParts.length);

            if (pathParts.length > 1) {
                String categorySlug = pathParts[1];
                NewsCategory nc = ncd.getCategoryBySlug(categorySlug);
//                for (Integer i : parentIds) {
//                    if (nc.getParentId().equals(String.valueOf(i))) {
//                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
//                        return;
//                    }
//                }
                if (nc != null) {
                    breadcrumbs.add(new Breadcrumb("news/" + categorySlug, nc.getName()));
                    ArrayList<News> news = nd.getListNewsByCategory(Integer.parseInt(nc.getId()));
                    HashMap<NewsCategory, ArrayList<News>> categorizedNews = categorizeNews(news, ncd);

                    request.setAttribute("news", news);
                    request.setAttribute("nc", nc);
                    request.setAttribute("categorizedNews", categorizedNews);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    return;
                }
            }

            if (pathParts.length > 2) {
                String slug = pathParts[2];
                System.out.println("slug: " + slug);
                System.out.println("ncd.getCategoryBySlug(slug): " + String.valueOf((ncd.getCategoryBySlug(slug) == null)));
                if (ncd.getCategoryBySlug(slug) == null) { //day la mot tin thuoc category khong co category con
                    News n = nd.getNewsFromSlug(slug);
                    if (n != null) {
                        breadcrumbs.add(new Breadcrumb("news/" + slug, n.getTitle()));
                        ArrayList<News> related = nd.getNNewestExcept(3, n.getId());

                        request.setAttribute("n", n);
                        request.setAttribute("related", related);
                        request.setAttribute("breadcrumbs", breadcrumbs);

                        request.getRequestDispatcher("/user-news.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    }
                } else { //day la mot category con thuoc mot category cha 
                    NewsCategory subCategory = ncd.getCategoryBySlug(slug);
                    breadcrumbs.add(new Breadcrumb("news/" + ncd.getParentCategoryOf(subCategory).getHref() + "/" + slug, subCategory.getName()));

                    ArrayList<News> news = nd.getListNewsByCategory(Integer.parseInt(subCategory.getId()));
                    HashMap<NewsCategory, ArrayList<News>> categorizedNews = categorizeNews(news, ncd);
                    request.setAttribute("news", news);
                    request.setAttribute("nc", subCategory);
                    request.setAttribute("categorizedNews", categorizedNews);
                    request.setAttribute("breadcrumbs", breadcrumbs);
                    //if pathParts.length > 3
                    if (pathParts.length > 3) { //day la mot tin tuc trong mot category con thuoc mot category cha
                        slug = pathParts[3];
                        System.out.println("slug: " + slug);
                        
                        News selectedNews = nd.getNewsFromSlug(slug);
                        breadcrumbs.add(new Breadcrumb("news/" + pathParts[1] + "/" + pathParts[2] + "/" + pathParts[3], selectedNews.getTitle()));
                        ArrayList<News> related = nd.getNNewestExcept(3, selectedNews.getId());
                        
                        request.setAttribute("n", selectedNews);
                        request.setAttribute("related", related);
                        request.setAttribute("breadcrumbs", breadcrumbs);

                        request.getRequestDispatcher("/user-news.jsp").forward(request, response);
                    } else {
                        request.getRequestDispatcher("/user-category.jsp").forward(request, response);
                    }
                }
            } else {
                request.setAttribute("breadcrumbs", breadcrumbs);
                request.getRequestDispatcher("/user-category.jsp").forward(request, response);
            }
        } else {
            News centeredNews = nd.getNewest(1).get(0);
            if (centeredNews != null) {
                setupNewsAttributes(request, centeredNews, nd, ncd, breadcrumbs);
                request.setAttribute("breadcrumbs", breadcrumbs);
                request.getRequestDispatcher("/user-news-general.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NO_CONTENT);
            }
        }
    }

    private void setupNewsAttributes(HttpServletRequest request, News centeredNews, NewsDAO nd, NewsCategoryDAO ncd, ArrayList<Breadcrumb> breadcrumbs) {
        request.setAttribute("centeredNews", centeredNews);

        ArrayList<News> news = nd.getNewestExcept(centeredNews.getId());
        HashMap<NewsCategory, ArrayList<News>> categorizedNews = categorizeNews(news, ncd);

        request.setAttribute("categorizedNews", categorizedNews);
    }

    private HashMap<NewsCategory, ArrayList<News>> categorizeNews(ArrayList<News> newsList, NewsCategoryDAO ncd) {
        HashMap<NewsCategory, ArrayList<News>> categorizedNews = new HashMap<>();

        for (News ns : newsList) {
            NewsCategory category = ncd.getCategoryBySlug(ns.getCategory().getHref());
            NewsCategory parentCategory = ncd.getParentCategoryOf(category);
            if (parentCategory != null && !parentCategory.getHref().equals("news")) {
                if (!categorizedNews.containsKey(parentCategory)) {
                    categorizedNews.put(parentCategory, new ArrayList<>());
                }
                categorizedNews.get(parentCategory).add(ns);
            } else {
                if (!categorizedNews.containsKey(category)) {
                    categorizedNews.put(category, new ArrayList<>());
                }
                categorizedNews.get(category).add(ns);
            }
        }
        return categorizedNews;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
