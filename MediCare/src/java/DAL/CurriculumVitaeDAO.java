/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.CurriculumVitae;

/**
 *
 * @author hoang
 */
public class CurriculumVitaeDAO extends DBContext {
    public ArrayList<CurriculumVitae> getListCurriculumVitae() {
        ArrayList<CurriculumVitae> list = new ArrayList<>();
        String SQL = "SELECT * FROM [CurriculumVitae]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CurriculumVitae cv = new CurriculumVitae(String.valueOf(rs.getInt(1)),
                                                rs.getString(2),
                                                rs.getString(3),
                                                rs.getString(4),
                                                String.valueOf(rs.getInt(5)));
                list.add(cv);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListCurriculumVitae: " + e.getMessage());
        } 
        return null;
    }
}
