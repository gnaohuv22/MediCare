/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.CurriculumVitae;
import model.Department;

/**
 *
 * @author hoang
 */
public class DepartmentDAO extends DBContext{
    public ArrayList<Department> getListDepartment() {
        ArrayList<Department> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Department]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Department d = new Department(String.valueOf(rs.getInt(1)),
                                                rs.getString(2),
                                                rs.getString(3)
                                            );
                list.add(d);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListDepartment: " + e.getMessage());
        } 
        return null;
    }
}
