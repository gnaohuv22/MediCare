/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.AcademicRank;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Appointments;
import Models.Branch;
import Models.Certificate;
import Models.Doctor;
import Models.FamilyProfile;
import Models.Province;
import Models.ServiceTag;
import Models.User;
import java.util.List;

/**
 *
 * @author hoang
 */
public class AppointmentsDAO extends DBContext {

    public ArrayList<Appointments> getAllAppointment() {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT Appointments.id, userId, doctorId, serviceId, plannedAt, Appointments.status,\n"
                + "    [User].name AS uName, Doctor.displayName AS dName, ServiceTag.nametag AS nameTag\n"
                + "FROM Appointments\n"
                + "JOIN [User] ON userId = [User].id\n"
                + "LEFT JOIN Doctor ON doctorId = Doctor.id\n"
                + "JOIN ServiceTag ON serviceId = ServiceTag.id;";
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

    public int getLastestId() {
        String sql = "SELECT TOP 1 id FROM Appointments\n"
                + "ORDER BY id DESC";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getLastestId: " + e);
        }
        return -1;
    }

    public boolean addNewAppointment(String userId, String branchId, String serviceId, String doctorId, String plannedAt, String slotId, String createdAt,
            String patientName, String gender, String birthDate, String phone, String emailPatient, String symptoms, String email, String password) {
        UserDAO ud = new UserDAO();
        // If guest book:
        User user;
        System.out.println("--- START ADD NEW APPOINTMENT ---");
        System.out.println("Check Login: email = " + email + " | password = " + password);
        if (password == null) {
            user = null;
        } else {
            user = ud.login(email);
        }
        User guest = ud.getUserNotRegistered(emailPatient);

        // Check if the profile of PATIENT existed?:
        FamilyProfileDAO fpd = new FamilyProfileDAO();
        FamilyProfile p;

        // If user book:
        if (user == null) { // Guest book:
            System.out.println("USER == NULL -> GUEST BOOK:");
            System.out.println("USER = " + user);
            // If guest is not in database -> Add guest to table User:
            if (guest == null) {
                if (ud.addUserNotRegistered(emailPatient)) {
                    System.out.println("Add user not register: SUCCESS");
                } else {
                    System.out.println("Add user not register: FAIL");
                }
            }
            guest = ud.getUserNotRegistered(emailPatient);
            p = fpd.getFamilyProfileByInfoGuest(patientName, gender, birthDate, phone, emailPatient, guest.getId());
            if (p == null) {
                fpd.addNewProfile(patientName, gender, birthDate, phone, emailPatient, guest.getId());
            }
        } else { // User book:
            System.out.println("USER != NULL -> USER BOOK:");
            p = fpd.getFamilyProfileByInfoUser(patientName, gender, birthDate, phone, emailPatient, user.getId());
            if (p == null) {
                fpd.addNewProfile(patientName, gender, birthDate, phone, emailPatient, user.getId());
            }
        }
        if (user != null) {
            p = fpd.getFamilyProfileByInfoGuest(patientName, gender, birthDate, phone, emailPatient, user.getId());
        } else {
            p = fpd.getFamilyProfileByInfoGuest(patientName, gender, birthDate, phone, emailPatient, guest.getId());
        }

        String profileId = p.getProfileId();
        System.out.println("Profile Id Guest: " + profileId);

        //User login:
        //TH1: userId != null, serviceId == null -> doctorId == null
        String sql1 = "INSERT INTO [dbo].[Appointments]\n"
                + "           ([id]\n"
                + "		   ,[userId]\n"
                + "           ,[plannedAt]\n"
                + "           ,[status]\n"
                + "           ,[branchId]\n"
                + "           ,[createdAt]\n"
                + "           ,[symptoms]\n"
                + "           ,[profileId])\n"
                + "     VALUES (?,?,?,?,?,?,?,?)";
        //TH2: userId != null, serviceId != null -> doctorId == null
        String sql2 = "INSERT INTO [dbo].[Appointments]\n"
                + "           ([id]\n"
                + "		   ,[userId]\n"
                + "		   ,[serviceId]\n"
                + "           ,[plannedAt]\n"
                + "           ,[status]\n"
                + "           ,[branchId]\n"
                + "           ,[createdAt]\n"
                + "           ,[symptoms]\n"
                + "           ,[profileId])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?)";
        //TH3: userId != null, serviceId != null -> doctorId != null
        String sql3 = "INSERT INTO [dbo].[Appointments]\n"
                + "           ([id]\n"
                + "		   ,[userId]\n"
                + "		   ,[doctorId]\n"
                + "		   ,[serviceId]\n"
                + "           ,[plannedAt]\n"
                + "           ,[status]\n"
                + "           ,[branchId]\n"
                + "           ,[createdAt]\n"
                + "           ,[symptoms]\n"
                + "           ,[profileId])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?,?)";
        //Guest:
        //TH4: userId == null, serviceId == null -> doctorId == null
        String sql4 = "INSERT INTO [dbo].[Appointments]\n"
                + "           ([id]\n"
                + "           ,[plannedAt]\n"
                + "           ,[status]\n"
                + "           ,[branchId]\n"
                + "           ,[createdAt]\n"
                + "           ,[symptoms]\n"
                + "           ,[profileId])\n"
                + "     VALUES (?,?,?,?,?,?,?)";
        //TH5: userId == null, serviceId != null -> doctorId == null
        String sql5 = "INSERT INTO [dbo].[Appointments]\n"
                + "           ([id]\n"
                + "		   ,[serviceId]\n"
                + "           ,[plannedAt]\n"
                + "           ,[status]\n"
                + "           ,[branchId]\n"
                + "           ,[createdAt]\n"
                + "           ,[symptoms]\n"
                + "           ,[profileId])\n"
                + "     VALUES (?,?,?,?,?,?,?,?)";
        //TH6: userId == null, serviceId != null -> doctorId != null
        String sql6 = "INSERT INTO [dbo].[Appointments]\n"
                + "           ([id]\n"
                + "		   ,[doctorId]\n"
                + "		   ,[serviceId]\n"
                + "           ,[plannedAt]\n"
                + "           ,[status]\n"
                + "           ,[branchId]\n"
                + "           ,[createdAt]\n"
                + "           ,[symptoms]\n"
                + "           ,[profileId])\n"
                + "     VALUES (?,?,?,?,?,?,?,?,?)";

        int lastId = this.getLastestId() + 1;

        System.out.println("Last ID: " + lastId);

        //User login:
        //TH1: userId != null, serviceId == null -> doctorId == null -> status: 0 - pending
        if (userId != null && serviceId == null && doctorId == null) {
            System.out.println("TH1: userId != null, serviceId == null -> doctorId == null");
            try {
                PreparedStatement st = connection.prepareStatement(sql1);
                st.setInt(1, lastId);
                st.setString(2, userId);
                st.setString(3, plannedAt);
                st.setInt(4, 0);
                st.setInt(5, Integer.parseInt(branchId));
                st.setString(6, createdAt);
                st.setString(7, symptoms);
                st.setInt(8, Integer.parseInt(profileId));
                st.execute();
                return true;
            } catch (SQLException e) {
                System.out.println("AppointmentsDAO.addNewAppointment - TH1: " + e);
            }
            //TH2: userId != null, serviceId != null -> doctorId == null -> status: 0 - pending
        } else if (userId != null && serviceId != null && doctorId == null) {
            System.out.println("TH2: userId != null, serviceId != null -> doctorId == null");
            try {
                PreparedStatement st = connection.prepareStatement(sql2);
                st.setInt(1, lastId);
                st.setString(2, userId);
                st.setInt(3, Integer.parseInt(serviceId));
                st.setString(4, plannedAt);
                st.setInt(5, 0);
                st.setInt(6, Integer.parseInt(branchId));
                st.setString(7, createdAt);
                st.setString(8, symptoms);
                st.setInt(9, Integer.parseInt(profileId));
                st.execute();
                return true;
            } catch (SQLException e) {
                System.out.println("AppointmentsDAO.addNewAppointment - TH2: " + e);
            }
            //TH3: userId != null, serviceId != null -> doctorId != null -> status: 1 - Accepted
        } else if (userId != null && serviceId != null && doctorId != null) {
            System.out.println("TH3: userId != null, serviceId != null -> doctorId != null");
            try {
                PreparedStatement st = connection.prepareStatement(sql3);
                st.setInt(1, lastId);
                st.setString(2, userId);
                st.setString(3, doctorId);
                st.setInt(4, Integer.parseInt(serviceId));
                st.setString(5, plannedAt);
                st.setInt(6, 1);
                st.setInt(7, Integer.parseInt(branchId));
                st.setString(8, createdAt);
                st.setString(9, symptoms);
                st.setInt(10, Integer.parseInt(profileId));
                st.execute();
                return true;
            } catch (SQLException e) {
                System.out.println("AppointmentsDAO.addNewAppointment - TH3: " + e);
            }
            //Guest:
            //TH4: userId == null, serviceId == null -> doctorId == null -> status: 0 - pending
        } else if (userId == null && serviceId == null && doctorId == null) {
            System.out.println("TH4: userId == null, serviceId == null -> doctorId == null");
            try {
                PreparedStatement st = connection.prepareStatement(sql4);
                st.setInt(1, lastId);
                st.setString(2, plannedAt);
                st.setInt(3, 0);
                st.setInt(4, Integer.parseInt(branchId));
                st.setString(5, createdAt);
                st.setString(6, symptoms);
                st.setInt(7, Integer.parseInt(profileId));
                st.execute();
                return true;
            } catch (SQLException | NumberFormatException e) {
                System.out.println("AppointmentsDAO.addNewAppointment - TH4: " + e);
            }
            //TH5: userId == null, serviceId != null -> doctorId == null -> status: 0 - pending
        } else if (userId == null && serviceId != null && doctorId == null) {
            System.out.println("TH5: userId == null, serviceId != null -> doctorId == null");

            try {
                PreparedStatement st = connection.prepareStatement(sql5);
                st.setInt(1, lastId);
                st.setInt(2, Integer.parseInt(serviceId));
                st.setString(3, plannedAt);
                st.setInt(4, 0);
                st.setInt(5, Integer.parseInt(branchId));
                st.setString(6, createdAt);
                st.setString(7, symptoms);
                st.setInt(8, Integer.parseInt(profileId));

                st.execute();
                return true;
            } catch (SQLException | NumberFormatException e) {
                System.out.println("AppointmentsDAO.addNewAppointment - TH5: " + e);
            }
            //TH6: userId == null, serviceId != null -> doctorId != null -> status: 1 - Accepted
        } else if (userId == null && serviceId != null && doctorId != null) {
            System.out.println("TH6: userId == null, serviceId != null -> doctorId != null");
            try {
                PreparedStatement st = connection.prepareStatement(sql6);
                st.setInt(1, lastId);
                st.setString(2, doctorId);
                st.setInt(3, Integer.parseInt(serviceId));
                st.setString(4, plannedAt);
                st.setInt(5, 1);
                st.setInt(6, Integer.parseInt(branchId));
                st.setString(7, createdAt);
                st.setString(8, symptoms);
                st.setInt(9, Integer.parseInt(profileId));
                st.execute();
                return true;
            } catch (SQLException | NumberFormatException e) {
                System.out.println("AppointmentsDAO.addNewAppointment - TH6: " + e);
            }
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql1);
        } catch (SQLException e) {
            System.out.println("AppointmentsDAO.addNewAppointment: " + e);
        }
        return false;
    }

//    public List<Appointments> getListAppointmentsByOwnerId(String ownerId) {// Khong phai cua TU BINH //khong phai cua THU
//        ArrayList<Appointments> list = new ArrayList<>();
//        String SQL = "SELECT * FROM [Appointments] where userId=?";
//        
//        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                Appointments a = new Appointments(
//                    String.valueOf(rs.getInt(1)),
//                    rs.getString(2),
//                    rs.getString(3),
//                    String.valueOf(rs.getInt(4)),
//                    String.valueOf(rs.getDate(5)),
//                    String.valueOf(rs.getInt(6)));
//                list.add(a);
//            }
//            return list;
//        } catch (SQLException e) {
//            System.out.println("AppointmentsDAO.getListAppointments: " + e.getMessage());
//        } 
//        return null;
//    }
}