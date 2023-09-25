/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Banner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author hoang
 */
public class BannerDAO extends DBContext{
    public ArrayList<Banner> getAllBanner() {
        String SQL = "SELECT * FROM [Banner]";
        ArrayList<Banner> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Banner b = new Banner(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4)
                );
                list.add(b);
            }
        } catch (SQLException e) {
            System.out.println("getAllBanner: " + e.getMessage());
        }
        return list;
    }
    
//    public static void main(String[] args) {
//        BannerDAO bd = new BannerDAO();
//        ArrayList<Banner> list = bd.getAllBanner();
//        if (list == null || list.isEmpty()) System.out.println("null");
//        for (Banner b : list) {
//            System.out.println(b.toString());
//        }
//    }
}
