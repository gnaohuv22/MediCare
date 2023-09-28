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
public class SocialNetworksDAO extends DBContext {
    public ArrayList<SocialNetworks> getListSocialNetworks() {
        String SQL = "SELECT * FROM [SocialNetworks]";
        ArrayList<SocialNetworks> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                SocialNetworks sn = new SocialNetworks(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2), 
                        rs.getString(3),
                        rs.getString(4)
                );
                list.add(sn);
            }
        } catch (SQLException e) {
            System.out.println("getListSocialNetworks: " + e.getMessage());
        }
        return list;
    }
}
