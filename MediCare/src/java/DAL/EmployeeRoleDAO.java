/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.EmployeeRole;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class EmployeeRoleDAO extends DBContext{
    public ArrayList<EmployeeRole> getAllEmployeeRole() {
        ArrayList<EmployeeRole> list = new ArrayList<>();
        String SQL = "SELECT id,role FROM EmployeeRole";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String role = rs.getString(2);
                EmployeeRole obj = new EmployeeRole(id,role);
                list.add(obj);
            }
        } catch (Exception e) {
            System.out.println("getAllEmployeeRole " + e.getMessage());
        }
        return list;
    }
    public String getRoleIdByName(String role) {
        String SQL = "SELECT id FROM EmployeeRole WHERE role = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,role);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString("id");
                return id;
            }
        }catch (Exception e) {
            System.out.println("getRoleIdByName " + e.getMessage());
        }
        return "";
    }
    public EmployeeRole getEmployeeRoleById(String id) {
        String SQL = "SELECT role FROM EmployeeRole WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String name = rs.getString(2);
                EmployeeRole obj = new EmployeeRole(id,name);
                return obj;
            }
        }catch (Exception e) {
            System.out.println("getEmployeeRoleById " + e.getMessage());
        }
        return null;
    }
}
