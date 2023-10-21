/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Province;
import Models.Relationship;

/**
 *
 * @author hoang
 */
public class RelationshipDAO extends DBContext{
    public ArrayList<Relationship> getRelationshipList() {
        String SQL = "SELECT * FROM [Relationship]";
        ArrayList<Relationship> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Relationship r = new Relationship(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2)
                );
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getRelationshipList: " + e.getMessage());
        }
        return list;
    }
    
    public Relationship getRelationshipByRelationshipId (String relationshipId) {
        String sql = "SELECT * FROM Relationship WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(relationshipId));
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Relationship(String.valueOf(rs.getInt("id")), rs.getString("relation"));
            }
        } catch (SQLException|NumberFormatException e) {
            System.out.println("getRelationshipByRelationshipId: " + e);
        }
        return null;
    }
}
