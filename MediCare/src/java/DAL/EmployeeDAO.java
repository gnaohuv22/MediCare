/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.DoctorWorkingSlot;
import Models.Employee;

/**
 *
 * @author hoang
 */
public class EmployeeDAO extends DBContext {
    public ArrayList<Employee> getEmployeeList() {
        String SQL = "SELECT * FROM [Employee]";
        ArrayList<Employee> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Employee e = new Employee(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)),
                        rs.getString(5),
                        String.valueOf(rs.getDate(6)),
                        String.valueOf(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9),
                        String.valueOf(rs.getInt(10)),
                        rs.getString(11),
                        rs.getString(12),
                        String.valueOf(rs.getInt(13)),
                        String.valueOf(rs.getDate(14)));
                list.add(e);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getEmployeeList: " + e.getMessage());
        }
        return list;
    }
}
