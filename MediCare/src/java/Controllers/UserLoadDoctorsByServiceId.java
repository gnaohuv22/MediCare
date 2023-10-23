/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DoctorDAO;
import DAL.ServiceTagDAO;
import Models.Doctor;
import Models.ServiceTag;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class UserLoadDoctorsByServiceId extends HttpServlet {

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
            out.println("<title>Servlet UserLoadDoctorsByServiceId</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserLoadDoctorsByServiceId at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("Ajax doGet User Load Doctors: ");

        String serviceId = request.getParameter("serviceid");
        String branchId = request.getParameter("branchId");

        System.out.println("serviceId = " + serviceId + "| branchId = " + branchId);

        ServiceTagDAO std = new ServiceTagDAO();
        DoctorDAO dd = new DoctorDAO();
        ArrayList<Doctor> doctors = dd.getAllDoctorsByBranchIdAndServiceId(branchId, serviceId);
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<select id=\"doctorId\" class=\"booking-select-class\" name=\"doctorId\" onchange='loadScheduleOfDoctor()'>\n");
            out.println("                                \n" + "<option value=\"" + -1 + "\" disabled selected>" + "Chọn bác sĩ muốn khám" + "</option>");

            for (Doctor doctor : doctors) {

                out.println("                                \n" + "<option value=\"" + doctor.getId() + "\">" + doctor.getDisplayName() + "</option>");

            }
            out.println("</select>");
//            out.println("<c:forEach items=\""+doctors+"\" var=\"doctor\">\n"
//                    + "                                    <option value=\"${doctor.id}\">${doctor.displayName}</option>\n"
//                    + "                                </c:forEach>");

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
