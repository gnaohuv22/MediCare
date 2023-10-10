/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.ScheduleDetail;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                + "WHERE DS.doctorId = ? AND DS.WorkDate = ?";

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
                + "WHERE b.id = ? AND DSv.serviceId = ? AND DS.WorkDate = ?\n AND SD.slotStatus = '1'"
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
                + "WHERE d.id = ? AND DSv.serviceId = ? AND DS.WorkDate = ?\n AND SD.slotStatus = '1'"
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

}
