/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.BillingHistoryDAO;
import DAL.UserDAO;
import Models.Appointments;
import Models.BillingHistory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author phuon
 */
public class UserPaymentHistoryServlet extends HttpServlet {

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
            out.println("<title>Servlet UserPaymentHistoryServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserPaymentHistoryServlet at " + request.getContextPath() + "</h1>");
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

        UserDAO uDAO = new UserDAO();
        String ownerId = uDAO.getIdByEmail(String.valueOf(session.getAttribute("email")));

        BillingHistoryDAO bhDAO = new BillingHistoryDAO();
        List<BillingHistory> bhList = bhDAO.getListBillingHistoryByOwnerId(ownerId);
        if (session.getAttribute("email") == null) {
            response.sendRedirect("user-login");
        } else {
            request.setAttribute("bhList", bhList);
            request.getRequestDispatcher("user-payment-history.jsp").forward(request, response);
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
        BillingHistoryDAO bhDAO = new BillingHistoryDAO();
        HttpSession session = request.getSession();
        UserDAO uDAO = new UserDAO();

        String search;
        search = request.getParameter("search-payment");

        String ownerId = uDAO.getIdByEmail(String.valueOf(session.getAttribute("email")));

        if (session.getAttribute("email") == null) {
            response.sendRedirect("user-login");
        } else {
            String method = request.getParameter("method");
            switch (method) {
                case "search":
                    List<BillingHistory> bhListName = bhDAO.getListBillingHistoryByOwnerIdAndName(search, ownerId);
                    List<BillingHistory> bhListPhone = bhDAO.getListBillingHistoryByOwnerIdAndPhone(search, ownerId);
                    List<BillingHistory> bhListEmail = bhDAO.getListBillingHistoryByOwnerIdAndEmail(search, ownerId);
                    if (!(bhListName.isEmpty())) {

                        request.setAttribute("bhList", bhListName);
                        request.getRequestDispatcher("user-payment-history.jsp").forward(request, response);
                    } else if (!(bhListPhone.isEmpty())) {

                        request.setAttribute("bhList", bhListPhone);
                        request.getRequestDispatcher("user-payment-history.jsp").forward(request, response);

                    } else {

                        request.setAttribute("bhList", bhListEmail);
                        request.getRequestDispatcher("user-payment-history.jsp").forward(request, response);
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
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
