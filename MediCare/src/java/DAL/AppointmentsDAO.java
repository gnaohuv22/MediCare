/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Appointments;

/**
 *
 * @author hoang
 */
public class AppointmentsDAO extends DBContext {
    public ArrayList<Appointments> getListAppointments() {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Appointments]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointments a = new Appointments(
                    String.valueOf(rs.getInt(1)),
                    rs.getString(2),
                    rs.getString(3),
                    String.valueOf(rs.getInt(4)),
                    String.valueOf(rs.getDate(5)),
                    String.valueOf(rs.getInt(6)));
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListAppointments: " + e.getMessage());
        } 
        return null;
    }
}
