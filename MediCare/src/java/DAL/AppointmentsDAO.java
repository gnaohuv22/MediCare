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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author hoang
 */
public class AppointmentsDAO extends DBContext {

//    public ArrayList<Appointments> getAllAppointment() {
//        ArrayList<Appointments> list = new ArrayList<>();
//        String SQL = "SELECT Appointments.id, userId, doctorId, serviceId, plannedAt, Appointments.status,\n"
//                + "    [User].name AS uName, Doctor.displayName AS dName, ServiceTag.nametag AS nameTag\n"
//                + "FROM Appointments\n"
//                + "JOIN [User] ON userId = [User].id\n"
//                + "LEFT JOIN Doctor ON doctorId = Doctor.id\n"
//                + "JOIN ServiceTag ON serviceId = ServiceTag.id;";
//        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
//            ResultSet rs = pstm.executeQuery();
//            while (rs.next()) {
//                String id = rs.getString("id");
//                String userId = rs.getString("userId");
//                String doctorId = rs.getString("doctorId");
//                String serviceId = rs.getString("serviceId");
//                String plannedAt = rs.getString("plannedAt");
//                String status = rs.getString("status");
//                String userName = rs.getString("uName");
//                String doctorName = rs.getString("dName");
//                String serviceName = rs.getString("nameTag");
//
//                User user = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
//                Doctor doctor = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
//                ServiceTag serviceTag = new ServiceTag(serviceId, serviceName, "", "");
//                Appointments appointments = new Appointments(id, user, doctor, serviceTag, plannedAt, status);
//                list.add(appointments);
//            }
//            return list;
//        } catch (Exception e) {
//            System.out.println("getAllAppointment " + e.getMessage());
//        }
//        return null;
//    }
    public ArrayList<Appointments> getAllAppointment() {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT\n"
                + "    Appointments.id,\n"
                + "    userId,\n"
                + "    doctorId,\n"
                + "    serviceId,\n"
                + "    plannedAt,\n"
                + "    Appointments.profileId,\n"
                + "    Appointments.branchId,\n"
                + "    CONVERT(VARCHAR, plannedAt, 23) AS AppointmentDay,\n"
                + "    CAST(plannedAt AS TIME) AS AppointmentTime,\n"
                + "    Appointments.status,\n"
                + "    [User].name AS uName,\n"
                + "    Doctor.displayName AS dName,\n"
                + "    ServiceTag.nametag AS nameTag,\n"
                + "    Branch.name AS bName,\n"
                + "    FamilyProfile.name AS patientName\n"
                + "FROM Appointments\n"
                + "LEFT JOIN [User] ON userId = [User].id\n"
                + "LEFT JOIN Doctor ON doctorId = Doctor.id\n"
                + "JOIN ServiceTag ON serviceId = ServiceTag.id\n"
                + "JOIN Branch ON Appointments.branchId = Branch.id\n"
                + "JOIN FamilyProfile ON Appointments.profileId = FamilyProfile.profileId WHERE Appointments.status NOT IN (0, 3);";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String userId = rs.getString("userId");
                String doctorId = rs.getString("doctorId");
                String branchId = String.valueOf(rs.getInt("branchId"));
                String serviceId = rs.getString("serviceId");
                String plannedAt = rs.getString("plannedAt");
                String appointmentDay = rs.getString("appointmentDay");
                String appointmentTime = rs.getString("appointmentTime");
                String status = rs.getString("status");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                String serviceName = rs.getString("nameTag");
                String branchName = rs.getString("bName");
                String patientName = rs.getString("patientName");

                User u = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor d = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                ServiceTag s = new ServiceTag(serviceId, serviceName, "", "");
                Branch b = new Branch(branchId, branchName, "", "");
                FamilyProfile p = new FamilyProfile("", "", patientName, "", "", "", "", "", "", "", "", "", "", "", "", null);
                Appointments a = new Appointments(String.valueOf(rs.getInt("id")),
                        u, d, s,
                        plannedAt,
                        status, b,
                        "",
                        "", p, appointmentDay, appointmentTime);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getAllAppointment " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Appointments> getAllPendingAppointment() {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT\n"
                + "    Appointments.id,\n"
                + "    userId,\n"
                + "    doctorId,\n"
                + "    serviceId,\n"
                + "    plannedAt,\n"
                + "    Appointments.profileId,\n"
                + "    Appointments.branchId,\n"
                + "    CONVERT(VARCHAR, plannedAt, 23) AS AppointmentDay,\n"
                + "    CAST(plannedAt AS TIME) AS AppointmentTime,\n"
                + "    Appointments.status,\n"
                + "    [User].name AS uName,\n"
                + "    Doctor.displayName AS dName,\n"
                + "    ServiceTag.nametag AS nameTag,\n"
                + "    Branch.name AS bName,\n"
                + "    FamilyProfile.name AS patientName\n"
                + "FROM Appointments\n"
                + "LEFT JOIN [User] ON userId = [User].id\n"
                + "LEFT JOIN Doctor ON doctorId = Doctor.id\n"
                + "JOIN ServiceTag ON serviceId = ServiceTag.id\n"
                + "JOIN Branch ON Appointments.branchId = Branch.id\n"
                + "JOIN FamilyProfile ON Appointments.profileId = FamilyProfile.profileId WHERE Appointments.status = 0;";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String userId = rs.getString("userId");
                String doctorId = rs.getString("doctorId");
                String branchId = String.valueOf(rs.getInt("branchId"));
                String serviceId = rs.getString("serviceId");
                String plannedAt = rs.getString("plannedAt");
                String appointmentDay = rs.getString("appointmentDay");
                String appointmentTime = rs.getString("appointmentTime");
                String status = rs.getString("status");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                String serviceName = rs.getString("nameTag");
                String branchName = rs.getString("bName");
                String patientName = rs.getString("patientName");

                User u = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor d = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                ServiceTag s = new ServiceTag(serviceId, serviceName, "", "");
                Branch b = new Branch(branchId, branchName, "", "");
                FamilyProfile p = new FamilyProfile("", "", patientName, "", "", "", "", "", "", "", "", "", "", "", "", null);
                Appointments a = new Appointments(String.valueOf(rs.getInt("id")),
                        u, d, s,
                        plannedAt,
                        status, b,
                        "",
                        "", p, appointmentDay, appointmentTime);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getAllAppointment " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Appointments> getAllCanceledAppointment() {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT\n"
                + "    Appointments.id,\n"
                + "    userId,\n"
                + "    doctorId,\n"
                + "    serviceId,\n"
                + "    plannedAt,\n"
                + "    Appointments.profileId,\n"
                + "    Appointments.branchId,\n"
                + "    CONVERT(VARCHAR, plannedAt, 23) AS AppointmentDay,\n"
                + "    CAST(plannedAt AS TIME) AS AppointmentTime,\n"
                + "    Appointments.status,\n"
                + "    [User].name AS uName,\n"
                + "    Doctor.displayName AS dName,\n"
                + "    ServiceTag.nametag AS nameTag,\n"
                + "    Branch.name AS bName,\n"
                + "    FamilyProfile.name AS patientName\n"
                + "FROM Appointments\n"
                + "LEFT JOIN [User] ON userId = [User].id\n"
                + "LEFT JOIN Doctor ON doctorId = Doctor.id\n"
                + "JOIN ServiceTag ON serviceId = ServiceTag.id\n"
                + "JOIN Branch ON Appointments.branchId = Branch.id\n"
                + "JOIN FamilyProfile ON Appointments.profileId = FamilyProfile.profileId WHERE Appointments.status = 3;";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String userId = rs.getString("userId");
                String doctorId = rs.getString("doctorId");
                String branchId = String.valueOf(rs.getInt("branchId"));
                String serviceId = rs.getString("serviceId");
                String plannedAt = rs.getString("plannedAt");
                String appointmentDay = rs.getString("appointmentDay");
                String appointmentTime = rs.getString("appointmentTime");
                String status = rs.getString("status");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                String serviceName = rs.getString("nameTag");
                String branchName = rs.getString("bName");
                String patientName = rs.getString("patientName");

                User u = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor d = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                ServiceTag s = new ServiceTag(serviceId, serviceName, "", "");
                Branch b = new Branch(branchId, branchName, "", "");
                FamilyProfile p = new FamilyProfile("", "", patientName, "", "", "", "", "", "", "", "", "", "", "", "", null);
                Appointments a = new Appointments(String.valueOf(rs.getInt("id")),
                        u, d, s,
                        plannedAt,
                        status, b,
                        "",
                        "", p, appointmentDay, appointmentTime);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getAllAppointment " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Appointments> searchAllAppointment(Appointments key, String searchStartDate, String searchEndDate) {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT\n"
                + "                     Appointments.id,\n"
                + "                     userId,\n"
                + "                     doctorId,\n"
                + "                     serviceId,\n"
                + "                     plannedAt,\n"
                + "                     Appointments.profileId,\n"
                + "                     Appointments.branchId,\n"
                + "                     CONVERT(VARCHAR, plannedAt, 23) AS AppointmentDay,\n"
                + "                     CAST(plannedAt AS TIME) AS AppointmentTime,\n"
                + "                     Appointments.status,\n"
                + "                     [User].name AS uName,\n"
                + "                     Doctor.displayName AS dName,\n"
                + "                     ServiceTag.nametag AS nameTag,\n"
                + "                     Branch.name AS bName,\n"
                + "                     FamilyProfile.name AS patientName\n"
                + "                 FROM Appointments\n"
                + "                 JOIN [User] ON userId = [User].id\n"
                + "                 LEFT JOIN Doctor ON doctorId = Doctor.id\n"
                + "                 JOIN ServiceTag ON serviceId = ServiceTag.id\n"
                + "                 JOIN Branch ON Appointments.branchId = Branch.id\n"
                + "                 JOIN FamilyProfile ON Appointments.profileId = FamilyProfile.profileId\n"
                + "				 WHERE\n"
                + "				 Doctor.displayName like ?\n"
                + "				 AND Appointments.serviceId like ?\n"
                + "				 AND Appointments.branchId like ?\n"
                + "				 AND Appointments.status like ?\n"
                + "				 AND Appointments.plannedAt>=? and  Appointments.plannedAt<=?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setNString(1, key.getDoctor().getDisplayName());
            pstm.setString(2, key.getServiceTag().getId());
            pstm.setString(3, key.getBranch().getId());
            pstm.setString(4, key.getStatus());
            pstm.setString(5, searchStartDate);
            pstm.setString(6, searchEndDate);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String userId = rs.getString("userId");
                String doctorId = rs.getString("doctorId");
                String branchId = String.valueOf(rs.getInt("branchId"));
                String serviceId = rs.getString("serviceId");
                String plannedAt = rs.getString("plannedAt");
                String appointmentDay = rs.getString("appointmentDay");
                String appointmentTime = rs.getString("appointmentTime");
                String status = rs.getString("status");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                String serviceName = rs.getString("nameTag");
                String branchName = rs.getString("bName");
                String patientName = rs.getString("patientName");

                User u = new User(userId, "", "", userName, "", "", "", new Province(), "", "", "", "", "", "");
                Doctor d = new Doctor(doctorId, "", "", doctorName, new Branch(), "", new AcademicRank(), new Certificate(), "", "", "", "", "", "", "");
                ServiceTag s = new ServiceTag(serviceId, serviceName, "", "");
                Branch b = new Branch(branchId, branchName, "", "");
                FamilyProfile p = new FamilyProfile("", "", patientName, "", "", "", "", "", "", "", "", "", "", "", "", null);
                Appointments a = new Appointments(String.valueOf(rs.getInt("id")),
                        u, d, s,
                        plannedAt,
                        status, b,
                        "",
                        "", p, appointmentDay, appointmentTime);
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("searchAllAppointment " + e.getMessage());
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
        if (doctorId.equals("-1")) {
            doctorId = null;
        }
        UserDAO ud = new UserDAO();
        // If guest book:
        User user;
        System.out.println("--- START ADD NEW APPOINTMENT ---");
        System.out.println("Check Login: email = " + email + " | password = " + password);
        if (email == null) {
            user = null;
        } else {
            user = ud.getUserRegistered(email);
        }
        User guestNotInDB = ud.getUserNotRegistered(emailPatient);
        User guest = ud.getGuestInUserTable(emailPatient);

        // Check if the profile of PATIENT existed?:
        FamilyProfileDAO fpd = new FamilyProfileDAO();
        FamilyProfile p;

        // If user book:
        if (user == null) { // Guest book:
            System.out.println("USER == NULL -> GUEST BOOK:");
            System.out.println("USER = " + user);
            // If guest is not in database -> Add guest to table User:
            if (guest == null && guestNotInDB == null) {
                if (ud.addUserNotRegistered(emailPatient)) {
                    System.out.println("Add user not register: SUCCESS");
                } else {
                    System.out.println("Add user not register: FAIL");
                }
                guest = ud.getGuestInUserTable(emailPatient);
            } else {
                guest = ud.getUserNotRegistered(emailPatient);
            }
            System.out.println("Guest: " + guest);
            p = fpd.getFamilyProfileByInfoGuest(patientName, gender, birthDate, phone, emailPatient, guest.getId() == null ? guest.getEmail() : guest.getId());
            if (p == null) {
                fpd.addNewProfile(patientName, gender, birthDate, phone, emailPatient, guest.getId() == null ? guest.getEmail() : guest.getId());
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
            p = fpd.getFamilyProfileByInfoGuest(patientName, gender, birthDate, phone, emailPatient, guest.getId() == null ? guest.getEmail() : guest.getId());
        }
        System.out.println("p: " + p);
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
            } catch (SQLException | NumberFormatException e) {
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
            } catch (SQLException | NumberFormatException e) {
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
                emailPatient = emailPatient.trim();

                AdminEmailContext.sendVerificationCodeToEmail(emailPatient, "User book success");
                AdminEmailContext.sendVerificationCodeToEmail(new DoctorDAO().getDoctorById(doctorId).getEmail(), "You have new appointment!");
                return true;
            } catch (SQLException | NumberFormatException e) {
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
                emailPatient = emailPatient.trim();

                AdminEmailContext.sendVerificationCodeToEmail(emailPatient, "User book success");
                AdminEmailContext.sendVerificationCodeToEmail(new DoctorDAO().getDoctorById(doctorId).getEmail(), "You have new appointment!");
                return true;
            } catch (SQLException | NumberFormatException e) {
                System.out.println("AppointmentsDAO.addNewAppointment - TH6: " + e);
            }
        }

//        try {
//            PreparedStatement st = connection.prepareStatement(sql1);
//        } catch (SQLException e) {
//            System.out.println("AppointmentsDAO.addNewAppointment: " + e);
//        }
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
    public Appointments getAppointmentById(String appointmentId) {
        String sql = "SELECT a.*,CONVERT(VARCHAR, plannedAt, 23) AS AppointmentDay,"
                + "CAST(plannedAt AS TIME) AS AppointmentTime FROM Appointments AS a WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(appointmentId));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getString("userId"));
                Doctor d = new Doctor(rs.getString("doctorId"));
                ServiceTag s = new ServiceTag(String.valueOf(rs.getInt("serviceId")));
                Branch b = new Branch(rs.getInt("branchId") + "");
                FamilyProfile p = new FamilyProfile(rs.getInt("profileId") + "");
                String appointmentDay = rs.getString("appointmentDay");
                String appointmentTime = rs.getString("appointmentTime");
                Appointments a = new Appointments(String.valueOf(rs.getInt("id")),
                        u, d, s,
                        String.valueOf(rs.getDate("plannedAt")),
                        String.valueOf(rs.getInt("status")), b,
                        String.valueOf(rs.getDate("createdAt")),
                        rs.getString("symptoms"), p, appointmentDay, appointmentTime);
                return a;
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("getAppointmentById: " + e);
        }
        return null;
    }

    public Appointments getPatientAndDoctorEmailById(String appointmentId) {
        String sql = "SELECT A.doctorId, D.displayName, F.ownerId, F.profileId, F.email AS PatientEmail, D.email AS DoctorEmail FROM Appointments AS A\n"
                + "JOIN Doctor AS D ON A.doctorId = D.id\n"
                + "JOIN FamilyProfile AS F ON F.profileId = A.profileId\n"
                + "WHERE A.id = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(appointmentId));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Appointments(new Doctor(rs.getString("doctorId"), rs.getString("DoctorEmail"), rs.getString("DisplayName")),
                        new FamilyProfile(rs.getInt("profileId") + "", rs.getString("PatientEmail"), rs.getString("ownerId")));
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("getPatientAndDoctorEmailById: " + e);
        }
        return null;
    }

    public boolean updateAppointment(String appointmentId, String doctorId, String statusAppointment) {
        String sql = "";
        if (doctorId.equals("-1")) {
            sql = "UPDATE [dbo].[Appointments]\n"
                    + "   SET \n"
                    + "      [status] = ?\n"
                    + " WHERE id = ?";
        } else {
            sql = "UPDATE [dbo].[Appointments]\n"
                    + "   SET \n"
                    + "      [status] = ?\n"
                    + "      ,[doctorId] = ?\n"
                    + " WHERE id = ?";
        }

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(statusAppointment));
            if (doctorId.equals("-1")) {
                st.setInt(2, Integer.parseInt(appointmentId));
            } else {
                st.setString(2, doctorId);
                st.setInt(3, Integer.parseInt(appointmentId));
            }

            st.execute();
            System.out.println("updateAppointment: SUCCESS");
            return true;
        } catch (SQLException | NumberFormatException e) {
            System.out.println("updateAppointment: FAIL");
            System.out.println("updateAppointment: " + e);
        }
        return false;
    }

    public List<Appointments> getListAppointmentsByOwnerId(String ownerId) {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT a.id,a.status,a.plannedAt,d.displayName,d.profilePicture,fp.profileId,fp.name,\n"
                + "fp.birthDate,fp.phone,fp.gender,fp.address FROM Appointments a\n"
                + "JOIN Doctor d ON a.doctorId = d.id\n"
                + "JOIN ServiceTag st ON a.serviceId = st.id\n"
                + "JOIN FamilyProfile fp ON a.profileId = fp.profileId\n"
                + "WHERE fp.ownerId = ?\n"
                + "ORDER BY a.status ASC";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gender = "Nam";
                if (rs.getInt("gender") == 1) {
                    gender = "Nữ";
                }

                int i = rs.getInt("status");
                String status = "Chờ xác nhận";
                switch (i) {
                    case 1:
                        status = "Ðang chờ khám";
                        break;
                    case 2:
                        status = "Ðã khám xong";
                        break;
                    case 3:
                        status = "Ðã huỷ";
                        break;
                    default:
                        break;
                }

                Appointments a = new Appointments();
                a.setId(rs.getString("id"));
                a.setStatus(status);
                a.setPlannedAt(rs.getString("plannedAt"));
                System.out.println("displayName " + rs.getString("displayName"));
                a.getDoctor().setDisplayName(rs.getString("displayName"));
                a.getDoctor().setProfilePicture(rs.getString("profilePicture"));
                a.getFp().setName(rs.getString("name"));
                a.getFp().setBirthDate(rs.getString("birthDate"));
                a.getFp().setPhone(rs.getString("phone"));
                a.getFp().setGender(gender);
                a.getFp().setAddress(rs.getString("address"));
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("AppointmentsDAO.getListAppointments: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<Appointments> getListAppointmentByDoctor(Doctor d) {
        String SQL = "SELECT a.id, a.plannedAt, a.status, a.branchId, a.createdAt, a.symptoms, st.id AS 'ServiceID', st.nametag AS 'ServiceName', fp.profileId AS 'ProfileID', fp.email AS 'Email', fp.name AS 'ProfileName', fp.birthDate AS 'DoB', fp.gender AS 'Gender', fp.address AS 'Address', fp.[identity] AS 'IdentityNumber', fp.medicalId AS 'MedicalID', fp.ethnic AS 'Ethnic', fp.phone AS 'PhoneNumber', fp.profilePicture AS 'ProfilePicture' FROM [Appointments] a\n"
                + "LEFT JOIN [ServiceTag] st ON st.id = a.serviceId\n"
                + "LEFT JOIN [FamilyProfile] fp ON fp.profileId = a.profileId\n"
                + "WHERE a.doctorId LIKE ?";

        ArrayList<Appointments> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, d.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Appointments(
                        String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getDate("plannedAt")),
                        String.valueOf(rs.getInt("status")),
                        String.valueOf(rs.getInt("branchId")),
                        String.valueOf(rs.getDate("createdAt")),
                        rs.getString("symptoms"),
                        new ServiceTag(
                                String.valueOf(rs.getInt("ServiceID")),
                                rs.getString("ServiceName")
                        ),
                        new FamilyProfile(
                                String.valueOf(rs.getString("ProfileID")),
                                rs.getString("Email"),
                                rs.getString("ProfileName"),
                                String.valueOf(rs.getDate("DoB")),
                                rs.getInt("Gender") == 1 ? "Male" : "Female",
                                rs.getString("Address"),
                                rs.getString("IdentityNumber"),
                                rs.getString("MedicalID"),
                                rs.getString("Ethnic"),
                                rs.getString("PhoneNumber"),
                                rs.getString("ProfilePicture")
                        )
                )
                );
            }
        } catch (SQLException e) {
            System.out.println("getListAppointmentByDoctor: " + e.getMessage());
        }
        return list.isEmpty() ? list : null;
    }

    public ArrayList<Appointments> getListAppointmentByDoctor(String doctorId) {
        String SQL = "SELECT a.id, a.plannedAt, a.status, a.branchId, a.createdAt, a.symptoms, st.id AS 'ServiceID', st.nametag AS 'ServiceName', fp.profileId AS 'ProfileID', fp.email AS 'Email', fp.name AS 'ProfileName', fp.birthDate AS 'DoB', fp.gender AS 'Gender', fp.address AS 'Address', fp.[identity] AS 'IdentityNumber', fp.medicalId AS 'MedicalID', fp.ethnic AS 'Ethnic', fp.phone AS 'PhoneNumber', fp.profilePicture AS 'ProfilePicture' FROM [Appointments] a\n"
                + "LEFT JOIN [ServiceTag] st ON st.id = a.serviceId\n"
                + "LEFT JOIN [FamilyProfile] fp ON fp.profileId = a.profileId\n"
                + "WHERE a.doctorId LIKE ? AND a.status != 0"
                + "ORDER BY a.createdAt DESC, a.status ASC";

        ArrayList<Appointments> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, doctorId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new Appointments(
                        String.valueOf(rs.getInt("id")),
                        String.valueOf(rs.getDate("plannedAt")),
                        String.valueOf(rs.getInt("status")),
                        String.valueOf(rs.getInt("branchId")),
                        String.valueOf(rs.getDate("createdAt")),
                        rs.getString("symptoms"),
                        new ServiceTag(
                                String.valueOf(rs.getInt("ServiceID")),
                                rs.getString("ServiceName")
                        ),
                        new FamilyProfile(
                                String.valueOf(rs.getString("ProfileID")),
                                rs.getString("Email"),
                                rs.getString("ProfileName"),
                                String.valueOf(rs.getDate("DoB")),
                                rs.getInt("Gender") == 1 ? "Male" : "Female",
                                rs.getString("Address"),
                                rs.getString("IdentityNumber"),
                                rs.getString("MedicalID"),
                                rs.getString("Ethnic"),
                                rs.getString("PhoneNumber"),
                                rs.getString("ProfilePicture")
                        )
                )
                );
            }
        } catch (SQLException e) {
            System.out.println("getListAppointmentByDoctor: " + e.getMessage());
        }
        return list.isEmpty() ? null : list;
    }

    public ArrayList<Appointments> getAppointmentsByDay(String date, String doctorId) {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT a.id, a.plannedAt, a.status, a.branchId, a.createdAt, a.symptoms, st.id AS 'ServiceID', st.nametag AS 'ServiceName', fp.profileId AS 'ProfileID', fp.email AS 'Email', fp.name AS 'ProfileName', fp.birthDate AS 'DoB', fp.gender AS 'Gender', fp.address AS 'Address', fp.[identity] AS 'IdentityNumber', fp.medicalId AS 'MedicalID', fp.ethnic AS 'Ethnic', fp.phone AS 'PhoneNumber', fp.profilePicture AS 'ProfilePicture' FROM [Appointments] a\n"
                + "LEFT JOIN [ServiceTag] st ON st.id = a.serviceId\n"
                + "LEFT JOIN [FamilyProfile] fp ON fp.profileId = a.profileId\n"
                + "WHERE CONVERT(date, plannedAt) = ? AND doctorId = ?\n"
                + "ORDER BY a.status ASC, a.plannedAt ASC";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setDate(1, java.sql.Date.valueOf(date));
            ps.setString(2, doctorId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("plannedAt");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String dateString = "";
                if (timestamp != null && !timestamp.toString().isEmpty()) {
                    dateString = format.format(timestamp);
                }
                list.add(new Appointments(
                        String.valueOf(rs.getInt("id")),
                        dateString,
                        String.valueOf(rs.getInt("status")),
                        String.valueOf(rs.getInt("branchId")),
                        String.valueOf(rs.getDate("createdAt")),
                        rs.getString("symptoms"),
                        new ServiceTag(
                                String.valueOf(rs.getInt("ServiceID")),
                                rs.getString("ServiceName")
                        ),
                        new FamilyProfile(
                                String.valueOf(rs.getString("ProfileID")),
                                rs.getString("Email"),
                                rs.getString("ProfileName"),
                                String.valueOf(rs.getDate("DoB")),
                                rs.getInt("Gender") == 1 ? "Male" : "Female",
                                rs.getString("Address"),
                                rs.getString("IdentityNumber"),
                                rs.getString("MedicalID"),
                                rs.getString("Ethnic"),
                                rs.getString("PhoneNumber"),
                                rs.getString("ProfilePicture")
                        )
                )
                );
//                System.out.println("ServiceName: " + rs.getString("ServiceName"));
            }
        } catch (SQLException e) {
            System.out.println("getAppointmentsByDay: " + e.getMessage());
        }
        return list;
    }

    public boolean updateAppointmentStatus(String id, int status) {
        String SQL = "UPDATE Appointments\n"
                + "SET status = ?\n"
                + "WHERE id = ?";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, status);
            ps.setInt(2, Integer.parseInt(id));

            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("updateAppointmentStatus: " + e.getMessage());
        }
        return false;
    }

    public ArrayList<Appointments> getListAppointmentsByProfileId(String profileId) {
        ArrayList<Appointments> list = new ArrayList<>();
        String sql = "SELECT A.* FROM FamilyProfile AS F JOIN Appointments AS A ON F.profileId = A.profileId WHERE F.profileId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(profileId));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getString("userId"));
                Doctor d = new Doctor(rs.getString("doctorId"));
                ServiceTag s = new ServiceTag(String.valueOf(rs.getInt("serviceId")));
                Branch b = new Branch(rs.getInt("branchId") + "");
                FamilyProfile p = new FamilyProfile(rs.getInt("profileId") + "");
                String appointmentDay = "";
                String appointmentTime = "";
                Appointments a = new Appointments(String.valueOf(rs.getInt("id")),
                        u, d, s,
                        String.valueOf(rs.getString("plannedAt")),
                        String.valueOf(rs.getInt("status")), b,
                        String.valueOf(rs.getDate("createdAt")),
                        rs.getString("symptoms"), p, appointmentDay, appointmentTime);
                list.add(a);
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("getListAppointmentsByProfileId: " + e);
        }
        return list;
    }

    public static void main(String[] args) {
        AppointmentsDAO ad = new AppointmentsDAO();
        ArrayList<Appointments> list = ad.getListAppointmentsByProfileId("59");
        for (Appointments appointments : list) {
            System.out.println(appointments);
        }
    }

    public ArrayList<Appointments> getListAppointmentsByPatientName(String search, String ownerId) {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT a.id,a.status,a.plannedAt,d.displayName,d.profilePicture,fp.profileId,fp.name,\n"
                + "fp.birthDate,fp.phone,fp.gender,fp.address FROM Appointments a\n"
                + "JOIN Doctor d ON a.doctorId = d.id\n"
                + "JOIN ServiceTag st ON a.serviceId = st.id\n"
                + "JOIN FamilyProfile fp ON a.profileId = fp.profileId\n"
                + "WHERE fp.ownerId = ?\n"
                + "ORDER BY a.status ASC";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gender = "Nam";
                if (rs.getInt("gender") == 1) {
                    gender = "Nữ";
                }
                int i = rs.getInt("status");
                String status = "Chờ xác nhận";
                switch (i) {
                    case 1:
                        status = "Ðang chờ khám";
                        break;
                    case 2:
                        status = "Ðã khám xong";
                        break;
                    case 3:
                        status = "Ðã huỷ";
                        break;
                    default:
                        break;
                }
                Appointments a = new Appointments();
                a.setId(rs.getString("id"));
                a.setStatus(status);
                a.setPlannedAt(rs.getString("plannedAt"));
                System.out.println("displayName " + rs.getString("displayName"));
                a.getDoctor().setDisplayName(rs.getString("displayName"));
                a.getDoctor().setProfilePicture(rs.getString("profilePicture"));
                a.getFp().setName(rs.getString("name"));
                a.getFp().setBirthDate(rs.getString("birthDate"));
                a.getFp().setPhone(rs.getString("phone"));
                a.getFp().setGender(gender);
                a.getFp().setAddress(rs.getString("address"));
                list.add(a);
            }
            Iterator<Appointments> iterator = list.iterator();
            while (iterator.hasNext()) {
                Appointments a = iterator.next();
                if (!a.getFp().getName().toLowerCase().contains(search.toLowerCase())) {
                    iterator.remove();
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println("AppointmentsDAO.getListAppointments: " + e.getMessage());
        }
        return null;

    }

    public ArrayList<Appointments> getListAppointmentsByPhone(String search, String ownerId) {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT a.id,a.status,a.plannedAt,d.displayName,d.profilePicture,fp.profileId,fp.name,\n"
                + "fp.birthDate,fp.phone,fp.gender,fp.address FROM Appointments a\n"
                + "JOIN Doctor d ON a.doctorId = d.id\n"
                + "JOIN ServiceTag st ON a.serviceId = st.id\n"
                + "JOIN FamilyProfile fp ON a.profileId = fp.profileId\n"
                + "WHERE fp.ownerId = ?\n"
                + "ORDER BY a.status ASC";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, ownerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String gender = "Nam";
                if (rs.getInt("gender") == 1) {
                    gender = "Nữ";
                }
                int i = rs.getInt("status");
                String status = "Chờ xác nhận";
                switch (i) {
                    case 1:
                        status = "Ðang chờ khám";
                        break;
                    case 2:
                        status = "Ðã khám xong";
                        break;
                    case 3:
                        status = "Ðã huỷ";
                        break;
                    default:
                        break;
                }
                Appointments a = new Appointments();
                a.setId(rs.getString("id"));
                a.setStatus(status);
                a.setPlannedAt(rs.getString("plannedAt"));
                System.out.println("displayName " + rs.getString("displayName"));
                a.getDoctor().setDisplayName(rs.getString("displayName"));
                a.getDoctor().setProfilePicture(rs.getString("profilePicture"));
                a.getFp().setName(rs.getString("name"));
                a.getFp().setBirthDate(rs.getString("birthDate"));
                a.getFp().setPhone(rs.getString("phone"));
                a.getFp().setGender(gender);
                a.getFp().setAddress(rs.getString("address"));
                list.add(a);
            }
            Iterator<Appointments> iterator = list.iterator();
            while (iterator.hasNext()) {
                Appointments a = iterator.next();
                if (!a.getFp().getPhone().toLowerCase().contains(search.toLowerCase())) {
                    iterator.remove();
                }
            }
            return list;
        } catch (SQLException e) {
            System.out.println("AppointmentsDAO.getListAppointments: " + e.getMessage());
        }
        return null;

    }
}
