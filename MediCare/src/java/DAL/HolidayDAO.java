/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Branch;
import Models.Employee;
import Models.Holiday;
import jakarta.servlet.jsp.jstl.sql.Result;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author tubinh
 */
public class HolidayDAO extends DBContext {

    public ArrayList<Holiday> getAllHolidays() {
        ArrayList<Holiday> list = new ArrayList<>();
        String sql = "SELECT * FROM Holiday";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Holiday(rs.getInt("id") + "", rs.getString("name"),
                        String.valueOf(rs.getDate("fromDate")),
                        String.valueOf(rs.getDate("toDate")),
                        rs.getString("createdAt"),
                        rs.getInt("createdBy") + "",
                        rs.getString("modifyAt"),
                        rs.getInt("modifyBy") + ""));
            }
        } catch (SQLException e) {
            System.out.println("getAllHolidays: " + e);
        }
        return list;
    }

    public boolean addEvent(String name, String fromDate, String toDate, String branchId, Employee employee) {
        String sql = "INSERT INTO [dbo].[Holiday]\n"
                + "           ([name]\n"
                + "           ,[fromDate]\n"
                + "           ,[toDate]\n"
                + "           ,[createdAt]\n"
                + "           ,[createdBy]\n"
                + "           ,[modifyAt]\n"
                + "           ,[modifyBy]\n"
                + "           ,[branchId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?,?)";

        Date currentDate = new Date();
        java.sql.Timestamp createdAt = new java.sql.Timestamp(currentDate.getTime());
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, name);
            st.setString(2, fromDate);
            st.setString(3, toDate);
            st.setString(4, createdAt.toString());
            st.setInt(5, Integer.parseInt(employee.getId()));
            st.setString(6, createdAt.toString());
            st.setInt(7, Integer.parseInt(employee.getId()));
            st.setInt(8, Integer.parseInt(branchId));
            st.execute();
            return true;
        } catch (NumberFormatException | SQLException e) {
            System.out.println("addEvent: " + e);
        }
        return false;
    }

    public Holiday getEventByBranchNameFromTo(String branchId, String eventName, String fromDate, String toDate) {
        String sql = "SELECT * FROM Holiday WHERE branchId = ? AND fromDate = ? AND toDate = ? AND [name] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(branchId));
            st.setString(2, fromDate);
            st.setString(3, toDate);
            st.setString(4, eventName);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Holiday("", "", "", "", "", "", "", rs.getInt("modifyBy")+"", null);
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("getEventByBranchNameFromTo: " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        HolidayDAO hd = new HolidayDAO();
        ArrayList<Holiday> list = hd.getAllHolidays();
        for (Holiday holiday : list) {
            System.out.println(holiday);
        }
    }

}
