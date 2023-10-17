/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.News;
import Models.NewsCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class NewsDAO extends DBContext{
    private int numberRecord;
    public int getNumberRecord(){
        return this.numberRecord;
    }
    public ArrayList<News> getAllNews() {
        ArrayList<News> list = new ArrayList<>();
        String SQL = "SELECT [News].[id][newsId],[title],[content],[author],[categoryId],[createdAt],[lastModified],[viewCount],[coverImage], [subtitle], " +
        "[NewsCategory].name[cName]\n" +
        "FROM [News]\n" +
        "JOIN [NewsCategory] on [News].categoryId = [NewsCategory].id";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
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
                String subtitle =rs.getString("subtitle");
                NewsCategory newsCategory = new NewsCategory(categoryId, categoryName);
                News news= new News(id, title, content, author, newsCategory, createdAt, lastModified, viewCount,coverImage,subtitle);
                list.add(news);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllNews " + e.getMessage());
        }
        return null;
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
    public boolean addNews(News news) {
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
            System.out.println("getListNews " + e.getMessage());
        }
        return false;
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
    public News getNewsById(String id) {
        String SQL = "SELECT [title],[content],[author],[categoryId],[createdAt],[lastModified],[viewCount],[coverImage], [subtitle], " +
        "[NewsCategory].name[cName] " +
        "FROM [News] " +
        "JOIN [NewsCategory] on [News].categoryId = [NewsCategory].id "+
        "WHERE [News].id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("title");
                String content = rs.getString("content");
                String author = rs.getString("author");
                String categoryId = rs.getString("categoryId");
                String createdAt = rs.getString("createdAt");
                String lastModified = rs.getString("lastModified");
                String viewCount = rs.getString("viewCount");
                String coverImage = rs.getString("coverImage");
                String categoryName = rs.getString("cName");
                String subtitle =rs.getString("subtitle");
                NewsCategory newsCategory = new NewsCategory(categoryId, categoryName);
                News news= new News(id, title, content, author, newsCategory, createdAt, lastModified, viewCount,coverImage,subtitle);
                return news;
            }
        } catch (Exception e) {
            System.out.println("getNewsById " + e.getMessage());
        }
        return null;
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
            String number = rs.getString(1);
            number = Integer.parseInt(number)+1+"";
            return number;
        }catch (Exception e) {
            System.out.println("generateId news " + e.getMessage());
        }
        return null;
    }
}
