/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AppointmentsDAO;
import DAL.DoctorDAO;
import DAL.FamilyProfileDAO;
import DAL.ScheduleDetailDAO;
import DAL.ServiceTagDAO;
import DAL.SubLevelMenuDAO;
import DAL.UserDAO;
import Models.AdminSidebarMenu;
import Models.Appointments;
import Models.Doctor;
import Models.Employee;
import Models.FamilyProfile;
import Models.ServiceTag;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tubinh
 */
@WebServlet(name = "AdminManageAppointmentsServlet", urlPatterns = {"/admin-manage-appointments"})
public class AdminManageAppointmentsServlet extends HttpServlet {

    private final String STATISTIC_APPOINTMENT = "admin-appointments/admin-manage-appointments.jsp";
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

        SubLevelMenuDAO sd = new SubLevelMenuDAO();
        ArrayList<AdminSidebarMenu> statusAppointments = sd.getSubLevelMenuByContent("Trạng thái cuộc hẹn");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        } else {
            AppointmentsDAO ad = new AppointmentsDAO();
            ArrayList<Appointments> appointments = ad.getAllAppointment();
            for (Appointments appointment : appointments) {
                System.out.println(appointment);
                SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                // Định dạng ngày tháng ban đầu
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

                // Định dạng ngày tháng mới (không có mili giây)
                SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");

                try {
                    java.util.Date date = inputFormat.parse(appointment.getPlannedAt());
                    String timeWithoutMillis = outputFormat.format(date);
                    appointment.setAppointmentTime(timeWithoutMillis);
                } catch (ParseException e) {
                }

                try {
                    java.util.Date appointmentDate = inputDateFormat.parse(appointment.getAppointmentDay()); // Chuyển đổi chuỗi thành Date
                    String formattedDate = outputDateFormat.format(appointmentDate); // Chuyển đổi lại thành chuỗi
                    appointment.setAppointmentDay(formattedDate);
                } catch (ParseException e) {
                    // Xử lý lỗi nếu định dạng chuỗi không khớp
                }
            }
            request.setAttribute("ALL_APPOINTMENT", appointments);
            request.setAttribute("statusAppointments", statusAppointments);
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
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("AJAX - doPost - admin-manage-appointments");
        String appointmentId = request.getParameter("appointmentId");
        String action = request.getParameter("action");
        AppointmentsDAO ad = new AppointmentsDAO();
        ServiceTagDAO std = new ServiceTagDAO();
        ArrayList<ServiceTag> services = std.getAllServiceTags();
        DoctorDAO dd = new DoctorDAO();
        SubLevelMenuDAO sd = new SubLevelMenuDAO();
        ArrayList<AdminSidebarMenu> statusAppointments = sd.getSubLevelMenuByContent("Trạng thái cuộc hẹn");

        if (action != null) {
            switch (action) {
                case "solve-pending-appointment": {
                    System.out.println("Case Solve-pending-appointment:");
                    Appointments a = ad.getAppointmentById(appointmentId);
                    FamilyProfileDAO fpd = new FamilyProfileDAO();
                    FamilyProfile profile = fpd.getFamilyProfileByProfileId(a.getProfile().getProfileId());
                    ArrayList<Doctor> doctors = dd.getAllDoctorsByBranchIdAndServiceId(a.getBranch().getId(), a.getServiceTag().getId());
                    // Get list of doctor by branchId, serviceId, date, slotId:
                    ArrayList<Doctor> doctorsAvailable = dd.getAllDoctorAvailable(a.getBranch().getId(), a.getServiceTag().getId(), a.getAppointmentDay(), a.getAppointmentTime());
                    try ( PrintWriter out = response.getWriter()) {
                        out.println("<i class=\"fas fa-close close-edit-appointment-btn\" onclick=\"closeSolvePendingAppointmentsForm()\"></i>\n"
                                + "            <div class=\"form-container\">\n"
                                + "                <!--appointment table - start-->\n"
                                + "                <div class=\"container\">\n"
                                + "                    <div class=\"content\">\n"
                                + "                        <div class=\"row\">\n"
                                + "                            <div class=\"col-lg-8 offset-lg-2\">\n"
                                + "                                <h4 class=\"page-title\">Xử lí lịch hẹn</h4>\n"
                                + "                            </div>\n"
                                + "                        </div>\n"
                                + "                        <div class=\"row\">\n"
                                + "                            <div class=\"col-lg-8 offset-lg-2\">\n"
                                + "                                <div>\n"
                                + "                                    <div class=\"row\">\n"
                                + "                                        <div class=\"col-md-6\">\n"
                                + "                                            <div class=\"form-group\">\n"
                                + "                                                <label>ID cuộc hẹn</label>\n"
                                + "                                                <input class=\"form-control\" type=\"text\" value=\"" + appointmentId + "\" readonly=\"\">\n"
                                + "                                            </div>\n"
                                + "                                        </div>\n"
                                + "                                        <div class=\"col-md-6\">\n"
                                + "                                            <div class=\"form-group\">\n"
                                + "                                                <label>Tên bệnh nhân</label>\n"
                                + "                                                <input class=\"form-control\" type=\"text\" value=\"" + profile.getName() + "\" disabled>\n"
                                + "                                            </div>\n"
                                + "                                        </div>\n"
                                + "                                    </div>\n"
                                + "                                    <div class=\"row\">\n"
                                + "                                        <div class=\"col-md-6\">\n"
                                + "                                            <div class=\"form-group\">\n"
                                + "                                                <label>Chuyên khoa</label>\n"
                                + "                                                <select class=\"select form-control\" disabled>\n"
                                + "                                                    <option value=\"-1\" disabled>Chọn chuyên khoa khám (*)</option>\n");
                        for (ServiceTag service : services) {
                            if (service.getId().equals(a.getServiceTag().getId())) {
                                out.println("<option selected value=\"" + service.getId() + "\">" + service.getNametag() + "</option>\n");
                            } else {
                                out.println("<option value=\"" + service.getId() + "\">" + service.getNametag() + "</option>\n");
                            }

                        }

                        out.println(
                                "                                                </select>\n"
                                + "                                            </div>\n"
                                + "                                        </div>\n"
                                + "                                        <div class=\"col-md-6\">\n"
                                + "                                            <div class=\"form-group\">\n"
                                + "                                                <label>Bác sĩ phụ trách</label>\n"
                                + "                                                <select class=\"select form-control doctor-choice-edit-appointment\" data-status=\"" + a.getStatus() + "\">\n");

                        out.println("                                \n" + "<option value=\"" + -1 + "\" disabled selected>" + "Chọn bác sĩ phụ trách" + "</option>");

                        for (Doctor doctor : doctorsAvailable) {
                            if (doctor.getId().equals(a.getDoctor().getId())) {
                                out.println("                                \n" + "<option selected value=\"" + doctor.getId() + "\">" + doctor.getDisplayName() + "</option>");
                            } else {
                                out.println("                                \n" + "<option value=\"" + doctor.getId() + "\">" + doctor.getDisplayName() + "</option>");
                            }

                        }

                        out.println(
                                "                                                </select>\n"
                                + "                                            </div>\n"
                                + "                                        </div>\n"
                                + "                                    </div>\n"
                                + "                                    <div class=\"row\">\n"
                                + "                                        <div class=\"col-md-6\">\n"
                                + "                                            <div class=\"form-group\">\n"
                                + "                                                <label>Ngày khám</label>\n"
                                + "                                                <div>\n"
                                + "                                                    <input type=\"date\" class=\"form-control\" name=\"date\" disabled value=\"" + a.getAppointmentDay() + "\">\n"
                                + "                                                </div>\n"
                                + "                                            </div>\n"
                                + "                                        </div>\n"
                                + "                                        <div class=\"col-md-6\">\n"
                                + "                                            <div class=\"form-group\">\n"
                                + "                                                <label>Slot</label>\n"
                                + "                                                <select class=\"select form-control\" disabled>\n"
                                //                                + "                                                    <option value=\"" + -1 + "\" disabled selected>Chọn giờ khám</option>\n"
                                + "                                                    <option value=\"" + -1 + "\" disabled selected>" + a.getAppointmentTime().substring(0, 8) + "</option>\n"
                        );

                        out.println(
                                "                                                </select>\n"
                                + "                                            </div>\n"
                                + "                                        </div>\n"
                                + "                                    </div>\n"
                                + "                                    <div class=\"row\">\n"
                                + "                                        <div class=\"col-md-6\">\n"
                                + "                                            <div class=\"form-group\">\n"
                                + "                                                <label>Email</label>\n"
                                + "                                                <input class=\"form-control\" type=\"email\" value=\"" + profile.getEmail() + "\" disabled>\n"
                                + "                                            </div>\n"
                                + "                                        </div>\n"
                                + "                                        <div class=\"col-md-6\">\n"
                                + "                                            <div class=\"form-group\">\n"
                                + "                                                <label>Số điện thoại</label>\n"
                                + "                                                <input class=\"form-control\" type=\"text\" value=\"" + profile.getPhone() + "\" disabled>\n"
                                + "                                            </div>\n"
                                + "                                        </div>\n"
                                + "                                    </div>\n"
                                + "                                    <div class=\"form-group\">\n"
                                + "                                        <label>Triệu chứng</label>\n"
                                + "                                        <textarea cols=\"30\" rows=\"4\" class=\"form-control\" disabled>" + a.getSymptoms() + "</textarea>\n"
                                + "                                    </div>\n"
                                + "                                    <div class=\"form-group\">\n"
                                + "                                        <label class=\"display-block\">Trạng thái cuộc hẹn</label>\n");
                        for (AdminSidebarMenu status : statusAppointments) {
                            out.println(
                                    "                                        <div class=\"\">\n"
                                    + "                                            <input class=\"form-check-input\" type=\"radio\" ");
                            if (status.getLink().equals(a.getStatus())) {
                                out.println(" checked ");
                            }

                            out.println("name=\"status\" id=\"product_inactive\" value=\"" + status.getLink() + "\">\n"
                                    + "                                            <label class=\"form-check-label\" for=\"product_inactive\">\n"
                                    + "                                                " + status.getName() + "\n"
                                    + "                                            </label>\n"
                                    + "                                        </div>\n");
                        }

                        out.println(
                                "                                    </div>\n"
                                + "                                    <div class=\"m-t-20 text-center\">\n"
                                + "                                        <button data-pending-appointment-id=\"" + a.getId() + "\" class=\"btn btn-primary submit-btn\" onclick=\"eventSaveEditingAppointment(this)\">Lưu</button>\n"
                                + "                                        <button class=\"btn btn-primary submit-btn\" onclick=\"closeScheduleOfDoctorForm()\">Hủy</button>\n"
                                + "                                    </div>\n"
                                + "                                </div>\n"
                                + "                            </div>\n"
                                + "                        </div>\n"
                                + "                    </div>\n"
                                + "                </div>\n"
                                + "                <!--appointment table - end-->\n"
                                + "\n"
                                + "                <!-- Add more input fields as needed -->\n"
                                + "                <!--                <input type=\"hidden\" name=\"method\" id=\"method\" value=\"\"/><br/>  \n"
                                + "                                <button class=\"button-container\" id=\"schedule-submit-button\" type=\"submit\"></button>-->\n"
                                //                                + "                <button  type=\"button\" id=\"schedule-close-button\" onclick=\"closeScheduleOfDoctorForm()\">Close</button>\n"
                                + "            </div>");
                    }
                    break;
                }
                case "save": {
                    System.out.println("Case Save-update-appointment:");
                    appointmentId = request.getParameter("appointmentId");
                    String doctorId = request.getParameter("doctorId");
                    String statusAppointment = request.getParameter("status");

                    System.out.println("BEFORE Update: ");
                    System.out.println("AppointmentId = " + appointmentId);
                    System.out.println("DoctorId = " + doctorId);
                    System.out.println("status = " + statusAppointment);
                    // temp:
                    Appointments a = ad.getAppointmentById(appointmentId);
                    System.out.println("Appointment OLD: " + a);

                    if (ad.updateAppointment(appointmentId, doctorId, statusAppointment)) {
                        System.out.println("Update appointment success");
                        System.out.println("Appointment NEW: " + ad.getAppointmentById(appointmentId));
                    } else {
                        System.out.println("Update appointment fail");
                    }
                    break;
                }
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
