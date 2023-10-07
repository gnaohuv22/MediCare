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

    public News getNewsById(String id) {
        String SQL = "SELECT n.id, n.title, n.content, n.author, nc.id AS categoryId, nc.name AS category, n.createdAt, n.lastModified, n.viewCount, n.coverImage FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE n.id = ?";

        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getInt(5)),
                        rs.getString(6),
                        String.valueOf(rs.getDate(7)),
                        String.valueOf(rs.getDate(8)),
                        String.valueOf(rs.getInt(9)),
                        rs.getString(10)
                );
                return n;
            }
        } catch (SQLException e) {
            System.out.println("getNewsById: " + e.getMessage());
        }
        return null;
    }

    public News getNewsByTitle(String title) {
        String SQL = "SELECT n.id, n.title, n.content, n.author, nc.id AS categoryId, nc.name AS category, n.createdAt, n.lastModified, n.viewCount, n.coverImage FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE n.title = ?";

        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getInt(5)),
                        rs.getString(6),
                        String.valueOf(rs.getDate(7)),
                        String.valueOf(rs.getDate(8)),
                        String.valueOf(rs.getInt(9)),
                        rs.getString(10)
                );
                return n;
            }
        } catch (SQLException e) {
            System.out.println("getNewsByTitle: " + e.getMessage());
        }
        return null;
    }
}