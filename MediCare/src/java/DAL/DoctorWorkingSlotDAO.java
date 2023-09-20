/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.DoctorWorkingDay;
import Models.DoctorWorkingSlot;

/**
 *
 * @author hoang
 */
public class DoctorWorkingSlotDAO extends DBContext {
    public ArrayList<DoctorWorkingSlot> getDoctorWorkingSlotList() {
        String SQL = "SELECT * FROM [DoctorWorkingSlot]";
        ArrayList<DoctorWorkingSlot> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                DoctorWorkingSlot dws = new DoctorWorkingSlot(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2));
                list.add(dws);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getDoctorWorkingSlotList: " + e.getMessage());
        }
        return list;
    }
}
