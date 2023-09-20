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

/**
 *
 * @author hoang
 */
public class UserDAO extends DBContext {

    public boolean login(String email, String password) {
        String SQL = "SELECT * FROM [User] WHERE email = ?";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if (PasswordEncryption.comparePasswords(password, rs.getString(3))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("login: " + e.getMessage());
        }
        return false;
    }
    
    public ArrayList<User> getListUser() {
        ArrayList<User> list = new ArrayList<>();
        String SQL = "SELECT * FROM [User]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = new User (String.valueOf(rs.getInt(1)),
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
                                rs.getString(11),
                                rs.getString(12),
                                String.valueOf(rs.getDate(13)));
                list.add(u);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListUser: " + e.getMessage());
        } 
        return null;
    }
}
