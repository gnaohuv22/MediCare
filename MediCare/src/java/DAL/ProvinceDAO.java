/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Province;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class ProvinceDAO extends DBContext{
    
    public ArrayList<Province> getAllProvinceId() {
        ArrayList<Province> list = new ArrayList<>();
        String SQL = "SELECT id,name FROM Province";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                Province obj = new Province(id,name);
                list.add(obj);
            }
        } catch (Exception e) {
            System.out.println("getAllProvinceId" + e.getMessage());
        }
        return list;
    }
    public Province getProvinceById(String id) {
        String SQL = "SELECT name FROM Province WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String name = rs.getString(2);
                Province obj = new Province(id,name);
                return obj;
            }
        }catch (Exception e) {
            System.out.println("getProvinceById " + e.getMessage());
        }
        return null;
    }
    //thu
    public String getProvinceId(String name) {
        String sql = "SELECT id FROM Province WHERE name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setNString(1, "%"+name+"%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString("id");
            }
        } catch (SQLException e) {
            System.out.println("getProvinceId: " + e);
        }
        return null;
    }
}
