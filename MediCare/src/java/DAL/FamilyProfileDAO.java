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
import java.util.List;

/**
 *
 * @author hoang
 */
public class FamilyProfileDAO extends DBContext {

    public ArrayList<FamilyProfile> getFamilyProfileList() {
        String SQL = "SELECT * FROM [FamilyProfile]";
        ArrayList<FamilyProfile> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gender = "Male";
                if (rs.getInt(5) == 1) {
                    gender = "Female";
                }
                FamilyProfile fp = new FamilyProfile(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getDate(4)),
                        gender,
                        rs.getString(6),
                        String.valueOf(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        String.valueOf(rs.getDate(13)),
                        String.valueOf(rs.getInt(14)),
                        rs.getString(15));
                list.add(fp);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getFamilyProfileList: " + e.getMessage());
        }
        return list;
    }

    public List<FamilyProfile> getFamilyProfileListByUserOwnerId(int idByEmail) {
        String SQL = "SELECT * FROM [FamilyProfile] where ownerid=?";
        ArrayList<FamilyProfile> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, String.valueOf(idByEmail));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gender = "Male";
                if (rs.getInt(5) == 1) {
                    gender = "Female";
                }
                FamilyProfile fp = new FamilyProfile(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getDate(4)),
                        gender,
                        rs.getString(6),
                        String.valueOf(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        String.valueOf(rs.getDate(13)),
                        String.valueOf(rs.getInt(14)),
                        rs.getString(15));
                System.out.println(fp.toString());
                list.add(fp);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileListByUserId: " + e.getMessage());
        }
        return null;
    }

    public List<FamilyProfile> getFamilyProfileListByUserName(String name, int ownerId) {
        String SQL = "SELECT * FROM [FamilyProfile] where ownerid=?";
        ArrayList<FamilyProfile> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, String.valueOf(ownerId));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gender = "Male";
                if (rs.getInt(5) == 1) {
                    gender = "Female";
                }

                if (rs.getString(3).contains(name)) {
                    FamilyProfile fp = new FamilyProfile(
                            String.valueOf(rs.getInt(1)),
                            rs.getString(2),
                            rs.getString(3),
                            String.valueOf(rs.getDate(4)),
                            gender,
                            rs.getString(6),
                            String.valueOf(rs.getInt(7)),
                            rs.getString(8),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11),
                            rs.getString(12),
                            String.valueOf(rs.getDate(13)),
                            String.valueOf(rs.getInt(14)),
                            rs.getString(15));
                    System.out.println(fp.toString());
                    list.add(fp);
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileListByUserName: " + e.getMessage());
        }
        return null;
    }

    public FamilyProfile getFamilyProfileById(String id, int ownerId) {
        String SQL = "SELECT * FROM [FamilyProfile] where ownerid=? and profileId=?";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, String.valueOf(ownerId));
            ps.setInt(2, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String gender = "Male";
                if (rs.getInt(5) == 1) {
                    gender = "Female";
                }
                FamilyProfile fp = new FamilyProfile(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getDate(4)),
                        gender,
                        rs.getString(6),
                        String.valueOf(rs.getInt(7)),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        String.valueOf(rs.getDate(13)),
                        String.valueOf(rs.getInt(14)),
                        rs.getString(15));
                System.out.println("getFamilyProfileById" + fp.toString());
                return fp;
            }
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileListByUserName: " + e.getMessage());
        }
        return null;
    }
}
