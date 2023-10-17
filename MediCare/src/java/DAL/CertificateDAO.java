/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Certificate;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class CertificateDAO extends DBContext{
    public ArrayList<Certificate> getAllCertificate() {
        ArrayList<Certificate> list = new ArrayList<>();
        String SQL = "SELECT id,name,wage FROM Certificate";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String wage = rs.getString(3);
                Certificate obj = new Certificate(id,name,wage);
                list.add(obj);
            }
        } catch (Exception e) {
            System.out.println("getAllCertificate " + e.getMessage());
        }
        return list;
    }
}
