
package DAL;

import Models.AdminHeaderProfile;
import Models.AdminSidebarMenu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ViewDAO extends DBContext {
    public ArrayList<AdminSidebarMenu> getSidebarMenu(){
        ArrayList list = new ArrayList<AdminSidebarMenu>();
        String SQL = "SELECT name,link,icon FROM AdminSidebarMenu";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String link = rs.getString("link");
                String icon = rs.getString("icon");
                AdminSidebarMenu obj = new AdminSidebarMenu(name,link,icon);
                list.add(obj);
            }
        }catch (Exception e) {
            System.out.println("getHeaderSidebar " + e.getMessage());
        }
        return list;
    } 
    public ArrayList<AdminHeaderProfile> getAdminHeaderProfile(){
        ArrayList list = new ArrayList<AdminHeaderProfile>();
        String SQL = "SELECT name,link FROM AdminHeaderProfile";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String link = rs.getString("link");
                AdminHeaderProfile obj = new AdminHeaderProfile(name,link);
                list.add(obj);
            }
        }catch (Exception e) {
            System.out.println("getAdminHeaderProfile " + e.getMessage());
        }
        return list;
    } 
    public ArrayList<String> getTitleTableEmployee(){
        ArrayList list = new ArrayList<String>();
        String SQL = "SELECT titleTableEmployee FROM TitleTable WHERE titleTableEmployee is not NULL";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("titleTableEmployee");
                list.add(title);
            }
        }catch (Exception e) {
            System.out.println("getTitleTableEmployee " + e.getMessage());
        }
        return list;
    } public ArrayList<String> getTitleTableUser(){
        ArrayList list = new ArrayList<String>();
        String SQL = "SELECT titleTableUser FROM titleTable WHERE titleTableUser is not NULL";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("titleTableUser");
                list.add(title);
            }
        }catch (Exception e) {
            System.out.println("getTitleTableUser " + e.getMessage());
        }
        return list;
    } public ArrayList<String> getTitleTableDoctor(){
        ArrayList list = new ArrayList<String>();
        String SQL = "SELECT titleTableDoctor FROM TitleTable WHERE titleTableDoctor is not NULL";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("titleTableDoctor");
                list.add(title);
            }
        }catch (Exception e) {
            System.out.println("getTitleTableDoctor " + e.getMessage());
        }
        return list;
    } 
    public ArrayList<String> getTitleTableAppointments(){
        ArrayList list = new ArrayList<String>();
        String SQL = "SELECT titleTableAppointments FROM TitleTable WHERE titleTableAppointments is not NULL";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("titleTableAppointments");
                list.add(title);
            }
        }catch (Exception e) {
            System.out.println("getTitleTableAppointments " + e.getMessage());
        }
        return list;
    } 
    public ArrayList<String> getTitleTableReviews(){
        ArrayList list = new ArrayList<String>();
        String SQL = "SELECT titleTableReviews FROM TitleTable WHERE titleTableReviews is not NULL";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("titleTableReviews");
                list.add(title);
            }
        }catch (Exception e) {
            System.out.println("getTitleTableReviews " + e.getMessage());
        }
        return list;
    } 
    public ArrayList<String> getTitleTableNews(){
        ArrayList list = new ArrayList<String>();
        String SQL = "SELECT titleTableNews FROM TitleTable WHERE titleTableNews is not NULL";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("titleTableNews");
                list.add(title);
            }
        }catch (Exception e) {
            System.out.println("getTitleTableNews " + e.getMessage());
        }
        return list;
    } 
    public int countAll(String column,String table){
        String SQL = "select COUNT(?) from ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, column);
            pstm.setString(2, table);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        }catch (Exception e) {
            System.out.println("countAll " + e.getMessage());
        }
        return -1;
    }
    public int countAllDoctor(){
        String SQL = "select COUNT(id) from Doctor";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        }catch (Exception e) {
            System.out.println("countAllDoctor " + e.getMessage());
        }
        return -1;
    }
    public int countAllUser(){
        String SQL = "select COUNT(id) from [User]";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        }catch (Exception e) {
            System.out.println("countAllUser " + e.getMessage());
        }
        return -1;
    }
    public int countAllEmployee(){
        String SQL = "select COUNT(id) from Employee";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        }catch (Exception e) {
            System.out.println("countAllEmployee " + e.getMessage());
        }
        return -1;
    }
}
