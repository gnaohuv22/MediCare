/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Branch;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class BranchDAO extends DBContext{
    public ArrayList<Branch> getAllBranch() {
        ArrayList<Branch> list = new ArrayList<>();
        String SQL = "SELECT id,name,description,locateId FROM Branch";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String locateId = rs.getString(4);
                Branch obj = new Branch(id,name,description,locateId);
                list.add(obj);
            }
        } catch (Exception e) {
            System.out.println("getAllBranch " + e.getMessage());
        }
        return list;
    }
    public Branch getBranchById(String id) {
        String SQL = "SELECT name,description,locateId FROM Branch WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String name = rs.getString(2);
                String description = rs.getString(3);
                String locateId = rs.getString(4);
                Branch obj = new Branch(id,name,description,locateId);
                return obj;
            }
        }catch (Exception e) {
            System.out.println("getBranchById " + e.getMessage());
        }
        return null;
    }
}
