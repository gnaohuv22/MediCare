/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DoctorWorkingDay;

/**
 *
 * @author hoang
 */
public class DoctorWorkingDayDAO extends DBContext {
    public ArrayList<DoctorWorkingDay> getDoctorWorkingDayList() {
        String SQL = "SELECT * FROM [DoctorWorkingDay]";
        ArrayList<DoctorWorkingDay> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                DoctorWorkingDay dwd = new DoctorWorkingDay(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2));
                list.add(dwd);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getDoctorWorkingDayList: " + e.getMessage());
        }
        return list;
    }
}
