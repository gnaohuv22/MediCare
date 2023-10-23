/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Reviews;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class ReviewsDAO extends DBContext {

    public ArrayList<Reviews> getReviewsByDoctorId(String doctorId) {
        ArrayList<Reviews> list = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[userId]\n"
                + "      ,[doctorId]\n"
                + "      ,[appointmentId]\n"
                + "      ,[rating]\n"
                + "      ,[reviewContent]\n"
                + "      ,[createdAt]\n"
                + "  FROM [dbo].[Reviews]\n"
                + "\n"
                + "  WHERE doctorId = ?\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reviews r = new Reviews(String.valueOf(rs.getInt("id")),
                        rs.getString("userId"),
                        rs.getString("doctorId"),
                        String.valueOf(rs.getInt("appointmentId")),
                        String.valueOf(rs.getFloat("rating")),
                        rs.getString("reviewContent"),
                        String.valueOf(rs.getDate("createdAt")));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println("getReviewsByDoctorId: " + e);
        }
        return list;
    }

    public int numberOfReviews(String doctorId) {
        int count = 0;
        String sql = "SELECT COUNT(id) AS numberOfReviews\n"
                + "FROM [dbo].[Reviews]\n"
                + "WHERE doctorId = ?  AND reviewContent IS NOT NULL";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = Integer.parseInt(rs.getString("numberOfReviews"));
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("numberOfReviews: " + e);
        }
        return count;
    }
    public int numberOfRatings(String doctorId) {
        int count = 0;
        String sql = "SELECT COUNT(id) AS numberOfRatings\n"
                + "FROM [dbo].[Reviews]\n"
                + "WHERE doctorId = ?  AND rating IS NOT NULL";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                count = Integer.parseInt(rs.getString("numberOfRatings"));
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("numberOfRatings: " + e);
        }
        return count;
    }
    
    public ArrayList<Reviews> getTopReviews() {
        ArrayList<Reviews> list = new ArrayList<>();
        String sql = "SELECT TOP(3) * FROM [dbo].[Reviews]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Reviews r = new Reviews(String.valueOf(rs.getInt("id")),
                        rs.getString("userId"),
                        rs.getString("doctorId"),
                        String.valueOf(rs.getInt("appointmentId")),
                        String.valueOf(rs.getFloat("rating")),
                        rs.getString("reviewContent"),
                        String.valueOf(rs.getDate("createdAt")));
                list.add(r);
            }
            return list;
        } catch (NumberFormatException | SQLException e) {
            System.out.println("getTopReviews: " + e);
        }
        return null;
    }

    public static void main(String[] args) {
        ReviewsDAO rd = new ReviewsDAO();
        ArrayList<Reviews> list = rd.getTopReviews();
//        int count = rd.numberOfReviews("1");
//        System.out.println("count: " + count);
        for (Reviews reviews : list) {
            System.out.println(reviews.toString());
        }
    }
}
