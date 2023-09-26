/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAL;

import Models.Awards;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class AwardsDAO extends DBContext{
    public ArrayList<Awards> getAwardsOfDoctorByDoctorId (String doctorId) {
        ArrayList<Awards> list = new ArrayList<>();
        String sql = "SELECT description FROM Awards WHERE DoctorId = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Awards a = new Awards(rs.getString("description"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println("getAwardsOfDoctorByDoctorId: " + e);
        } 
        return list;
    }
    
    public static void main(String[] args) {
        AwardsDAO  awd = new AwardsDAO();
        ArrayList<Awards> awards = awd.getAwardsOfDoctorByDoctorId("1");
        
        for (Awards award : awards) {
            System.out.println(award);
        }
    }
}
