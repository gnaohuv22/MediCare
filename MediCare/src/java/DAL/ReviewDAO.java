/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.AcademicRank;
import Models.Doctor;
import Models.Province;
import Models.Reviews;
import Models.User;
import Models.Appointments;
import Models.Branch;
import Models.Certificate;
import Models.FamilyProfile;
import Models.ServiceTag;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ReviewDAO extends DBContext {

    private int numberRecord;

    public int getNumberRecord() {
        return numberRecord;
    }

    public ArrayList<Reviews> getAllReview() {
        ArrayList<Reviews> list = new ArrayList<>();
        String SQL = "SELECT Reviews.id[rId], Reviews.userId[rUserId], doctorId, appointmentId, rating, reviewContent, Reviews.createdAt,"
                + " [User].name[uName], Doctor.displayName[dName]"
                + " FROM Reviews"
                + " JOIN [User] on userId = [User].id"
                + " JOIN Doctor on doctorId = Doctor.id";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("rId");
                String userId = rs.getString("rUserId");
                String doctorId = rs.getString("doctorId");
                String appointmentId = rs.getString("appointmentId");
                String rating = rs.getString("rating");
                String reviewContent = rs.getString("reviewContent");
                String createdAt = rs.getString("createdAt");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                User user = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor doctor = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                Appointments appoinment = new Appointments(appointmentId, new User(), new Doctor(), new ServiceTag(), "", "");
                Reviews review = new Reviews(id, user, doctor, appoinment, rating, reviewContent, createdAt);
                list.add(review);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllReview " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Reviews> getListReview(int offset, int fetch) {
        ArrayList<Reviews> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        String SQL = "SELECT Reviews.id[rId], Reviews.userId[rUserId], doctorId, appointmentId, rating, reviewContent, Reviews.createdAt,"
                + " [User].name[uName], Doctor.displayName[dName]"
                + " FROM Reviews"
                + " JOIN [User] on userId = [User].id"
                + " JOIN Doctor on doctorId = Doctor.id"
                + " GROUP BY Reviews.id, Reviews.userId, doctorId, appointmentId, rating, reviewContent, Reviews.createdAt,"
                + " [User].name, Doctor.displayName"
                + " HAVING Reviews.id IS NOT NULL"
                + " ORDER BY COUNT(Reviews.id) DESC"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, offset);
            pstm.setInt(2, fetch);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("rId");
                String userId = rs.getString("rUserId");
                String doctorId = rs.getString("doctorId");
                String appointmentId = rs.getString("appointmentId");
                String rating = rs.getString("rating");
                String reviewContent = rs.getString("reviewContent");
//                String createdAt = rs.getString("createdAt");
                Timestamp ts = rs.getTimestamp("createdAt");
                String createdAt;
                if (ts!=null){
                    createdAt = format.format(ts);
                }else{
                    createdAt = "";
                }
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                User user = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor doctor = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                Appointments appoinment = new Appointments(appointmentId, new User(), new Doctor(), new ServiceTag(), "", "");
                Reviews review = new Reviews(id, user, doctor, appoinment, rating, reviewContent, createdAt);
                list.add(review);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListReview " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Reviews> searchListReview(int offset, int fetch) {
        ArrayList<Reviews> list = new ArrayList<>();

        String SQL = "SELECT Reviews.id[rId], Reviews.userId[rUserId], doctorId, appointmentId, rating, reviewContent, Reviews.createdAt,"
                + " [User].name[uName], Doctor.displayName[dName]"
                + " FROM Reviews"
                + " JOIN [User] on userId = [User].id"
                + " JOIN Doctor on doctorId = Doctor.id"
                + " WHERE SELECT Reviews.id[rId] like ? AND Reviews.userId[rUserId] like ? AND doctorId like ? AND appointmentId like ? "
                + " AND rating like ? AND reviewContent like ? AND Reviews.createdAt like ? "
                + " AND [User].name[uName] like ? AND Doctor.displayName[dName] like ?"
                + " GROUP BY Reviews.id, Reviews.userId, doctorId, appointmentId, rating, reviewContent, Reviews.createdAt,"
                + " [User].name, Doctor.displayName"
                + " HAVING Reviews.id IS NOT NULL"
                + " ORDER BY COUNT(Reviews.id) DESC"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        String SQL2 = "SELECT count(*) "
                + " FROM Reviews"
                + " JOIN [User] on userId = [User].id"
                + " JOIN Doctor on doctorId = Doctor.id"
                + " WHERE SELECT Reviews.id[rId] like ? AND Reviews.userId[rUserId] like ? AND doctorId like ? AND appointmentId like ? "
                + " AND rating like ? AND reviewContent like ? AND Reviews.createdAt like ? "
                + " AND [User].name[uName] like ? AND Doctor.displayName[dName] like ?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL2)) {
            pstm.setString(1, "%%");
            pstm.setString(2, "%%");
            pstm.setString(3, "%%");
            pstm.setString(4, "%%");
            pstm.setString(5, "%%");
            pstm.setString(6, "%%");
            pstm.setString(7, "%%");
            pstm.setString(8, "%%");
            pstm.setString(9, "%%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                numberRecord = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("search review " + e.getMessage());
        }
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, "%%");
            pstm.setString(2, "%%");
            pstm.setString(3, "%%");
            pstm.setString(4, "%%");
            pstm.setString(5, "%%");
            pstm.setString(6, "%%");
            pstm.setString(7, "%%");
            pstm.setString(8, "%%");
            pstm.setString(9, "%%");
            pstm.setInt(10, offset);
            pstm.setInt(11, fetch);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("rId");
                String userId = rs.getString("rUserId");
                String doctorId = rs.getString("doctorId");
                String appointmentId = rs.getString("appointmentId");
                String rating = rs.getString("rating");
                String reviewContent = rs.getString("reviewContent");
                String createdAt = rs.getString("createdAt");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                User user = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor doctor = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                Appointments appoinment = new Appointments(appointmentId, new User(), new Doctor(), new ServiceTag(), "", "");
                Reviews review = new Reviews(id, user, doctor, appoinment, rating, reviewContent, createdAt);
                list.add(review);
            }
            return list;
        } catch (Exception e) {
            System.out.println("searchListReview " + e.getMessage());
        }
        return null;
    }

    public Boolean deleteReview(String id) {
        String SQL = "DELETE Reviews WHERE id = ? ";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("deleteReview " + e.getMessage());
        }
        return false;
    }

    public int countAllReview() {
        String SQL = "select COUNT(id) from Reviews";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        } catch (Exception e) {
            System.out.println("countAllReview " + e.getMessage());
        }
        return -1;
    }

    public String generateId() {
        String SQL = "select top(1) id from Reviews order by id DESC";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            String number = null;
            while (rs.next()) {
                number = rs.getString(1);
            }
            number = Integer.parseInt(number) + 1 + "";
            return number;
        } catch (Exception e) {
            System.out.println("generateId review " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Reviews> getReviewsByDoctorId(String doctorId) {
        String SQL = "SELECT fp.name AS profileName, fp.profilePicture, fp.email AS profileEmail, fp.gender AS profileGender, a.plannedAt, a.status, a.symptoms, st.id AS ServiceID, st.nametag AS ServiceName, r.id as reviewID, r.rating AS ReviewRating, r.reviewContent, r.createdAt AS ReviewTime FROM [Reviews] r\n"
                + "LEFT JOIN [Appointments] a ON a.id = r.appointmentId\n"
                + "LEFT JOIN [FamilyProfile] fp ON fp.profileId = a.profileId\n"
                + "LEFT JOIN [ServiceTag] st ON st.id = a.serviceId\n"
                + "WHERE r.doctorId = ?\n"
                + "ORDER BY ReviewTime DESC\n";

        ArrayList<Reviews> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, doctorId);

            ResultSet rs = ps.executeQuery();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

            while (rs.next()) {
                Timestamp ts = rs.getTimestamp("plannedAt");
                String plannedAt = format.format(ts);
                
                ts = rs.getTimestamp("ReviewTime");
                String createdAt = format.format(ts);
                list.add(new Reviews(
                        String.valueOf(rs.getInt("reviewID")),
                        String.valueOf(rs.getFloat("ReviewRating")),
                        rs.getString("reviewContent"),
                        createdAt,
                        new Appointments(
                                plannedAt,
                                rs.getString("symptoms"),
                                new ServiceTag(
                                        String.valueOf(rs.getInt("ServiceID")),
                                        rs.getString("ServiceName")),
                                new FamilyProfile(
                                        rs.getString("profileEmail"),
                                        rs.getString("profileName"),
                                        rs.getString("profileGender"),
                                        rs.getString("profilePicture")))
                )
                );
            }
//            rs.close();
//
//            SQL = "SELECT COUNT(*)\n"
//                    + "FROM (\n"
//                    + "SELECT fp.name AS profileName, fp.profilePicture, fp.email AS profileEmail, fp.gender AS profileGender, a.plannedAt, a.status, a.symptoms, st.id AS ServiceID, st.nametag AS ServiceName, r.id as reviewID, r.rating AS ReviewRating, r.reviewContent, r.createdAt AS ReviewTime FROM [Reviews] r\n"
//                    + "LEFT JOIN [Appointments] a ON a.id = r.appointmentId\n"
//                    + "LEFT JOIN [FamilyProfile] fp ON fp.profileId = a.profileId\n"
//                    + "LEFT JOIN [ServiceTag] st ON st.id = a.serviceId\n"
//                    + "WHERE r.doctorId = ?\n"
//                    + ") AS subquery";
//
//            try ( PreparedStatement psm = connection.prepareStatement(SQL)) {
//                psm.setString(1, doctorId);
//                rs = psm.executeQuery();
//
//                while (rs.next()) {
//                    this.numberRecord = rs.getInt(1);
//                }
//            } catch (SQLException e) {
//                System.out.println("ReviewsDAO, countRecords: " + e.getMessage());
//            }
        } catch (SQLException e) {
            System.out.println("ReviewsDAO, getReviewsByDoctorId: " + e.getMessage());
        }
        return list;
    }

}
