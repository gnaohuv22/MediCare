/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Department;
import Models.Doctor;

/**
 *
 * @author hoang
 */
public class DoctorDAO extends DBContext {
    public ArrayList<Doctor> getListCurriculumVitae() {
        ArrayList<Doctor> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Doctor]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        String.valueOf(rs.getInt(7)),
                        String.valueOf(rs.getFloat(8)),
                        rs.getString(9),
                        rs.getString(10),
                        String.valueOf(rs.getBoolean(11))
                    );
                list.add(d);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListDoctor: " + e.getMessage());
        } 
        return null;
    }
    
    public boolean login(String email, String password)  {
        String SQL = "SELECT * FROM [Doctor] WHERE email = ? AND password = ?";
        try (PreparedStatement pstm=connection.prepareStatement(SQL)){
            pstm.setString(1, email);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                return true;
            else 
                return false;
        } catch (SQLException e) {
            System.out.println("dal.UserDAO.Login(): "+e);
        }
        return false;
    } 
    
    public Doctor getDoctorByEmail(String email) {
        String SQL = "SELECT * FROM [Doctor] WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Doctor d = new Doctor(String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getInt(5)),
                        rs.getString(6),
                        String.valueOf(rs.getInt(7)),
                        String.valueOf(rs.getInt(8)),
                        String.valueOf(rs.getFloat(9)),
                        rs.getString(10),
                        rs.getString(11),
                        String.valueOf(rs.getBoolean(12))
                    );
                return d;
            }
        } catch (SQLException e) {
            System.out.println("getDoctorByEmail: " + e.getMessage());
        }
        return null;
    }
}
