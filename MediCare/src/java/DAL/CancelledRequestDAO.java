/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.CancelledRequest;

/**
 *
 * @author hoang
 */
public class CancelledRequestDAO extends DBContext {
    public ArrayList<CancelledRequest> getListCancelledRequest() {
        ArrayList<CancelledRequest> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Branch]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CancelledRequest cr = new CancelledRequest(
                        String.valueOf(rs.getInt(1)), 
                        String.valueOf(rs.getInt(2)), 
                        String.valueOf(rs.getFloat(3)), 
                        rs.getString(4), 
                        rs.getString(5), 
                        String.valueOf(rs.getDate(6)), 
                        String.valueOf(rs.getInt(7)));
                list.add(cr);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListCancelledRequest: " + e.getMessage());
        } 
        return null;
    }
}
