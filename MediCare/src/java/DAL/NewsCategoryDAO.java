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
}
