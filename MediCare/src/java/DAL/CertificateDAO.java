/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.AcademicRank;
import Models.Certificate;

/**
 *
 * @author hoang
 */
public class CertificateDAO extends DBContext{
    public ArrayList<Certificate> getListCertificate() {
        ArrayList<Certificate> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Certificate]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Certificate c = new Certificate(String.valueOf(rs.getInt(1)),
                                                rs.getString(2),
                                                String.valueOf(rs.getFloat(3)));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListCertificate: " + e.getMessage());
        } 
        return null;
    }
}
