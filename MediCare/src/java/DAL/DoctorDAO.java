/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author DELL
 */
public class DoctorDAO extends DBContext{
    
    public int countAllDoctor(){
        String SQL = "select COUNT(id) from Doctor";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        }catch (Exception e) {
            System.out.println("countAllDoctor " + e.getMessage());
        }
        return -1;
    }
}
