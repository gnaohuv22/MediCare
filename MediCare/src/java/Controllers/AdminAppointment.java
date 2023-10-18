/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AppointmentDAO;
import DAL.SubLevelMenuDAO;

import Models.Appointments;
import Models.Employee;
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
//@WebServlet(name = "Appointment", urlPatterns = {"/admin-list-appointments"})
public class AdminAppointment extends HttpServlet {

    private final String STATISTIC_APPOINTMENT = "admin-appointments/admin-appointments.jsp";
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
        Employee checkEmp = (Employee) session.getAttribute("EMPLOYEE");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        try {
            if (request.getParameter("delete-appointment") != null) {
                throw new AdminException.RedirecUrlException();
            }
            //get list appointment
            AppointmentDAO adao = new AppointmentDAO();
            SubLevelMenuDAO slmDao = new SubLevelMenuDAO();
            ArrayList<String> titleList = slmDao.getTitleTable("titleTableAppointments");
            int page = 1;
            final int recordsPerPage = 5;
            //set start page = 1
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            ArrayList<Appointments> list = adao.getListAppointment((page - 1) * recordsPerPage, recordsPerPage);
            //insert <th></th>
            for (int i = 0; i < titleList.size(); i++) {
                titleList.set(i, "<th>" + titleList.get(i) + "</th>");
            }
            int recordCount = adao.countAllAppointment();
            int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);
            request.setAttribute("TITLE_APPOINTMENTS", titleList);
            session.setAttribute("ALL_APPOINTMENT", list);
            request.getRequestDispatcher(STATISTIC_APPOINTMENT).forward(request, response);
        } catch (AdminException.RedirecUrlException e) {
            AppointmentDAO dao = new AppointmentDAO();
            String id = request.getParameter("id");
            dao.deleteAppointmentById(id);
            //get list appointment
            SubLevelMenuDAO slmDao = new SubLevelMenuDAO();
            ArrayList<Appointments> list = dao.getAllAppointment();
            ArrayList<String> titleList = slmDao.getTitleTable("titleTableAppointments");
            //insert <th></th>
            for (int i = 0; i < titleList.size(); i++) {
                titleList.set(i, "<th>" + titleList.get(i) + "</th>");
            }
            request.setAttribute("TITLE_APPOINTMENTS", titleList);
            session.setAttribute("ALL_APPOINTMENT", list);
            request.getRequestDispatcher(STATISTIC_APPOINTMENT).forward(request, response);
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
