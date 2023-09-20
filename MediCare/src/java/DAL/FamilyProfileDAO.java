/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.FamilyProfile;

/**
 *
 * @author hoang
 */
public class FamilyProfileDAO extends DBContext{
    public ArrayList<FamilyProfile> getFamilyProfileList() {
        String SQL = "SELECT * FROM [FamilyProfile]";
        ArrayList<FamilyProfile> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                FamilyProfile fp = new FamilyProfile(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getDate(4)),
                        String.valueOf(rs.getInt(5)),
                        rs.getString(6),
                        String.valueOf(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        String.valueOf(rs.getDate(13)),
                        String.valueOf(rs.getInt(14)));
                list.add(fp);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getFamilyProfileList: " + e.getMessage());
        }
        return list;
    }
}
