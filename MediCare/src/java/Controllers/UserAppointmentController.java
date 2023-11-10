/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AppointmentsDAO;
import DAL.FamilyProfileDAO;
import DAL.RelationshipDAO;
import DAL.UserDAO;
import Models.Appointments;
import Models.FamilyProfile;
import Models.Relationship;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author phuon
 */
public class UserAppointmentController extends HttpServlet {

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
        
        AppointmentsDAO aDAO = new AppointmentsDAO();
        List<Appointments> aList = aDAO.getListAppointmentsByOwnerId(ownerId);
        
        if (session.getAttribute("email") == null) {
            response.sendRedirect("user-login");
        } else {
            request.setAttribute("aList", aList);
            request.getRequestDispatcher("user-appointment.jsp").forward(request, response);
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
        AppointmentsDAO aDAO = new AppointmentsDAO();
        HttpSession session = request.getSession();
        List<Appointments> aList;
        UserDAO uDAO = new UserDAO();

        String search;
        search = request.getParameter("search-appointment");

        String ownerId = uDAO.getIdByEmail(String.valueOf(session.getAttribute("email")));


        if (session.getAttribute("email") == null){
            response.sendRedirect("user-login");
        }
        else{
            String method = request.getParameter("method");
            switch(method){
                case "search":
                    aList = aDAO.getListAppointmentsByPatientName(search,ownerId);
                    if(!aList.isEmpty()){
                        request.setAttribute("aList", aList);
                        request.getRequestDispatcher("user-appointment.jsp").forward(request, response);
                    }  
                    else {
                        aList = aDAO.getListAppointmentsByPhone(search,ownerId);
                        
                        request.setAttribute("aList", aList);
                        request.getRequestDispatcher("user-appointment.jsp").forward(request, response);
                        
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
