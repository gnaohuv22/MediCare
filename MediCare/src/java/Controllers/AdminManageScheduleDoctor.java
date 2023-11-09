/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.BranchDAO;
import DAL.DoctorDAO;
import DAL.EmployeeDAO;
import DAL.HolidayDAO;
import DAL.ScheduleDetailDAO;
import DAL.SubLevelMenuDAO;
import Models.AdminSidebarMenu;
import Models.Branch;
import Models.Doctor;
import Models.Employee;
import Models.Holiday;
import Models.ScheduleDetail;
import com.google.gson.JsonObject;
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
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tubinh
 */
//@WebServlet(name = "AdminManageScheduleDoctor", urlPatterns = {"/admin-manage-schedule-doctor"})
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
        HttpSession session = request.getSession();
        Employee employee = (Employee) session.getAttribute("EMPLOYEE");
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
        String currentDate = sdd.getCurrentDate();
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
                                if (sdd.compareDates(daysInWeekInRange_raw.get(i - 1), currentDate) >= 0) {
                                    if (s.getSlotStatus().equals("1")) {
                                        out.println("<i class=\"fas fa-close delete-schedule-icon\"   data-scheduleDetailId=\"" + s.getId() + "\" onclick=\"eventClickDeleteScheduleIcon(this)\"></i>");
                                    }
                                }
//                                out.println("<span class=\"bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom font-size16 xs-font-size10\">Dance</span>");
//                                out.println("<div class=\"font-size10 text-light-gray\">Ivana Wong</div>");
                                for (AdminSidebarMenu status : statusSlot) {
                                    System.out.println("Slot - status: " + status);
                                    if (status.getLink().equals(s.getSlotStatus())) {
                                        System.out.println("status.getLink() == s.getSlotStatus()");
                                        if (sdd.compareDates(daysInWeekInRange_raw.get(i - 1), currentDate) < 0) {
                                            out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + "status-grey" + "\">" + status.getName() + "</span></div>");
                                        } else {
                                            out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + status.getIcon() + "\">" + status.getName() + "</span></div>");

                                        }
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
                                if (sdd.compareDates(daysInWeekInRange_raw.get(i - 1), currentDate) >= 0) {
                                    if (s.getSlotStatus().equals("1")) {
                                        out.println("<i class=\"fas fa-close delete-schedule-icon\"   data-scheduleDetailId=\"" + s.getId() + "\" onclick=\"eventClickDeleteScheduleIcon(this)\"></i>");
                                    }
                                }
//                                out.println("<span class=\"bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom font-size16 xs-font-size10\">Dance</span>");
//                                out.println("<div class=\"font-size10 text-light-gray\">Ivana Wong</div>");

                                for (AdminSidebarMenu status : statusSlot) {
                                    System.out.println("Slot - status: " + status);
                                    if (status.getLink().equals(s.getSlotStatus())) {
                                        System.out.println("status.getLink() == s.getSlotStatus()");

                                        if (sdd.compareDates(daysInWeekInRange_raw.get(i - 1), currentDate) < 0) {
                                            out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + "status-grey" + "\">" + status.getName() + "</span></div>");
                                        } else {
                                            out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + status.getIcon() + "\">" + status.getName() + "</span></div>");

                                        }
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
            System.out.println("Thuc hien function getScheduleOfDoctorByDoctorIdAndDateRange voi: ");
            System.out.println("doctorId = " + doctorId);
            System.out.println("daysOfCurrentWeek_raw.get(0) = " + daysOfCurrentWeek_raw.get(0));
            System.out.println("daysOfCurrentWeek_raw.get(6) = " + daysOfCurrentWeek_raw.get(6));

            slotsofDoctor = sdd.getScheduleOfDoctorByDoctorIdAndDateRange(doctorId, daysOfCurrentWeek_raw.get(0), daysOfCurrentWeek_raw.get(6));
            System.out.println("Schedule in current week:");
            for (ScheduleDetail s : slotsofDoctor) {
                System.out.println(s.getWorkDate() + " - " + s.getEndTime() + " : " + s.getScheduleId() + " || slot id = " + s.getSlotId());
            }
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
                                if (sdd.compareDates(daysInWeekInRange_raw.get(i - 1), currentDate) >= 0) {
                                    if (s.getSlotStatus().equals("1")) {
                                        out.println("<i class=\"fas fa-close delete-schedule-icon\"  data-scheduleDetailId=\"" + s.getId() + "\" onclick=\"eventClickDeleteScheduleIcon(this)\"></i>");
                                    }
                                }
//                                out.println("<span class=\"bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom font-size16 xs-font-size10\">Dance</span>");
//                                out.println("<div class=\"font-size10 text-light-gray\">Ivana Wong</div>");

                                for (AdminSidebarMenu status : statusSlot) {
                                    System.out.println("Slot - status: " + status);
                                    if (status.getLink().equals(s.getSlotStatus())) {
                                        System.out.println("status.getLink() == s.getSlotStatus()");
                                        if (sdd.compareDates(daysInWeekInRange_raw.get(i - 1), currentDate) < 0) {
                                            out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + "status-grey" + "\">" + status.getName() + "</span></div>");
                                        } else {
                                            out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + status.getIcon() + "\">" + status.getName() + "</span></div>");

                                        }
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
                                if (sdd.compareDates(daysInWeekInRange_raw.get(i - 1), currentDate) >= 0) {
                                    if (s.getSlotStatus().equals("1")) {
                                        out.println("<i class=\"fas fa-close delete-schedule-icon\"   data-scheduleDetailId=\"" + s.getId() + "\" onclick=\"eventClickDeleteScheduleIcon(this)\"></i>");
                                    }
                                }
//                                out.println("<span class=\"bg-sky padding-5px-tb padding-15px-lr border-radius-5 margin-10px-bottom font-size16 xs-font-size10\">Dance</span>");
//                                out.println("<div class=\"font-size10 text-light-gray\">Ivana Wong</div>");
                                System.out.println("START - SLOT - STATUS:");
                                for (AdminSidebarMenu status : statusSlot) {
                                    System.out.println("Slot - status: " + status);
                                    if (status.getLink().equals(s.getSlotStatus())) {
                                        System.out.println("status.getLink() == s.getSlotStatus()");
                                        if (sdd.compareDates(daysInWeekInRange_raw.get(i - 1), currentDate) < 0) {
                                            out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + "status-grey" + "\">" + status.getName() + "</span></div>");
                                        } else {
                                            out.println("<div class=\"status-appointment\"><span class=\"custom-badge " + status.getIcon() + "\">" + status.getName() + "</span></div>");

                                        }
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
        String action = request.getParameter("action");
        String branchId = request.getParameter("branchId");
        String monthSelect = request.getParameter("monthSelect");
        String eventName = request.getParameter("eventName");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        PrintWriter out = response.getWriter();
        BranchDAO bd = new BranchDAO();
        ArrayList<Branch> branches = bd.getAllBranches();
        ArrayList<String> lastestMonthSchedule = new ArrayList<>();
        HolidayDAO hd = new HolidayDAO();
        if (branchId != null) {
            lastestMonthSchedule = sdd.getLastestScheduleByBranchId(branchId);
        } else {
            lastestMonthSchedule = sdd.getLastestScheduleByBranchId(branches.get(0).getId());
        }
        if (action != null) {
            switch (action) {
                case "add-schedule-all-doctor": {
                    System.out.println("ACTION: add-schedule-all-doctor");
                    ajaxFunction(out, branches, lastestMonthSchedule, branchId, monthSelect);
                    break;
                }
                case "save-add-schedule": {
                    System.out.println("ACTION: save-add-schedule");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    try {
                        Date date = dateFormat.parse(lastestMonthSchedule.get(0));
                        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");

                        year = yearFormat.format(date);
                        String month = monthFormat.format(date);

                        System.out.println("Year: " + year);
                        System.out.println("Month: " + month);
                        if (sdd.addScheduleForMonthAndBranchId(year, month, branchId, monthSelect)) {
                            System.out.println("Add schedule success!");
                        } else {
                            System.out.println("Add schedule fail!");
                        }
                        System.out.println("line 725:");
                        lastestMonthSchedule = sdd.getLastestScheduleByBranchId(branchId);
                        ajaxFunction(out, branches, lastestMonthSchedule, branchId, monthSelect);
                        System.out.println("line 728:");

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case "onchange-branch-add-schedule": {
                    System.out.println("ACTION: onchange-branch-add-schedule");
                    ajaxFunction(out, branches, lastestMonthSchedule, branchId, monthSelect);
                    break;
                }
                case "add-event": {
                    System.out.println("ACTION: add-event");
                    ajaxFunctionAddEvent(out, branches, branchId);
                    break;
                }
                case "save-add-event": {
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    out = response.getWriter();
                    System.out.println("ACTION: save-add-event");
                    JsonObject jsonResponse = new JsonObject();
                    // check exist event:
                    Holiday holiday = hd.getEventByBranchNameFromTo(branchId, eventName, fromDate, toDate);
                    if (holiday != null) {
//                        out.println("Ngày lễ đã tồn tại, được sửa đổi lần cuối bởi " + employee.getName() + "!");
                        // Simulate data or retrieve data from a database
                        String message = "Ngày lễ đã tồn tại, được sửa đổi lần cuối bởi " + new EmployeeDAO().getEmployeeById(holiday.getModifyBy()).getName() + "! Bạn có muốn tiếp tục thêm ngày lễ?";

                        // Create a JSON object with attributes
                        jsonResponse.addProperty("status", "fail");
                        jsonResponse.addProperty("message", message);

                        System.out.println("jsonResponse: " + jsonResponse);
                        out.println(jsonResponse);
                    } else {
                        // isDelete = 1 -> Nghi le:
                        if (sdd.setDayOffForDoctorByEvent(fromDate, toDate, branchId)) {
                            System.out.println("Set day off from " + fromDate + " to" + toDate + " success!");
                            // add holiday to Database:
                            System.out.println("Employee: " + employee);
                            if (hd.addEvent(eventName, fromDate, toDate, branchId, employee)) {
                                System.out.println("Add new Event success!");
                            } else {
                                System.out.println("Add new Event fail!");
                            }
                        } else {
                            System.out.println("Set day off from " + fromDate + " to" + toDate + " success!");
                        }
                        // Simulate data or retrieve data from a database
                        String message = " Thêm ngày lễ thành công!";

                        // Create a JSON object with attributes
                        jsonResponse.addProperty("status", "success");
                        jsonResponse.addProperty("message", message);

                        System.out.println("jsonResponse: " + jsonResponse);
                        out.println(jsonResponse);
                    }

                    System.out.println("Event-name: " + eventName);
                    System.out.println("From-date: " + fromDate);
                    System.out.println("To-date: " + toDate);

                    break;
                }
                case "add-leave": {
                    System.out.println("ACTION: add-leave");
                    ajaxFunctionAddLeave(out, null, branchId);
                    break;
                }

                default:
                    throw new AssertionError();
            }
        }
    }

    public static void ajaxFunction(PrintWriter out, ArrayList<Branch> branches, ArrayList<String> lastestMonthSchedule, String branchId, String monthSelect) {
        out.println("<div class=\"booking-container\">\n"
                + "            <!--Step-1-container - start-->\n"
                + "            <form action=\"user-book-appointment\" method=\"post\">\n"
                + "                <div id=\"step-1-container\">\n"
                + "                    <h2 class=\"booking-header\">Xếp lịch cho bác sĩ</h2>\n"
                + "                    <div class=\"row add-schedule-info-input\">\n"
                + "                        <!-- Cột 1 -->\n"
                + "                        <div class=\"col-md-6\">\n"
                + "                            <div><span>Chọn chi nhánh </span>\n"
                + "                                <select id=\"branchId\" name=\"branchId\" class=\"booking-select-class\" onchange=\"onchangeBranchIdLoadSchedule()\">\n");
        for (Branch b : branches) {
            if (branchId != null && b.getId().equals(branchId)) {
                out.println("<option selected value=\"" + b.getId() + "\">" + b.getName() + "</option>");
            } else {
                out.println("<option value=\"" + b.getId() + "\">" + b.getName() + "</option>");
            }
        }
        out.println(
                "                                </select>\n"
                + "                            </div>\n"
                + "                            <div> \n"
                + "                            </div>\n"
                + "                        </div>\n"
                + "\n"
                + "                        <!-- Cột 2 -->\n"
                + "                        <div class=\"col-md-6 add-schedule-select-box-part\">  \n"
                + "<div>Thêm lịch làm việc trong " + "<select id=\"monthSelect\">\n");
        for (int i = 1; i < 4; i++) {
            if (monthSelect != null && monthSelect.contains(i + "")) {
                out.println("<option selected value=\"" + i + "\">" + i + "</option>\n");
            } else {
                out.println("<option value=\"" + i + "\">" + i + "</option>\n");
            }
        }
        out.println(
                "                </select>" + " tháng tiếp theo</div>"
                + " <button type=\"button\" class=\"schedule-add-button\" onclick=\"saveAddScheduleOfDoctorForm()\">Thêm</button>"
                + "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                            <div>\n"
                + "                <!--Step-1-container - end-->\n"
                + "</div>");
        try {
            // Chuyển chuỗi thành đối tượng Date
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(lastestMonthSchedule.get(0));

            // Định dạng lại thành "tháng MM năm yyyy"
            String outputDateStr = new SimpleDateFormat(" 'tháng' MM 'năm' yyyy").format(date);

            System.out.println(outputDateStr);
            for (Branch b : branches) {
                if (branchId != null) {
                    if (b.getId().equals(branchId)) {
                        out.println("<div class=\"row step2-add-schedule\">\n"
                                + "                        <div class=\"col-md-12\">\n"
                                //                                +"                                <img id=\"loading\" src=\"/MediCare/assets/admin/images/c7e1b7b5753737039e1bdbda578132b8.gif\" alt=\"Loading...\" style=\"display: block;\">\n"
                                + "<h3>Lịch mới nhất đến thời điểm hiện tại: <span style=\"color: green;\">" + outputDateStr + "</span> (chi nhánh " + b.getName() + ")</h3>");
                    }
                } else {
                    out.println("<div class=\"row step2-add-schedule\">\n"
                            + "                        <div class=\"col-md-12\">\n"
                            + "<h3>Lịch mới nhất đến thời điểm hiện tại: <span style=\"color: green;\">" + outputDateStr + "</span> (chi nhánh " + branches.get(0).getName() + ")</h3>");
                    break;
                }
            }
        } catch (ParseException e) {
            System.out.println(e);
        }
        // Ngày đầu tiên của tháng (ví dụ: 2023-03-01)
        Date firstDayOfMonth = null;
        try {
            firstDayOfMonth = new SimpleDateFormat("yyyy-MM-dd").parse(lastestMonthSchedule.get(0));
        } catch (ParseException ex) {
            Logger.getLogger(AdminManageScheduleDoctor.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Calendar để tính toán
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(firstDayOfMonth);
        out.println("<table class=\"lastest-month-schedule\">");
        out.println("<tr><th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th><th>Thu</th><th>Fri</th><th>Sat</th></tr>");
        out.println("<tr>");

// Tìm thứ của ngày đầu tiên của tháng
        int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int emptyCells = firstDayOfWeek - 1; // Số ô trống để điền
        int dayCounter = 1;

// In các ô trống trước ngày đầu tiên
        for (int i = 0; i < emptyCells; i++) {
            out.println("<td style=\"background-color: #ccc;\" class='non-month-day'></td>");
        }

        while (calendar.get(Calendar.MONTH) == firstDayOfMonth.getMonth()) {
            // Kiểm tra xem ngày có sự kiện hay không (dựa trên danh sách lastestMonthSchedule)
            boolean hasEvent = lastestMonthSchedule.contains(String.valueOf(dayCounter));

            // Đánh dấu ngày có sự kiện bằng lớp CSS event-day
            if (hasEvent) {
                out.println("<td class='event-day'>" + dayCounter + "</td>");
            } else {
                out.println("<td>" + dayCounter + "</td>");
            }

            // Nếu đủ 7 ngày trong tuần, bắt đầu hàng mới
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                out.println("</tr><tr>");
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1); // Chuyển sang ngày tiếp theo
            dayCounter++;
        }

// Hoàn thiện hàng cuối cùng bằng cách in các ngày không phải của tháng với giá trị trống
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            out.println("<td style=\"background-color: #ccc;\" class='non-month-day'></td>");
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        out.println("</tr>");
        out.println("</table>");

        out.println("</div>\n");
        out.println("</div>\n");
        out.println("<div class=\"row step3-add-schedule\">\n");
        out.println("<div class=\"btn btn-primary\" onclick=\"closeScheduleOfDoctorForm()\">Hủy</div>");
        out.println("</div>\n");
    }

    public static void ajaxFunctionAddEvent(PrintWriter out, ArrayList<Branch> branches, String branchId) {
        out.println("<div class=\"booking-container\">\n"
                + "            <!--Step-1-container - start-->\n"
                + "            <form action=\"user-book-appointment\" method=\"post\">\n"
                + "                <div id=\"step-1-container\">\n"
                + "                    <h2 class=\"booking-header\">Thêm lịch nghỉ lễ</h2>\n"
                + "                    <div class=\"row add-event-info-input\">\n"
                + "                        <!-- Cột 1 -->\n"
                + "                        <div class=\"col-md-6\">\n"
                + "                            <div class=\"form-group\"><label>Chọn chi nhánh <span class=\"text-danger\">*</span></label>"
                + "                                <select id=\"branchId\" name=\"branchId\" class=\"booking-select-class\" onchange=\"onchangeBranchIdLoadSchedule()\">\n");
        for (Branch b : branches) {
            if (branchId != null && b.getId().equals(branchId)) {
                out.println("<option selected value=\"" + b.getId() + "\">" + b.getName() + "</option>");
            } else {
                out.println("<option value=\"" + b.getId() + "\">" + b.getName() + "</option>");
            }
        }
        out.println(
                "                                </select>\n");
        out.println(
                "                            </div>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                            <div>\n"
                + "                <!--Step-1-container - end-->\n"
                + "<div class=\"row\">\n"
                + "                            <div class=\"form-group col-md-6\">\n"
                + "                                <label>Tên ngày lễ <span class=\"text-danger\">*</span></label>\n"
                + "                                <input id=\"eventName\" class=\"form-control\" type=\"text\">\n"
                + "                            </div>\n"
                + "                            </div>\n"
                + "<div class=\"row\">\n"
                + "                            <div class=\"form-group col-md-6\">\n"
                + "                                <label>Ngày bắt đầu <span class=\"text-danger\">*</span></label>\n"
                + "                                <input id=\"fromDate\" class=\"form-control\" type=\"date\">\n"
                + "                            </div>\n"
                + "                            <div class=\"form-group col-md-6\">\n"
                + "                                <label>Kết thúc vào <span class=\"text-danger\">*</span></label>\n"
                + "                                <div>\n"
                + "                                    <input id=\"toDate\" class=\"form-control\" type=\"date\">\n"
                + "                                </div>\n"
                + "                            </div>\n"
                + "                </div>"
                + "<p class=\"text-center\" id=\"error-save-add-appointment\"></p>"
                + "                </div>"
                + "</div>");
        out.println("<div class=\"row step3-add-event\">\n");
        out.println("<div class=\"btn btn-primary\" onclick=\"saveAddEvent()\">Thêm ngày lễ</div>");
        out.println("<div class=\"btn btn-primary\" onclick=\"closeScheduleOfDoctorForm()\">Hủy</div>");
        out.println("</div>\n");
    }

    public static void ajaxFunctionAddLeave(PrintWriter out, ArrayList<Doctor> doctorList, String branchId) {
        doctorList = new DoctorDAO().getAllDoctors();
        out.println("<div class=\"booking-container\">\n"
                + "            <!--Step-1-container - start-->\n"
                + "                <div id=\"step-1-container\">\n"
                + "                    <h2 class=\"booking-header\">Thêm ngày nghỉ cho bác sĩ</h2>\n"
                + "                    <div class=\"row add-event-info-input\">\n"
                + "                        <!-- Cột 1 -->\n"
                + "                        <div class=\"col-md-6\">\n"
                + "                            <div class=\"form-group\"><label>Tìm kiếm bác sĩ qua tên</label>"
                + "                                <input type=\"text\" id=\"searchPattern\" name=\"searchPattern\" class=\"form-control\">\n"
                + "                            </div>\n");
        out.println(" <table border=\"1\">\n"
                + "        <tr>\n"
                + "            <th>ID</th>\n"
                + "            <th>Tên</th>\n"
                + "            <th>Chi nhánh</th>\n"
                + "            <th>Chuyên khoa</th>\n"
                + "            <th>Email</th>\n"
                + "        </tr>\n");
        for (Doctor doctor : doctorList) {

            out.println("            <tr>\n"
                    + "                <td>" + 2 + "</td>\n"
                    + "                <td>" + 2 + "</td>\n"
                    + "                <td>" + 2 + "</td>\n"
                    + "                <td>" + 2 + "</td>\n"
                    + "                <td>" + 2 + "</td>\n"
                    + "            </tr>\n");
        }
        out.println("    </table>");
        out.println("                        </div>\n"
                + "<div class=\"form-group col-md-6\">\n"
                + "                                <span>Ngày bắt đầu <span class=\"text-danger\">*</span></span>\n"
                + "                                <input id=\"fromDate\" class=\"form-control\" type=\"date\">\n"
                + "                                <span>Kết thúc vào <span class=\"text-danger\">*</span></span>\n"
                + "                                <input id=\"fromDate\" class=\"form-control\" type=\"date\">\n"
                + "<div class=\"btn btn-primary\" onclick=\"saveAddEvent()\">Thêm ngày lễ</div>");
        out.println("<div><div class=\"btn btn-primary\" onclick=\"closeScheduleOfDoctorForm()\">Hủy</div>"
                + "                            </div></div>\n");
        out.println("</div></div>");
        out.println("</div>\n");
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
