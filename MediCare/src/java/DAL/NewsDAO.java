package DAL;

import Controllers.RemoveAccent;
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
                        rs.getString(10),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getInt(5)),
                        String.valueOf(rs.getDate(6)),
                        String.valueOf(rs.getDate(7)),
                        String.valueOf(rs.getInt(8)),
                        rs.getString(9),
                        rs.getString(11)
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
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.slug AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
                        String.valueOf(rs.getDate(9)),
                        String.valueOf(rs.getDate(10)),
                        String.valueOf(rs.getInt(11)),
                        rs.getString(12),
                        rs.getString(13)
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
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.slug AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE n.id = ?";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
                        String.valueOf(rs.getDate(9)),
                        String.valueOf(rs.getDate(10)),
                        String.valueOf(rs.getInt(11)),
                        rs.getString(12),
                        rs.getString(13)
                );
                return n;
            }
        } catch (SQLException e) {
            System.out.println("getNewsById: " + e.getMessage());
        }
        return null;
    }

    public News getNewsByTitle(String title) {
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.slug AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE n.title = ?";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
                        String.valueOf(rs.getDate(9)),
                        String.valueOf(rs.getDate(10)),
                        String.valueOf(rs.getInt(11)),
                        rs.getString(12),
                        rs.getString(13)
                );
                return n;
            }
        } catch (SQLException e) {
            System.out.println("getNewsByTitle: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<News> getListNewsByCategory(int categoryId) {
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.slug AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE nc.id = ? OR nc.parentId = ?";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, categoryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
                        String.valueOf(rs.getDate(9)),
                        String.valueOf(rs.getDate(10)),
                        String.valueOf(rs.getInt(11)),
                        rs.getString(12),
                        rs.getString(13)
                );
                list.add(n);

            }
        } catch (SQLException e) {
            System.out.println("getListNewsByCategory: " + e.getMessage());
        }
        return list;
    }

    public void addNews(News news) {
        // Generate the slug from the title
        String slug = RemoveAccent.removeAccent(news.getTitle());

        // Create the SQL query
        String sql = "INSERT INTO News (id, title, content, author, categoryId, createdAt, lastModified, viewCount, coverImage, subtitle, slug) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {

            // Create a PreparedStatement from the SQL query
            PreparedStatement pstmt = connection.prepareStatement(sql);

            // Set the parameters in the PreparedStatement
            pstmt.setString(2, news.getTitle());
            pstmt.setString(3, news.getContent());
            pstmt.setString(4, news.getAuthor());
            pstmt.setInt(5, Integer.parseInt(news.getCategoryId()));
            pstmt.setDate(6, java.sql.Date.valueOf(news.getCreatedAt()));
            pstmt.setDate(7, java.sql.Date.valueOf(news.getLastModified()));
            pstmt.setInt(8, Integer.parseInt(news.getViewCount()));
            pstmt.setString(9, news.getCoverImage());
            pstmt.setString(10, news.getSubtitle());
            pstmt.setString(11, slug);

            // Execute the query
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("addNews: " + e.getMessage());
        }
    }

    public News getNewsFromSlug(String slug) {
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.slug AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.slug LIKE ?";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, slug);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
                        String.valueOf(rs.getDate(9)),
                        String.valueOf(rs.getDate(10)),
                        String.valueOf(rs.getInt(11)),
                        rs.getString(12),
                        rs.getString(13)
                );
                return n;
            }
        } catch (SQLException e) {
            System.out.println("getNewsFromSlug: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<News> getNewestExcept(String id) {
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.slug AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.id != ?\n"
                + "ORDER BY n.createdAt DESC, n.viewCount DESC";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
                        String.valueOf(rs.getDate(9)),
                        String.valueOf(rs.getDate(10)),
                        String.valueOf(rs.getInt(11)),
                        rs.getString(12),
                        rs.getString(13)
                );
                list.add(n);

            }
        } catch (SQLException e) {
            System.out.println("getNewestExcept: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<News> getNewestExcept(int num, String id) {
        String SQL = "SELECT TOP(" + num + ") n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.slug AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.id != ?\n"
                + "ORDER BY n.createdAt DESC, n.viewCount DESC";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
                        String.valueOf(rs.getDate(9)),
                        String.valueOf(rs.getDate(10)),
                        String.valueOf(rs.getInt(11)),
                        rs.getString(12),
                        rs.getString(13)
                );
                list.add(n);

            }
        } catch (SQLException e) {
            System.out.println("getNewestExcept: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<News> getNewest(int n) {
        String SQL = "SELECT TOP(" + n + ") n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.slug AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "ORDER BY n.createdAt DESC, n.viewCount DESC\n";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News news = new News(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        rs.getString(7),
                        rs.getString(8),
                        String.valueOf(rs.getDate(9)),
                        String.valueOf(rs.getDate(10)),
                        String.valueOf(rs.getInt(11)),
                        rs.getString(12),
                        rs.getString(13)
                );
                list.add(news);
            }
        } catch (SQLException e) {
            System.out.println("getNewest: " + e.getMessage());
        }
        return list;
    }
}
