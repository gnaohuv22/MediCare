/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class TopLevelMenuDAO extends DBContext {
    public ArrayList<TopLevelMenu> getTopLevelMenu() {
        ArrayList<TopLevelMenu> list = new ArrayList<>();
        String SQL = "SELECT * FROM [TopLevelMenu]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                TopLevelMenu tlm = new TopLevelMenu(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString(2));
                list.add(tlm);
            }
        } catch (SQLException e) {
            System.out.println("getTopLevelMenu: " + e.getMessage());
        }
        return list;
    }
}
