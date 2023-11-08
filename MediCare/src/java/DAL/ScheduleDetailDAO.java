/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.ScheduleDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;

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

    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Định dạng ngày tháng
        return sdf.format(new Date());
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
//        String sql = "DECLARE @Today DATE = GETDATE();\n"
//                + "DECLARE @StartOfWeek DATE = DATEADD(DAY, 1 - DATEPART(WEEKDAY, @Today), @Today);\n"
//                + "\n"
//                + "IF DATEPART(WEEKDAY, @Today) = 1\n"
//                + "    SET @StartOfWeek = DATEADD(DAY, -6, @StartOfWeek);\n"
//                + "\n"
//                + "WITH Numbers AS (\n"
//                + "    SELECT 1 AS Number\n"
//                + "    UNION ALL\n"
//                + "    SELECT Number + 1\n"
//                + "    FROM Numbers\n"
//                + "    WHERE Number < 7\n"
//                + ")\n"
//                + "\n"
//                + "SELECT DATEADD(DAY, Number - 1, @StartOfWeek) AS WeekDay\n"
//                + "FROM Numbers\n"
//                + "OPTION (MAXRECURSION 0);";
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

    public boolean updateStatusOfDoctorScheduleDetail(String branchId, String serviceId, String appointmentDate, String appointmentTime, String doctorId, int status) {
        System.out.println("--- Start Update StatusOfDoctorScheduleDetail - status " + status + "---");
        String sql = "UPDATE SD\n"
                + "SET SD.[SlotStatus] = " + status + "\n"
                + "FROM [dbo].[ScheduleDetail] AS SD\n"
                + "JOIN [dbo].[DoctorSchedule] AS DS ON DS.id = SD.[ScheduleID]\n"
                + "WHERE DS.[DoctorID] = ? \n"
                + "AND DS.[WorkDate] = ? \n"
                + "AND SD.[SlotID] IN (SELECT ScdDt.[SlotID]\n"
                + "FROM [dbo].[Doctor] AS d\n"
                + "JOIN [dbo].[DoctorService] AS DS ON DS.[doctorId] = d.[id]\n"
                + "JOIN [dbo].[DoctorSchedule] AS DScd ON d.[id] = DScd.[DoctorID]\n"
                + "JOIN [dbo].[ScheduleDetail] AS ScdDt ON DScd.[id] = ScdDt.[ScheduleID]\n"
                + "JOIN [dbo].[WorkingSlot] AS WS ON ScdDt.[SlotID] = WS.[id]\n"
                + "WHERE [branchId] = ?\n"
                + "AND DS.[serviceId] = ?\n"
                + "AND DScd.[WorkDate] = ?\n"
                + "AND WS.[startTime] = ?\n"
                + "AND d.[isDelete] = 0\n"
                + "AND d.[id] = ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            st.setString(2, appointmentDate);
            st.setInt(3, Integer.parseInt(branchId));
            st.setInt(4, Integer.parseInt(serviceId));
            st.setString(5, appointmentDate);
            st.setString(6, appointmentTime);
            st.setString(7, doctorId);
            st.execute();
            System.out.println("updateStatusOfDoctorScheduleDetail: SUCCESS");
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("updateStatusOfDoctorScheduleDetail: FAIL");
            System.out.println("updateStatusOfDoctorScheduleDetail: " + e);
        }
        System.out.println("--- End Update StatusOfDoctorScheduleDetail ---");
        return false;
    }

    public int compareDates(String dateString1, String dateString2) {
        // Định dạng của chuỗi ngày
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            // Chuyển đổi chuỗi ngày thành đối tượng LocalDate
            LocalDate date1 = LocalDate.parse(dateString1, dateFormatter);
            LocalDate date2 = LocalDate.parse(dateString2, dateFormatter);

            // So sánh hai ngày
            if (date1.isBefore(date2)) {
                return -1; // Ngày 1 trước Ngày 2
            } else if (date1.isAfter(date2)) {
                return 1; // Ngày 1 sau Ngày 2
            } else {
                return 0; // Ngày 1 bằng Ngày 2
            }
        } catch (Exception e) {
            return -2; // Lỗi: Sai định dạng ngày
        }
    }

    public ArrayList<String> getLastestScheduleByBranchId(String branchId) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "DECLARE @branchid INT = ?\n"
                + "SELECT DS.WorkDate\n"
                + "FROM DoctorSchedule AS DS\n"
                + "JOIN Doctor AS D ON DS.DoctorID = D.id\n"
                + "JOIN Branch AS B ON D.branchId = B.id\n"
                + "WHERE B.id = @branchid AND DATEPART(YEAR, DS.WorkDate) = (SELECT DATEPART(YEAR, MAX(WorkDate)) FROM DoctorSchedule AS DS JOIN Doctor AS D On DS.DoctorID = D.id WHERE D.branchId = @branchid)\n"
                + "AND DATEPART(MONTH, DS.WorkDate) = (SELECT DATEPART(MONTH, MAX(WorkDate)) FROM DoctorSchedule AS DS JOIN Doctor AS D On DS.DoctorID = D.id WHERE D.branchId = @branchid)\n"
                + "GROUP BY DS.WorkDate\n"
                + "ORDER BY DS.WorkDate ASC\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(branchId));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(String.valueOf(rs.getDate("workdate")));
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("getLastestScheduleByBranchId: " + e);
        }
        return list;
    }

    public boolean addScheduleForMonthAndBranchId(String year, String month, String branchId, String monthSelect) {
        String sql = "-- Xác định tháng và năm cụ thể (tháng 1, 2024) và ID của branch (ví dụ: BranchID = 3)\n"
                + "DECLARE @TargetMonth INT = ?;\n"
                + "DECLARE @TargetYear INT = ?;\n"
                + "DECLARE @BranchID INT = ?;\n"
                + "DECLARE @NumberOfMonths INT = ?; -- Số tháng bạn muốn tạo lịch\n"
                + "-- Tạo biến để lưu trữ ngày bắt đầu và ngày kết thúc của tháng cụ thể\n"
                + "DECLARE @StartDatetmp DATE = DATEFROMPARTS(@TargetYear, @TargetMonth, 1);\n"
                + "DECLARE @StartDate DATE = DATEADD(MONTH, 1, @StartDatetmp);\n"
                + "\n"
                + "-- Tạo biến để lưu trữ ngày kết thúc của tháng cụ thể\n"
                + "DECLARE @EndDatetmp DATE = DATEADD(MONTH, @NumberOfMonths-1, @StartDate);\n"
                + "DECLARE @EndDate DATE = EOMONTH(@EndDatetmp);\n"
                + "\n"
                + "-- Kiểm tra xem đã có lịch cho tháng cụ thể và branch cụ thể chưa\n"
                + "IF NOT EXISTS (\n"
                + "    SELECT 1\n"
                + "    FROM DoctorSchedule DS\n"
                + "    JOIN Doctor D ON DS.DoctorID = D.id\n"
                + "    WHERE DS.WorkDate BETWEEN @StartDate AND @EndDate\n"
                + "    AND D.branchId = @BranchID\n"
                + ")\n"
                + "BEGIN\n"
                + "    -- Thêm DoctorSchedule cho tháng cụ thể và branch cụ thể\n"
                + "    INSERT INTO DoctorSchedule (DoctorID, WorkDate)\n"
                + "    SELECT DISTINCT D.id, Dates.Date\n"
                + "    FROM Doctor D\n"
                + "    CROSS APPLY (\n"
                + "        SELECT DATEADD(DAY, number, @StartDate) AS Date\n"
                + "        FROM master.dbo.spt_values\n"
                + "        WHERE type = 'P'\n"
                + "            AND DATEADD(DAY, number, @StartDate) <= @EndDate\n"
                + "    ) AS Dates\n"
                + "    WHERE D.branchId = @BranchID;\n"
                + "\n"
                + "    -- Thêm ScheduleDetail cho DoctorSchedule và slot làm việc (nếu cần)\n"
                + "    INSERT INTO ScheduleDetail (ScheduleID, SlotID, SlotStatus, isDelete)\n"
                + "    SELECT DS.id, WS.id, '1', 0\n"
                + "    FROM DoctorSchedule DS\n"
                + "    JOIN WorkingSlot WS\n"
                + "    ON DS.WorkDate BETWEEN @StartDate AND @EndDate;\n"
                + "END";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(month));
            st.setInt(2, Integer.parseInt(year));
            st.setInt(3, Integer.parseInt(branchId));
            st.setInt(4, Integer.parseInt(monthSelect));
            st.execute();
            return true;
        } catch (NumberFormatException | SQLException e) {
            System.out.println("addScheduleForMonthAndBranchId: " + e);
        }
        return false;
    }

    public boolean setDayOffForDoctorByEvent(String fromDate, String toDate, String branchId) {
        String sql = "DECLARE @startDate DATE = ?; \n"
                + "DECLARE @endDate DATE = ?;   \n"
                + "DECLARE @branchID INT = ?; \n"
                + "\n"
                + "UPDATE ScheduleDetail\n"
                + "SET isDelete = 1 \n"
                + "WHERE ScheduleID IN (\n"
                + "    SELECT DS.id\n"
                + "    FROM DoctorSchedule DS\n"
                + "    WHERE DS.WorkDate BETWEEN @startDate AND @endDate\n"
                + ")";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fromDate);
            st.setString(2, toDate);
            st.setInt(3, Integer.parseInt(branchId));
            st.execute();
            return true;
        } catch (NumberFormatException | SQLException e) {
            System.out.println("setDayOffForDoctorByEvent: " + e);
        }
        return false;
    }

    public static void main(String[] args) {
        ScheduleDetailDAO sdd = new ScheduleDetailDAO();
        String currentDate = sdd.getCurrentDate();
        System.out.println("current date: " + currentDate);
        System.out.println(sdd.compareDates("2023-10-05", "2023-10-05"));

        ArrayList<String> list = sdd.getLastestScheduleByBranchId("1");
        System.out.println("List: " + list);
        for (String string : list) {
            System.out.println(string);
        }
    }

}
