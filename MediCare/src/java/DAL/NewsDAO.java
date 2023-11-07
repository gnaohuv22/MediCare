package DAL;

import Controllers.RemoveAccent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.News;
import Models.NewsCategory;
import java.util.List;
import org.apache.poi.util.LocaleID;

/**
 *
 * @author hoang
 */
public class NewsDAO extends DBContext {

    public ArrayList<News> getListNews(int offset, int fetch) {
        ArrayList<News> list = new ArrayList<>();
        String SQL = "SELECT [News].[id][newsId],[title],[content],[author],[categoryId],[createdAt],[lastModified],[viewCount],[coverImage], [subtitle],[slug],\n"
                + "                         [NewsCategory].name[cName],type,[href]\n"
                + "                         FROM [News]   \n"
                + "                         LEFT JOIN [NewsCategory] on [News].categoryId = [NewsCategory].id  \n"
                + "                         GROUP BY [News].[id],[title],[content],[author],[categoryId],[createdAt],[lastModified],[viewCount],type,[coverImage], [subtitle],[slug],\n"
                + "                         [NewsCategory].name,[href]   \n"
                + "                         HAVING [News].id IS NOT NULL AND type IS NULL \n"
                + "                         ORDER BY COUNT([News].id) DESC \n"
                + "                         OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, offset);
            pstm.setInt(2, fetch);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("newsId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String author = rs.getString("author");
                String categoryId = rs.getString("categoryId");
                String createdAt = rs.getString("createdAt");
                String lastModified = rs.getString("lastModified");
                String viewCount = rs.getString("viewCount");
                String coverImage = rs.getString("coverImage");
                String categoryName = rs.getString("cName");
                String subtitle = rs.getString("subtitle");
                String href = rs.getString("href");
                String slug = rs.getString("slug");
                NewsCategory newsCategory = new NewsCategory(categoryId, categoryName, href);
                News news = new News(id, title, content, author, newsCategory, createdAt, lastModified, viewCount, coverImage, subtitle, slug);
                list.add(news);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListNews " + e.getMessage());
        }
        return null;
    }

    public ArrayList<News> getNewsByThai() {
        String sql = "select n.id, n.title , n.content, n.author, n.categoryId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.subtitle, n.slug, n.type, nc.name, nc.parentId, nc.href, nc.locateId from News n \n"
                + "				 join NewsCategory nc on n.categoryId = nc.id";
        ArrayList<News> list = new ArrayList<>();
        try ( PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                News n = new News();
                NewsCategory nc = new NewsCategory();
                n.setId(String.valueOf(rs.getInt("id")));
                n.setTitle(rs.getString("title"));
                n.setContent(rs.getString("content"));
                n.setAuthor(rs.getString("author"));
                nc.setId(String.valueOf(rs.getInt("categoryId")));
                n.setCreatedAt(String.valueOf(rs.getDate("createdAt")));
                n.setLastModified(String.valueOf(rs.getDate("lastModified")));
                n.setViewCount(String.valueOf(rs.getInt("viewCount")));
                n.setCoverImage(rs.getString("coverImage"));
                n.setSubtitle(rs.getString("subtitle"));
                n.setSlug(rs.getString("slug"));
                n.setType(String.valueOf(rs.getInt("type")));
                nc.setName(rs.getString("name"));
                nc.setParentId(String.valueOf(rs.getInt("parentId")));
                nc.setHref(rs.getString("href"));
                nc.setLocateId(String.valueOf(rs.getInt("locateId")));
                n.setCategory(nc);
                System.out.println("categoryId" + rs.getInt("categoryId"));
                System.out.println("News: " + n.toString());
                list.add(n);
            }
        } catch (SQLException e) {
            System.out.println("getNewsByThai: " + e.getMessage());
        }
        return list;
    }

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
                + "WHERE type IS NULL\n"
                + "ORDER BY n.createdAt DESC, n.viewCount DESC";
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
        String sql = "INSERT INTO News (id, title, content, author, categoryId, createdAt, lastModified, viewCount, coverImage, subtitle, slug, type) VALUES (?, ?, ?, ?, ?, GETDATE(), GETDATE(), ?, ?, ?, ?, NULL)";

        try {

            // Create a PreparedStatement from the SQL query
            PreparedStatement pstmt = connection.prepareStatement(sql);

            // Set the parameters in the PreparedStatement
            pstmt.setInt(1, Integer.parseInt(news.getId()));
            pstmt.setString(2, news.getTitle());
            pstmt.setString(3, news.getContent());
            pstmt.setString(4, news.getAuthor());
            pstmt.setInt(5, Integer.parseInt(news.getCategory().getId()));
            pstmt.setInt(6, Integer.parseInt(news.getViewCount()));
            pstmt.setString(7, news.getCoverImage());
            pstmt.setString(8, news.getSubtitle());
            pstmt.setString(9, news.getSlug());


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

    public ArrayList<News> getNRelated(int num, News news) {
        String SQL = "SELECT TOP(" + num + ") n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.href AS categorySlug, nc.parentId AS categoryParentId, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.id != ? AND type IS NULL AND (n.categoryId = ? OR nc.parentId = ? OR n.categoryId = ?)\n"
                + "ORDER BY n.createdAt DESC, n.viewCount DESC";
        ArrayList<News> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setInt(1, Integer.parseInt(news.getId()));
            ps.setInt(2, Integer.parseInt(news.getCategory().getId()));
            ps.setInt(3, Integer.parseInt(news.getCategory().getId()));
            ps.setInt(4, Integer.parseInt(news.getCategory().getLocateId()));
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
            System.out.println("getNRelated: " + e.getMessage());
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

    public int countAllNews() {
        String SQL = "SELECT count([News].id)\n"
                + "        FROM [News]  \n"
                + "        LEFT JOIN [NewsCategory] on [News].categoryId = [NewsCategory].id \n"
                + "        WHERE type IS NULL";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        } catch (Exception e) {
            System.out.println("countAllNews " + e.getMessage());
        }
        return -1;
    }

    public String generateId() {
        String SQL = "select top(1) id from [News] order by id DESC";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            String number = null;
            while (rs.next()) {
                number = rs.getString(1);
            }
            number = Integer.parseInt(number) + 1 + "";
            return number;
        } catch (Exception e) {
            System.out.println("generateId news " + e.getMessage());
        }
        return null;
    }

    public Boolean deleteNewsById(String id) {
        String SQL = "DELETE News WHERE id = ? ";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("deleteNewsById " + e.getMessage());
        }
        return false;
    }

    public boolean checkNews(String id) {
        String SQL = "SELECT title from [News] WHERE id =? ";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            //return false if id exist
            while (rs.next()) {
                rs.getString("title");
                return true;
            }
        } catch (Exception e) {
            System.out.println("getListNews " + e.getMessage());
        }
        return false;
    }

    public Boolean setNewsById(News news) {
        String SQL = "UPDATE News SET title = ?, content = ?, author = ?, categoryId = ?, lastModified = getDate(), viewCount = ?, coverImage = ?, subtitle = ? WHERE id = ? ";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, news.getTitle());
            pstm.setString(2, news.getContent());
            pstm.setString(3, news.getAuthor());
            pstm.setString(4, news.getCategory().getId());
            pstm.setString(5, news.getViewCount());
            pstm.setString(6, news.getCoverImage());
            pstm.setString(7, news.getSubtitle());
            pstm.setString(8, news.getId());
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("setEmployeeById " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        NewsDAO n = new NewsDAO();
        ArrayList<News> list = n.getNewsByThai();
        for (News news : list) {
            System.out.println(news);
        }
        News niga = n.getNewsById("11111");
        System.out.println("Niga : " + niga);
    }

}
