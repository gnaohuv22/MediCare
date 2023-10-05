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
import Models.CertificateDoctor;
import java.util.List;

/**
 *
 * @author hoang
 */
public class CertificateDoctorDAO extends DBContext {

    public ArrayList<CertificateDoctor> getListCertificateDoctor() {
        ArrayList<CertificateDoctor> list = new ArrayList<>();
        String SQL = "SELECT * FROM [CertificateDoctor]";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
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

    public void addCertificateForDoctor(String certId, String doctorId) {
        try {
            String sql
                    = "INSERT INTO [dbo].[CertificateDoctor]\n"
                    + "           ([certId]\n"
                    + "           ,[doctorId])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, certId);
            statement.setString(2, doctorId);
            statement.executeUpdate();
            System.out.println("Add complete");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("pasre fail");
        }
    }

    public void deleteCertificateForDoctor(CertificateDoctor cd) {
        try {
            String sql
                    = "DELETE FROM [dbo].[CertificateDoctor]\n"
                    + "      WHERE certId = ? and doctorId = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, cd.getCertId());
            statement.setString(2, cd.getDoctorId());
            statement.executeUpdate();
            System.out.println("Delete complete");
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("pasre fail");
        }
    }
        public ArrayList<CertificateDoctor> getCertificateByDoctorId(String id) {
        ArrayList<CertificateDoctor> list = new ArrayList<>();
        String SQL = "SELECT * FROM [CertificateDoctor] where DoctorId = ?";
        
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CertificateDoctor c = new CertificateDoctor(String.valueOf(rs.getInt(1)),
                        rs.getString(2));
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListCertificate: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        CertificateDoctorDAO dao = new CertificateDoctorDAO();
        dao.addCertificateForDoctor("1", "1");
        
       
    }
}
