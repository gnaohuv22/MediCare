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

/**
 *
 * @author hoang
 */
public class NewsDAO extends DBContext {

    public ArrayList<News> getNewsList() {
        String SQL = "SELECT * FROM [News]";
        ArrayList<News> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getInt(5)),
                        String.valueOf(rs.getDate(6)),
                        String.valueOf(rs.getDate(7)),
                        String.valueOf(rs.getInt(8)),
                        rs.getString(9)
                );
                list.add(n);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getNewsList: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<News> getTopNews() {
        String SQL = "SELECT TOP(5) * FROM [News] \n"
                + "ORDER BY createdAt DESC, viewCount DESC";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getInt(5)),
                        String.valueOf(rs.getDate(6)),
                        String.valueOf(rs.getDate(7)),
                        String.valueOf(rs.getInt(8)),
                        rs.getString(9)
                );
                list.add(n);
            }
            return list;

        } catch (SQLException e) {
            System.out.println("getTopNews" + e.getMessage());
        }
        return null;
    }
}
