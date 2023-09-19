/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.News;
import model.NewsCategory;

/**
 *
 * @author hoang
 */
public class NewsCategoryDAO extends DBContext {
    public ArrayList<NewsCategory> getNewsCategoryList() {
        String SQL = "SELECT * FROM [NewsCategory]";
        ArrayList<NewsCategory> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                NewsCategory nc = new NewsCategory(
                        String.valueOf(rs.getInt(1)), 
                        rs.getString(2)
                );
                list.add(nc);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getNewsCategoryList: " + e.getMessage());
        }
        return list;
    }
}
