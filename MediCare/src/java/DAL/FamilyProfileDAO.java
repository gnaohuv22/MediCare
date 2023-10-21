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
import Models.Relationship;
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
                RelationshipDAO rd = new RelationshipDAO();
                Relationship r = rd.getRelationshipByRelationshipId(String.valueOf(rs.getInt("relationId")));
                String[] names = rs.getString(3).split(" ");
                String lastName = names[names.length - 1];
                FamilyProfile fp = new FamilyProfile(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        //                        rs.getString(3),
                        lastName,
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
                        rs.getString(15),
                        r);
                list.add(fp);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileList: " + e.getMessage());
        }
        return list;
    }

    public List<FamilyProfile> getFamilyProfileListByUserOwnerId(String idByEmail) {
        String SQL = "SELECT * FROM [FamilyProfile] where ownerid=? ORDER BY relationId";
        ArrayList<FamilyProfile> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, idByEmail);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gender = "Male";
                if (Integer.parseInt(rs.getString("gender")) == 1) {
                    gender = "Female";
                }
                RelationshipDAO rd = new RelationshipDAO();
                Relationship r = rd.getRelationshipByRelationshipId(String.valueOf(rs.getInt("relationId")));
                String[] names = rs.getString(3).split(" ");
                String lastName = names[names.length - 1];
                FamilyProfile fp = new FamilyProfile(
                        String.valueOf(rs.getInt("profileId")),
                        rs.getString("email"),
//                        rs.getString(3),
                        lastName,
                        String.valueOf(rs.getDate("birthDate")),
                        gender,
                        rs.getString("address"),
                        rs.getString("identity"),
                        rs.getString("medicalId"),
                        rs.getString("ethnic"),
                        rs.getString("phone"),
                        rs.getString("profilePicture"),
                        rs.getString("ownerId"),
                        r);
                System.out.println(fp.toString());
                list.add(fp);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileListByUserId: " + e.getMessage());
        }
        return null;
    }

    public List<FamilyProfile> getFamilyProfileListByUserOwnerIdForBooking(String idByEmail) {
        String SQL = "SELECT * FROM [FamilyProfile] where ownerid=? AND relationId IS NOT NULL ORDER BY relationId";
        ArrayList<FamilyProfile> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, String.valueOf(idByEmail));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gender = "Male";
                if (rs.getInt(5) == 1) {
                    gender = "Female";
                }
                RelationshipDAO rd = new RelationshipDAO();
                String[] names = rs.getString(3).split(" ");
                String lastName = names[names.length - 1];
                FamilyProfile fp = new FamilyProfile(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        //                        rs.getString(3),
                        lastName,
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
                        rs.getString(15),
                        rd.getRelationshipByRelationshipId(String.valueOf(rs.getInt(14))));
                System.out.println(fp.toString());
                list.add(fp);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileListByUserOwnerIdForBooking: " + e.getMessage());
        }
        return null;
    }

    public List<FamilyProfile> getFamilyProfileListByUserName(String name, String ownerId) {
        String SQL = "SELECT * FROM [FamilyProfile] where ownerid=?";
        ArrayList<FamilyProfile> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gender = "Male";
                if (rs.getInt(5) == 1) {
                    gender = "Female";
                }

                if (rs.getString(3).toLowerCase().contains(name.toLowerCase())) {
                    RelationshipDAO rd = new RelationshipDAO();
                    Relationship r = rd.getRelationshipByRelationshipId(String.valueOf(rs.getInt("relationId")));
                    String[] names = rs.getString(3).split(" ");
                    String lastName = names[names.length - 1];
                    FamilyProfile fp = new FamilyProfile(
                            String.valueOf(rs.getInt(1)),
                            rs.getString(2),
                            //                        rs.getString(3),
                            lastName,
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
                            rs.getString(15),
                            r);
                    list.add(fp);
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileListByUserName: " + e.getMessage());
        }
        return null;
    }

    public FamilyProfile getFamilyProfileById(String id, String ownerId) {
        String SQL = "SELECT * FROM [FamilyProfile] where ownerid=? and profileId=?";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ownerId);
            ps.setInt(2, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String gender = "Male";
                if (rs.getInt(5) == 1) {
                    gender = "Female";
                }
                RelationshipDAO rd = new RelationshipDAO();
                Relationship r = rd.getRelationshipByRelationshipId(String.valueOf(rs.getInt("relationId")));
                String[] names = rs.getString(3).split(" ");
                String lastName = names[names.length - 1];
                FamilyProfile fp = new FamilyProfile(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        //                        rs.getString(3),
                        lastName,
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
                        rs.getString(15),
                        r);
                System.out.println("getFamilyProfileById" + fp.toString());
                return fp;
            }
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileById: " + e.getMessage());
        }
        return null;
    }

    public FamilyProfile getFamilyProfileByProfileId(String profileId) {
        String SQL = "SELECT * FROM [FamilyProfile] where profileId=?";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, Integer.parseInt(profileId));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String gender = "Male";
                if (rs.getInt(5) == 1) {
                    gender = "Female";
                }
                RelationshipDAO rd = new RelationshipDAO();
                Relationship r = rd.getRelationshipByRelationshipId(String.valueOf(rs.getInt("relationId")));
                String[] names = rs.getString(3).split(" ");
                String lastName = names[names.length - 1];
                FamilyProfile fp = new FamilyProfile(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        //                        rs.getString(3),
                        lastName,
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
                        rs.getString(15),
                        r);
                System.out.println("getFamilyProfileById" + fp.toString());
                return fp;
            }
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileByProfileId: " + e.getMessage());
        }
        return null;
    }

    public FamilyProfile getFamilyProfileByInfo(String patientName, String gender, String birthDate, String phone, String emailPatient) {
        String sql = "SELECT * FROM FamilyProfile\n"
                + "WHERE email = ? AND birthDate = ? AND gender = ? AND [name] =? AND phone = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, emailPatient);
            st.setString(2, birthDate);
            st.setString(3, gender);
            st.setString(4, patientName);
            st.setString(5, phone);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new FamilyProfile(String.valueOf(rs.getInt("profileId")));
            }
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileByInfo-guest: " + e);
        }
        return null;
    }

    public FamilyProfile getFamilyProfileByInfoGuest(String patientName, String gender, String birthDate, String phone, String emailPatient, String ownerId) {
        String sql = "SELECT * FROM FamilyProfile\n"
                + "WHERE email = ? AND birthDate = ? AND gender = ? AND [name] =? AND phone = ? AND ownerId = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, emailPatient);
            st.setString(2, birthDate);
            st.setString(3, gender);
            st.setString(4, patientName);
            st.setString(5, phone);
            st.setString(6, ownerId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new FamilyProfile(String.valueOf(rs.getInt("profileId")));
            }
        } catch (SQLException e) {
            System.out.println("getFamilyProfileByInfo-guest: " + e);
        }
        return null;
    }

    public FamilyProfile getFamilyProfileByInfoUser(String patientName, String gender, String birthDate, String phone, String emailPatient, String ownerId) {
        String sql = "SELECT * FROM FamilyProfile\n"
                + "WHERE email = ? AND birthDate = ? AND gender = ? AND [name] =? AND phone = ? AND ownerId = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, emailPatient);
            st.setString(2, birthDate);
            st.setString(3, gender);
            st.setString(4, patientName);
            st.setString(5, phone);
            st.setString(6, ownerId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new FamilyProfile(String.valueOf(rs.getInt("profileId")));
            }
        } catch (SQLException e) {
            System.out.println("getFamilyProfileByInfo-user: " + e);
        }
        return null;
    }

    public boolean addNewProfile(String patientName, String gender, String birthDate, String phone, String emailPatient, String ownerId) {
        String sql = "INSERT INTO  [dbo].[FamilyProfile]\n"
                + "           ([email]\n"
                + "           ,[name]\n"
                + "           ,[birthDate]\n"
                + "           ,[gender]\n"
                + "           ,[phone]\n"
                + "           ,[createdAt]\n"
                + "		   ,[ownerId])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?,?,?)";
        java.util.Date currentDate = new java.util.Date();
        java.sql.Timestamp createdAt = new java.sql.Timestamp(currentDate.getTime());
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, emailPatient);
            st.setString(2, patientName);
            st.setString(3, birthDate);
            st.setString(4, gender);
            st.setString(5, phone);
            st.setString(6, createdAt.toString());
            st.setString(7, ownerId);
            st.execute();
            System.out.println("Add new profile-------------");
            return true;
        } catch (SQLException e) {
            System.out.println("addNewProfile: " + e);
        }
        return false;
    }

    FamilyProfile getFamilyProfileByInfo(String patientName, String gender, String birthDate, String phone, String emailPatient, String ownerId) {
        String sql = "SELECT * FROM FamilyProfile\n"
                + "WHERE email = ? AND birthDate = ? AND gender = ? AND [name] =? AND phone = ? AND ownerId = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, emailPatient);
            st.setString(2, birthDate);
            st.setString(3, gender);
            st.setString(4, patientName);
            st.setString(5, phone);
            st.setString(6, ownerId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new FamilyProfile(String.valueOf(rs.getInt("profileId")));
            }
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.getFamilyProfileByInfo-user: " + e);
        }
        return null;
    }

    public boolean addNewUserProfile(FamilyProfile fp) {
        String sql = "INSERT INTO [dbo].[FamilyProfile] ([email], [name], [birthDate], [gender], [address], [identity], [medicalId], [ethnic], [phone], [createdAt], [ownerId], [relationId]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            String gender = "0";
            if(fp.getGender().equals("Female")){
            gender = "1";
            }
            st.setString(1, fp.getEmail());
            st.setString(2, fp.getName());
            st.setString(3, fp.getBirthDate());
            st.setString(4, gender);
            st.setString(5, fp.getAddress());
            st.setString(6, fp.getIdentity());
            st.setString(7, fp.getMedicalId());
            st.setString(8, fp.getEthnic());
            st.setString(9, fp.getPhone());
            st.setString(10, fp.getCreatedAt());
            st.setString(11, fp.getOwnerId());
            st.setInt(12, Integer.parseInt(fp.getRelationId()));
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("FamilyProfileDAO.addNewUserProfile: " + e);
        }
        return false;
    }
}
