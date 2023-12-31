/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.AcademicRank;
import Models.WorkingSlot;

/**
 *
 * @author hoang
 */
public class AcademicRankDAO extends DBContext {

    public ArrayList<AcademicRank> getListAcademicRank() {
        ArrayList<AcademicRank> list = new ArrayList<>();
        String SQL = "SELECT * FROM [AcademicRank]";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AcademicRank ar = new AcademicRank(String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        String.valueOf(rs.getFloat(3)));
                list.add(ar);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListAcademicRank: " + e.getMessage());
        }
        return null;
    }
}
