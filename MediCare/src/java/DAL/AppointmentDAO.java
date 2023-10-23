/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.AcademicRank;
import Models.Appointments;
import Models.Branch;
import Models.Certificate;
import Models.Doctor;
import Models.Province;
import Models.ServiceTag;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class AppointmentDAO extends DBContext {

    private int numberRecord;

    public int getNumberRecord() {
        return this.numberRecord;
    }

    public Boolean deleteAppointmentById(String id) {
        String getReviewSQL = "SELECT id FROM Reviews WHERE appointmentId=?";
        String delAppointmentSQL = "DELETE Appointments WHERE id = ? ";
        try {
            PreparedStatement pstm = connection.prepareStatement(getReviewSQL);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ReviewDAO rdao = new ReviewDAO();
                rdao.deleteReview(rs.getString("id"));
            }
            pstm = connection.prepareStatement(delAppointmentSQL);
            pstm.setString(1, id);
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("deleteAppointmentById " + e.getMessage());
        }
        return false;
    }

    public ArrayList<Appointments> getAllAppointment() {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT Appointments.id, userId, doctorId, serviceId, plannedAt, Appointments.status,"
                + " [User].name[uName], Doctor.displayName[dName], ServiceTag.nametag[nameTag]"
                + " FROM Appointments"
                + " JOIN [User] on userId = [User].id"
                + " JOIN Doctor on doctorId = Doctor.id"
                + " JOIN ServiceTag on serviceId = ServiceTag.id";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String userId = rs.getString("userId");
                String doctorId = rs.getString("doctorId");
                String serviceId = rs.getString("serviceId");
                String plannedAt = rs.getString("plannedAt");
                String status = rs.getString("status");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                String serviceName = rs.getString("nameTag");

                User user = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor doctor = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                ServiceTag serviceTag = new ServiceTag(serviceId, serviceName, "", "");
                Appointments appointments = new Appointments(id, user, doctor, serviceTag, plannedAt, status);
                list.add(appointments);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllAppointment " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Appointments> getListAppointment(int offset, int fetch) {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT Appointments.id, userId, doctorId, serviceId, plannedAt, Appointments.status,"
                + " [User].name[uName], Doctor.displayName[dName], ServiceTag.nametag[nameTag]"
                + " FROM Appointments"
                + " JOIN [User] on userId = [User].id"
                + " JOIN Doctor on doctorId = Doctor.id"
                + " JOIN ServiceTag on serviceId = ServiceTag.id"
                + " GROUP BY Appointments.id, userId, doctorId, serviceId, plannedAt, Appointments.status,"
                + " [User].name, Doctor.displayName, ServiceTag.nametag"
                + " HAVING Appointments.id IS NOT NULL"
                + " ORDER BY COUNT(Appointments.id) DESC"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, offset);
            pstm.setInt(2, fetch);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String userId = rs.getString("userId");
                String doctorId = rs.getString("doctorId");
                String serviceId = rs.getString("serviceId");
                String plannedAt = rs.getString("plannedAt");
                String status = rs.getString("status");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                String serviceName = rs.getString("nameTag");
                User user = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor doctor = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                ServiceTag serviceTag = new ServiceTag(serviceId, serviceName, "", "");
                Appointments appointments = new Appointments(id, user, doctor, serviceTag, plannedAt, status);

                list.add(appointments);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListAppointment " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Appointments> searchListAppointment(int offset, int fetch) {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT Appointments.id, userId, doctorId, serviceId, plannedAt, Appointments.status,"
                + " [User].name[uName], Doctor.displayName[dName], ServiceTag.nametag[nameTag]"
                + " FROM Appointments"
                + " JOIN [User] on userId = [User].id"
                + " JOIN Doctor on doctorId = Doctor.id"
                + " JOIN ServiceTag on serviceId = ServiceTag.id"
                + " WHERE SELECT Appointments.id like ? AND userId like ? AND doctorId like ? AND serviceId like ? AND plannedAt like ? AND Appointments.status"
                + " AND [User].name[uName] like ? AND Doctor.displayName[dName] like ? AND ServiceTag.nametag[nameTag] like ?"
                + " GROUP BY Appointments.id, userId, doctorId, serviceId, plannedAt, Appointments.status,"
                + " [User].name, Doctor.displayName, ServiceTag.nametag"
                + " HAVING Appointments.id IS NOT NULL"
                + " ORDER BY COUNT(Appointments.id) DESC"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        String SQL2 = "SELECT count(*) "
                + " FROM Appointments"
                + " JOIN [User] on userId = [User].id"
                + " JOIN Doctor on doctorId = Doctor.id"
                + " JOIN ServiceTag on serviceId = ServiceTag.id"
                + " WHERE SELECT Appointments.id like ? AND userId like ? AND doctorId like ? AND serviceId like ? AND plannedAt like ? AND Appointments.status"
                + " AND [User].name[uName] like ? AND Doctor.displayName[dName] like ? AND ServiceTag.nametag[nameTag] like ?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL2)) {
            pstm.setString(1, "%%"); //appointment id
            pstm.setString(2, "%%"); // userId
            pstm.setString(3, "%%"); //doctorId
            pstm.setString(4, "%%"); //serviceId
            pstm.setString(5, "%%"); //plannedAt
            pstm.setString(6, "%%"); //user name
            pstm.setString(7, "%%"); //doctor displayname
            pstm.setString(8, "%%"); //serviceTag nameTag
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                numberRecord = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("searchAppointment " + e.getMessage());
        }
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, "%%"); //appointment id
            pstm.setString(2, "%%"); // userId
            pstm.setString(3, "%%"); //doctorId
            pstm.setString(4, "%%"); //serviceId
            pstm.setString(5, "%%"); //plannedAt
            pstm.setString(6, "%%"); //user name
            pstm.setString(7, "%%"); //doctor displayname
            pstm.setString(8, "%%"); //serviceTag nameTag
            pstm.setInt(9, offset);
            pstm.setInt(10, fetch);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String userId = rs.getString("userId");
                String doctorId = rs.getString("doctorId");
                String serviceId = rs.getString("serviceId");
                String plannedAt = rs.getString("plannedAt");
                String status = rs.getString("status");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                String serviceName = rs.getString("nameTag");
                User user = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor doctor = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                ServiceTag serviceTag = new ServiceTag(serviceId, serviceName, "", "");
                Appointments appointments = new Appointments(id, user, doctor, serviceTag, plannedAt, status);

                list.add(appointments);
            }
            return list;
        } catch (Exception e) {
            System.out.println("searchListAppointment " + e.getMessage());
        }
        return null;
    }

    public int countAllAppointment() {
        String SQL = "select COUNT(id) from Appointments";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        } catch (Exception e) {
            System.out.println("countAllAppointment " + e.getMessage());
        }
        return -1;
    }
    public String generateId(){
        String SQL = "select top(1) id from Appointments order by id DESC";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            String number = null;
            while(rs.next()){
                number = rs.getString(1);
            }
            number = Integer.parseInt(number)+1+"";
            return number;
        }catch (Exception e) {
            System.out.println("generateId appointments " + e.getMessage());
        }
        return null;
    }
}