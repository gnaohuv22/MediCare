/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AdminEmailContext;
import DAL.AppointmentsDAO;
import DAL.BranchDAO;
import DAL.DoctorDAO;
import DAL.FamilyProfileDAO;
import DAL.ScheduleDetailDAO;
import DAL.ServiceTagDAO;
import DAL.SubLevelMenuDAO;
import Models.AdminSidebarMenu;
import Models.Appointments;
import Models.Branch;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
        String doctorDisplayName = request.getParameter("doctorDisplayName");
        String actionSendMail = request.getParameter("actionSendMail");
        String action = request.getParameter("action");
        AppointmentsDAO ad = new AppointmentsDAO();
        ServiceTagDAO std = new ServiceTagDAO();
        ArrayList<ServiceTag> services = std.getAllServiceTags();
        DoctorDAO dd = new DoctorDAO();
        SubLevelMenuDAO sd = new SubLevelMenuDAO();
        ArrayList<AdminSidebarMenu> statusAppointments = sd.getSubLevelMenuByContent("Trạng thái cuộc hẹn");
        ScheduleDetailDAO sdd = new ScheduleDetailDAO();

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

                        if (a.getDoctor().getId() != null) {
                            a.getDoctor().setDisplayName(doctorDisplayName);
                            doctorsAvailable.add(a.getDoctor());
                        }
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
                        for (AdminSidebarMenu status : statusAppointments) {
                            // Kiểm tra giá trị trạng thái và thêm phần HTML cho checkbox "Có muốn gửi mail"
                            if ("1".equals(status.getLink())) {
                                out.println("                                            <div class=\"mail-checkbox-container\">\n"
                                        + "                                                <input checked class=\"mail-checkbox\" type=\"checkbox\" name=\"sendMail1\" id=\"send_mail_1\" data-action-send-mail=\"1\">\n"
                                        + "                                                <span class=\"form-check-label\" for=\"send_mail_1\">\n"
                                        + "                                                    Gửi mail xác nhận cuộc hẹn cho cả bác sĩ và bệnh nhân\n"
                                        + "                                                </span>\n"
                                        + "                                            </div>\n");
                            } else if ("2".equals(status.getLink())) {
                                out.println("                                            <div class=\"mail-checkbox-container\">\n"
                                        + "                                                <input checked class \"mail-checkbox\" type=\"checkbox\" name=\"sendMail2\" id=\"send_mail_2\" data-action-send-mail=\"2\">\n"
                                        + "                                                <span class=\"form-check-label\" for=\"send_mail_2\">\n"
                                        + "                                                    Gửi mail hóa đơn và bệnh án cho bệnh nhân\n"
                                        + "                                                </span>\n"
                                        + "                                            </div>\n");
                            } else if ("3".equals(status.getLink())) {
                                out.println("                                            <div class=\"mail-checkbox-container\">\n"
                                        + "                                                <input checked class=\"mail-checkbox\" type=\"checkbox\" name=\"sendMail3\" id=\"send_mail_3\" data-action-send-mail=\"3\">\n"
                                        + "                                                <span class=\"form-check-label\" for=\"send_mail_3\">\n"
                                        + "                                                    Gửi mail xác nhận đã hủy lịch hẹn cho cả bệnh nhân và bác sĩ\n"
                                        + "                                                </span>\n"
                                        + "                                            </div>\n");
                            }
                        }

                        out.println(
                                "                                    </div>\n"
                                + "                                    <div class=\"m-t-20 text-center\">\n"
                                + "                                        <button data-pending-appointment-id=\"" + a.getId() + "\" class=\"btn btn-primary submit-btn\" onclick=\"eventSaveEditingAppointment(this)\">Lưu</button>\n"
                                + "                                        <button class=\"btn btn-primary submit-btn\" onclick=\"closeSolvePendingAppointmentsForm()\">Hủy</button>\n"
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
                    String oldDoctorId = a.getDoctor().getId();
                    System.out.println("Appointment OLD: " + a);
                    if (ad.updateAppointment(appointmentId, doctorId, statusAppointment)) {
                        if (statusAppointment.equals("0") || statusAppointment.equals("3")) {
                            if (oldDoctorId.equals(doctorId)) {
                                if (sdd.updateStatusOfDoctorScheduleDetail(a.getBranch().getId(), a.getServiceTag().getId(), a.getAppointmentDay(), a.getAppointmentTime(), oldDoctorId, 1)) {
                                    System.out.println("Line 310 - Update appointment (AVAILABLE - 1) success");
                                }
                            } else {
                                if (sdd.updateStatusOfDoctorScheduleDetail(a.getBranch().getId(), a.getServiceTag().getId(), a.getAppointmentDay(), a.getAppointmentTime(), oldDoctorId, 1)) {
                                    System.out.println("Line 325 - Update appointment (AVAILABLE - 1) success");
                                }
                                if (sdd.updateStatusOfDoctorScheduleDetail(a.getBranch().getId(), a.getServiceTag().getId(), a.getAppointmentDay(), a.getAppointmentTime(), doctorId, 1)) {
                                    System.out.println("Line 328 - Update appointment (AVAILABLE - 1) success");
                                }
                            }
                        } else {
                            if (oldDoctorId.equals(doctorId)) {
                                if (sdd.updateStatusOfDoctorScheduleDetail(a.getBranch().getId(), a.getServiceTag().getId(), a.getAppointmentDay(), a.getAppointmentTime(), doctorId, 0)) {
                                    System.out.println("Line 321 - Update appointment (NOT AVAILABLE - 0) success");
                                }
                            } else {
                                if (sdd.updateStatusOfDoctorScheduleDetail(a.getBranch().getId(), a.getServiceTag().getId(), a.getAppointmentDay(), a.getAppointmentTime(), oldDoctorId, 1)) {
                                    System.out.println("Line 325 - Update appointment (AVAILABLE - 1) success");
                                }
                                if (sdd.updateStatusOfDoctorScheduleDetail(a.getBranch().getId(), a.getServiceTag().getId(), a.getAppointmentDay(), a.getAppointmentTime(), doctorId, 0)) {
                                    System.out.println("Line 328 - Update appointment (NOT AVAILABLE - 0) success");
                                }

                            }
                        }
                        System.out.println("Appointment NEW: " + ad.getAppointmentById(appointmentId));
                    } else {
                        System.out.println("Update appointment fail");
                    }

                    // Send email:
                    if (actionSendMail != null) {
                        // send Email:
                        Appointments apm = ad.getPatientAndDoctorEmailById(appointmentId);
                        String patientEmail = apm.getProfile().getEmail().trim();
                        String doctorEmail = apm.getDoctor().getEmail().trim();

                        String OTP = AdminEmailContext.generateRandomVerificationCode(6);

                        AdminEmailContext.sendVerificationCodeToEmail(patientEmail, "XAC NHAN LICH HEN");
                        AdminEmailContext.sendVerificationCodeToEmail(doctorEmail, "XAC NHAN LICH HEN");
//                    JsonObject jsonObject = new JsonObject();
//                    jsonObject.addProperty("message", "Thành công! Hãy kiểm tra email để lấy mã OTP");
//                    jsonObject.addProperty("OTP", OTP);
//
//                    response.getWriter().write(OTP);
                        // end - send email:
                    }
                    break;
                }
                case "add-appointment": {
                    BranchDAO bd = new BranchDAO();
                    ArrayList<Branch> branches = bd.getAllBranches();
                    System.out.println("Case Add-appointment:");
                    FamilyProfileDAO fpd = new FamilyProfileDAO();
                    try ( PrintWriter out = response.getWriter()) {
                        out.println("<div class=\"booking-container\">\n"
                                + "            \n"
                                + "\n"
                                + "            <!--Step-1-container - start-->\n"
                                + "            <form action=\"user-book-appointment\" method=\"post\">\n"
                                + "                <div id=\"step-1-container\">\n"
                                + "                    <h2 class=\"booking-header\">1. Nhập thông tin bệnh nhân</h2>\n"
                                + "                    <div class=\"row booking-info-input\">\n"
                                + "                        <!-- Cột 1 -->\n"
                                + "                        <div class=\"col-md-6\">\n"
                                + "                            <div> \n"
                                + "                                <input type=\"text\" name=\"patientName\" id=\"patientName\" placeholder=\"Họ và tên (*)\" required/> \n"
                                + "                                <input type=\"radio\" name=\"gender\" checked value=\"1\"> Nam &nbsp;\n"
                                + "                                <input type=\"radio\" name=\"gender\" value=\"0\"> Nữ<br>\n"
                                + "                                <p style=\"color: red;\" id=\"inputNameError\" class=\"error-message-input-step-2\"></p>\n"
                                + "\n"
                                + "                                <input type=\"date\" id=\"birthDate\" name=\"birthDate\" placeholder=\"Ngày sinh (*)\" value=\"\" max=\"" + new ScheduleDetailDAO().getCurrentDate() + "\" required> (Birth date - Thông tin bắt buộc)<br>\n"
                                + "                                <p style=\"color: red;\" id=\"inputBirthDateError\" class=\"error-message-input-step-2\"></p>\n"
                                + "\n"
                                + "                                <input type=\"tel\" id=\"phone\" name=\"phone\" placeholder=\"Eg: 0123456789 (*)\" pattern=\"[0-9]{10}\" required value=\"\" maxlength=\"10\"><br>\n"
                                + "                                <p style=\"color: red;\" id=\"inputPhoneError\" class=\"error-message-input-step-2\"></p> \n"
                                + "\n"
                                + "                                <input type=\"text\" id=\"email\" value=\"\" name=\"email\" maxlength=\"32\" placeholder=\"Email liên hệ (*)\" required>\n"
                                + "                                <p style=\"color: red;\" id=\"inputEmailError\" class=\"error-message-input-step-2\"></p> \n"
                                + "\n"
                                + "                            </div>\n"
                                + "                        </div>\n"
                                + "\n"
                                + "                        <!-- Cột 2 -->\n"
                                + "                        <div class=\"col-md-6\">  \n"
                                + "                            <div>\n"
                                + "                                <textarea id=\"description\" name=\"description\" rows=\"4\" cols=\"60\" placeholder=\"Mô tả triệu chứng và nhu cầu thăm khám của bệnh nhân (*)\" required></textarea>\n"
                                + "                                <p style=\"color: red;\" id=\"inputSymptomsError\" class=\"error-message-input-step-2\"></p>\n"
                                + "                            </div>\n"
                                + "                        </div>\n"
                                + "                    </div>\n"
                                + "                <!--Step-1-container - end-->\n"
                                + "                <!--Step-2-container - start-->\n"
                                + "                    <h2 class=\"booking-header\">2. Nhập thông tin khám bệnh</h2>\n"
                                + "                    <div class=\"row\">\n"
                                + "                        <!-- Cột 1 -->\n"
                                + "                        <div class=\"col-md-6 branch-part\">\n"
                                + "                            <div>\n"
                                + "                                <select id=\"branchId\" name=\"branchId\" class=\"booking-select-class\" onchange=\"onchangeBranchId()\">\n"
                                + "                                    <option value=\"-1\" disabled selected>Chọn cơ sở khám (*)</option>\n");
//                                + "                                    <c:forEach items=\"${requestScope.branches}\" var=\"branch\">\n"
//                                + "                                        <option value=\"${branch.id}\">${branch.name}</option>\n"
//                                + "                                    </c:forEach>\n");
                        for (Branch b : branches) {
                            out.println("<option value=\"" + b.getId() + "\">" + b.getName() + "</option>");
                        }
                        out.println(
                                "                                </select>\n"
                                + "                                <!--<span style=\"color: red;\" id=\"serviceError\" class=\"error-message-service\"></span>-->\n"
                                + "                            </div>\n"
                                + "                            <div>\n"
                                + "                                <select class=\"booking-select-class\" id=\"serviceId\" name=\"serviceId\" onchange=\"loadDoctors()\">\n"
                                + "                                    <option value=\"-1\" disabled selected>Chọn chuyên khoa khám (*)</option>\n");
//                                + "                                    <c:forEach items=\"${requestScope.services}\" var=\"service\">\n"
//                                + "                                        <option value=\"${service.id}\">${service.nametag}</option>\n"
//                                + "                                    </c:forEach>\n"

                        for (ServiceTag s : services) {
                            out.println("<option value=\"" + s.getId() + "\">" + s.getNametag() + "</option>");
                        }
                        out.println(
                                "                                </select>\n"
                                + "                                <p id=\"serviceError\" class=\"error-message-input-step-1\"></p>\n"
                                + "                            </div>\n"
                                + "                            <div id=\"doctor-select-box\">\n");
                        out.println("<select id=\"doctorId\" class=\"booking-select-class\" name=\"doctorId\" onchange='loadScheduleOfDoctor()'>\n");
                        out.println(
                                "                                    <option value=\"-1\" disabled selected>Chọn bác sĩ (*)</option>\n");
                        out.println("</select>");
                        out.println(
                                "                            </div>\n"
                                + "                        </div>\n"
                                + "\n"
                                + "                        <!-- Cột 2 -->\n"
                                + "                        <div class=\"col-md-6 time-part\">\n"
                                + "                            <h2>Thời gian khám</h2>\n"
                                + "                            <div>\n"
                                + "                                <label for=\"date-appointment\">Ngày khám (*)</label></br>\n"
                                + "                                <input style=\"margin: 10px;\" type=\"date\" class=\"\" value=\"${requestScope.currentDate}\" id=\"booking-calendar\" name=\"booking-calendar\" onchange=\"loadScheduleOfDoctor()\" min=" + sdd.getCurrentDate() + ">\n"
                                + "                            </div>\n"
                                + "                            <p id=\"dateError\" class=\"error-message-input-step-1\"></p>\n"
                                + "                            <label for=\"booking-slot\">Khung giờ khám (*)</label><br>\n"
                                + "                            <div id=\"slot-select-box\">\n"
                                + "<select id=\"booking-slot\" onchange=\"onchangeSlot()\" class=\"booking-select-class\" name=\"booking-slot\">\n"
                                + "<option value=\"" + -1 + "\" disabled selected >" + "Chọn khung giờ khám" + "</option>\n"
                                + "</select>"
                                + "                            </div>\n"
                                + "                            <p id=\"slotError\" class=\"error-message-input-step-1\"></p>\n"
                                + "                        </div>\n"
                                + "                    </div>\n"
                                + "                <!--Step-2-container - end-->\n"
                                + "                <!--Step-3-container - start-->\n"
                                + "<p class=\"text-center\" id=\"error-save-add-appointment\"></p>"
                                + "                    <div class=\"row add-appointment-btn\">\n"
                                + "                    <div id=\"submit\" class=\"btn btn-primary\" onclick=\"eventSaveAddAppointment(this)\">Thêm bệnh nhân</div>\n"
                                + "                    <div class=\"btn btn-primary\" onclick=\"closeSolvePendingAppointmentsForm()\">Hủy</div>\n"
                                + "                    </div>\n"
                                + "            </div>");

                    }
                    break;
                }
                case "submit-add-appointment": {
                    String branchId, serviceId, doctorId, date, slotId;
                    branchId = request.getParameter("branchId");
                    serviceId = request.getParameter("serviceId");
                    doctorId = request.getParameter("doctorId");
                    date = request.getParameter("date");
                    slotId = request.getParameter("slotId");
                    String patientName = request.getParameter("patientName");
                    String gender = request.getParameter("gender");
                    String birthDate = request.getParameter("birthDate");
                    String phone = request.getParameter("phone");
                    String emailPatient = request.getParameter("email");
                    String symptoms = request.getParameter("description");

                    java.util.Date currentDate = new java.util.Date();
                    java.sql.Timestamp createdAt = new java.sql.Timestamp(currentDate.getTime());

//        ad.test(u==null?null:u.getId());
                    System.out.println("SlotId: " + slotId);
                    String startTime = sdd.getStartTimeBySlotId(slotId);

                    System.out.println("Line 150: date + start-time = " + date + startTime);
                    String plannedAt = date + " " + startTime;

                    if (ad.addNewAppointment(null, branchId, serviceId, doctorId, plannedAt, slotId, createdAt.toString(),
                            patientName, gender, birthDate, phone, emailPatient, symptoms, null, null) && sdd.setStatusForBookingSlot(slotId, doctorId, date)) {
                        System.out.println("Add appointment to database successfully!");
                        System.out.println("Planned at :.... = " + plannedAt);
                    }
                    System.out.println("email = " + emailPatient + " | branchId = " + branchId + " | serviceId = " + serviceId + " | doctorId = " + doctorId + " | date = " + date + " | slotId = " + slotId);
                    response.getWriter().write("Đặt lịch khám thành công!");
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
