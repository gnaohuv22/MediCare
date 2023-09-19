/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Department;
import model.Doctor;

/**
 *
 * @author hoang
 */
public class DoctorDAO extends DBContext {
    public ArrayList<Doctor> getListCurriculumVitae() {
        ArrayList<Doctor> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Doctor]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        String.valueOf(rs.getInt(7)),
                        String.valueOf(rs.getFloat(8)),
                        rs.getString(9),
                        rs.getString(10),
                        String.valueOf(rs.getBoolean(11))
                    );
                list.add(d);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListDoctor: " + e.getMessage());
        } 
        return null;
    }
}
