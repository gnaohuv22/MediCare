/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import controller.PasswordEncryption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
