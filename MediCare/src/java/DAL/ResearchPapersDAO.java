/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.ResearchPapers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class ResearchPapersDAO extends DBContext {

    public ArrayList<ResearchPapers> getResearchPapersOfDoctorByDoctorId(String doctorId) {
        ArrayList<ResearchPapers> list = new ArrayList<>();
        String sql = "SELECT description FROM ResearchPapers WHERE doctorId = ?";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ResearchPapers r = new ResearchPapers(rs.getString("description"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println("getResearchPapersOfDoctorByDoctorId: " + e);
        }
        return list;
    }
    
    public static void main(String[] args) {
        ResearchPapersDAO rpd = new ResearchPapersDAO();
        ArrayList<ResearchPapers> papers = rpd.getResearchPapersOfDoctorByDoctorId("1");
        for (ResearchPapers paper : papers) {
            System.out.println(paper);
        }
    }
}
