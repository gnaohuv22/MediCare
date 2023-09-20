/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Branch;

/**
 *
 * @author hoang
 */
public class BranchDAO extends DBContext {
    public ArrayList<Branch> getListBranch() {
        ArrayList<Branch> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Branch]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Branch b = new Branch(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2), 
                        rs.getString(3), 
                        String.valueOf(rs.getInt(4)));
                list.add(b);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListBranch: " + e.getMessage());
        } 
        return null;
    }
}
