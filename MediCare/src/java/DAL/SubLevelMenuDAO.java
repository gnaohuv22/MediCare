/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.AdminSidebarMenu;
import Models.SubLevelMenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class SubLevelMenuDAO extends DBContext {

    public ArrayList<AdminSidebarMenu> getSidebarMenu() {
        ArrayList<AdminSidebarMenu> list = new ArrayList<>();
        String SQL = "SELECT name,href,icon FROM NewsCategory WHERE parentId = (select id from NewsCategory Where name like 'AdminSidebarMenu')";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String link = rs.getString("href");
                String icon = rs.getString("icon");
                AdminSidebarMenu obj = new AdminSidebarMenu(name, link, icon);
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getSidebarMenu " + e.getMessage());
        }
        return null;
    }

    public ArrayList<String> getTitleTable(String table) {
        ArrayList<String> list = new ArrayList<>();
        String SQL = "SELECT name FROM NewsCategory WHERE parentId = (select id from NewsCategory Where name like ?)";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, table);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("name");
                list.add(title);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getTitleTable " + table + " " + e.getMessage());
        }
        return null;
    }

    public ArrayList<String> getTitleTableForSchedule(String table) {
        ArrayList<String> list = new ArrayList<>();
        String SQL = "SELECT name FROM NewsCategory WHERE parentId = (select id from NewsCategory Where name like ?)";
        int count = 0;
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, table);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("name");
                count++;
                list.add(title);
                if (count == 4) {
                    break;
                }
            }
            return list;
        } catch (Exception e) {
            System.out.println("getTitleTableForSchedule " + table + " " + e.getMessage());
        }
        return null;
    }

    public ArrayList<AdminSidebarMenu> getSubLevelMenuByContent(String content) {
        ArrayList<AdminSidebarMenu> list = new ArrayList<>();
        String SQL = "SELECT name,href,icon FROM NewsCategory WHERE parentId = (select id from NewsCategory Where name like ?)";
        System.out.println("AA");
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, content);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                System.out.println("abc");
                String name = rs.getString("name");
                String link = rs.getString("href");
                String icon = rs.getString("icon");
                AdminSidebarMenu obj = new AdminSidebarMenu(name, link, icon);
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getSubLevelMenuByContent " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        SubLevelMenuDAO slv = new SubLevelMenuDAO();
        ArrayList<AdminSidebarMenu> list = slv.getSubLevelMenuByContent("Trạng thái slot");
        System.out.println(list);
    }

}
