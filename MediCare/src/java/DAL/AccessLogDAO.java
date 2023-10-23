/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Models.AccessLog;

/**
 *
 * @author hoang
 */
public class AccessLogDAO extends DBContext {
    public int getTotalAccesses() {
        String SQL = "SELECT COUNT(*) FROM [AccessLog]";
        int total = 0;
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                total += rs.getInt(4);
            }
        
        } catch (Exception e) {
        }
        return total;
    }
}
