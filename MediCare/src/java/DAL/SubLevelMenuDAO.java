/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.AdminSidebarMenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class SubLevelMenuDAO extends DBContext {

    public ArrayList<AdminSidebarMenu> getSidebarMenu() {
        ArrayList<AdminSidebarMenu> list = new ArrayList<>();
        String SQL = "SELECT content,href,icon FROM SubLevelMenu WHERE parentId = (select id from SubLevelMenu Where content like 'AdminSidebarMenu')";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("content");
                String link = rs.getString("href");
                String icon = rs.getString("icon");
                AdminSidebarMenu obj = new AdminSidebarMenu(name, link, icon);
                list.add(obj);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getHeaderSidebar " + e.getMessage());
        }
        return null;
    }

    public ArrayList<String> getTitleTable(String table) {
        ArrayList<String> list = new ArrayList<>();
        String SQL = "SELECT content FROM SubLevelMenu WHERE parentId = (select id from SubLevelMenu Where content like ?)";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, table);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("content");
                list.add(title);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getTitleTable " + table + " " + e.getMessage());
        }
        return null;
    }
}
