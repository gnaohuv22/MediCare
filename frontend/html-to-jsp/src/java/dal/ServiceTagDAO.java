/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ServiceTag;

/**
 *
 * @author hoang
 */
public class ServiceTagDAO extends DBContext {
    public ArrayList<ServiceTag> getServiceTagList() {
        String SQL = "SELECT * FROM [ServiceTag]";
        ArrayList<ServiceTag> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ServiceTag r = new ServiceTag(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4))
                );
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getServiceTagList: " + e.getMessage());
        }
        return list;
    }
}
