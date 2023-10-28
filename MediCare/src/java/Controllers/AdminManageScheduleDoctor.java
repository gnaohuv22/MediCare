/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DoctorDAO;
import DAL.ScheduleDetailDAO;
import DAL.SubLevelMenuDAO;
import Models.AdminSidebarMenu;
import Models.Doctor;
import Models.ScheduleDetail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
@WebServlet(name = "AdminManageScheduleDoctor", urlPatterns = {"/admin-manage-schedule-doctor"})
public class AdminManageScheduleDoctor extends HttpServlet {

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
            out.println("<title>Servlet AdminManageScheduleDoctor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManageScheduleDoctor at " + request.getContextPath() + "</h1>");
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
        System.out.println("doGet - Admin - manage - schedule - doctor:");
        DoctorDAO dd = new DoctorDAO();
        ArrayList<Doctor> doctors = dd.getAllDoctors();
        SubLevelMenuDAO slmDao = new SubLevelMenuDAO();
        ArrayList<String> titleList = slmDao.getTitleTableForSchedule("titleTableEmployee");
        ScheduleDetailDAO sdd = new ScheduleDetailDAO();
        ArrayList<ScheduleDetail> slots = sdd.getAllSlots();
        ArrayList<ScheduleDetail> slotsOfDoctor = sdd.getScheduleOfDoctorByDoctorIdCurrentWeek("1");
        ArrayList<String> daysOfCurrentWeek_raw = sdd.getDaysOfCurrentWeek();
        ArrayList<String> daysOfCurrentWeek = new ArrayList<>();
//        ArrayList<ScheduleDetail> weeksIn5Year_raw = sdd.getAllWeeksIn5CurrentYears();
//        ArrayList<ScheduleDetail> weeksIn5Year = new ArrayList<>();
        ArrayList<String> years = sdd.get5CurrentYears();
        String currentYear = years.get(3);

        SubLevelMenuDAO sd = new SubLevelMenuDAO();
        ArrayList<AdminSidebarMenu> statusSlot = sd.getSubLevelMenuByContent("Trạng thái slot");
        for (AdminSidebarMenu adminSidebarMenu : statusSlot) {
            System.out.println("Slot status: " + statusSlot);
        }
        HttpSession session = request.getSession();
        session.setAttribute("statusSlot", statusSlot);

        for (String string : daysOfCurrentWeek_raw) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM"); // Định dạng ngày tháng
            System.out.println(dateFormat.format(java.sql.Date.valueOf(string)));
            daysOfCurrentWeek.add(dateFormat.format(java.sql.Date.valueOf(string)));
        }
//        for (ScheduleDetail s : weeksIn5Year_raw) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM"); // Định dạng ngày tháng
//            System.out.println(dateFormat.format(java.sql.Date.valueOf(s.getStartTime())));
//            System.out.println(dateFormat.format(java.sql.Date.valueOf(s.getEndTime())));
//            weeksIn5Year.add(new ScheduleDetail(s.getSlotId(), dateFormat.format(java.sql.Date.valueOf(s.getStartTime())), dateFormat.format(java.sql.Date.valueOf(s.getEndTime())), 0));
//        }

        for (int i = 0; i < titleList.size(); i++) {
            titleList.set(i, "<th>" + titleList.get(i) + "</th>");
        }
        request.setAttribute("TITLE_EMPLOYEE", titleList);
        request.setAttribute("doctors", doctors);
        request.setAttribute("slots", slots);
        request.setAttribute("slotsOfDoctor", slotsOfDoctor);
        request.setAttribute("daysOfCurrentWeek_raw", daysOfCurrentWeek_raw);
        request.setAttribute("daysOfCurrentWeek", daysOfCurrentWeek);
//        request.setAttribute("weeksIn5Year", weeksIn5Year);
        request.setAttribute("years", years);
        request.setAttribute("currentYear", currentYear);

        request.getRequestDispatcher("admin-employees/admin-manage-schedule-doctor.jsp").forward(request, response);
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
        String year = request.getParameter("year");
        String week_raw = request.getParameter("week");
        String doctorId = request.getParameter("doctorId");
        String scheduleDetailId = request.getParameter("scheduleDetailId");
        ScheduleDetailDAO sdd = new ScheduleDetailDAO();
        ArrayList<String> years = sdd.get5CurrentYears();
        ArrayList<ScheduleDetail> slots = sdd.getAllSlots();
        ArrayList<ScheduleDetail> weeks_raw = new ArrayList<>();
        SubLevelMenuDAO sd = new SubLevelMenuDAO();
        ArrayList<AdminSidebarMenu> statusSlot = sd.getSubLevelMenuByContent("Trạng thái slot");
        System.out.println("Year = " + year);
        if (year != null) {
            System.out.println("Get weeks_raw with (year not null)");
            weeks_raw = sdd.getWeeksInYearWithValidation(Integer.parseInt(year));
        } else {
            System.out.println("Get weeks_raw with (year null)");
            weeks_raw = sdd.getWeeksInYearWithValidation(Integer.parseInt(years.get(3)));
        }
        System.out.println("Weeks_raw - before cast dd/mm:");
        for (ScheduleDetail scheduleDetail : weeks_raw) {
            System.out.println(scheduleDetail);
        }
        ArrayList<ScheduleDetail> weeks = new ArrayList<>();
        ArrayList<String> daysOfCurrentWeek_raw = sdd.getDaysOfCurrentWeek();
        ArrayList<String> daysOfCurrentWeek = new ArrayList<>();
        for (ScheduleDetail s : weeks_raw) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM"); // Định dạng ngày tháng
            System.out.println(dateFormat.format(java.sql.Date.valueOf(s.getStartTime())));
            System.out.println(dateFormat.format(java.sql.Date.valueOf(s.getEndTime())));
            weeks.add(new ScheduleDetail(s.getSlotId(), dateFormat.format(java.sql.Date.valueOf(s.getStartTime())), dateFormat.format(java.sql.Date.valueOf(s.getEndTime())), 0));
        }
        System.out.println("Ajax - doPost: weeks in " + year + ":");
        for (ScheduleDetail w : weeks) {
            System.out.println(w);
        }

        System.out.println("line 146:");
        System.out.println("Weeks_raw:");
        for (ScheduleDetail scheduleDetail : weeks_raw) {
            System.out.println(scheduleDetail);
        }
        System.out.println("Weeks_raw - end!");
        ArrayList<String> daysInWeekInRange_raw = sdd.getDaysOfWeekInDateRange(weeks_raw.get(0).getStartTime(), weeks_raw.get(0).getEndTime());
        System.out.println("line 148:");
        ArrayList<String> daysInWeekInRange = new ArrayList<>();

        for (String s : daysInWeekInRange_raw) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM"); // Định dạng ngày tháng
            System.out.println(dateFormat.format(java.sql.Date.valueOf(s)));
            daysInWeekInRange.add(dateFormat.format(java.sql.Date.valueOf(s)));
        }

        for (String string : daysOfCurrentWeek_raw) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM"); // Định dạng ngày tháng
            System.out.println(dateFormat.format(java.sql.Date.valueOf(string)));
            daysOfCurrentWeek.add(dateFormat.format(java.sql.Date.valueOf(string)));
        }

        ArrayList<ScheduleDetail> slotsofDoctor = sdd.getScheduleOfDoctorByDoctorIdAndDateRange(doctorId, weeks_raw.get(0).getStartTime(), weeks_raw.get(0).getEndTime());

        for (ScheduleDetail scheduleDetail : slotsofDoctor) {
            System.out.println(scheduleDetail);
        }
        // AJAX - On change YEAR:
        String onchangeYear = request.getParameter("onchangeYear");
        // AJAX - On change WEEK:
        String onchangeWeek = request.getParameter("onchangeWeek");
        // AJAX - On click POPUP:
        String onclickPopup = request.getParameter("onclickPopup");
        // AJAX - On click DELETE SCHEDULE-DETAIL (Slot) ICON:
        String deleteSchedule = request.getParameter("deleteSchedule");
        if (onchangeYear != null && onchangeYear.equals("true")) {
            System.out.println("ON CHANGE YEAR:");
            try ( PrintWriter out = response.getWriter()) {
                out.println("<p id=\"year-schedule-doctor-txt\">YEAR</p>\n"
                        + "            <select class=\"year-schedule-doctor\" name=\"year-schedule-doctor\" id=\"year-schedule-doctor\" onchange=\"eventLoadWeekByYear(this)\">\n");
                for (String y : years) {
                    if (y.equals(year)) {
                        out.println("<option selected value=\"" + y + "\">" + y + "</option>");
                    } else {
                        out.println("<option value=\"" + y + "\">" + y + "</option>");
                    }
                }
                out.println("            </select>\n"
                        + "            <p id=\"week-schedule-doctor-txt\">WEEK</p>\n"
                        + "            <select class=\"week-schedule-doctor\" name=\"week-schedule-doctor\" id=\"week-schedule-doctor\"  onchange=\"eventLoadSlotsByWeek(this)\">\n");

                for (ScheduleDetail week : weeks) {
                    out.println("<option value=\"" + week.getSlotId() + "\">" + week.getStartTime() + " To " + week.getEndTime() + "</option>");
                }
                out.println("            </select>\n"
                        + "            <i class=\"fas fa-close\" id=\"close-schedule-btn\" onclick=\"closeScheduleOfDoctorForm()\"></i>\n"
                        + "            <form action=\"admin-update-schedule-doctor\" class=\"form-container\" method=\"post\">\n"
                        + "                <h3><b>Chỉnh sửa lịch trình của bác sĩ " + doctorId + "</b></h3>\n"
                        + "\n"
                        + "                <!--schedule table - start-->\n"
                        + "                <div class=\"container\">\n"
                        + "                    <div class=\"timetable-img text-center\">\n"
                        + "                        <img src=\"img/content/timetable.png\" alt=\"\">\n"
                        + "                    </div>\n"
                        + "                    <div class=\"table-responsive\">\n"
                        + "                        <table class=\"table table-bordered text-center schedule-doctor-detail-table\">\n"
                        + "                            <thead>\n"
                        + "                                <c:set var=\"daysOfCurrentWeek\" value=\"${requestScope.daysOfCurrentWeek}\"></c:set>\n"
                        + "                                <c:set var=\"daysOfCurrentWeek_raw\" value=\"${requestScope.daysOfCurrentWeek_raw}\"></c:set>\n"
                        + "                                    <tr class=\"bg-light-gray\">\n"
                        + "                                        <th class=\"text-uppercase\">Slot</th>\n"
                        + "                                        <th class=\"text-uppercase\"><p>Thứ 2</p><p>" + daysInWeekInRange.get(0) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 3</p><p>" + daysInWeekInRange.get(1) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 4</p><p>" + daysInWeekInRange.get(2) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 5</p><p>" + daysInWeekInRange.get(3) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 6</p><p>" + daysInWeekInRange.get(4) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 7</p><p>" + daysInWeekInRange.get(5) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Chủ nhật</p><p>" + daysInWeekInRange.get(6) + "</p></th>\n"
                        + "                                </tr>\n"
                        + "                            </thead>\n"
                        + "                            <tbody>\n"
                        + "                                <c:set var=\"slotsOfDoctor\" value=\"${requestScope.slotsOfDoctor}\"></c:set>\n");

                for (ScheduleDetail slot : slots) {
                    out.println("<tr>");
                    out.println("<td class=\"align-middle\">Slot " + slot.getSlotId() + "</td>");
                    for (int i = 1; i <= 7; i++) {
                        out.println("<td>");
                        boolean slotFound = false;
                        for (ScheduleDetail s : slotsofDoctor) {
                            if (daysInWeekInRange_raw.get(i - 1).equals(s.getWorkDate()) && slot.getSlotId().equals(s.getSlotId())) {
                                out.println("<i class=\"fas fa-close delete-schedule-icon\"   data-scheduleDetailId=\"" + s.getId() + "\" onclick=\"eventClickDeleteScheduleIcon(this)\"></i>");
//                                out.println("<span class=\"bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom font-size16 xs-font-size10\">Dance</span>");
//                                out.println("<div class=\"font-size10 text-light-gray\">Ivana Wong</div>");
                                for (AdminSidebarMenu status : statusSlot) {
                                    System.out.println("Slot - status: " + status);
                                    if (status.getLink().equals(s.getSlotStatus())) {
                                        System.out.println("status.getLink() == s.getSlotStatus()");
                                        out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + status.getIcon() + "\">" + status.getName() + "</span></div>");
                                    }
                                }

                                out.println("<div class=\"margin-10px-top font-size10\">" + slot.getStartTime() + " - " + slot.getEndTime() + "</div>");
                                slotFound = true;
                                break;
                            }
                        }
                        if (!slotFound) {
                            out.println("<i class=\"fas fa-plus create-schedule-icon\" onclick=\"eventClickCreateScheduleDoctor(this)\"></i>");
                            out.println("<span>-</span>");
                        }
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }

                out.println(
                        "                            </tbody>\n"
                        + "                        </table>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "                <!--schedule table - end-->\n"
                        + "\n"
                        + "                <!-- Add more input fields as needed -->\n"
                        + "                <button type=\"button\" id=\"schedule-close-button\" onclick=\"closeScheduleOfDoctorForm()\">Close</button>\n"
                        + "            </form>");
            }
        } else if (onchangeWeek != null && onchangeWeek.equals("true")) {
            System.out.println("ON CHANGE WEEK:");
            System.out.println("line 262:");
            daysInWeekInRange_raw = sdd.getDaysOfWeekInDateRange(weeks_raw.get(Integer.parseInt(week_raw)).getStartTime(), weeks_raw.get(Integer.parseInt(week_raw)).getEndTime());
            System.out.println("line 264:");
            daysInWeekInRange = new ArrayList<>();

            for (String s : daysInWeekInRange_raw) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM"); // Định dạng ngày tháng
                System.out.println(dateFormat.format(java.sql.Date.valueOf(s)));
                daysInWeekInRange.add(dateFormat.format(java.sql.Date.valueOf(s)));
            }

            slotsofDoctor = sdd.getScheduleOfDoctorByDoctorIdAndDateRange(doctorId, weeks_raw.get(Integer.parseInt(week_raw)).getStartTime(), weeks_raw.get(Integer.parseInt(week_raw)).getEndTime());

            try ( PrintWriter out = response.getWriter()) {
                out.println("<p id=\"year-schedule-doctor-txt\">YEAR</p>\n"
                        + "            <select class=\"year-schedule-doctor\" name=\"year-schedule-doctor\" id=\"year-schedule-doctor\" onchange=\"eventLoadWeekByYear(this)\">\n");
                for (String y : years) {
                    if (y.equals(year)) {
                        out.println("<option selected value=\"" + y + "\">" + y + "</option>");
                    } else {
                        out.println("<option value=\"" + y + "\">" + y + "</option>");
                    }
                }
                out.println("            </select>\n"
                        + "            <p id=\"week-schedule-doctor-txt\">WEEK</p>\n"
                        + "            <select class=\"week-schedule-doctor\" name=\"week-schedule-doctor\" id=\"week-schedule-doctor\"  onchange=\"eventLoadSlotsByWeek(this)\">\n");

                for (ScheduleDetail week : weeks) {
                    if (week.getSlotId().equals(week_raw)) {
                        out.println("<option selected value=\"" + week.getSlotId() + "\">" + week.getStartTime() + " To " + week.getEndTime() + "</option>");
                    } else {
                        out.println("<option value=\"" + week.getSlotId() + "\">" + week.getStartTime() + " To " + week.getEndTime() + "</option>");
                    }
                }
                out.println("            </select>\n"
                        + "            <i class=\"fas fa-close\" id=\"close-schedule-btn\" onclick=\"closeScheduleOfDoctorForm()\"></i>\n"
                        + "            <form action=\"admin-update-schedule-doctor\" class=\"form-container\" method=\"post\">\n"
                        + "                <h3><b>Chỉnh sửa lịch trình của bác sĩ " + doctorId + "</b></h3>\n"
                        + "\n"
                        + "                <!--schedule table - start-->\n"
                        + "                <div class=\"container\">\n"
                        + "                    <div class=\"timetable-img text-center\">\n"
                        + "                        <img src=\"img/content/timetable.png\" alt=\"\">\n"
                        + "                    </div>\n"
                        + "                    <div class=\"table-responsive\">\n"
                        + "                        <table class=\"table table-bordered text-center schedule-doctor-detail-table\">\n"
                        + "                            <thead>\n"
                        + "                                <c:set var=\"daysOfCurrentWeek\" value=\"${requestScope.daysOfCurrentWeek}\"></c:set>\n"
                        + "                                <c:set var=\"daysOfCurrentWeek_raw\" value=\"${requestScope.daysOfCurrentWeek_raw}\"></c:set>\n"
                        + "                                    <tr class=\"bg-light-gray\">\n"
                        + "                                        <th class=\"text-uppercase\">Slot</th>\n"
                        + "                                        <th class=\"text-uppercase\"><p>Thứ 2</p><p>" + daysInWeekInRange.get(0) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 3</p><p>" + daysInWeekInRange.get(1) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 4</p><p>" + daysInWeekInRange.get(2) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 5</p><p>" + daysInWeekInRange.get(3) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 6</p><p>" + daysInWeekInRange.get(4) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 7</p><p>" + daysInWeekInRange.get(5) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Chủ nhật</p><p>" + daysInWeekInRange.get(6) + "</p></th>\n"
                        + "                                </tr>\n"
                        + "                            </thead>\n"
                        + "                            <tbody>\n"
                        + "                                <c:set var=\"slotsOfDoctor\" value=\"${requestScope.slotsOfDoctor}\"></c:set>\n");

                for (ScheduleDetail slot : slots) {
                    out.println("<tr>");
                    out.println("<td class=\"align-middle\">Slot " + slot.getSlotId() + "</td>");
                    for (int i = 1; i <= 7; i++) {
                        out.println("<td>");
                        boolean slotFound = false;
                        for (ScheduleDetail s : slotsofDoctor) {
                            if (daysInWeekInRange_raw.get(i - 1).equals(s.getWorkDate()) && slot.getSlotId().equals(s.getSlotId())) {
                                out.println("<i class=\"fas fa-close delete-schedule-icon\"   data-scheduleDetailId=\"" + s.getId() + "\" onclick=\"eventClickDeleteScheduleIcon(this)\"></i>");
//                                out.println("<span class=\"bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom font-size16 xs-font-size10\">Dance</span>");
//                                out.println("<div class=\"font-size10 text-light-gray\">Ivana Wong</div>");

                                for (AdminSidebarMenu status : statusSlot) {
                                    System.out.println("Slot - status: " + status);
                                    if (status.getLink().equals(s.getSlotStatus())) {
                                        System.out.println("status.getLink() == s.getSlotStatus()");
                                        out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + status.getIcon() + "\">" + status.getName() + "</span></div>");
                                    }
                                }
                                out.println("<div class=\"margin-10px-top font-size10\">" + slot.getStartTime() + " - " + slot.getEndTime() + "</div>");
                                slotFound = true;
                                break;
                            }
                        }
                        if (!slotFound) {
                            out.println("<i class=\"fas fa-plus create-schedule-icon\" onclick=\"eventClickCreateScheduleDoctor(this)\"></i>");
                            out.println("<span>-</span>");
                        }
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }

                out.println(
                        "                            </tbody>\n"
                        + "                        </table>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "                <!--schedule table - end-->\n"
                        + "\n"
                        + "                <!-- Add more input fields as needed -->\n"
                        + "                <button type=\"button\" id=\"schedule-close-button\" onclick=\"closeScheduleOfDoctorForm()\">Close</button>\n"
                        + "            </form>");
            }
        } else if (onclickPopup != null && onclickPopup.equals("true")) {
            System.out.println("ON CLICK POPUP:");
            daysInWeekInRange_raw = sdd.getDaysOfWeekInDateRange(daysOfCurrentWeek_raw.get(0), daysOfCurrentWeek_raw.get(6));
            daysInWeekInRange = new ArrayList<>();

            for (String s : daysInWeekInRange_raw) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM"); // Định dạng ngày tháng
                System.out.println(dateFormat.format(java.sql.Date.valueOf(s)));
                daysInWeekInRange.add(dateFormat.format(java.sql.Date.valueOf(s)));
            }

            slotsofDoctor = sdd.getScheduleOfDoctorByDoctorIdAndDateRange(doctorId, daysOfCurrentWeek_raw.get(0), daysOfCurrentWeek_raw.get(6));

            try ( PrintWriter out = response.getWriter()) {
                out.println("<p id=\"year-schedule-doctor-txt\">YEAR</p>\n"
                        + "            <select class=\"year-schedule-doctor\" name=\"year-schedule-doctor\" id=\"year-schedule-doctor\" onchange=\"eventLoadWeekByYear(this)\">\n");
                for (String y : years) {
                    if (y.equals(years.get(3))) {
                        out.println("<option selected value=\"" + y + "\">" + y + "</option>");
                    } else {
                        out.println("<option value=\"" + y + "\">" + y + "</option>");
                    }
                }
                out.println("            </select>\n"
                        + "            <p id=\"week-schedule-doctor-txt\">WEEK</p>\n"
                        + "            <select class=\"week-schedule-doctor\" name=\"week-schedule-doctor\" id=\"week-schedule-doctor\"  onchange=\"eventLoadSlotsByWeek(this)\">\n");

                for (int i = 0; i < weeks.size(); i++) {
                    if (weeks_raw.get(i).getStartTime().equals(daysOfCurrentWeek_raw.get(0))) {
                        out.println("<option selected value=\"" + weeks.get(i).getSlotId() + "\">" + weeks.get(i).getStartTime() + " To " + weeks.get(i).getEndTime() + "</option>");
                    } else {
                        out.println("<option value=\"" + weeks.get(i).getSlotId() + "\">" + weeks.get(i).getStartTime() + " To " + weeks.get(i).getEndTime() + "</option>");
                    }
                }
//                for (ScheduleDetail week : weeks) {
//                    if (week.getStartTime().equals(daysOfCurrentWeek.get(0))) {
//                        out.println("<option selected value=\"" + week.getSlotId() + "\">" + week.getStartTime() + " To " + week.getEndTime() + "</option>");
//                    } else {
//                        out.println("<option value=\"" + week.getSlotId() + "\">" + week.getStartTime() + " To " + week.getEndTime() + "</option>");
//                    }
//                }
                out.println("            </select>\n"
                        + "            <i class=\"fas fa-close\" id=\"close-schedule-btn\" onclick=\"closeScheduleOfDoctorForm()\"></i>\n"
                        + "            <form action=\"admin-update-schedule-doctor\" class=\"form-container\" method=\"post\">\n"
                        + "                <h3><b>Chỉnh sửa lịch trình của bác sĩ " + doctorId + "</b></h3>\n"
                        + "\n"
                        + "                <!--schedule table - start-->\n"
                        + "                <div class=\"container\">\n"
                        + "                    <div class=\"timetable-img text-center\">\n"
                        + "                        <img src=\"img/content/timetable.png\" alt=\"\">\n"
                        + "                    </div>\n"
                        + "                    <div class=\"table-responsive\">\n"
                        + "                        <table class=\"table table-bordered text-center schedule-doctor-detail-table\">\n"
                        + "                            <thead>\n"
                        + "                                <c:set var=\"daysOfCurrentWeek\" value=\"${requestScope.daysOfCurrentWeek}\"></c:set>\n"
                        + "                                <c:set var=\"daysOfCurrentWeek_raw\" value=\"${requestScope.daysOfCurrentWeek_raw}\"></c:set>\n"
                        + "                                    <tr class=\"bg-light-gray\">\n"
                        + "                                        <th class=\"text-uppercase\">Slot</th>\n"
                        + "                                        <th class=\"text-uppercase\"><p>Thứ 2</p><p>" + daysInWeekInRange.get(0) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 3</p><p>" + daysInWeekInRange.get(1) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 4</p><p>" + daysInWeekInRange.get(2) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 5</p><p>" + daysInWeekInRange.get(3) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 6</p><p>" + daysInWeekInRange.get(4) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 7</p><p>" + daysInWeekInRange.get(5) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Chủ nhật</p><p>" + daysInWeekInRange.get(6) + "</p></th>\n"
                        + "                                </tr>\n"
                        + "                            </thead>\n"
                        + "                            <tbody>\n"
                        + "                                <c:set var=\"slotsOfDoctor\" value=\"${requestScope.slotsOfDoctor}\"></c:set>\n");

                for (ScheduleDetail slot : slots) {
                    out.println("<tr>");
                    out.println("<td class=\"align-middle\">Slot " + slot.getSlotId() + "</td>");
                    for (int i = 1; i <= 7; i++) {
                        out.println("<td>");
                        boolean slotFound = false;
                        for (ScheduleDetail s : slotsofDoctor) {
                            if (daysInWeekInRange_raw.get(i - 1).equals(s.getWorkDate()) && slot.getSlotId().equals(s.getSlotId())) {
                                out.println("<i class=\"fas fa-close delete-schedule-icon\"  data-scheduleDetailId=\"" + s.getId() + "\" onclick=\"eventClickDeleteScheduleIcon(this)\"></i>");
//                                out.println("<span class=\"bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom font-size16 xs-font-size10\">Dance</span>");
//                                out.println("<div class=\"font-size10 text-light-gray\">Ivana Wong</div>");

                                for (AdminSidebarMenu status : statusSlot) {
                                    System.out.println("Slot - status: " + status);
                                    if (status.getLink().equals(s.getSlotStatus())) {
                                        System.out.println("status.getLink() == s.getSlotStatus()");
                                        out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + status.getIcon() + "\">" + status.getName() + "</span></div>");
                                    }
                                }
                                out.println("<div class=\"margin-10px-top font-size10\">" + slot.getStartTime() + " - " + slot.getEndTime() + "</div>");
                                slotFound = true;
                                break;
                            }
                        }
                        if (!slotFound) {
                            out.println("<i class=\"fas fa-plus create-schedule-icon\" onclick=\"eventClickCreateScheduleDoctor(this)\"></i>");
                            out.println("<span>-</span>");
                        }
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }

                out.println(
                        "                            </tbody>\n"
                        + "                        </table>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "                <!--schedule table - end-->\n"
                        + "\n"
                        + "                <!-- Add more input fields as needed -->\n"
                        + "                <button type=\"button\" id=\"schedule-close-button\" onclick=\"closeScheduleOfDoctorForm()\">Close</button>\n"
                        + "            </form>");
            }
            System.out.println("END POPUP!");
        } else if (deleteSchedule != null && deleteSchedule.equals("true")) {
            System.out.println("ON CLICK DELETE SCHEDULE DETAIL (SLOT) BY ID:");
            // Delete ScheduleDetail by id:

            if (sdd.deleteScheduleDetailSlotById(scheduleDetailId)) {
                System.out.println("Delete SLot SUCCESS!");
            } else {
                System.out.println("Delete SLot FAIL!");
            }

            System.out.println("line 482:");
            daysInWeekInRange_raw = sdd.getDaysOfWeekInDateRange(weeks_raw.get(Integer.parseInt(week_raw)).getStartTime(), weeks_raw.get(Integer.parseInt(week_raw)).getEndTime());
            System.out.println("line 484:");
            daysInWeekInRange = new ArrayList<>();

            for (String s : daysInWeekInRange_raw) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM"); // Định dạng ngày tháng
                System.out.println(dateFormat.format(java.sql.Date.valueOf(s)));
                daysInWeekInRange.add(dateFormat.format(java.sql.Date.valueOf(s)));
            }

            slotsofDoctor = sdd.getScheduleOfDoctorByDoctorIdAndDateRange(doctorId, weeks_raw.get(Integer.parseInt(week_raw)).getStartTime(), weeks_raw.get(Integer.parseInt(week_raw)).getEndTime());

            try ( PrintWriter out = response.getWriter()) {
                out.println("<p id=\"year-schedule-doctor-txt\">YEAR</p>\n"
                        + "            <select class=\"year-schedule-doctor\" name=\"year-schedule-doctor\" id=\"year-schedule-doctor\" onchange=\"eventLoadWeekByYear(this)\">\n");
                for (String y : years) {
                    if (y.equals(year)) {
                        out.println("<option selected value=\"" + y + "\">" + y + "</option>");
                    } else {
                        out.println("<option value=\"" + y + "\">" + y + "</option>");
                    }
                }
                out.println("            </select>\n"
                        + "            <p id=\"week-schedule-doctor-txt\">WEEK</p>\n"
                        + "            <select class=\"week-schedule-doctor\" name=\"week-schedule-doctor\" id=\"week-schedule-doctor\"  onchange=\"eventLoadSlotsByWeek(this)\">\n");

                for (ScheduleDetail week : weeks) {
                    if (week.getSlotId().equals(week_raw)) {
                        out.println("<option selected value=\"" + week.getSlotId() + "\">" + week.getStartTime() + " To " + week.getEndTime() + "</option>");
                    } else {
                        out.println("<option value=\"" + week.getSlotId() + "\">" + week.getStartTime() + " To " + week.getEndTime() + "</option>");
                    }
                }
                out.println("            </select>\n"
                        + "            <i class=\"fas fa-close\" id=\"close-schedule-btn\" onclick=\"closeScheduleOfDoctorForm()\"></i>\n"
                        + "            <form action=\"admin-update-schedule-doctor\" class=\"form-container\" method=\"post\">\n"
                        + "                <h3><b>Chỉnh sửa lịch trình của bác sĩ " + doctorId + "</b></h3>\n"
                        + "\n"
                        + "                <!--schedule table - start-->\n"
                        + "                <div class=\"container\">\n"
                        + "                    <div class=\"timetable-img text-center\">\n"
                        + "                        <img src=\"img/content/timetable.png\" alt=\"\">\n"
                        + "                    </div>\n"
                        + "                    <div class=\"table-responsive\">\n"
                        + "                        <table class=\"table table-bordered text-center schedule-doctor-detail-table\">\n"
                        + "                            <thead>\n"
                        + "                                <c:set var=\"daysOfCurrentWeek\" value=\"${requestScope.daysOfCurrentWeek}\"></c:set>\n"
                        + "                                <c:set var=\"daysOfCurrentWeek_raw\" value=\"${requestScope.daysOfCurrentWeek_raw}\"></c:set>\n"
                        + "                                    <tr class=\"bg-light-gray\">\n"
                        + "                                        <th class=\"text-uppercase\">Slot</th>\n"
                        + "                                        <th class=\"text-uppercase\"><p>Thứ 2</p><p>" + daysInWeekInRange.get(0) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 3</p><p>" + daysInWeekInRange.get(1) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 4</p><p>" + daysInWeekInRange.get(2) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 5</p><p>" + daysInWeekInRange.get(3) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 6</p><p>" + daysInWeekInRange.get(4) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Thứ 7</p><p>" + daysInWeekInRange.get(5) + "</p></th>\n"
                        + "                                    <th class=\"text-uppercase\"><p>Chủ nhật</p><p>" + daysInWeekInRange.get(6) + "</p></th>\n"
                        + "                                </tr>\n"
                        + "                            </thead>\n"
                        + "                            <tbody>\n"
                        + "                                <c:set var=\"slotsOfDoctor\" value=\"${requestScope.slotsOfDoctor}\"></c:set>\n");

                for (ScheduleDetail slot : slots) {
                    out.println("<tr>");
                    out.println("<td class=\"align-middle\">Slot " + slot.getSlotId() + "</td>");
                    for (int i = 1; i <= 7; i++) {
                        out.println("<td>");
                        boolean slotFound = false;
                        for (ScheduleDetail s : slotsofDoctor) {
                            if (daysInWeekInRange_raw.get(i - 1).equals(s.getWorkDate()) && slot.getSlotId().equals(s.getSlotId())) {
                                out.println("<i class=\"fas fa-close delete-schedule-icon\"   data-scheduleDetailId=\"" + s.getId() + "\" onclick=\"eventClickDeleteScheduleIcon(this)\"></i>");
//                                out.println("<span class=\"bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom font-size16 xs-font-size10\">Dance</span>");
//                                out.println("<div class=\"font-size10 text-light-gray\">Ivana Wong</div>");
                                System.out.println("START - SLOT - STATUS:");
                                for (AdminSidebarMenu status : statusSlot) {
                                    System.out.println("Slot - status: " + status);
                                    if (status.getLink().equals(s.getSlotStatus())) {
                                        System.out.println("status.getLink() == s.getSlotStatus()");
                                        out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + status.getIcon() + "\">" + status.getName() + "</span></div>");
                                    }
                                }
                                out.println("<div class=\"margin-10px-top font-size10\">" + slot.getStartTime() + " - " + slot.getEndTime() + "</div>");
                                slotFound = true;
                                break;
                            }
                        }
                        if (!slotFound) {
                            out.println("<i class=\"fas fa-plus create-schedule-icon\" onclick=\"eventClickCreateScheduleDoctor(this)\"></i>");
                            out.println("<span>-</span>");
                        }
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }

                out.println(
                        "                            </tbody>\n"
                        + "                        </table>\n"
                        + "                    </div>\n"
                        + "                </div>\n"
                        + "                <!--schedule table - end-->\n"
                        + "\n"
                        + "                <!-- Add more input fields as needed -->\n"
                        + "                <button type=\"button\" id=\"schedule-close-button\" onclick=\"closeScheduleOfDoctorForm()\">Close</button>\n"
                        + "            </form>");
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
