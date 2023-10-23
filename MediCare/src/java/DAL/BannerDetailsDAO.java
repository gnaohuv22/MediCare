/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Banner;
import Models.BannerDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class BannerDetailsDAO extends DBContext {
    public ArrayList<BannerDetails> getAllBannerDetails() {
        String SQL = "SELECT * FROM [BannerDetails]";
        ArrayList<BannerDetails> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                BannerDetails bd = new BannerDetails(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2), 
                        rs.getString(3), 
                        rs.getString(4)
                );
                list.add(bd);
            }
        } catch (SQLException e) {
            System.out.println("getAllBannerDetails: " + e.getMessage());
        }
        return list;
    }
    
//    public static void main(String[] args) {
//        BannerDetailsDAO bd = new BannerDetailsDAO();
//        ArrayList<BannerDetails> list = bd.getAllBannerDetails();
//        for (BannerDetails b : list) {
//            System.out.println(b.toString());
//        }
//    }
}
