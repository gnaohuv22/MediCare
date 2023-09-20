/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.NewsCategory;
import Models.Province;

/**
 *
 * @author hoang
 */
public class ProvinceDAO extends DBContext {
    public ArrayList<Province> getProvinceList() {
        String SQL = "SELECT * FROM [Province]";
        ArrayList<Province> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Province p = new Province(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2)
                );
                list.add(p);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getProvinceList: " + e.getMessage());
        }
        return list;
    }
}
