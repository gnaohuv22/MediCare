/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.News;
import Models.NewsCategory;
import java.util.List;

/**
 *
 * @author hoang
 */
public class NewsCategoryDAO extends DBContext {

    public ArrayList<NewsCategory> getTopLevelCategory() {
        String SQL = "SELECT * FROM [NewsCategory] WHERE parentId IS NULL";
        ArrayList<NewsCategory> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NewsCategory nc = new NewsCategory(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(4)
                );
                list.add(nc);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getTopLevelCategory: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<NewsCategory> getSubLevelCategory() {
        String SQL = "SELECT * FROM [NewsCategory] WHERE parentId IS NOT NULL";
        ArrayList<NewsCategory> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NewsCategory nc = new NewsCategory(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        String.valueOf(rs.getInt(3)),
                        rs.getString(4)
                );
                list.add(nc);
            }
        } catch (SQLException e) {
            System.out.println("getSubLevelCategory: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<NewsCategory> getNewsCategoryList() {
        String SQL = "SELECT * FROM [NewsCategory]";
        ArrayList<NewsCategory> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NewsCategory nc = new NewsCategory(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(4)
                );
                list.add(nc);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getTopLevelCategory: " + e.getMessage());
        }
        return list;
    }

    public List<Integer> getParentCategoryNumber() {
        String SQL = "SELECT DISTINCT parentId  FROM [NewsCategory]\n"
                + "WHERE parentId IS NOT NULL";
        List<Integer> list = new ArrayList();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(Integer.parseInt(rs.getString(1)));
            }
        } catch (SQLException e) {
            System.out.println("getParentCategoryNumber: " + e.getMessage());
        }
        return list;
    }

    public NewsCategory getCategoryBySlug(String categorySlug) {
        String SQL = "SELECT * FROM [NewsCategory]\n"
                + "WHERE slug LIKE ?";
        NewsCategory nc = new NewsCategory();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, categorySlug);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                nc = new NewsCategory(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString("name"), 
                        String.valueOf(rs.getInt("parentId")), 
                        rs.getString("slug"));
            }
        } catch (SQLException e) {
            System.out.println("getCategoryBySlug: " + e.getMessage());
        }
        return nc;
    }
}
