/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Controllers.PasswordEncryption;
import Models.FamilyProfile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.User;
import java.sql.Date;
import Models.Province;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author hoang
 */
public class UserDAO extends DBContext {

    public ArrayList<User> getAllUsers() { // TU BINH
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Province p = new Province(String.valueOf(rs.getInt(8)));
                User u = new User(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getDate(5)),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        p,
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        String.valueOf(rs.getDate(14)));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println("getAllUsers: " + e);
        }
        return list;
    }

    public User login(String email, String password) {
        System.out.println("USER - LOGIN - Method:");
        String sql = "SELECT id, [name], email, password from [user] WHERE email = ? and password IS NOT NULL";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            byte[] salt = PasswordEncryption.generateSalt();
            String encryptedPassword = PasswordEncryption.encryptPassword(password, salt).substring(0, 64);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (PasswordEncryption.comparePasswords(password, rs.getString("password"))) {
                    User u = new User(rs.getString("id"),
                            rs.getString("email"),
                            rs.getString("name"));
                    System.out.println(u);
                    return u;
                }
            }
        } catch (SQLException e) {
            System.out.println("login: " + e);
        }
        System.out.println("END - USER - LOGIN - Method");
        return null;
    }

    // Function: get object of user by email - GOOGLE ACCOUNT:
    public User login(String email) {
        String sql = "SELECT id, name, email FROM [User] WHERE email = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("name"));
                System.out.println(u);
                return u;
            }
        } catch (SQLException e) {
            System.out.println("login: " + e);
        }
        return null;
    }

    public boolean registerUser(User u) {
        byte[] salt = PasswordEncryption.generateSalt();
        String encryptedPassword = PasswordEncryption.encryptPassword(u.getPassword(), salt);
        try {
            String sql;
            PreparedStatement st;
            // Check whether email is registered - having password:
            UserDAO ud = new UserDAO();
            User userRegistered = ud.getUserRegistered(u.getEmail());
            User guestInUser = ud.getGuestInUserTable(u.getEmail());
            if (userRegistered == null && guestInUser == null) { // => insert new to [User]
                sql = "INSERT INTO [dbo].[User]\n"
                        + "           ([id]\n"
                        + "           ,[email]\n"
                        + "           ,[password]\n"
                        + "           ,[name]\n"
                        + "           ,[birthDate]\n"
                        + "           ,[gender]\n"
                        + "           ,[address]\n"
                        + "           ,[phone]\n"
                        + "           ,[createdAt])\n"
                        + "     VALUES"
                        + "           (?,?,?,?,?,?,?,?,?)";
                st = connection.prepareStatement(sql);
                st.setString(1, this.getLastUserId() + 1 + "");
                st.setString(2, u.getEmail());
                st.setString(3, encryptedPassword);
                st.setString(4, u.getName());
                st.setDate(5, Date.valueOf(u.getBirthDate()));
                st.setBoolean(6, (u.getGender().equals("0")) ? Boolean.TRUE : Boolean.FALSE);
                st.setString(7, u.getAddress());
                st.setString(8, u.getPhone());
                st.setString(9, u.getCreatedAt());
                st.execute();
                return true;
            } else if (guestInUser != null) { // update in [User]
                sql = "UPDATE [dbo].[User]\n"
                        + "   SET [password] = ?\n"
                        + "      ,[name] = ?\n"
                        + "      ,[birthDate] = ?\n"
                        + "      ,[gender] = ?\n"
                        + "      ,[address] = ?\n"
                        + "      ,[phone] = ?\n"
                        + "      ,[createdAt] = ?\n"
                        + " WHERE id = ?";
                st = connection.prepareStatement(sql);
                st.setString(1, encryptedPassword);
                st.setString(2, u.getName());
                st.setDate(3, Date.valueOf(u.getBirthDate()));
                st.setBoolean(4, (u.getGender().equals("0")) ? Boolean.TRUE : Boolean.FALSE);
                st.setString(5, u.getAddress());
                st.setString(6, u.getPhone());
                st.setString(7, u.getCreatedAt());
                st.setString(8, guestInUser.getId());
                st.execute();
                User userfp = ud.getUserById(guestInUser.getId());
                // Add profile of user to FamilyProfile:
                FamilyProfileDAO fd = new FamilyProfileDAO();
                fd.addNewUserProfile(new FamilyProfile(userfp.getEmail(),
                        userfp.getName(),
                        userfp.getBirthDate(),
                        userfp.getGender(),
                        userfp.getAddress(),
                        userfp.getIdentity(),
                        userfp.getMedicalId(),
                        userfp.getEthnic(),
                        userfp.getPhone(),
                        userfp.getCreatedAt(),
                        "0",
                        guestInUser.getId()));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("registerUser: " + e);
        }
        return false;
    }

    public boolean registerUserGoogleAccount(User u) {
        byte[] salt = PasswordEncryption.generateSalt();
        String encryptedPassword = PasswordEncryption.encryptPassword(u.getPassword(), salt);
        try {
            String sql;
            PreparedStatement st;
            // Check whether email is registered - having password:
            UserDAO ud = new UserDAO();
            User userRegistered = ud.getUserRegistered(u.getEmail());
            User guestInUser = ud.getGuestInUserTable(u.getEmail());
            if (userRegistered == null && guestInUser == null) { // => insert new to [User]
                sql = "INSERT INTO [dbo].[User]\n"
                        + "           ([id]\n"
                        + "           ,[email]\n"
                        + "           ,[password]\n"
                        + "           ,[name]\n"
                        + "           ,[createdAt])\n"
                        + "     VALUES"
                        + "           (?,?,?,?,?)";
                st = connection.prepareStatement(sql);
                st.setString(1, this.getLastUserId() + 1 + "");
                st.setString(2, u.getEmail());
                st.setString(3, encryptedPassword);
                st.setString(4, u.getName());
                st.setString(5, u.getCreatedAt());
                st.execute();
                return true;
            } else if (guestInUser != null) { // update in [User]
                sql = "UPDATE [dbo].[User]\n"
                        + "   SET [password] = ?\n"
                        + "      ,[name] = ?\n"
                        + "      ,[createdAt] = ?\n"
                        + " WHERE id = ?";
                st = connection.prepareStatement(sql);
                st.setString(1, encryptedPassword);
                st.setString(2, u.getName());
                st.setString(3, u.getCreatedAt());
                st.setString(4, guestInUser.getId());
                st.execute();
                User userfp = ud.getUserById(guestInUser.getId());
                // Add profile of user to FamilyProfile:
                FamilyProfileDAO fd = new FamilyProfileDAO();
                fd.addNewUserProfile(new FamilyProfile(userfp.getEmail(),
                        userfp.getName(),
                        userfp.getBirthDate(),
                        "",
                        userfp.getAddress(),
                        userfp.getIdentity(),
                        userfp.getMedicalId(),
                        userfp.getEthnic(),
                        userfp.getPhone(),
                        userfp.getCreatedAt(),
                        "0",
                        guestInUser.getId()));
                return true;
            }
        } catch (SQLException e) {
            System.out.println("registerUserGoogleAccount: " + e);
        }
        return false;
    }

    public int getLastUserId() {
        // First, cast the id from nvarchar to int:
        String sql = "SELECT *\n"
                + "FROM [User]\n"
                + "ORDER BY CAST(id AS INT) DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return Integer.parseInt(rs.getString(1));
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("getLastUserId: " + e);

        }
        return 0;
    }

    private int numberRecord;

    public int getNumberRecord() {
        return this.numberRecord;
    }

    public int countAllUser() {
        String SQL = "select COUNT(id) from [User] WHERE id IS NOT NULL";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        } catch (Exception e) {
            System.out.println("countAllUser " + e.getMessage());
        }
        return -1;
    }

    public boolean createUser(User obj) {
        String SQL = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, Integer.parseInt(obj.getId()));
            pstm.setString(2, obj.getEmail());
            pstm.setString(3, obj.getPassword());
            pstm.setString(4, obj.getName());
            pstm.setString(5, obj.getBirthDate());
            pstm.setString(6, obj.getGender());
            pstm.setString(7, obj.getAddress());
            pstm.setInt(8, Integer.parseInt(obj.getProvince().getId()));
            pstm.setString(9, obj.getIdentity());
            pstm.setInt(10, Integer.parseInt(obj.getMedicalId()));
            pstm.setString(11, obj.getEthnic());
            pstm.setString(12, obj.getPhone());
            pstm.setString(13, obj.getProfilePicture());
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("createUser " + e.getMessage());

        }
        return false;
    }

    public User getUserRegistered(String email) {
        String sql = "SELECT id, password FROM [User]\n"
                + "WHERE email = ? and password is not null";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println("getUserRegistered: " + e);
        }
        return null;
    }

    public User getGuestInUserTable(String email) {
        String sql = "SELECT id FROM [User]\n"
                + "WHERE email = ? and password is null";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println("getUserRegistered: " + e);
        }
        return null;
    }

    public User getUserNotRegistered(String email) {
//        String sql = "SELECT id FROM [User]\n"
//                + "WHERE password IS NULL AND email = ?";
        String sql = "SELECT id, password FROM [User]\n"
                + "WHERE email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"), rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("getUserNotRegistered: " + e);
        }
        return null;
    }

    public boolean addUserNotRegistered(String emailPatient) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([id]\n"
                + "           ,[email]\n"
                + "           ,[createdAt])\n"
                + "     VALUES\n"
                + "           (?,?,?)";
        UserDAO ud = new UserDAO();
        String id = String.valueOf(ud.getLastUserId() + 1);
        java.util.Date currentDate = new java.util.Date();
        java.sql.Timestamp createdAt = new java.sql.Timestamp(currentDate.getTime());
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, emailPatient);
            st.setString(3, createdAt.toString());
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("addUserNotRegistered: " + e);
        }
        return false;
    }

    public boolean editUserById(User obj) {
        String SQL = "UPDATE User SET email = ?, password = ?, name = ?, birthDate = ?, gender = ?, address = ?, provinceId = ?, [identity] = ?, medicalId = ?, ethnic = ?, phone = ?, profilePicture = ? WHERE id = ? ";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, obj.getEmail());
            pstm.setString(2, obj.getPassword());
            pstm.setString(3, obj.getName());
            pstm.setString(4, obj.getBirthDate());
            pstm.setString(5, obj.getGender());
            pstm.setString(6, obj.getAddress());
            pstm.setInt(7, Integer.parseInt(obj.getProvince().getId()));
            pstm.setString(8, obj.getIdentity());
            pstm.setInt(9, Integer.parseInt(obj.getMedicalId()));
            pstm.setString(10, obj.getEthnic());
            pstm.setString(11, obj.getPhone());
            pstm.setString(12, obj.getProfilePicture());
            pstm.setInt(13, Integer.parseInt(obj.getId()));
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("editUser " + e.getMessage());
        }
        return false;
    }

    public String getUserEmailById(String id) {
        String SQL = "SELECT email FROM [User] WHERE id = ?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                return email;
            }
        } catch (Exception e) {
            System.out.println("getUserEmailById" + e.getMessage());
        }
        return null;
    }

    public String getUserIdByEmail(String email) {
        String SQL = "SELECT id FROM [User] WHERE email = ?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                return id;
            }
        } catch (Exception e) {
            System.out.println("getUserIdByEmail" + e.getMessage());
        }
        return null;
    }

    public ArrayList<User> getAllUser() {
        ArrayList<User> list = new ArrayList<>();
        String SQL = "SELECT [User].id[uId],email,password, [User].name[uName],birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture,createdAt "
                + " , Province.name[pName]"
                + " FROM [User]"
                + " JOIN Province on [User].provinceId = Province.id";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("uId");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String uName = rs.getString("uName");
                String birthDate = rs.getString("birthDate");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String provinceId = rs.getString("provinceId");
                String identity = rs.getString("identity");
                String medicalId = rs.getString("medicalId");
                String ethnic = rs.getString("ethnic");
                String phone = rs.getString("phone");
                String profilePicture = rs.getString("profilePicture");
                String createdAt = rs.getString("createdAt");
                String pName = rs.getString("pName");
                Province province = new Province(provinceId, pName);
                User user = new User(id, email, password, uName, birthDate, gender, address, province, identity, medicalId, ethnic, phone, profilePicture, createdAt);
                list.add(user);
            }
        } catch (Exception e) {
            System.out.println("getAllUser" + e.getMessage());
        }
        return list;
    }

    public ArrayList<User> getListUser(int offset, int fetch) {
        ArrayList<User> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String SQL = "SELECT [User].id[uId],email,password, [User].name[uName],birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture "
                + " , Province.name[pName]"
                + " FROM [User]"
                + " LEFT JOIN Province on [User].provinceId = Province.id"
                + " GROUP BY [User].id,email,password, [User].name,birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture "
                + " , Province.name"
                + " HAVING [User].id IS NOT NULL"
                + " ORDER BY COUNT([User].id) DESC"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, offset);
            pstm.setInt(2, fetch);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("uId");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String uName = rs.getString("uName");
                String birthDate = rs.getDate("birthDate") != null ? format.format(rs.getDate("birthDate")) : "-";
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String provinceId = rs.getString("provinceId");
                String identity = rs.getString("identity");
                String medicalId = rs.getString("medicalId");
                String ethnic = rs.getString("ethnic");
                String phone = rs.getString("phone");
                String profilePicture = rs.getString("profilePicture");
                String pName = rs.getString("pName");
                Province province = new Province(provinceId, pName);
                User user = new User(id, email, password, uName, birthDate, gender, address, province, identity, medicalId, ethnic, phone, profilePicture);
                list.add(user);
            }
        } catch (Exception e) {
            System.out.println("getListUser: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<User> getMoreListUser(int offset, int fetch) {
        ArrayList<User> list = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String SQL = "SELECT [User].id[uId],email,password, [User].name[uName],birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture, createdAt, createBy, modifyAt, modifyBy "
                + " , Province.name[pName]"
                + " FROM [User]"
                + " LEFT JOIN Province on [User].provinceId = Province.id"
                + " GROUP BY [User].id,email,password, [User].name,birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture, createdAt, createBy, modifyAt, modifyBy "
                + " , Province.name"
                + " HAVING [User].id IS NOT NULL"
                + " ORDER BY COUNT([User].id) DESC"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, offset);
            pstm.setInt(2, fetch);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("uId");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String uName = rs.getString("uName");
                String birthDate = rs.getDate("birthDate") != null ? format.format(rs.getDate("birthDate")) : "-";
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String provinceId = rs.getString("provinceId");
                String identity = rs.getString("identity");
                String medicalId = rs.getString("medicalId");
                String ethnic = rs.getString("ethnic");
                String phone = rs.getString("phone");
                String profilePicture = rs.getString("profilePicture");
//                String createdAt = rs.getString("createdAt");
                Timestamp ts = rs.getTimestamp("createdAt");
                String createdAt;
                if (ts!=null){
                    createdAt = format.format(ts);
                }else{
                    createdAt = "";
                }
                String createBy = rs.getString("createBy");
//                String modifyAt = rs.getString("modifyAt");
                ts = rs.getTimestamp("modifyAt");
                String modifyAt;
                if (ts!=null){
                    modifyAt = format.format(ts);
                }else{
                    modifyAt = "";
                }
                String modifyBy = rs.getString("modifyBy");
                String pName = rs.getString("pName");
                Province province = new Province(provinceId, pName);
                User user = new User(id, email, password, uName, birthDate, gender, address, province, identity, medicalId, ethnic, phone, profilePicture, createdAt, createBy, modifyAt, modifyBy);
                list.add(user);
            }
        } catch (Exception e) {
            System.out.println("getMoreListUser" + e.getMessage());
        }
        return list;
    }

    public ArrayList<User> searchListUser(User user,int offset, int fetch) {
        ArrayList<User> list = new ArrayList<>();
        if (user.getId().isEmpty()) user.setId("%");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String SQL = "SELECT [User].id[uId],email,password, [User].name[uName],birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture "
                + " , Province.name[pName]"
                + " FROM [User]"
                + " JOIN Province on [User].provinceId = Province.id"
                + " WHERE [User].id like ? AND email like ? AND [User].name COLLATE SQL_Latin1_General_CP1_CI_AI like ? "
                + " AND birthDate like ? AND gender like ? AND address COLLATE SQL_Latin1_General_CP1_CI_AI like ? AND provinceId like ? AND [identity] like ? "
                + " AND medicalId like ? AND ethnic COLLATE SQL_Latin1_General_CP1_CI_AI like ? AND phone like ? "
                + " GROUP BY [User].id,email,password, [User].name,birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture "
                + " , Province.name"
                + " HAVING [User].id IS NOT NULL"
                + " ORDER BY COUNT([User].id) DESC"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String SQL2 = "SELECT count(*) " +
                " FROM [User]"
                + " JOIN Province on [User].provinceId = Province.id"
                + " WHERE [User].id like ? AND email like ? AND [User].name COLLATE SQL_Latin1_General_CP1_CI_AI like ? "
                + " AND birthDate like ? AND gender like ? AND address COLLATE SQL_Latin1_General_CP1_CI_AI like ? AND provinceId like ? AND [identity] like ? "
                + " AND medicalId like ? AND ethnic COLLATE SQL_Latin1_General_CP1_CI_AI like ? AND phone like ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL2)){
            pstm.setString(1, user.getId()); 
            pstm.setString(2, "%"+user.getEmail()+"%"); 
            pstm.setNString(3, "%"+user.getName()+"%");
            pstm.setString(4, "%"+user.getBirthDate()+"%"); 
            pstm.setString(5, "%"+user.getGender()+"%"); 
            pstm.setNString(6, "%"+user.getAddress()+"%"); 
            pstm.setString(7, "%"+user.getProvince().getId()+"%"); 
            pstm.setString(8, "%"+user.getIdentity()+"%"); 
            pstm.setString(9, "%"+user.getMedicalId()+"%"); 
            pstm.setNString(10, "%"+user.getEthnic()+"%"); 
            pstm.setString(11, "%"+user.getPhone()+"%"); 
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                numberRecord = rs.getInt(1);
            }
        }catch(Exception e) {
            System.out.println("search user " + e.getMessage());
        }
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, user.getId()); 
            pstm.setString(2, "%"+user.getEmail()+"%"); 
            pstm.setNString(3, "%"+user.getName()+"%"); 
            pstm.setString(4, "%"+user.getBirthDate()+"%"); 
            pstm.setString(5, "%"+user.getGender()+"%"); 
            pstm.setNString(6, "%"+user.getAddress()+"%"); 
            pstm.setString(7, "%"+user.getProvince().getId()+"%"); 
            pstm.setString(8, "%"+user.getIdentity()+"%"); 
            pstm.setString(9, "%"+user.getMedicalId()+"%"); 
            pstm.setNString(10, "%"+user.getEthnic()+"%"); 
            pstm.setString(11, "%"+user.getPhone()+"%"); 
            pstm.setInt(12, offset);
            pstm.setInt(13, fetch);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("uId");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String uName = rs.getString("uName");
                String birthDate = format.format(rs.getDate("birthDate"));
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String provinceId = rs.getString("provinceId");
                String identity = rs.getString("identity");
                String medicalId = rs.getString("medicalId");
                String ethnic = rs.getString("ethnic");
                String phone = rs.getString("phone");
                String profilePicture = rs.getString("profilePicture");
                String pName = rs.getString("pName");
                Province province = new Province(provinceId, pName);
                User findUser = new User(id, email, password, uName, birthDate, gender, address, province, identity, medicalId, ethnic, phone, profilePicture);
                list.add(findUser);
            }
        } catch (Exception e) {
            System.out.println("searchListUser " + e.getMessage());
        }
        return list;
    }
    public ArrayList<User> searchMoreListUser(User user,int offset, int fetch) {
        ArrayList<User> list = new ArrayList<>();
        if (user.getId().isEmpty()) user.setId("%");
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String SQL = "SELECT [User].id[uId],email,password, [User].name[uName],birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture,createdAt,createBy,modifyAt,modifyBy "
                + " , Province.name[pName]"
                + " FROM [User]"
                + " JOIN Province on [User].provinceId = Province.id"
                + " WHERE [User].id like ? AND email like ? AND [User].name COLLATE SQL_Latin1_General_CP1_CI_AI like ? "
                + " AND birthDate like ? AND gender like ? AND address COLLATE SQL_Latin1_General_CP1_CI_AI like  ? AND provinceId like ? AND [identity] like ? "
                + " AND medicalId like ? AND ethnic COLLATE SQL_Latin1_General_CP1_CI_AI like ? AND phone like ? "
                + " GROUP BY [User].id,email,password, [User].name,birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture,createdAt,createBy,modifyAt,modifyBy "
                + " , Province.name"
                + " HAVING [User].id IS NOT NULL"
                + " ORDER BY COUNT([User].id) DESC"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String SQL2 = "SELECT count(*) " +
                " FROM [User]"
                + " JOIN Province on [User].provinceId = Province.id"
                + " WHERE [User].id like ? AND email like ? AND [User].name COLLATE SQL_Latin1_General_CP1_CI_AI like ? "
                + " AND birthDate like ? AND gender like ? AND address COLLATE SQL_Latin1_General_CP1_CI_AI like  ? AND provinceId like ? AND [identity] like ? "
                + " AND medicalId like ? AND ethnic COLLATE SQL_Latin1_General_CP1_CI_AI like ? AND phone like ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL2)){
            pstm.setString(1, user.getId()); 
            pstm.setString(2, "%"+user.getEmail()+"%"); 
            pstm.setNString(3, "%"+user.getName()+"%"); 
            pstm.setString(4, "%"+user.getBirthDate()+"%"); 
            pstm.setString(5, "%"+user.getGender()+"%"); 
            pstm.setNString(6, "%"+user.getAddress()+"%"); 
            pstm.setString(7, "%"+user.getProvince().getId()+"%"); 
            pstm.setString(8, "%"+user.getIdentity()+"%"); 
            pstm.setString(9, "%"+user.getMedicalId()+"%"); 
            pstm.setNString(10, "%"+user.getEthnic()+"%"); 
            pstm.setString(11, "%"+user.getPhone()+"%"); 
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                numberRecord = rs.getInt(1);
            }
        }catch(Exception e) {
            System.out.println("search more user " + e.getMessage());
        }
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, user.getId()); 
            pstm.setString(2, "%"+user.getEmail()+"%"); 
            pstm.setNString(3, "%"+user.getName()+"%"); 
            pstm.setString(4, "%"+user.getBirthDate()+"%"); 
            pstm.setString(5, "%"+user.getGender()+"%"); 
            pstm.setNString(6, "%"+user.getAddress()+"%"); 
            pstm.setString(7, "%"+user.getProvince().getId()+"%"); 
            pstm.setString(8, "%"+user.getIdentity()+"%"); 
            pstm.setString(9, "%"+user.getMedicalId()+"%"); 
            pstm.setNString(10, "%"+user.getEthnic()+"%"); 
            pstm.setString(11, "%"+user.getPhone()+"%"); 
            pstm.setInt(12, offset);
            pstm.setInt(13, fetch);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("uId");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String uName = rs.getString("uName");
                String birthDate = format.format(rs.getDate("birthDate"));
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String provinceId = rs.getString("provinceId");
                String identity = rs.getString("identity");
                String medicalId = rs.getString("medicalId");
                String ethnic = rs.getString("ethnic");
                String phone = rs.getString("phone");
                String profilePicture = rs.getString("profilePicture");
//                String createdAt = rs.getString("createdAt");
                Timestamp ts = rs.getTimestamp("createdAt");
                String createdAt;
                if (ts!=null){
                    createdAt = format.format(ts);
                }else{
                    createdAt = "";
                }
                String createBy = rs.getString("createBy");
//                String modifyAt = rs.getString("modifyAt");
                ts = rs.getTimestamp("modifyAt");
                String modifyAt;
                if (ts!=null){
                    modifyAt = format.format(ts);
                }else{
                    modifyAt = "";
                }
                String modifyBy = rs.getString("modifyBy");
                String pName = rs.getString("pName");
                Province province = new Province(provinceId, pName);
                User findUser = new User(id, email, password, uName, birthDate, gender, address, province, identity, medicalId, ethnic, phone, profilePicture,createdAt,createBy,modifyAt,modifyBy);
                list.add(findUser);
            }
        } catch (Exception e) {
            System.out.println("searchMoreListUser" + e.getMessage());
        }
        return list;
    }

    public User getUserById(String id) {
        String SQL = "SELECT [User].id[uId],email,password, [User].name[uName],birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture,createdAt "
                + " , Province.name[pName]"
                + " FROM [User]"
                + " LEFT JOIN Province on [User].provinceId = Province.id"
                + " WHERE [User].id = ?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String uName = rs.getString("uName");
                String birthDate = rs.getString("birthDate");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String provinceId = rs.getString("provinceId");
                String identity = rs.getString("identity");
                String medicalId = rs.getString("medicalId");
                String ethnic = rs.getString("ethnic");
                String phone = rs.getString("phone");
                String profilePicture = rs.getString("profilePicture");
                String createdAt = rs.getString("createdAt");
                String pName = rs.getString("pName");
                Province province = new Province(provinceId, pName);
                User user = new User(id, email, password, uName, birthDate, gender, address, province, identity, medicalId, ethnic, phone, profilePicture, createdAt);

                return user;
            }
        } catch (Exception e) {
            System.out.println("getUserById" + e.getMessage());
        }
        return null;
    }

    public boolean addUser(User user) {
        String SQL = "INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, Integer.parseInt(user.getId()));
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPassword());
            pstm.setString(4, user.getName());
            pstm.setString(5, user.getBirthDate());
            pstm.setString(6, user.getGender());
            pstm.setString(7, user.getAddress());
            pstm.setInt(8, Integer.parseInt(user.getProvince().getId()));
            pstm.setString(9, user.getIdentity());
            pstm.setString(10, user.getMedicalId());
            pstm.setString(11, user.getEthnic());
            pstm.setString(12, user.getPhone());
            pstm.setString(13, user.getProfilePicture());
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("addUser " + e.getMessage());
        }
        return false;
    }
    //thu
    public boolean addUserByAdmin(User user) {
        String SQL = "INSERT [dbo].[User] ([id], [email], [password], [name], [birthDate], [gender], [address], [provinceId], [identity], [medicalId], [ethnic], [phone], [profilePicture], [createdAt],[createBy]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE(),?)";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, Integer.parseInt(user.getId()));
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getPassword());
            pstm.setString(4, user.getName());
            pstm.setString(5, user.getBirthDate());
            pstm.setString(6, user.getGender());
            pstm.setString(7, user.getAddress());
            pstm.setInt(8, Integer.parseInt(user.getProvince().getId()));
            pstm.setString(9, user.getIdentity());
            pstm.setString(10, user.getMedicalId());
            pstm.setString(11, user.getEthnic());
            pstm.setString(12, user.getPhone());
            pstm.setString(13, user.getProfilePicture());
            pstm.setString(14, user.getCreateBy());
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("addUser " + e.getMessage());
        }
        return false;
    }public Boolean setUserById(User user) {
        String SQL = "UPDATE [User] SET email = ?, password = ?, name = ?, birthDate = ?, gender = ?, address = ?, provinceId = ?, [identity] = ?, medicalId = ?, ethnic = ?, phone = ?, profilePicture = ?, modifyAt=GETDATE(), modifyBy = ? WHERE id = ? ";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, user.getEmail());
            pstm.setString(2, user.getPassword());
            pstm.setString(3, user.getName());
            pstm.setString(4, user.getBirthDate());
            pstm.setString(5, user.getGender());
            pstm.setString(6, user.getAddress());
            pstm.setString(7, user.getProvince().getId());
            pstm.setString(8, user.getIdentity());
            pstm.setString(9, user.getMedicalId());
            pstm.setString(10, user.getEthnic());
            pstm.setString(11, user.getPhone());
            pstm.setString(12, user.getProfilePicture());
            pstm.setString(13, user.getModifyBy());
            pstm.setString(14, user.getId());
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("setUserById " + e.getMessage());
        }
        return false;
    }

    public String generateId() {
        String SQL = "select top(1) id from [User] order by id DESC";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            String number = null;
            while (rs.next()) {
                number = rs.getString(1);
            }
            number = Integer.parseInt(number) + 1 + "";
            return number;
        } catch (Exception e) {
            System.out.println("generateId user" + e.getMessage());
        }
        return null;
    }

    //thu
    public ArrayList<String> getTitleTableUser() {
        ArrayList<String> list = new ArrayList<>();
        String SQL = "SELECT name FROM NewsCategory WHERE parentId = (select id from NewsCategory Where name like 'titleTableUser')";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("name");
                list.add(title);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getTitleTableUser " + e.getMessage());
        }
        return null;
    }

    //thu
    public ArrayList<String> getMoreTitleTableUser() {
        ArrayList<String> list = new ArrayList<>();
        String SQL = "SELECT name FROM NewsCategory WHERE parentId = (select id from NewsCategory WHERE name like 'titleTableUser') "
                + " OR parentId = (select id from NewsCategory WHERE name like 'LoadMoreTitle')";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("name");
                list.add(title);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getMoreTitleTableUser " + e.getMessage());
        }
        return null;
    }

    public String getIdByEmail(String email) {
        System.out.println("UserDAO.getIdByEmail: email:" + email);
        String sql = "SELECT id FROM [User] WHERE email=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (NumberFormatException | SQLException e) {
            System.out.println("UserDAO.getIdByEmail: " + e);
        }
        return "";
    }

    public boolean editUserByFp(FamilyProfile fp) {
                String SQL = "UPDATE [dbo].[User] SET name = ?, birthDate = ?, gender = ?, address = ?, [identity] = ?,"
                        + "medicalId = ?, ethnic = ?, phone = ?, profilePicture = ? WHERE id = ?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            int gender = 0;
            if (fp.getGender().equals("Female"))
                gender = 1;
            pstm.setString(1, fp.getName());
            pstm.setString(2, fp.getBirthDate());
            pstm.setInt(3, gender);
            pstm.setString(4, fp.getAddress());
            pstm.setString(5, fp.getIdentity());
            pstm.setString(6, fp.getMedicalId());
            pstm.setString(7, fp.getEthnic());
            pstm.setString(8, fp.getPhone());
            pstm.setString(9, fp.getProfilePicture());
            pstm.setString(10, fp.getOwnerId());
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("editUser " + e.getMessage());
        }
        return false;
    }
}
