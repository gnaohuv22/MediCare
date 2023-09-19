/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.EmployeeRole;

/**
 *
 * @author hoang
 */
public class EmployeeRoleDAO extends DBContext {
    public ArrayList<EmployeeRole> getEmployeeRoleList() {
        String SQL = "SELECT * FROM [EmployeeRole]";
        ArrayList<EmployeeRole> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                EmployeeRole er = new EmployeeRole(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2));
                list.add(er);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getEmployeeRoleList: " + e.getMessage());
        }
        return list;
    }
}
