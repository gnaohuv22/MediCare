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
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.href AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE type IS NULL";
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
            System.out.println("getTopNews: " + e.getMessage());
        }
        return null;
    }

    public News getNewsById(String id) {
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.href AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE n.id = ? AND type IS NULL";

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
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id AS categoryId, nc.name AS category, nc.href AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc \n"
                + "ON n.categoryId = nc.id \n"
                + "WHERE n.title = ? AND type IS NULL";

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
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.href AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n\n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE nc.id = ? OR nc.parentId = ? AND type IS NULL";
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
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.href AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.slug LIKE ? AND type IS NULL";

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
        String SQL = "SELECT n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.href AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.id != ? AND type IS NULL\n"
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
        String SQL = "SELECT TOP(" + num + ") n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.href AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE n.id != ? AND type IS NULL\n"
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
        String SQL = "SELECT TOP(" + n + ") n.id, n.title, n.subtitle, n.content, n.author, nc.id as categoryId, nc.name AS category, nc.href AS categorySlug, n.createdAt, n.lastModified, n.viewCount, n.coverImage, n.slug FROM [News] n \n"
                + "LEFT JOIN [NewsCategory] nc ON n.categoryId = nc.id\n"
                + "WHERE type IS NULL\n"
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
    
    public ArrayList<News> getTopLevelMenu() {
        String SQL = "SELECT id, title, type FROM [News]";
        ArrayList<News> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
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
    //thu
    
    private int numberRecord;
    public int getNumberRecord(){
        return this.numberRecord;
    }
    public ArrayList<News> getListNews(int offset, int fetch) {
        ArrayList<News> list = new ArrayList<>();
        String SQL = "SELECT [News].[id][newsId],[title],[content],[author],[categoryId],[createdAt],[lastModified],[viewCount],[coverImage], [subtitle],  \n" +
"        [NewsCategory].name[cName],type\n" +
"        FROM [News]  \n" +
"        LEFT JOIN [NewsCategory] on [News].categoryId = [NewsCategory].id \n" +
"        GROUP BY [News].[id],[title],[content],[author],[categoryId],[createdAt],[lastModified],[viewCount],type,[coverImage], [subtitle],  \n" +
"        [NewsCategory].name  \n" +
"        HAVING [News].id IS NOT NULL AND type IS NULL\n" +
"        ORDER BY COUNT([News].id) DESC " +
"        OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
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
                NewsCategory newsCategory = new NewsCategory(categoryId, categoryName);
                News news= new News(id, title, content, author, newsCategory, createdAt, lastModified, viewCount,coverImage,subtitle);
                list.add(news);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListNews " + e.getMessage());
        }
        return null;
    }
    public ArrayList<News> searchListNews(int offset, int fetch) {
        ArrayList<News> list = new ArrayList<>();
        String SQL = "SELECT [News].[id][newsId],[title],[content],[author],[categoryId],[createdAt],[lastModified],[viewCount],[coverImage], [subtitle], " +
"        [NewsCategory].name[cName] " +
"        FROM [News] " +
"        JOIN [NewsCategory] on [News].categoryId = [NewsCategory].id" +
"        WHERE SELECT [News].[id][newsId] like ? AND [title] like ? AND [content] like ? AND [author] like ? " + 
"        AND [categoryId] like ? AND [createdAt] like ? AND [lastModified] like ? AND [viewCount] like ? " + 
"        AND [coverImage] like ? AND [subtitle] like ? " +
"        AND [NewsCategory].name[cName] like ? " +              
"        GROUP BY [News].[id],[title],[content],[author],[categoryId],[createdAt],[lastModified],[viewCount],[coverImage], [subtitle], " +
"        [NewsCategory].name " +
"        HAVING [News].id IS NOT NULL" +
"        ORDER BY COUNT([News].id) DESC" +
"        OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String SQL2 = "SELECT count(*) " +
"        FROM [News] " +
"        JOIN [NewsCategory] on [News].categoryId = [NewsCategory].id" +
"        WHERE SELECT [News].[id][newsId] like ? AND [title] like ? AND [content] like ? AND [author] like ? " + 
"        AND [categoryId] like ? AND [createdAt] like ? AND [lastModified] like ? AND [viewCount] like ? " + 
"        AND [coverImage] like ? AND [subtitle] like ? " +
"        AND [NewsCategory].name[cName] like ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL2)){
            pstm.setString(1, "%%"); 
            pstm.setString(2, "%%"); 
            pstm.setString(3, "%%"); 
            pstm.setString(4, "%%"); 
            pstm.setString(5, "%%"); 
            pstm.setString(6, "%%"); 
            pstm.setString(7, "%%"); 
            pstm.setString(8, "%%"); 
            pstm.setString(9, "%%");
            pstm.setString(10, "%%");
            pstm.setString(11, "%%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                numberRecord = rs.getInt(1);
            }
        }catch(Exception e) {
            System.out.println("search news " + e.getMessage());
        }
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, offset);
            pstm.setInt(2, fetch);
            pstm.setString(3,"%%");
            pstm.setString(4,"%%");
            pstm.setString(5,"%%");
            pstm.setString(6,"%%");
            pstm.setString(7,"%%");
            pstm.setString(8,"%%");
            pstm.setString(9,"%%");
            pstm.setString(10,"%%");
            pstm.setString(11,"%%");
            pstm.setString(12,"%%");
            pstm.setString(13,"%%");
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
                NewsCategory newsCategory = new NewsCategory(categoryId, categoryName);
                News news= new News(id, title, content, author, newsCategory, createdAt, lastModified, viewCount,coverImage,subtitle);
                list.add(news);
            }
            return list;
        } catch (Exception e) {
            System.out.println("searchListNews " + e.getMessage());
        }
        return null;
    }
    public boolean checkNews(String id) {
        String SQL = "SELECT title from [News] WHERE id =? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            //return false if id exist
            while (rs.next()){
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
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,news.getTitle());
            pstm.setString(2,news.getContent());
            pstm.setString(3,news.getAuthor());
            pstm.setString(4,news.getNewsCategory().getId());
            pstm.setString(5,news.getViewCount());
            pstm.setString(6,news.getCoverImage());
            pstm.setString(7,news.getSubtitle());
            pstm.setString(8,news.getId());
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("setEmployeeById " + e.getMessage());
        }
        return false;
    }
    public Boolean deleteNewsById(String id) {
        String SQL = "DELETE News WHERE id = ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("deleteNewsById " + e.getMessage());
        }
        return false;
    }
    public int countAllNews(){
        String SQL = "SELECT count([News].id)\n" +
"        FROM [News]  \n" +
"        LEFT JOIN [NewsCategory] on [News].categoryId = [NewsCategory].id \n" +
"        WHERE type IS NULL";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        }catch (Exception e) {
            System.out.println("countAllNews " + e.getMessage());
        }
        return -1;
    }
    public String generateId(){
        String SQL = "select top(1) id from [News] order by id DESC";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            String number = null;
            while(rs.next()){
                number = rs.getString(1);
            }
            number = Integer.parseInt(number)+1+"";
            return number;
        }catch (Exception e) {
            System.out.println("generateId news " + e.getMessage());
        }
        return null;
    }
    public boolean writeNews(News news) {
        ArrayList<News> list = new ArrayList<>();
        String SQL = "INSERT [News] ([id], [title], [content], [author], [categoryId], [createdAt], [lastModified], [viewCount], [coverImage], [subtitle]) " +
                     "VALUES (?, ?, ?, ?, ?, getdate(), getdate(), ?, ?, ?)";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
                pstm.setString(1,news.getId());
                pstm.setString(2,news.getTitle());
                pstm.setString(3,news.getContent());
                pstm.setString(4,news.getAuthor());
                pstm.setString(5,news.getNewsCategory().getId());
                pstm.setString(6,news.getViewCount());
                pstm.setString(7,news.getCoverImage());
                pstm.setString(8,news.getSubtitle());
                pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("writeNews " + e.getMessage());
        }
        return false;
    }
}
