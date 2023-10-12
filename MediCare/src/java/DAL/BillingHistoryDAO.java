/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.BillingHistory;

/**
 *
 * @author hoang
 */
public class BillingHistoryDAO extends DBContext {
    public ArrayList<BillingHistory> getListBillingHistory() {
        ArrayList<BillingHistory> list = new ArrayList<>();
        String SQL = "SELECT * FROM [BillingHistory]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BillingHistory bh = new BillingHistory (String.valueOf(rs.getInt(1)),
                                                String.valueOf(rs.getInt(2)),
                                                rs.getString(3),
                                                String.valueOf(rs.getDate(4)));
                list.add(bh);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListBillingHistory: " + e.getMessage());
        } 
        return null;
    }
}
