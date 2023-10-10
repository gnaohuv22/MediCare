/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.ScheduleDetailDAO;
import Models.ScheduleDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author tubinh
 */
public class UserLoadScheduleBooking extends HttpServlet {

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
            out.println("<title>Servlet UserLoadDatesBookingByServiceIdAndDoctorId</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserLoadDatesBookingByServiceIdAndDoctorId at " + request.getContextPath() + "</h1>");
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
        System.out.println("Ajax doGet User Load Schedule Booking: ");
        String serviceId = request.getParameter("serviceid");
        String branchId = request.getParameter("branchId");
        String doctorId = request.getParameter("doctorId");
        String date = request.getParameter("date");
        System.out.println("serviceId = " + serviceId + " | branchId = " + branchId + " | doctorId = " + doctorId + " | date = " + date);
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTimeInMillis());

        ArrayList<ScheduleDetail> scheduleDetailSlots = new ArrayList<>();
        ScheduleDetailDAO sdd = new ScheduleDetailDAO();

        // Case 1: doctorId == null && date == null --> List of Slots follow by BranchId & ServiceId, date = Current date:
        if ((doctorId == null || doctorId.equals("-1")) && (date == null || date.equals(""))) {
            // temp function !!!:
            System.out.println("Case date = null && doctorId = null");
            scheduleDetailSlots = sdd.getAllSlotsOfDoctorByDate(branchId, serviceId, currentDate.toString());
        } else if ((doctorId == null || doctorId.equals("-1")) && !(date == null || date.equals(""))) {
            System.out.println("Case doctorid = null && date != null");
            scheduleDetailSlots = sdd.getAllSlotsOfDoctorByDate(branchId, serviceId, date);
        } else if (!(doctorId == null || doctorId.equals("-1")) && (date == null || date.equals(""))) {
            System.out.println("Case doctorId != null && date = null");
            scheduleDetailSlots = sdd.getAllSlotsOfDoctorByDoctorId(doctorId, serviceId, currentDate.toString());
        } else {
            System.out.println("Case doctorId != null && date != null");
            scheduleDetailSlots = sdd.getAllSlotsOfDoctorByDoctorId(doctorId, serviceId, date);
        }

        System.out.println("Slots:");

        for (ScheduleDetail scheduleDetailSlot : scheduleDetailSlots) {
            System.out.println(scheduleDetailSlot);
        }
        try ( PrintWriter out = response.getWriter()) {
            if (scheduleDetailSlots.isEmpty()) {
                out.println("<p style=\"color: green;\">Hiện không có lịch trống trong ngày này. Vui lòng chọn ngày khác.</p>");
                out.println("<input id=\"booking-slot\" value=\"-1\" type=\"hidden\" class=\"booking-select-class\" name=\"booking-slot\">\n");
            } else {
                out.println("<select id=\"booking-slot\" onchange=\"onchangeSlot()\" class=\"booking-select-class\" name=\"booking-slot\">\n");
                out.println("<option value=\"" + -1 + "\" disabled selected >" + "Chọn khung giờ khám" + "</option>\n");

                for (ScheduleDetail s : scheduleDetailSlots) {

                    out.println("<option value=\"" + s.getSlotId() + "\">" + s.getStartTime() + "</option>\n");

                }
                out.println("</select>");
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
