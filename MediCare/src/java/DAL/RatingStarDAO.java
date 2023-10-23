/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.RatingStar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class RatingStarDAO extends DBContext {

    public ArrayList<RatingStar> getRatingStarInfoByDoctorId(String doctorId) {
        ArrayList<RatingStar> list = new ArrayList<>();
        String sql = "SELECT \n"
                + "    SUM(CASE WHEN rating = ?  THEN 1 ELSE 0 END) AS quantity,\n"
                + "    100.0 * SUM(CASE WHEN rating = ? THEN 1 ELSE 0 END) / NULLIF(SUM(CASE WHEN rating IS NOT NULL THEN 1 ELSE 0 END), 0) AS [percent]\n"
                + "FROM \n"
                + "    Reviews\n"
                + "WHERE \n"
                + "    doctorId = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            for (int i = 5; i > 0; i--) {
                st.setFloat(1, i);
                st.setFloat(2, i);
                st.setString(3, doctorId);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    RatingStar r = new RatingStar(i + "", rs.getInt("quantity") + "", rs.getFloat("percent") + "");
                    list.add(r);
                }
            }
        } catch (SQLException e) {
            System.out.println("getRatingStarInfoByDoctorId: " + e);
        }
        return list;
    }

    public double overallRating(ArrayList<RatingStar> list) {
        if (!list.isEmpty()) {
            int sum1 = 0;
            int sum2 = 0;
            for (RatingStar r : list) {
                sum1 += (Integer.parseInt(r.getId()) * Integer.parseInt(r.getQuantity()));
                sum2 += (5 * Integer.parseInt(r.getQuantity()));
            }
            return (double) sum1 * 5 / sum2;
        }
        return 0;
    }

    public static void main(String[] args) {
        RatingStarDAO rsd = new RatingStarDAO();
        ArrayList<RatingStar> ratingstars = rsd.getRatingStarInfoByDoctorId("1");
        for (RatingStar ratingstar : ratingstars) {
            System.out.println(ratingstar);
        }

        double rating = rsd.overallRating(ratingstars);
        System.out.println("Rating: " + rating);
    }
}
