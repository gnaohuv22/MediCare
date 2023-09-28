/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class UsefulLinksDAO extends DBContext {
    public ArrayList<UsefulLinks> getListUsefulLinks() {
        String SQL = "SELECT * FROM [UsefulLinks]";
        ArrayList<UsefulLinks> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                UsefulLinks ul = new UsefulLinks(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2), 
                        rs.getString(3)
                );
                list.add(ul);
            }
        } catch (SQLException e) {
            System.out.println("getListUsefulLinks: " + e.getMessage());
        }
        return list;
    }
    
    public static void main(String[] args) {
        UsefulLinksDAO uld = new UsefulLinksDAO();
        ArrayList<UsefulLinks> list = uld.getListUsefulLinks();
        
        for (UsefulLinks ul : list) {
            System.out.println(ul.toString());
        }
    }
}
