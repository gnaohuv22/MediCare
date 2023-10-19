/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.ReviewDAO;
import DAL.SubLevelMenuDAO;
import Models.Reviews;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
//@WebServlet(name = "Review", urlPatterns = {"/admin-list-review"})
public class AdminReview extends HttpServlet {

    private final String STATISTIC_REVIEW = "admin-reviews/admin-reviews.jsp";
    private final String REVIEW_PAGE = "admin-list-review";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";

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
            out.println("<title>Servlet StatisticReview</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticReview at " + request.getContextPath() + "</h1>");
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
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        } else {
            try {
                if (request.getParameter("delete-news") != null) {
                    throw new AdminException.RedirecUrlException();
                }
                //get list review
                SubLevelMenuDAO slmDao = new SubLevelMenuDAO();
                ReviewDAO rdao = new ReviewDAO();
                int page = 1;
                final int recordsPerPage = 5;
                //set start page =1
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<Reviews> list = rdao.getListReview((page - 1) * recordsPerPage, recordsPerPage);
                ArrayList<String> titleList = slmDao.getTitleTable("titleTableReviews");
                //insert <th></th>
                for (int i = 0; i < titleList.size(); i++) {
                    titleList.set(i, "<th>" + titleList.get(i) + "</th>");
                }
                int recordCount = rdao.countAllReview();
                int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
                request.setAttribute("pageCount", pageCount);
                request.setAttribute("currentPage", page);
                request.setAttribute("TITLE_REVIEWS", titleList);
                session.setAttribute("ALL_REVIEW", list);
                request.getRequestDispatcher(STATISTIC_REVIEW).forward(request, response);
            } catch (AdminException.RedirecUrlException e) {
                ReviewDAO dao = new ReviewDAO();
                String id = request.getParameter("id");
                dao.deleteReview(id);
                request.getRequestDispatcher(REVIEW_PAGE).forward(request, response);
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
