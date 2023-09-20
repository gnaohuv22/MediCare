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
import Models.CertificateDoctor;

/**
 *
 * @author hoang
 */
public class CertificateDoctorDAO extends DBContext{
    public ArrayList<CertificateDoctor> getListCertificateDoctor() {
        ArrayList<CertificateDoctor> list = new ArrayList<>();
        String SQL = "SELECT * FROM [CertificateDoctor]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CertificateDoctor cd = new CertificateDoctor(
                        String.valueOf(rs.getInt(1)),
                        rs.getString(2));
                list.add(cd);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListCertificateDoctor: " + e.getMessage());
        } 
        return null;
    }
}
