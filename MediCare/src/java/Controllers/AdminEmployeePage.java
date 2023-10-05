/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.DoctorDAO;
import DAL.ViewDAO;
import Models.Doctor;
import Models.GetEmployee;
import java.io.IOException;
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
@WebServlet(name="EmployeePage", urlPatterns={"/admin-employee-home"})
public class AdminEmployeePage extends HttpServlet {
    private final String EMPLOYEE_PAGE = "admin-screen/admin-home.jsp";
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
        HttpSession session = request.getSession();
        GetEmployee emp = (GetEmployee)session.getAttribute("EMPLOYEE");
        if (emp==null){
            request.setAttribute("MESSAGE", emp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        ViewDAO dao = new ViewDAO();
        session.setAttribute("ADMIN_SIDEBAR_MENU", dao.getSidebarMenu());
        request.setAttribute("DOCTOR_NUMBER", dao.countAllDoctor());
        request.setAttribute("USER_NUMBER", dao.countAllUser());
        request.setAttribute("EMPLOYEE_NUMBER", dao.countAllEmployee());
         DoctorDAO DocDAO = new DoctorDAO();
        ArrayList<Doctor> Doctorlist = DocDAO.getAllDoctors();
        request.setAttribute("Doctorlist", Doctorlist);
        
        request.getRequestDispatcher(EMPLOYEE_PAGE).forward(request, response);
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
        processRequest(request, response);
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
