/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Appointments;
import Models.Doctor;
import Models.FamilyProfile;
import Models.ServiceTag;
import Models.User;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author hoang
 */
public class AppointmentsDAO extends DBContext {

    public ArrayList<Appointments> getListAppointments() {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Appointments]";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointments a = new Appointments(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)),
                        String.valueOf(rs.getDate(5)),
                        String.valueOf(rs.getInt(6)));
                list.add(a);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("AppointmentsDAO.getListAppointments: " + e.getMessage());
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

    public List<Appointments> getListAppointmentsByOwnerId(String ownerId) {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Appointments] where userId=?";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Appointments a = new Appointments(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)),
                        String.valueOf(rs.getDate(5)),
                        String.valueOf(rs.getInt(6)));
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

    public ArrayList<Appointments> getAppointmentsByDay(String date, String doctorId) {
        ArrayList<Appointments> list = new ArrayList<>();
        String SQL = "SELECT a.id, a.plannedAt, a.status, a.branchId, a.createdAt, a.symptoms, st.id AS 'ServiceID', st.nametag AS 'ServiceName', fp.profileId AS 'ProfileID', fp.email AS 'Email', fp.name AS 'ProfileName', fp.birthDate AS 'DoB', fp.gender AS 'Gender', fp.address AS 'Address', fp.[identity] AS 'IdentityNumber', fp.medicalId AS 'MedicalID', fp.ethnic AS 'Ethnic', fp.phone AS 'PhoneNumber', fp.profilePicture AS 'ProfilePicture' FROM [Appointments] a\n"
                + "LEFT JOIN [ServiceTag] st ON st.id = a.serviceId\n"
                + "LEFT JOIN [FamilyProfile] fp ON fp.profileId = a.profileId\n"
                + "WHERE CONVERT(date, plannedAt) = ? AND doctorId = ?\n"
                + "ORDER BY a.status ASC, a.plannedAt ASC";

        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setDate(1, java.sql.Date.valueOf(date));
            ps.setString(2, doctorId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("plannedAt");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String dateString = "";
                if (timestamp != null && !timestamp.toString().isEmpty())
                    dateString = format.format(timestamp);
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
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, status);
            ps.setInt(2, Integer.parseInt(id));
            
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("updateAppointmentStatus: " + e.getMessage());
        }
        return false;
    }
}
