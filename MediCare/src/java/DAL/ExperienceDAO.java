/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Experience;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class ExperienceDAO extends DBContext {

    public ArrayList<Experience> getExperiencesOfDoctorByDoctorId(String doctorId) {
        ArrayList<Experience> list = new ArrayList<>();
        String sql = "SELECT description FROM Experience WHERE DoctorId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Experience e = new Experience(rs.getString("description"));
                list.add(e);
            }
        } catch (SQLException e) {
            System.out.println("getExperiencesOfDoctorByDoctorId: " + e);
        }
        return list;
    }
    
    
    public static void main(String[] args) {
        ExperienceDAO ed = new ExperienceDAO();
        ArrayList<Experience> list = ed.getExperiencesOfDoctorByDoctorId("1");
        for (Experience experience : list) {
             System.out.println(experience);
        }
    }
}
