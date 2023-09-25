/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.NavigationItem;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class NavigationItemDAO extends DBContext {
    public ArrayList<NavigationItem> getListNavigationItem() {
        String SQL = "SELECT * FROM [NavigationItem]";
        ArrayList<NavigationItem> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new NavigationItem(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2), 
                        rs.getString(3))
                );
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListNavigationItem: " + e.getMessage());
        }
        return null;
    }
}
