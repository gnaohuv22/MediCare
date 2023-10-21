/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Controllers.PasswordEncryption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.User;
import java.sql.Date;
import Models.Province;

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

    // Function: get object of user by username and password: - TU BINH
//    public User login1(String email, String password) {
//        String sql = "SELECT * FROM [User] WHERE email = ?";
//
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setString(1, email);
//            byte[] salt = PasswordEncryption.generateSalt();
//            String encryptedPassword = PasswordEncryption.encryptPassword(password, salt).substring(0, 64);
//            ResultSet rs = st.executeQuery();
//            if (rs.next()) {
//                if (PasswordEncryption.comparePasswords(password, rs.getString("password"))) {
//                    User u = new User(rs.getString(1),
//                            rs.getString(2),
//                            rs.getString(3),
//                            rs.getString(4),
//                            String.valueOf(rs.getDate(5)),
//                            String.valueOf(rs.getInt(6)),
//                            rs.getString(7),
//                            String.valueOf(rs.getInt(8)),
//                            rs.getString(9),
//                            rs.getString(10),
//                            rs.getString(11),
//                            rs.getString(12),
//                            rs.getString(13),
//                            String.valueOf(rs.getDate(14)));
//                    System.out.println(u);
//                    return u;
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("login: " + e);
//        }
//        return null;
//    }
    public User login(String email, String password) {
        String sql = "SELECT u.name, u.email, u.password from [user] as u WHERE u.email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            byte[] salt = PasswordEncryption.generateSalt();
            String encryptedPassword = PasswordEncryption.encryptPassword(password, salt).substring(0, 64);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (PasswordEncryption.comparePasswords(password, rs.getString("password"))) {
                    User u = new User(rs.getString("email"),
                            rs.getString("email"));
                    System.out.println(u);
                    return u;
                }
            }
        } catch (SQLException e) {
            System.out.println("login: " + e);
        }
        return null;
    }

    // Function: get object of user by email - GOOGLE ACCOUNT:
    public User login(String email) {
        String sql = "SELECT u.name, u.email FROM [User] WHERE email = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString("email"),
                        rs.getString("email"));
                System.out.println(u);
                return u;
            }
        } catch (SQLException e) {
            System.out.println("login: " + e);
        }
        return null;
    }

    public boolean registerUser(User u) {
        String sql = "INSERT INTO [dbo].[User]\n"
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

        byte[] salt = PasswordEncryption.generateSalt();
        String encryptedPassword = PasswordEncryption.encryptPassword(u.getPassword(), salt);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
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
        } catch (SQLException e) {
            System.out.println("registerUser: " + e);
        }
        return false;
    }

    public boolean registerUserGoogleAccount(User u) {
        String sql = "INSERT INTO [dbo].[User]\n"
                + "           ([id]\n"
                + "           ,[email]\n"
                + "           ,[password]\n"
                + "           ,[name]\n"
                + "           ,[createdAt])\n"
                + "     VALUES"
                + "           (?,?,?,?,?)";

        byte[] salt = PasswordEncryption.generateSalt();
        String encryptedPassword = PasswordEncryption.encryptPassword(u.getPassword(), salt);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, this.getLastUserId() + 1 + "");
            st.setString(2, u.getEmail());
            st.setString(3, encryptedPassword);
            st.setString(4, u.getName());
            st.setString(5, u.getCreatedAt());
            st.execute();
            return true;
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

    public User getUserNotRegistered(String email) {
        String sql = "SELECT id FROM [User]\n"
                + "WHERE password IS NULL AND email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"));
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
        String SQL = "SELECT [User].id[uId],email,password, [User].name[uName],birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture,createdAt "
                + " , Province.name[pName]"
                + " FROM [User]"
                + " LEFT JOIN Province on [User].provinceId = Province.id"
                + " GROUP BY [User].id,email,password, [User].name,birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture,createdAt "
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
            System.out.println("getListUser" + e.getMessage());
        }
        return list;
    }

    public ArrayList<User> searchListUser(int offset, int fetch) {
        ArrayList<User> list = new ArrayList<>();
        String SQL = "SELECT [User].id[uId],email,password, [User].name[uName],birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture,createdAt "
                + " , Province.name[pName]"
                + " FROM [User]"
                + " JOIN Province on [User].provinceId = Province.id"
                + " WHERE [User].id[uId] like ? AND email like ? AND password like ? AND [User].name[uName] like ? "
                + " AND birthDate like ? AND gender like ? AND address like ? AND provinceId like ? AND [identity] like ? "
                + " AND medicalId like ? AND ethnic like ? AND phone like ? AND profilePicture like ? AND createdAt like ?"
                + " like ? AND Province.name[pName]"
                + " GROUP BY [User].id,email,password, [User].name,birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture,createdAt "
                + " , Province.name"
                + " HAVING [User].id IS NOT NULL"
                + " ORDER BY COUNT([User].id) DESC"
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String SQL2 = "SELECT count(*) "
                + " FROM [User]"
                + " JOIN Province on [User].provinceId = Province.id"
                + " WHERE [User].id[uId] like ? AND email like ? AND password like ? AND [User].name[uName] like ? "
                + " AND birthDate like ? AND gender like ? AND address like ? AND provinceId like ? AND [identity] like ? "
                + " AND medicalId like ? AND ethnic like ? AND phone like ? AND profilePicture like ? AND createdAt like ?"
                + " like ? AND Province.name[pName]";
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
            pstm.setString(10, "%%");
            pstm.setString(11, "%%");
            pstm.setString(12, "%%");
            pstm.setString(13, "%%");
            pstm.setString(14, "%%");
            pstm.setString(15, "%%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                numberRecord = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("search user " + e.getMessage());
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
            pstm.setString(10, "%%");
            pstm.setString(11, "%%");
            pstm.setString(12, "%%");
            pstm.setString(13, "%%");
            pstm.setString(14, "%%");
            pstm.setString(15, "%%");
            pstm.setInt(16, offset);
            pstm.setInt(17, fetch);
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
            System.out.println("searchListUser" + e.getMessage());
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

    public Boolean setUserById(User user) {
        String SQL = "UPDATE [User] SET email = ?, password = ?, name = ?, birthDate = ?, gender = ?, address = ?, provinceId = ?, [identity] = ?, medicalId = ?, ethnic = ?, phone = ?, profilePicture = ? WHERE id = ? ";
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
            pstm.setString(13, user.getId());
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
}
