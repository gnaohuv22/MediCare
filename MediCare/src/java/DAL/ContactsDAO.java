/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Contacts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class ContactsDAO extends DBContext {
    public ArrayList<Contacts> getListContacts() {
        String SQL = "SELECT * FROM [Contacts]";
        ArrayList<Contacts> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Contacts c = new Contacts(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2), 
                        rs.getString(3)
                );
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println("getListContacts: " + e.getMessage());
        }
        return list;
    }
}
