/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.ScheduleDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class ScheduleDetailDAO extends DBContext {

    public ArrayList<ScheduleDetail> getAllSlotsOfDoctorByDoctorIdAndDate(String doctorId, String date) {
        ArrayList<ScheduleDetail> list = new ArrayList<>();
        String sql = "SELECT SD.id AS ScheduleDetailId, DS.id AS scheduleId, WS.id AS slotId, SD.slotStatus , WS.startTime FROM ScheduleDetail AS SD\n"
                + "JOIN \n"
                + "WorkingSlot AS WS ON WS.id = SD.SlotID\n"
                + "JOIN \n"
                + "DoctorSchedule AS DS ON DS.id = SD.ScheduleId\n"
                + "WHERE DS.doctorId = ? AND DS.WorkDate = ? AND SD.isdelete = 0";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            st.setString(2, date);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ScheduleDetail s = new ScheduleDetail(String.valueOf(rs.getLong("ScheduleDetailId")),
                        String.valueOf(rs.getLong("scheduleId")),
                        String.valueOf(rs.getInt("slotId")),
                        rs.getString("slotStatus"),
                        String.valueOf(rs.getTime("startTime")));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("getAllSlotsOfDoctorByDoctorIdAndDate: " + e);
        }
        return list;
    }

    public ArrayList<ScheduleDetail> getAllSlotsOfDoctorByBranchIdAndServiceId(String branchId, String serviceId) {
        ArrayList<ScheduleDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM WorkingSlot";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, branchId);
//            st.setString(2, serviceId);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ScheduleDetail s = new ScheduleDetail(String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getTime("startTime")));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("getAllSlotsOfDoctorByBranchIdAndServiceId: " + e);
        }
        return list;
    }

    public ArrayList<ScheduleDetail> getAllSlots() {
        ArrayList<ScheduleDetail> list = new ArrayList<>();
        String sql = "SELECT * FROM WorkingSlot";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ScheduleDetail s = new ScheduleDetail(String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getTime("startTime")),
                        String.valueOf(rs.getTime("endTime")), 0);
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("getAllSlotsOfDoctorByBranchIdAndServiceId: " + e);
        }
        return list;
    }

    public ArrayList<ScheduleDetail> getAllSlotsOfDoctorByDate(String branchId, String serviceId, String date) {
        ArrayList<ScheduleDetail> list = new ArrayList<>();
        String sql = "SELECT WS.id AS slotId, SD.slotStatus , WS.startTime FROM ScheduleDetail AS SD\n"
                + "JOIN \n"
                + "WorkingSlot AS WS ON WS.id = SD.SlotID\n"
                + "JOIN \n"
                + "DoctorSchedule AS DS ON DS.id = SD.ScheduleId\n"
                + "JOIN \n"
                + "Doctor AS d ON d.id = DS.DoctorID\n"
                + "JOIN \n"
                + "Branch AS b ON b.id = d.branchId\n"
                + "JOIN \n"
                + "DoctorService AS DSv ON DSv.doctorId = d.id\n"
                + "WHERE b.id = ? AND DSv.serviceId = ? AND DS.WorkDate = ?\n AND SD.slotStatus = '1' AND SD.isDelete = 0   "
                + "GROUP BY WS.id, SD.slotStatus, WS.startTime\n"
                + "";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(branchId));
            st.setInt(2, Integer.parseInt(serviceId));
            st.setString(3, date);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ScheduleDetail s = new ScheduleDetail(String.valueOf(rs.getInt("slotId")),
                        rs.getString("slotStatus"),
                        String.valueOf(rs.getTime("startTime")));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("getAllSlotsOfDoctorByDate: " + e);
        }
        return list;
    }

    public ArrayList<ScheduleDetail> getAllSlotsOfDoctorByDoctorId(String doctorId, String serviceId, String date) {
        ArrayList<ScheduleDetail> list = new ArrayList<>();
        String sql = "SELECT WS.id AS slotId, SD.slotStatus , WS.startTime FROM ScheduleDetail AS SD\n"
                + "JOIN \n"
                + "WorkingSlot AS WS ON WS.id = SD.SlotID\n"
                + "JOIN \n"
                + "DoctorSchedule AS DS ON DS.id = SD.ScheduleId\n"
                + "JOIN \n"
                + "Doctor AS d ON d.id = DS.DoctorID\n"
                + "JOIN \n"
                + "DoctorService AS DSv ON DSv.doctorId = d.id\n"
                + "WHERE d.id = ? AND DSv.serviceId = ? AND DS.WorkDate = ?\n AND SD.slotStatus = '1' AND SD.isDelete = 0"
                + "GROUP BY WS.id, SD.slotStatus, WS.startTime\n"
                + "";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            st.setInt(2, Integer.parseInt(serviceId));
            st.setString(3, date);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ScheduleDetail s = new ScheduleDetail(String.valueOf(rs.getInt("slotId")),
                        rs.getString("slotStatus"),
                        String.valueOf(rs.getTime("startTime")));
                list.add(s);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("getAllSlotsOfDoctorByDoctorId: " + e);
        }
        return list;
    }

    public static void main(String[] args) {
        ScheduleDetailDAO sdd = new ScheduleDetailDAO();
        ArrayList<ScheduleDetail> list = sdd.getAllSlotsOfDoctorByDoctorId("2", "5", "2023-10-05");

        for (ScheduleDetail scheduleDetail : list) {
            System.out.println(scheduleDetail);
        }
    }

    public String getStartTimeBySlotId(String slotId) {
        String sql = "SELECT startTime FROM WorkingSlot\n"
                + "WHERE id = ?";
        String startTime = "";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(slotId));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                startTime = rs.getTime(1).toString();
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("getStartTimeBySlotId: " + e);
        }
        return startTime;
    }

    public boolean setStatusForBookingSlot(String slotId, String doctorId, String date) {
        String sql = "UPDATE ScheduleDetail \n"
                + "SET SlotStatus = 0\n"
                + "WHERE id = (SELECT SD.id FROM ScheduleDetail AS SD\n"
                + "JOIN DoctorSchedule AS DS ON SD.ScheduleID = DS.id\n"
                + "WHERE SD.SlotID = ? AND DS.WorkDate = ? AND DS.DoctorID = ? )";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(slotId));
            st.setString(2, date);
            st.setString(3, doctorId);
            st.execute();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("setStatusForBookingSlot: " + e);
        }
        return false;
    }

    public ArrayList<String> get5CurrentYears() {
        ArrayList<String> years = new ArrayList<>();
        int currentYear = Year.now().getValue();

        // Thêm 3 năm trước
        for (int i = 3; i >= 1; i--) {
            years.add((currentYear - i) + "");
        }
        // Thêm năm hiện tại
        years.add(currentYear + "");

        // Thêm năm sau
        years.add((currentYear + 1) + "");

        return years;
    }

    public static boolean isFirstWeekComplete(ArrayList<ScheduleDetail> weeks) {
        if (!weeks.isEmpty()) {
            ScheduleDetail firstWeek = weeks.get(0);
            return calculateDaysBetween(firstWeek.getStartTime(), firstWeek.getEndTime()) == 7;
        }
        return false;
    }

    public static boolean isLastWeekComplete(ArrayList<ScheduleDetail> weeks) {
        if (!weeks.isEmpty()) {
            ScheduleDetail lastWeek = weeks.get(weeks.size() - 1);
            return calculateDaysBetween(lastWeek.getStartTime(), lastWeek.getEndTime()) == 7;
        }
        return false;
    }

    public static int calculateDaysBetween(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return (int) ChronoUnit.DAYS.between(start, end) + 1;
    }

    public ArrayList<ScheduleDetail> getWeeksInYearWithValidation(int year) {
        ArrayList<ScheduleDetail> weeks = new ArrayList<>();
        LocalDate startDate = LocalDate.of(year - 1, 12, 26);
        LocalDate endDate = LocalDate.of(year + 1, 1, 6);

        LocalDate endOfWeek = startDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));

        int count = 0;
        // Tuần đầu tiên không cần bắt đầu từ 01/01
        if (!endOfWeek.isAfter(endDate)) {
            if (calculateDaysBetween(String.valueOf(startDate), String.valueOf(endOfWeek)) == 7) {
                weeks.add(new ScheduleDetail(count + "", String.valueOf(startDate), String.valueOf(endOfWeek), 0));
            }
            startDate = endOfWeek.plusDays(1);
            count++;
        }

        while (!startDate.isAfter(endDate)) {
            endOfWeek = startDate.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
            if (endOfWeek.isAfter(endDate)) {
                // Tuần cuối cùng bao gồm 31/12 (kéo dài vài ngày sang năm sau)
                endOfWeek = endDate;
            }

            // Kiểm tra xem tuần có đủ 7 ngày không
            if (calculateDaysBetween(String.valueOf(startDate), String.valueOf(endOfWeek)) == 7) {
                weeks.add(new ScheduleDetail(count + "", String.valueOf(startDate), String.valueOf(endOfWeek), 0));
            }
            startDate = endOfWeek.plusDays(1);
            count++;
        }

        // Kiểm tra tuần cuối cùng
        if (!isLastWeekComplete(weeks)) {
            weeks.remove(weeks.size() - 1);
            LocalDate lastWeekEndDate = LocalDate.parse(weeks.get(weeks.size() - 1).getEndTime());
            lastWeekEndDate = lastWeekEndDate.plusDays(1);
            weeks.add(new ScheduleDetail(count + "", String.valueOf(lastWeekEndDate), String.valueOf(endDate), 0));
        }

//        return weeks;
        ArrayList<ScheduleDetail> list = new ArrayList<>();
        count = 0;
        for (ScheduleDetail week : weeks) {
            list.add(new ScheduleDetail(count + "", week.getStartTime(), week.getEndTime(), 0));
            count++;
        }
        return list;
    }

    public ArrayList<String> getDaysOfCurrentWeek() {
        ArrayList<String> list = new ArrayList<>();
        String sql = "DECLARE @Today DATE = GETDATE();\n"
                + "DECLARE @StartOfWeek DATE = DATEADD(DAY, 1 - DATEPART(WEEKDAY, @Today), @Today);\n"
                + "IF DATEPART(WEEKDAY, @Today) = 1\n"
                + "    SET @StartOfWeek = DATEADD(DAY, -6, @StartOfWeek);\n"
                + "SELECT DATEADD(DAY, number, @StartOfWeek) AS WeekDay\n"
                + "FROM master.dbo.spt_values\n"
                + "WHERE type = 'P' AND number BETWEEN 1 AND 7;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(String.valueOf(rs.getDate(1)));
            }
        } catch (SQLException e) {
            System.out.println("getDaysOfCurrentWeek: " + e);
        }
        return list;
    }

    public ArrayList<ScheduleDetail> getScheduleOfDoctorByDoctorIdAndDateRange(String doctorId, String startDate, String endDate) {
        ArrayList<ScheduleDetail> list = new ArrayList<>();
        String sql = "SELECT SD.*, DS.WorkDate\n"
                + "FROM ScheduleDetail AS SD\n"
                + "JOIN WorkingSlot AS WS ON SD.SlotID = WS.id\n"
                + "JOIN DoctorSchedule AS DS ON SD.ScheduleID = DS.id\n"
                + "JOIN Doctor AS D ON DS.DoctorID = D.id\n"
                + "WHERE D.id = ?\n"
                + "AND DS.WorkDate >= ?\n"
                + "AND DS.WorkDate <= ? AND SD.isDelete = 0;";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            st.setString(2, (startDate));
            st.setString(3, (endDate));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ScheduleDetail s = new ScheduleDetail(
                        String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getInt("ScheduleId")),
                        String.valueOf(rs.getInt("SlotId")),
                        rs.getString("SlotStatus"),
                        String.valueOf(rs.getDate("workDate")),
                        0
                );
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("getScheduleOfDoctorByDoctorIdAndDateRange: " + e);
        }
        return list;
    }

    public ArrayList<ScheduleDetail> getScheduleOfDoctorByDoctorIdCurrentWeek(String doctorId) {
        ArrayList<ScheduleDetail> list = new ArrayList<>();
        String sql = "DECLARE @Today DATE = GETDATE();\n"
                + "DECLARE @StartOfWeek DATE = DATEADD(DAY, 1 - DATEPART(WEEKDAY, @Today), @Today);\n"
                + "DECLARE @EndOfWeek DATE = DATEADD(DAY, 8 - DATEPART(WEEKDAY, @Today), @Today);\n"
                + "\n"
                + "-- Kiểm tra nếu ngày hiện tại là Chủ Nhật thì lùi lại một tuần\n"
                + "IF DATEPART(WEEKDAY, @Today) = 1\n"
                + "    SET @StartOfWeek = DATEADD(DAY, -6, @StartOfWeek);\n"
                + "\n"
                + "SELECT SD.*, DS.WorkDate\n"
                + "FROM ScheduleDetail AS SD\n"
                + "JOIN WorkingSlot AS WS ON SD.SlotID = WS.id\n"
                + "JOIN DoctorSchedule AS DS ON SD.ScheduleID = DS.id\n"
                + "JOIN Doctor AS D ON DS.DoctorID = D.id\n"
                + "WHERE D.id = ?\n"
                + "AND DS.WorkDate >= @StartOfWeek\n"
                + "AND DS.WorkDate <= @EndOfWeek AND SD.isDelete = 0; ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ScheduleDetail s = new ScheduleDetail(String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getInt("ScheduleId")),
                        String.valueOf(rs.getInt("SlotId")),
                        rs.getString("SlotStatus"), String.valueOf(rs.getDate("workDate")), 0);
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("getScheduleOfDoctorByDoctorIdCurrentWeek: " + e);
        }
        return list;
    }

    public ArrayList<String> getDaysOfWeekInDateRange(String startDate, String endDate) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "DECLARE @StartOfWeek DATE = DATEADD(DAY, 1 - DATEPART(WEEKDAY, ?), ?);\n"
                + "IF DATEPART(WEEKDAY, ?) = 1\n"
                + "    SET @StartOfWeek = DATEADD(DAY, -6, @StartOfWeek);\n"
                + "SELECT DATEADD(DAY, number, @StartOfWeek) AS WeekDay\n"
                + "FROM master.dbo.spt_values\n"
                + "WHERE type = 'P' AND number BETWEEN 1 AND 7;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, startDate);
            st.setString(2, startDate);
            st.setString(3, startDate);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(String.valueOf(rs.getDate(1)));
            }
        } catch (SQLException e) {
            System.out.println("getDaysOfWeekInDateRange: " + e);
        }
        return list;
    }

    public boolean deleteScheduleDetailSlotById(String scheduleDetailId) {
        String sql = "UPDATE ScheduleDetail\n"
                + "SET isDelete = 1\n"
                + "WHERE id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(scheduleDetailId));
            st.execute();
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("deleteScheduleDetailSlotById: " + e);
        }
        return false;
    }

    public boolean updateStatusOfDoctorScheduleDetail(String branchId, String serviceId, String appointmentDate, String appointmentTime) {
        String sql = "UPDATE [dbo].[ScheduleDetail]\n"
                + "   SET [SlotStatus] = 0\n"
                + "   WHERE id = (SELECT SD.id FROM [hehe1].[dbo].[ScheduleDetail] AS SD\n"
                + "JOIN DoctorSchedule AS DS ON DS.id = SD.ScheduleID\n"
                + "WHERE ds.DoctorID = ? AND ds.WorkDate = ? AND SD.SlotID = (SELECT ScdDt.slotId\n"
                + "FROM Doctor AS d\n"
                + "JOIN DoctorService AS DS \n"
                + "ON DS.doctorId = d.id\n"
                + "JOIN DoctorSchedule AS DScd\n"
                + "ON d.id = DScd.DoctorID\n"
                + "JOIN ScheduleDetail AS ScdDt\n"
                + "ON DScd.id = ScdDt.ScheduleID\n"
                + "JOIN WorkingSlot AS WS\n"
                + "ON ScdDt.SlotID = WS.id\n"
                + "WHERE branchId = ? AND serviceId = ? AND WorkDate = ? AND startTime = ? AND d.isDelete = 0 AND d.id = ?))";

        return false;
    }

}
