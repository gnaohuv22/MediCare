package DAL;

import Controllers.RemoveAccent;
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
public class NewsDAO extends DBContext {

    public ArrayList<News> getNewsList() {
        String SQL = "SELECT id, title, subtitle, content, author, categoryId, createdAt, lastModified, viewCount, coverImage, subtitle, slug FROM [News]"
                + "WHERE type IS NULL";
        ArrayList<News> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("content"),
                        rs.getString("author"),
                        String.valueOf(rs.getInt("createdAt")),
                        String.valueOf(rs.getDate("lastModified")),
                        String.valueOf(rs.getDate("viewCount")),
                        String.valueOf(rs.getInt("coverImage")),
                        rs.getString("slug"),
                        null
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
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.href AS categorySlug, nc.parentId AS categoryParentId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE type IS NULL";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("content"),
                        rs.getString("author"),
                        String.valueOf(rs.getDate("createdAt")),
                        String.valueOf(rs.getDate("lastModified")),
                        String.valueOf(rs.getInt("viewCount")),
                        rs.getString("coverImage"),
                        rs.getString("slug"),
                        null,
                        new NewsCategory(
                                String.valueOf(rs.getInt("categoryId")),
                                rs.getString("category"),
                                rs.getString("categorySlug"),
                                null,
                                String.valueOf(rs.getInt("categoryParentId")))
                );
                list.add(n);
            }
            return list;

        } catch (SQLException e) {
            System.out.println("getTopNews: " + e.getMessage());
        }
        return null;
    }

    public News getNewsById(String id) {
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.href AS categorySlug, nc.parentId AS categoryParentId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE n.id = ? AND type IS NULL";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("content"),
                        rs.getString("author"),
                        String.valueOf(rs.getDate("createdAt")),
                        String.valueOf(rs.getDate("lastModified")),
                        String.valueOf(rs.getInt("viewCount")),
                        rs.getString("coverImage"),
                        rs.getString("slug"),
                        null,
                        new NewsCategory(
                                String.valueOf(rs.getInt("categoryId")),
                                rs.getString("category"),
                                rs.getString("categorySlug"),
                                null,
                                String.valueOf(rs.getInt("categoryParentId")))
                );
                return n;
            }
        } catch (SQLException e) {
            System.out.println("getNewsById: " + e.getMessage());
        }
        return null;
    }

    public News getNewsByTitle(String title) {
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.href AS categorySlug, nc.parentId AS categoryParentId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE n.title = ? AND type IS NULL";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("content"),
                        rs.getString("author"),
                        String.valueOf(rs.getDate("createdAt")),
                        String.valueOf(rs.getDate("lastModified")),
                        String.valueOf(rs.getInt("viewCount")),
                        rs.getString("coverImage"),
                        rs.getString("slug"),
                        null,
                        new NewsCategory(
                                String.valueOf(rs.getInt("categoryId")),
                                rs.getString("category"),
                                rs.getString("categorySlug"),
                                null,
                                String.valueOf(rs.getInt("categoryParentId")))
                );
                return n;
            }
        } catch (SQLException e) {
            System.out.println("getNewsByTitle: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<News> getListNewsByCategory(int categoryId) {
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.href AS categorySlug, nc.parentId AS categoryParentId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE nc.id = ? OR nc.parentId = ? AND type IS NULL";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, categoryId);
            ps.setInt(2, categoryId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("content"),
                        rs.getString("author"),
                        String.valueOf(rs.getDate("createdAt")),
                        String.valueOf(rs.getDate("lastModified")),
                        String.valueOf(rs.getInt("viewCount")),
                        rs.getString("coverImage"),
                        rs.getString("slug"),
                        null,
                        new NewsCategory(
                                String.valueOf(rs.getInt("categoryId")),
                                rs.getString("category"),
                                rs.getString("categorySlug"),
                                null,
                                String.valueOf(rs.getInt("categoryParentId")))
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
            pstmt.setInt(5, Integer.parseInt(news.getCategory().getId()));
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
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.href AS categorySlug, nc.parentId AS categoryParentId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.slug LIKE ? AND type IS NULL";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, slug);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("content"),
                        rs.getString("author"),
                        String.valueOf(rs.getDate("createdAt")),
                        String.valueOf(rs.getDate("lastModified")),
                        String.valueOf(rs.getInt("viewCount")),
                        rs.getString("coverImage"),
                        rs.getString("slug"),
                        null,
                        new NewsCategory(
                                String.valueOf(rs.getInt("categoryId")),
                                rs.getString("category"),
                                rs.getString("categorySlug"),
                                null,
                                String.valueOf(rs.getInt("categoryParentId")))
                );
                return n;
            }
        } catch (SQLException e) {
            System.out.println("getNewsFromSlug: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<News> getNewestExcept(String id) {
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.href AS categorySlug, nc.parentId AS categoryParentId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.id != ? AND type IS NULL\n"
                + "ORDER BY n.createdAt DESC, n.viewCount DESC";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("content"),
                        rs.getString("author"),
                        String.valueOf(rs.getDate("createdAt")),
                        String.valueOf(rs.getDate("lastModified")),
                        String.valueOf(rs.getInt("viewCount")),
                        rs.getString("coverImage"),
                        rs.getString("slug"),
                        null,
                        new NewsCategory(
                                String.valueOf(rs.getInt("categoryId")),
                                rs.getString("category"),
                                rs.getString("categorySlug"),
                                null,
                                String.valueOf(rs.getInt("categoryParentId")))
                );
                list.add(n);

            }
        } catch (SQLException e) {
            System.out.println("getNewestExcept: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<News> getNNewestExcept(int num, String id) {
        String SQL = "SELECT TOP(" + num + ") n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.href AS categorySlug, nc.parentId AS categoryParentId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.id != ? AND type IS NULL\n"
                + "ORDER BY n.createdAt DESC, n.viewCount DESC";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, Integer.parseInt(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News n = new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("content"),
                        rs.getString("author"),
                        String.valueOf(rs.getDate("createdAt")),
                        String.valueOf(rs.getDate("lastModified")),
                        String.valueOf(rs.getInt("viewCount")),
                        rs.getString("coverImage"),
                        rs.getString("slug"),
                        null,
                        new NewsCategory(
                                String.valueOf(rs.getInt("categoryId")),
                                rs.getString("category"),
                                rs.getString("categorySlug"),
                                null,
                                String.valueOf(rs.getInt("categoryParentId")))
                );
                list.add(n);

            }
        } catch (SQLException e) {
            System.out.println("getNewestExcept: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<News> getNewest(int n) {
        String SQL = "SELECT TOP(?) n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.href AS categorySlug, nc.parentId AS categoryParentId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n "
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id "
                + "WHERE type IS NULL "
                + "ORDER BY n.createdAt DESC, n.viewCount DESC";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, n);  // Sử dụng setInt() để tránh SQL Injection
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                News news = new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        rs.getString("subtitle"),
                        rs.getString("content"),
                        rs.getString("author"),
                        String.valueOf(rs.getDate("createdAt")),
                        String.valueOf(rs.getDate("lastModified")),
                        String.valueOf(rs.getInt("viewCount")),
                        rs.getString("coverImage"),
                        rs.getString("slug"),
                        null,
                        new NewsCategory(
                                String.valueOf(rs.getInt("categoryId")),
                                rs.getString("category"),
                                rs.getString("categorySlug"),
                                null,
                                String.valueOf(rs.getInt("categoryParentId")))
                );
                list.add(news);
            }
        } catch (SQLException e) {
            e.printStackTrace();  // In ra lỗi để dễ dàng xác định vấn đề
        }

        return list;
    }

    public ArrayList<News> getTopLevelMenu() {
        String SQL = "SELECT id, title, type FROM [News]";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new News(
                        String.valueOf(rs.getInt("id")),
                        rs.getString("title"),
                        String.valueOf(rs.getInt("type")))
                );
            }
        } catch (SQLException e) {
            System.out.println("getTopLevelMenu: " + e.getMessage());
        }
        return list;
    }
}
