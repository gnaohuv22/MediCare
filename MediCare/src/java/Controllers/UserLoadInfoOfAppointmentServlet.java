/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.BranchDAO;
import DAL.DoctorDAO;
import DAL.ScheduleDetailDAO;
import DAL.ServiceTagDAO;
import Models.Appointments;
import Models.Branch;
import Models.Doctor;
import Models.ScheduleDetail;
import Models.ServiceTag;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author tubinh
 */
public class UserLoadInfoOfAppointmentServlet extends HttpServlet {

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
            out.println("<title>Servlet UserLoadInfoOfAppointmentServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserLoadInfoOfAppointmentServlet at " + request.getContextPath() + "</h1>");
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
//        branchId: branchId,
//                        serviceId: serviceId,
//                        doctorId: doctorId,
//                        date: date,
//                        slotId: slotId,
//                        
//                        patientName: patientName,
//                        birthDate: birthDate,
//                        gender: gender,
//                        phone: phone,
//                        email: email,
//                        description: description
        System.out.println("Ajax - doGet - load info of appointment: ");

        String branchId = request.getParameter("branchId");
        String serviceId = request.getParameter("serviceId");
        String doctorId = request.getParameter("doctorId");
        String date = request.getParameter("date");
        String slotId = request.getParameter("slotId");
        String patientName = request.getParameter("patientName");
        String birthDate = request.getParameter("birthDate");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String description = request.getParameter("description");

        ScheduleDetailDAO sdd = new ScheduleDetailDAO();
        String slotStart = sdd.getStartTimeBySlotId(slotId);

        System.out.println("branchId = " + branchId + " | serviceId = " + serviceId);
        System.out.println("doctorID = " + doctorId + " | date = " + date);
        System.out.println("slotId = " + slotId + " | patientName = " + patientName);
        System.out.println("birthDate = " + birthDate + " | gender = " + gender);
        System.out.println("phone = " + phone + " | email = " + email + " | description = " + description);

        Appointments a = new Appointments(new Doctor(doctorId), new ServiceTag(serviceId), slotStart + ", " + date, new Branch(branchId), description);
        if (gender.equals("1")) {
            gender = "Nam";
        } else {
            gender = "Nữ";
        }

        BranchDAO bd = new BranchDAO();
        a.setBranch(new Branch(bd.getBranchByBranchId(a.getBranch().getId()).getName()));
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<h2 class=\"booking-header\">Bước 3/3 - Xác nhận đặt lịch khám</h2>\n"
                    + "                <!--<form action=\"user-book-appointment\" method=\"post\">-->\n"
                    + "                <div class=\"row\">\n"
                    + "                    <div class=\"group-info-booking col-md-12\">\n"
                    + "                        <table class=\"group-info-booking-table\">\n"
                    + "                            <tr>\n"
                    + "                                <th class=\"group-info-booking-header\">Dịch vụ</th>\n"
                    + "                                <th></th>\n"
                    + "                            </tr>\n"
                    + "                            <tr>\n"
                    + "                                <td>Hình thức</td>\n"
                    + "                                <td>Khám chuyên khoa tại bệnh viện</td>\n"
                    + "                            </tr>\n"
                    + "                        </table>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "                <div class=\"row\">\n"
                    + "\n"
                    + "                    <div class=\"group-info-booking col-md-12\">\n"
                    + "                        <table class=\"group-info-booking-table\">\n"
                    + "                            <tr>\n"
                    + "                                <th class=\"group-info-booking-header\">Khách hàng</th>\n"
                    + "                                <th></th>\n"
                    + "                            </tr>\n");
            out.println(
                    "                            <tr>\n"
                    + "                                <td>Khách hàng</td>\n"
                    + "                                <td>" + patientName + "</td>\n"
                    + "                            </tr>\n");
            out.println(
                    "                            <tr>\n"
                    + "                                <td>Ngày sinh</td>\n"
                    + "                                <td>" + birthDate + "</td>\n"
                    + "                            </tr>\n");
            out.println(
                    "                            <tr>\n"
                    + "                                <td>Giới tính</td>\n"
                    + "                                <td>" + gender + "</td>\n"
                    + "                            </tr>\n");
            out.println(
                    "                            <tr>\n"
                    + "                                <td>Số điện thoại</td>\n"
                    + "                                <td>" + phone + "</td>\n"
                    + "                            </tr>\n");
            out.println(
                    "                            <tr>\n"
                    + "                                <td>Email</td>\n"
                    + "                                <td>" + email + "</td>\n"
                    + "                            </tr>\n");
            out.println(
                    "                            <tr>\n"
                    + "                                <td>Lý do khám</td>\n"
                    + "                                <td>" + description + "</td>\n"
                    + "                            </tr>\n");

            out.println(
                    "                        </table>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "                <div class=\"row\">\n"
                    + "\n"
                    + "                    <div class=\"group-info-booking col-md-12\">\n"
                    + "                        <table class=\"group-info-booking-table\">\n"
                    + "                            <tr>\n"
                    + "                                <th class=\"group-info-booking-header\">Bác sĩ</th>\n"
                    + "                                <th></th>\n"
                    + "                            </tr>\n");
            try {

                if (doctorId == null || Integer.parseInt(doctorId) <= 0) {
                    System.out.println("doctorId = null");
                } else {
                    DoctorDAO dd = new DoctorDAO();
                    a.setDoctor(new Doctor(dd.getDoctorByDoctorId(a.getDoctor().getId()).getDisplayName()));
                    out.println(
                            "                            <tr>\n"
                            + "                                <td>Bác sĩ</td>\n"
                            + "                                <td>" + a.getDoctor().getId()+ "</td>\n"
                            + "                            </tr>\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("error doctorId: " + e);
            }
            out.println(
                    "                            <tr>\n"
                    + "                                <td>Thời gian khám</td>\n"
                    + "                                <td>" + a.getPlannedAt() + "</td>\n"
                    + "                            </tr>\n");
            out.println(
                    "                            <tr>\n"
                    + "                                <td>Địa điểm</td>\n"
                    + "                                <td>" + a.getBranch().getId()+ "</td>\n"
                    + "                            </tr>\n");
            try {
                if (a.getServiceTag().getId()== null || Integer.parseInt(a.getServiceTag().getId()) <= 0) {
                    System.out.println("serviceId = null");
                } else {
                    ServiceTagDAO std = new ServiceTagDAO();
                    a.setServiceTag(new ServiceTag(std.getServiceTagByServiceTagId(a.getServiceTag().getId()).getNametag()));
                    out.println(
                            "                            <tr>\n"
                            + "                                <td>Chuyên khoa</td>\n"
                            + "                                <td>" + a.getServiceTag().getId()+ "</td>\n"
                            + "                            </tr>\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("serviceId: " + e);
            }

            out.println(
                    "                        </table>\n"
                    + "                    </div>\n"
                    + "                </div>\n"
                    + "\n"
                    + "                <div class=\"col-md-12 mt-3 text-center btn-booking\">\n"
                    + "                    <!--<button type=\"submit\" class=\"btn btn-primary\">Tiếp tục</button>-->\n"
                    + "                    <div id=\"step-3-to-2\" class=\"btn btn-primary\" onclick=\"handleSubmit(this)\">Quay lại</div>\n"
                    + "                    <div id=\"submit\" class=\"btn btn-primary\" onclick=\"handleSubmit(this)\">Xác nhận đặt hẹn</div>\n"
//                    + "                    <button id=\"submit\" type=\"submit\" class=\"btn btn-primary\" onclick=\"handleSubmit(this)\">Xác nhận đặt hẹn</button>\n"
                    + "                </div>\n");
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
