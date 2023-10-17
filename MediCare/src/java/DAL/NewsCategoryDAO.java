/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.NewsCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class NewsCategoryDAO extends DBContext{
    public ArrayList<NewsCategory> getAllNewsCategory() {
        ArrayList<NewsCategory> list = new ArrayList<>();
        String SQL = "SELECT id,name FROM NewsCategory";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                NewsCategory obj = new NewsCategory(id,name);
                list.add(obj);
            }
        } catch (Exception e) {
            System.out.println("getAllNewsCategory" + e.getMessage());
        }
        return list;
    }
}
