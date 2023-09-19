/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;
import model.WorkingSlot;

/**
 *
 * @author hoang
 */
public class WorkingSlotDAO extends DBContext {
    public ArrayList<WorkingSlot> getListWorkingSlot() {
        ArrayList<WorkingSlot> list = new ArrayList<>();
        String SQL = "SELECT * FROM [WorkingSlot]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                WorkingSlot ws = new WorkingSlot(String.valueOf(rs.getInt(1)),
                                                String.valueOf(rs.getDate(2)),
                                                String.valueOf(rs.getDate(3)));
                list.add(ws);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListWorkingSlot: " + e.getMessage());
        } 
        return null;
    }
}
