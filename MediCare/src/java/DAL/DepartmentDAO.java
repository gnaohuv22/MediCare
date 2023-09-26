/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Department;

/**
 *
 * @author tubinh
 */
public class DepartmentDAO extends DBContext {

    public ArrayList<Department> getAllDepartments() {
        ArrayList<Department> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Department]";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
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

    public static void main(String[] args) {
        DepartmentDAO dd = new DepartmentDAO();
        ArrayList<Department> list = dd.getAllDepartments();
        for (Department department : list) {
            System.out.println(department);
        }
    }
}
