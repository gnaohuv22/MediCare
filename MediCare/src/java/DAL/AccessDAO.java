/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.AccessLog;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author DELL
 */
public class AccessDAO extends DBContext {
    public AccessLog getAccessLog(int id){
        String SQL = "SELECT id, name, accessCount FROM AccessLog WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String name = rs.getString(2);
                String accessCount = rs.getString(3);
                AccessLog obj = new AccessLog(id+"", name, accessCount);
                return obj;
            }
        }catch (Exception e) {
            System.out.println("getAccessLog " + e.getMessage());
        }
        return null;
    }
    public AccessLog getAccessLogByName(String name){
        String SQL = "SELECT id, name, accessCount FROM AccessLog WHERE name = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, name);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String accessCount = rs.getString(3);
                AccessLog obj = new AccessLog(id, name, accessCount);
                return obj;
            }
        }catch (Exception e) {
            System.out.println("getAccessLogByName " + e.getMessage());
        }
        return null;
    }
    public boolean setAccessLog(int id,int newAccess){
        String SQL = "UPDATE AccessLog SET accessCount=? WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, newAccess);
            pstm.setInt(2, id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("setAccessLog" + e.getMessage());
        }
        return false;
    }
    public boolean createAccessLog(int id,String name, int value){
        String SQL = "INSERT INTO AccessLog VALUES (?,?,?)";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, id);
            pstm.setString(2,name);
            pstm.setInt(3, value);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("setAccessLog" + e.getMessage());
        }
        return false;
    }
    public int getNumberAccessLog(){
        String SQL = "SELECT id, name, accessCount FROM AccessLog";
        int count = 0;
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                count++;
            }
        return count;
        }catch (Exception e) {
            System.out.println("getNumberAccessLog " + e.getMessage());
        }
        return -1;
    }
}
