/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAL;

import Models.Branch;
import Models.Holiday;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class HolidayDAO extends DBContext{
    public ArrayList<Holiday> getAllHolidays() {
        ArrayList<Holiday> list = new ArrayList<>();
        String sql = "SELECT * FROM Holiday";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Holiday(rs.getInt("id")+"", rs.getString("name"), 
                        String.valueOf(rs.getDate("fromDate")), 
                        String.valueOf(rs.getDate("toDate")), 
                        rs.getString("createdAt"), 
                        rs.getInt("createdBy")+"", 
                        rs.getString("modifyAt"), 
                        rs.getInt("modifyBy")+""));
            }
        } catch (SQLException e) {
            System.out.println("getAllHolidays: " + e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        HolidayDAO hd= new HolidayDAO();
        ArrayList<Holiday> list = hd.getAllHolidays();
        for (Holiday holiday : list) {
            System.out.println(holiday);
        }
    }
}
