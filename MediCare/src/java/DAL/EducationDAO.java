/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DAL;

import Models.Education;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class EducationDAO extends DBContext{
    public ArrayList<Education> getEducationOfDoctorByDoctorId (String doctorId) {
        ArrayList<Education> list = new ArrayList<>();
        String sql = "SELECT description FROM Education WHERE DoctorId = ?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Education e = new Education(rs.getString("description"));
                list.add(e);
            }
        } catch (SQLException e) {
            System.out.println("getEducationOfDoctorByDoctorId: " + e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        EducationDAO ed = new EducationDAO();
        ArrayList<Education> education = ed.getEducationOfDoctorByDoctorId("1");
        for (Education education1 : education) {
            System.out.println(education1);
        }
    }
    

    
}
