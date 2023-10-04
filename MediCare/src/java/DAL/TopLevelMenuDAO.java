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
    public ArrayList<TopLevelMenu> getListTopLevelMenu() {
        String SQL = "SELECT * FROM [TopLevelMenu]";
        ArrayList<TopLevelMenu> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new TopLevelMenu(String.valueOf(rs.getInt(1)), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println("getListTopLevelMenu: " + e.getMessage());
        }
        return list;
    }
}
