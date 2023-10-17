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
import Models.ServiceTag;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ReviewDAO extends DBContext{
    private int numberRecord;
    public int getNumberRecord(){
        return this.numberRecord;
    }
    public ArrayList<Reviews> getAllReview() {
        ArrayList<Reviews> list = new ArrayList<>();
        String SQL = "SELECT Reviews.id[rId], Reviews.userId[rUserId], doctorId, appointmentId, rating, reviewContent, Reviews.createdAt,"
+" [User].name[uName], Doctor.displayName[dName]"
+" FROM Reviews"
+" JOIN [User] on userId = [User].id"
+" JOIN Doctor on doctorId = Doctor.id";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
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
                User user = new User(userId,"","",userName,"","","",new Province(),"","","","","","");
                Doctor doctor = new Doctor(doctorId,"","",doctorName,new Branch(),"",new AcademicRank(),new Certificate(),"","","","","","","");
                Appointments appoinment = new Appointments(appointmentId,new User(),new Doctor(),new ServiceTag(),"","");
                Reviews review= new Reviews(id, user, doctor, appoinment, rating, reviewContent, createdAt);
                list.add(review);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllReview " + e.getMessage());
        }
        return null;
    }
    public ArrayList<Reviews> getListReview(int offset,int fetch) {
        ArrayList<Reviews> list = new ArrayList<>();
        
        String SQL = "SELECT Reviews.id[rId], Reviews.userId[rUserId], doctorId, appointmentId, rating, reviewContent, Reviews.createdAt," +
" [User].name[uName], Doctor.displayName[dName]" +
" FROM Reviews" +
" JOIN [User] on userId = [User].id" +
" JOIN Doctor on doctorId = Doctor.id" +
" GROUP BY Reviews.id, Reviews.userId, doctorId, appointmentId, rating, reviewContent, Reviews.createdAt," +
" [User].name, Doctor.displayName" +
" HAVING Reviews.id IS NOT NULL" +
" ORDER BY COUNT(Reviews.id) DESC" +
" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
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
                String createdAt = rs.getString("createdAt");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                User user = new User(userId,"","",userName,"","","",new Province(),"","","","","","");
                Doctor doctor = new Doctor(doctorId,"","",doctorName,new Branch(),"",new AcademicRank(),new Certificate(),"","","","","","","");
                Appointments appoinment = new Appointments(appointmentId,new User(),new Doctor(),new ServiceTag(),"","");
                Reviews review= new Reviews(id, user, doctor, appoinment, rating, reviewContent, createdAt);
                list.add(review);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListReview " + e.getMessage());
        }
        return null;
    }
    public ArrayList<Reviews> searchListReview(int offset,int fetch) {
        ArrayList<Reviews> list = new ArrayList<>();
        
        String SQL = "SELECT Reviews.id[rId], Reviews.userId[rUserId], doctorId, appointmentId, rating, reviewContent, Reviews.createdAt," +
" [User].name[uName], Doctor.displayName[dName]" +
" FROM Reviews" +
" JOIN [User] on userId = [User].id" +
" JOIN Doctor on doctorId = Doctor.id" + 
" WHERE SELECT Reviews.id[rId] like ? AND Reviews.userId[rUserId] like ? AND doctorId like ? AND appointmentId like ? "+
" AND rating like ? AND reviewContent like ? AND Reviews.createdAt like ? " +
" AND [User].name[uName] like ? AND Doctor.displayName[dName] like ?" +
" GROUP BY Reviews.id, Reviews.userId, doctorId, appointmentId, rating, reviewContent, Reviews.createdAt," +
" [User].name, Doctor.displayName" +
" HAVING Reviews.id IS NOT NULL" +
" ORDER BY COUNT(Reviews.id) DESC" +
" OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        String SQL2 = "SELECT count(*) " +
" FROM Reviews" +
" JOIN [User] on userId = [User].id" +
" JOIN Doctor on doctorId = Doctor.id" + 
" WHERE SELECT Reviews.id[rId] like ? AND Reviews.userId[rUserId] like ? AND doctorId like ? AND appointmentId like ? "+
" AND rating like ? AND reviewContent like ? AND Reviews.createdAt like ? " +
" AND [User].name[uName] like ? AND Doctor.displayName[dName] like ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL2)){
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
            while (rs.next()){
                numberRecord = rs.getInt(1);
            }
        }catch(Exception e) {
            System.out.println("search review " + e.getMessage());
        }
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
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
                User user = new User(userId,"","",userName,"","","",new Province(),"","","","","","");
                Doctor doctor = new Doctor(doctorId,"","",doctorName,new Branch(),"",new AcademicRank(),new Certificate(),"","","","","","","");
                Appointments appoinment = new Appointments(appointmentId,new User(),new Doctor(),new ServiceTag(),"","");
                Reviews review= new Reviews(id, user, doctor, appoinment, rating, reviewContent, createdAt);
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
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("deleteReview " + e.getMessage());
        }
        return false;
    }
    public int countAllReview(){
        String SQL = "select COUNT(id) from Reviews";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        }catch (Exception e) {
            System.out.println("countAllReview " + e.getMessage());
        }
        return -1;
    }
}
