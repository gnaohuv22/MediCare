/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.NewsCategoryDAO;
import DAL.NewsDAO;
import DAL.SubLevelMenuDAO;
import Models.Employee;
import Models.News;
import Models.NewsCategory;
import Models.RegisterError;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import java.io.InputStream;
import java.util.Base64;
import org.apache.commons.io.IOUtils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class AdminNews extends HttpServlet {

    private final String STATISTIC_NEWS = "admin-news/admin-news.jsp";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
    private final String NEED_LOGIN = "Bạn cần đăng nhập để truy cập vào nội dung này";
    private final String ADD_NEWS = "admin-news/admin-add-news.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StatisticNews</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticNews at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Models.Employee checkEmp = (Models.Employee) session.getAttribute("EMPLOYEE");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", NEED_LOGIN);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        try {
            if (request.getParameter("add-news") != null
                    || request.getParameter("edit-news") != null
                    || request.getParameter("delete-news") != null) {
                throw new AdminException.RedirecUrlException();
            }
            //get list news
            NewsDAO newsdao = new NewsDAO();
            NewsCategoryDAO ncdao = new NewsCategoryDAO();
            SubLevelMenuDAO slmDao = new SubLevelMenuDAO();
            int page = 1;
            final int recordsPerPage = 5;
            //set start page = 1
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            ArrayList<News> list = newsdao.getListNews((page - 1) * recordsPerPage, recordsPerPage);
            ArrayList<String> titleList = slmDao.getTitleTable("titleTableNews");
            //insert <th></th>
            for (int i = 0; i < titleList.size(); i++) {
                titleList.set(i, "<th>" + titleList.get(i) + "</th>");
            }
            int recordCount = newsdao.countAllNews();
            int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
            List<News> newsList = newsdao.getNewsByThai();
            request.setAttribute("newsList", newsList);
            newsList.get(1).getSlug();
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);
            request.setAttribute("TITLE_NEWS", titleList);
            session.setAttribute("ALL_NEWS", list);
            request.setAttribute("ALL_NEWSCATEGORY", ncdao.getAllNewsCategory());
            request.getRequestDispatcher(STATISTIC_NEWS).forward(request, response);
        } catch (AdminException.RedirecUrlException e) {
            try {
                if (request.getParameter("delete-news") != null) {
                    throw new AdminException.RedirecUrlException();
                }
                NewsDAO newsdao = new NewsDAO();
                NewsCategoryDAO ncdao = new NewsCategoryDAO();
                String id = request.getParameter("id");
                String edit = request.getParameter("edit");
                request.setAttribute("edit", edit);
                request.setAttribute("ALL_NEWSCATEGORY", ncdao.getAllNewsCategory());
                if (id == null) {
                    request.setAttribute("add_news", true);
                    request.getRequestDispatcher(ADD_NEWS).forward(request, response);
                } else {
                    News getNews = newsdao.getNewsById(id);
                    request.setAttribute("edit_news", true);
                    request.setAttribute("EDIT_NEWS", getNews);
                    request.getRequestDispatcher(ADD_NEWS).forward(request, response);
                }
            } catch (AdminException.RedirecUrlException e2) {
                NewsDAO dao = new NewsDAO();
                String id = request.getParameter("id");
                dao.deleteNewsById(id);
                //get list news
                NewsDAO newsdao = new NewsDAO();
                SubLevelMenuDAO slmDao = new SubLevelMenuDAO();
                int page = 1;
                final int recordsPerPage = 5;
                //set start page = 1
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<News> list = newsdao.getListNews((page - 1) * recordsPerPage, recordsPerPage);
                ArrayList<String> titleList = slmDao.getTitleTable("titleTableNews");
                //insert <th></th>
                for (int i = 0; i < titleList.size(); i++) {
                    titleList.set(i, "<th>" + titleList.get(i) + "</th>");
                }
                int recordCount = newsdao.countAllNews();
                int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
                request.setAttribute("pageCount", pageCount);
                request.setAttribute("currentPage", page);
                request.setAttribute("TITLE_NEWS", titleList);
                session.setAttribute("ALL_NEWS", list);
                request.getRequestDispatcher(STATISTIC_NEWS).forward(request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Employee checkEmp = (Employee) session.getAttribute("EMPLOYEE");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        try {
            if (request.getParameter("edit-news") != null) {
                throw new AdminException.RedirecUrlException();
            }
            //add 
            RemoveAccent ra = new RemoveAccent();
            request.setAttribute("add_news", true);
            String id = null;
            String title = request.getParameter("title");
            System.out.println("Title of news in Add : " + title);
            String content = null;
            String newsCategoryId = null;
            String subtitle = null;
            NewsDAO newsdao = new NewsDAO();
            NewsCategoryDAO ncdao = new NewsCategoryDAO();
            request.setAttribute("ALL_NEWSCATEGORY", ncdao.getAllNewsCategory());
            boolean error = false;
            RegisterError msg = new RegisterError();
            //----------File Handle 
//            Part filePart = request.getPart("coverImage");
//            System.out.println("filePart value at add : " + filePart);
            String coverImage = "";
//            Base64Encoding base = new Base64Encoding();

            try {
                id = newsdao.generateId();
                //generate id if duplicate
                while (newsdao.checkNews(id)) {
                    id = Integer.parseInt(id) + 1 + "";
                }
            } catch (Exception e) {
                error = true;
                msg.setIdError("Lỗi");
            }
            try {
                title = request.getParameter("title");
//                request.setAttribute("title", title);
                //check empty string
                if (title != null && title.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setTitleError(e.getMessage());
            }
            try {
                content = request.getParameter("content");
                //check empty string
                if (content.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setContentError(e.getMessage());
            }
            newsCategoryId = request.getParameter("newsCategoryId");
            request.setAttribute("newsCategoryId", newsCategoryId);
             
                coverImage = request.getParameter("coverImage");
                request.setAttribute("coverImage", coverImage);
                //check empty string
               
           

            try {
                subtitle = request.getParameter("subtitle");
                //check empty string
                if (subtitle.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setSubtitleError(e.getMessage());
            }
            //----------File Handle 
//            boolean fileCheck = base.isFileValid(filePart);
//            if (filePart.getSubmittedFileName().equals("")) {
//                coverImage = "";
//            } else if (fileCheck == true) {
//                coverImage = base.convertImageToBase64(filePart);
//            } else {
//                request.setAttribute("profilePictureError", "Ảnh phải có định dạng .PNG hoặc .JPG và không được có kích thước vượt quá 50kb, chọn ảnh khác");
//                error = true;
//            }

            //validate
            if (error) {
                request.setAttribute("MESSAGE", "Không đăng kí được!");
                request.setAttribute("REGISTER_ERROR", msg);
                request.getRequestDispatcher(ADD_NEWS).forward(request, response);
            } else {
                String slug = ra.removeAccent(title);
                System.out.println("slug : " + slug);
                NewsCategory newsCategory = new NewsCategory(newsCategoryId, "");
                News news = new News(id, title, content, checkEmp.getEmail(), newsCategory, coverImage, "", "0", "", subtitle, slug);
                newsdao.addNews(news);
                //add news
                request.setAttribute("MESSAGE", "Đăng kí thành công!");
                request.getRequestDispatcher(ADD_NEWS).forward(request, response);
            }

        } catch (AdminException.RedirecUrlException e) {
            //edit

            request.setAttribute("edit_news", true);
            NewsDAO newsDao = new NewsDAO();
            String id = request.getParameter("id");
            String author;
            String title = null;
            String content = null;
            String newsCategoryId;
            String subtitle = null;
            boolean error = false;
            NewsDAO newsdao = new NewsDAO();
            NewsCategoryDAO ncdao = new NewsCategoryDAO();
            request.setAttribute("ALL_NEWSCATEGORY", ncdao.getAllNewsCategory());
            RegisterError msg = new RegisterError();
            request.setAttribute("id", id);
            request.setAttribute("edit", 1);
            News getNews = newsdao.getNewsById(id);
            //----------File Handle 
//            Part filePart = request.getPart("coverImage");
//            System.out.println("filePart value at add : " + filePart);
            String coverImage = "";
//            Base64Encoding base = new Base64Encoding();

            try {
                author = getNews.getAuthor();
                request.setAttribute("author", author);
                //check empty string
                if (author.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setAuthorError(ex.getMessage());
            }
            try {
                title = request.getParameter("title");
                request.setAttribute("title", title);
                //check empty string
                if (title.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setTitleError(ex.getMessage());
            }
            try {
                content = request.getParameter("content");
                request.setAttribute("content", content);
                //check empty string
                if (content.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setContentError(ex.getMessage());
            }
            newsCategoryId = request.getParameter("newsCategoryId");
            request.setAttribute("newsCategoryId", newsCategoryId);
            //----------File Handle 
//            boolean fileCheck = base.isFileValid(filePart);
//            if (filePart.getSubmittedFileName().equals("")) {
//                coverImage = newsDao.getNewsById(id).getCoverImage();
//            } else if (fileCheck == true) {
//                coverImage = base.convertImageToBase64(filePart);
//            } else {
//                request.setAttribute("profilePictureError", "Ảnh phải có định dạng .PNG hoặc .JPG và không được có kích thước vượt quá 50kb, chọn ảnh khác");
//                error = true;
//            }
               try {
                coverImage = request.getParameter("coverImage");
                request.setAttribute("coverImage", coverImage);
                //check empty string
                if (coverImage.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException ep) {
                error = true;
                msg.setCoverImageError(ep.getMessage());
            }
            try {
                subtitle = request.getParameter("subtitle");
                request.setAttribute("subtitle", subtitle);
                //check empty string
                if (subtitle.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setSubtitleError(ex.getMessage());
            }
            request.setAttribute("EDIT_NEWS", getNews);
            //validate
            if (error) {
                request.setAttribute("MESSAGE", "Không sửa được!");
                request.setAttribute("REGISTER_ERROR", msg);
                request.getRequestDispatcher(ADD_NEWS).forward(request, response);
            } else {
                NewsCategory newCategory = new NewsCategory(newsCategoryId, "");
                News news = new News(id, title, content, getNews.getAuthor(), newCategory, coverImage, "", "", "", subtitle);
                //return massage
                if (newsdao.setNewsById(news)) {
                    request.setAttribute("EDIT_NEWS", news);
                    request.setAttribute("MESSAGE", "Sửa thành công!");
                    request.getRequestDispatcher(ADD_NEWS).forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Sửa không thành công!");
                    request.getRequestDispatcher(ADD_NEWS).forward(request, response);
                }
            }
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
