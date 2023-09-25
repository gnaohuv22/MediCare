/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Reviews;

/**
 *
 * @author hoang
 */
public class ReviewsDAO extends DBContext {
    public ArrayList<Reviews> getReviewsList() {
        String SQL = "SELECT * FROM [Reviews]";
        ArrayList<Reviews> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Reviews r = new Reviews(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)),
                        String.valueOf(rs.getFloat(5)),
                        rs.getString(6),
                        String.valueOf(rs.getDate(7))
                );
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getReviewsList: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<Reviews> getTopReviews() {
        String SQL = "SELECT TOP(3) * FROM [Reviews]";
        ArrayList<Reviews> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Reviews r = new Reviews(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)),
                        String.valueOf(rs.getFloat(5)),
                        rs.getString(6),
                        String.valueOf(rs.getDate(7))
                );
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getTopReviews: " + e.getMessage());
        }
        return list;
    }
}
