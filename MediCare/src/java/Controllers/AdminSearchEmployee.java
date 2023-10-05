/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.EmployeeDAO;
import DAL.ViewDAO;
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
@WebServlet(name="AdminSearchEmployee", urlPatterns={"/admin-search-employee"})
public class AdminSearchEmployee extends HttpServlet {
    private final String STATISTIC_EMPLOYEE = "admin-employees/admin-employees.jsp";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminSearchEmployee</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminSearchEmployee at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        Models.GetEmployee checkEmp = (Models.GetEmployee)session.getAttribute("EMPLOYEE");
        if (checkEmp==null){
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        String keyId = request.getParameter("searchId");
        String keyName = request.getParameter("searchName");
        String keyRole = request.getParameter("searchRole");
        if (keyRole.equals("Select Role")) keyRole="";
        EmployeeDAO eDao = new EmployeeDAO();
        ViewDAO vDao = new ViewDAO();
        ArrayList<String> titleList = vDao.getTitleTableEmployee();
        ArrayList<Models.GetEmployee> list = eDao.searchEmployee(keyId, keyName, keyRole);
        request.setAttribute("TITLE_EMPLOYEE", titleList);
        request.setAttribute("ALL_EMPLOYEEROLE",eDao.getAllEmployeeRole());
        request.setAttribute("ALL_EMPLOYEE", list);
        request.getRequestDispatcher(STATISTIC_EMPLOYEE).forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
