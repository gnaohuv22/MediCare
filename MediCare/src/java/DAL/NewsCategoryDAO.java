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

    public ArrayList<NewsCategory> getTopLevelSideBar() {
        String SQL = "SELECT id, name, parentId, href FROM [NewsCategory]" 
                + "WHERE locateId = 10";
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
            return list;
        } catch (SQLException e) {
            System.out.println("getTopLevelSideBar: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<NewsCategory> getSubLevelSideBar() {
        String SQL = "SELECT id, name, parentId, href FROM [NewsCategory] WHERE parentId IS NOT NULL";
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
            System.out.println("getSubLevelSideBar: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<NewsCategory> getNewsCategoryList() {
        String SQL = "SELECT id, name, parentId, href FROM [NewsCategory]";
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
            return list;
        } catch (SQLException e) {
            System.out.println("getTopLevelCategory: " + e.getMessage());
        }
        return list;
    }

    public List<Integer> getParentCategoryNumber() {
        String SQL = "SELECT DISTINCT parentId FROM [NewsCategory]\n"
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
        String SQL = "SELECT id, name, parentId, href FROM [NewsCategory]\n"
                + "WHERE href LIKE ?";
        NewsCategory nc = new NewsCategory();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, categorySlug);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                nc = new NewsCategory(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString("name"), 
                        String.valueOf(rs.getInt("parentId")), 
                        rs.getString("href"));
            }
        } catch (SQLException e) {
            System.out.println("getCategoryBySlug: " + e.getMessage());
        }
        return nc;
    }
    
    public ArrayList<NewsCategory> getAllSubMenu() {
        String SQL = "SELECT id, name, parentId, href, icon, locateId FROM [NewsCategory]"
                + "WHERE parentId IS NOT NULL";
        ArrayList<NewsCategory> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new NewsCategory(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString("name"), 
                        String.valueOf(rs.getInt("parentId")), 
                        rs.getString("href"), 
                        rs.getString("icon"), 
                        String.valueOf(rs.getInt("locateId")))
                );
            }
        } catch (SQLException e) {
            System.out.println("getSubLevelMenu: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<NewsCategory> getNavigationBar() {
        String SQL = "SELECT id, name, href FROM [NewsCategory]"
                + "WHERE locateId = 1";
        ArrayList<NewsCategory> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new NewsCategory(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString("name"), 
                        rs.getString("href")
                ));
            }
        } catch (SQLException e) {
            System.out.println("getNavigationBar: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<NewsCategory> getContacts() {
        String SQL = "SELECT id, name, href, icon, locateId FROM [NewsCategory]"
                + "WHERE locateId = 4";
        ArrayList<NewsCategory> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new NewsCategory(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString("name"), 
                        rs.getString("href"),
                        rs.getString("icon"),
                        rs.getString("locateId")
                ));
            }
        } catch (SQLException e) {
            System.out.println("getContacts: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<NewsCategory> getReferences() {
        String SQL = "SELECT id, name, href, icon, locateId FROM [NewsCategory]"
                + "WHERE locateId = 5";
        ArrayList<NewsCategory> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new NewsCategory(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString("name"), 
                        rs.getString("href"),
                        rs.getString("icon"),
                        rs.getString("locateId")
                ));
            }
        } catch (SQLException e) {
            System.out.println("getReferences: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<NewsCategory> getSocial() {
        String SQL = "SELECT id, name, href, icon, locateId FROM [NewsCategory]"
                + "WHERE locateId = 6";
        ArrayList<NewsCategory> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new NewsCategory(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString("name"), 
                        rs.getString("href"),
                        rs.getString("icon"),
                        rs.getString("locateId")
                ));
            }
        } catch (SQLException e) {
            System.out.println("getSocial: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<NewsCategory> getProfileMenu() {
        String SQL = "SELECT id, name, href, icon, locateId FROM [NewsCategory]"
                + "WHERE locateId = 8";
        ArrayList<NewsCategory> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new NewsCategory(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString("name"), 
                        rs.getString("href"),
                        rs.getString("icon"),
                        rs.getString("locateId")
                ));
            }
        } catch (SQLException e) {
            System.out.println("getProfileMenu: " + e.getMessage());
        }
        return list;
    }
    
    public ArrayList<NewsCategory> getProfileSidebar() {
        String SQL = "SELECT id, name, href, icon, locateId FROM [NewsCategory]"
                + "WHERE locateId = 9";
        ArrayList<NewsCategory> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                list.add(new NewsCategory(
                        String.valueOf(rs.getInt("id")), 
                        rs.getString("name"), 
                        rs.getString("href"),
                        rs.getString("icon"),
                        rs.getString("locateId")
                ));
            }
        } catch (SQLException e) {
            System.out.println("getProfileSidebar: " + e.getMessage());
        }
        return list;
    }
    
    //thu
    public ArrayList<String> getAllStatus(){
        String SQL = "select name from NewsCategory where locateId=5002";
        ArrayList<String> list = new ArrayList<>();
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                String name = rs.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            System.out.println("getAllStatus: " + e.getMessage());
        }
        return list;
    }

}
