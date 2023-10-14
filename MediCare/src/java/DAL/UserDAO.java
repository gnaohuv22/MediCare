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

/**
 *
 * @author hoang
 */
public class UserDAO extends DBContext {

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User u = new User(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getDate(5)),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        String.valueOf(rs.getInt(8)),
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

    // Function: get object of user by username and password:
    public User login1(String email, String password) {
        String sql = "SELECT * FROM [User] WHERE email = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            byte[] salt = PasswordEncryption.generateSalt();
            String encryptedPassword = PasswordEncryption.encryptPassword(password, salt).substring(0, 64);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                if (PasswordEncryption.comparePasswords(password, rs.getString("password"))) {
                    User u = new User(rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            String.valueOf(rs.getDate(5)),
                            String.valueOf(rs.getInt(6)),
                            rs.getString(7),
                            String.valueOf(rs.getInt(8)),
                            rs.getString(9),
                            rs.getString(10),
                            rs.getString(11),
                            rs.getString(12),
                            rs.getString(13),
                            String.valueOf(rs.getDate(14)));
                    System.out.println(u);
                    return u;
                }
            }
        } catch (SQLException e) {
            System.out.println("login: " + e);
        }
        return null;
    }

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
        String sql = "SELECT * FROM [User] WHERE email = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getDate(5)),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        String.valueOf(rs.getInt(8)),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        String.valueOf(rs.getDate(14)));
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
        return -1;
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

    public User getUserRegistered(String email, String password) {
        String sql = "SELECT id FROM [User]\n"
                + "WHERE password = ? AND email = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, password);
            st.setString(2, email);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println("getUserRegistered: " + e);
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

    public static void main(String[] args) {
        UserDAO ud = new UserDAO();
        ArrayList<User> list = ud.getAllUsers();
        for (User user : list) {
            System.out.println(user);
        }
    }

}
