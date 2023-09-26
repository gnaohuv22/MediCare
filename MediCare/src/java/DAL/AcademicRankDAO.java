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

/**
 *
 * @author tubinh
 */
public class AcademicRankDAO extends DBContext {

    public ArrayList<AcademicRank> getAllAcademicRanks() {
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

    public static void main(String[] args) {
        AcademicRankDAO ad = new AcademicRankDAO();
        ArrayList<AcademicRank> list = ad.getAllAcademicRanks();
        for (AcademicRank academicRank : list) {
            System.out.println(academicRank);
        }
    }
}
