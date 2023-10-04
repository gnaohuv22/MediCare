/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class SubLevelMenuDAO extends DBContext {

    public ArrayList<SubLevelMenu> getListSubLevelMenu() {
        String SQL = "SELECT * FROM [SubLevelMenu]";
        ArrayList<SubLevelMenu> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new SubLevelMenu(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        String.valueOf(rs.getInt(3)),
                        String.valueOf(rs.getInt(4)),
                        rs.getString(5),
                        rs.getString(6)
                )
                );
            }
        } catch (SQLException e) {
            System.out.println("getListSubLevelMenu: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<SubLevelMenu> getListSubLevelCategory() {
        String SQL = "SELECT s.id, s.content, s.parentId, t.id, t.name, s.href, s.icon FROM [SubLevelMenu] as s\n"
                + "LEFT JOIN [TopLevelMenu] as t \n"
                + "ON t.id = s.locateId";
        ArrayList<SubLevelMenu> list = new ArrayList<>();

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new SubLevelMenu(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        String.valueOf(rs.getInt(3)),
                        String.valueOf(rs.getInt(4)),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7)
                )
                );
            }
        } catch (SQLException e) {
            System.out.println("getListSubLevelCategory: " + e.getMessage());
        }
        return list;
    }
}
