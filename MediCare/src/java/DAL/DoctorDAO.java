/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Department;
import Models.Doctor;
import java.util.List;

/**
 *
 * @author hoang
 */
public class DoctorDAO extends DBContext {
    public ArrayList<Doctor> getListDoctor() {
        ArrayList<Doctor> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Doctor]";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        String.valueOf(rs.getInt(7)),
                        String.valueOf(rs.getFloat(8)),
                        rs.getString(9),
                        rs.getString(10),
                        String.valueOf(rs.getBoolean(11))
                    );
                list.add(d);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListDoctor: " + e.getMessage());
        } 
        return null;
    }
    
    public boolean login(String email, String password)  {
        String SQL = "SELECT * FROM [Doctor] WHERE email = ? AND password = ?";
        try (PreparedStatement pstm=connection.prepareStatement(SQL)){
            pstm.setString(1, email);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if(rs.next())
                return true;
            else 
                return false;
        } catch (SQLException e) {
            System.out.println("dal.UserDAO.Login(): "+e);
        }
        return false;
    } 
    
    public Doctor getDoctorByEmail(String email) {
        String SQL = "SELECT * FROM [Doctor] WHERE email = ?";
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Doctor d = new Doctor(String.valueOf(rs.getInt(1)),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getInt(5)),
                        rs.getString(6),
                        String.valueOf(rs.getInt(7)),
                        String.valueOf(rs.getInt(8)),
                        String.valueOf(rs.getFloat(9)),
                        rs.getString(10),
                        rs.getString(11),
                        String.valueOf(rs.getBoolean(12))
                    );
                return d;
            }
        } catch (SQLException e) {
            System.out.println("getDoctorByEmail: " + e.getMessage());
        }
        return null;
    }
    
    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "/****** Script for SelectTopNRows command from SSMS  ******/\n"
                + "SELECT d.*, b.[name] AS branchName, a.[name] AS ARName, \n"
                + "DC.Certificates AS Certificates, Department.id as DepartmentId, Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory, CV.startYear\n"
                + "FROM Doctor AS d\n"
                + "\n"
                + "FULL JOIN\n"
                + "\n"
                + "Branch AS b On b.id = d.branchId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "AcademicRank AS a\n"
                + "On a.id = d.ARId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "DoctorCertificates DC \n"
                + "ON d.id = DC.DoctorId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "DoctorService AS DS\n"
                + "ON DS.doctorId = d.id\n"
                + "\n"
                + "FULL JOIN \n"
                + "ServiceTag AS ST\n"
                + "ON ST.id = DS.serviceId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "Department\n"
                + "ON Department.id = ST.departmentId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "CurriculumVitae AS CV\n"
                + "On CV.id = d.CVId\n"
                + "\n"                
                + "WHERE d.id IS NOT NULL";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while (rs.next()) {
                Doctor d = new Doctor(rs.getString(1),
                        rs.getString(2),
                        rs.getString(4),
                        String.valueOf(rs.getInt(5)),
                        rs.getString(6),
                        String.valueOf(rs.getInt(7)),
                        String.valueOf(rs.getInt(8)),
                        String.valueOf(rs.getFloat(9)),
                        rs.getString(10),
                        rs.getString(11),
                        String.valueOf(rs.getInt(12)),
                        rs.getString(3),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        String.valueOf(rs.getInt(16)),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        String.valueOf(rs.getInt(21)));
                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println("getAllDoctors: " + e);
        }
        return list;
    }

    public Doctor getDoctorByDoctorId(String doctorId) {
        String sql = "/****** Script for SelectTopNRows command from SSMS  ******/\n"
                + "SELECT d.*, b.[name] AS branchName, a.[name] AS ARName, \n"
                + "DC.Certificates AS Certificates, Department.id as DepartmentId, Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory, CV.startYear\n"
                + "FROM Doctor AS d\n"
                + "\n"
                + "FULL JOIN\n"
                + "\n"
                + "Branch AS b On b.id = d.branchId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "AcademicRank AS a\n"
                + "On a.id = d.ARId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "DoctorCertificates DC \n"
                + "ON d.id = DC.DoctorId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "DoctorService AS DS\n"
                + "ON DS.doctorId = d.id\n"
                + "\n"
                + "FULL JOIN \n"
                + "ServiceTag AS ST\n"
                + "ON ST.id = DS.serviceId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "Department\n"
                + "ON Department.id = ST.departmentId\n"
                + "\n"
                + "FULL JOIN \n"
                + "\n"
                + "CurriculumVitae AS CV\n"
                + "On CV.id = d.CVId\n"
                + "\n"
                + "WHERE d.id = ?\n"
                + "GROUP BY d.ARId, d.branchId, d.CVId, d.displayName, d.email, d.id, d.phone, d.profilePicture, d.salary, d.status, d.workplace, \n"
                + "b.[name], a.[name], DC.Certificates,  Department.[name], Department.id, CV.education, CV.introduce, CV.workHistory, CV.startYear, d.[password]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Doctor(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        String.valueOf(rs.getInt(4)),
                        rs.getString(5),
                        String.valueOf(rs.getInt(6)),
                        String.valueOf(rs.getInt(7)),
                        String.valueOf(rs.getFloat(8)),
                        rs.getString(9),
                        rs.getString(10),
                        String.valueOf(rs.getBoolean(11)),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        rs.getString(15),
                        String.valueOf(rs.getInt(16)),
                        rs.getString(17),
                        rs.getString(18),
                        rs.getString(19),
                        rs.getString(20),
                        String.valueOf(rs.getInt(21)));
            }
        } catch (SQLException e) {
            System.out.println("getDoctorByDoctorId: " + e);
        }
        return null;
    }

    public static String concatenateNames(String jsonString) {
            // Bỏ đi ký tự '[' và ']' ở đầu và cuối chuỗi
        jsonString = jsonString.substring(1, jsonString.length() - 1);

        // Tách chuỗi thành các phần con dựa trên ký tự '},'
        String[] parts = jsonString.split("\\},");

        // Tạo một danh sách để lưu trữ các tên
        List<String> names = new ArrayList<>();

        // Xử lý từng phần con
        for (String part : parts) {
            // Bỏ đi ký tự '{' ở đầu phần con
            part = part.substring(1);

            // Tìm vị trí của chuỗi "name":""
            int nameIndex = part.indexOf("\"name\":\"");

            if (nameIndex >= 0) {
                // Bỏ đi "name":""
                String name = part.substring(nameIndex + 8);

                // Bỏ đi dấu nháy kép cuối cùng
                name = name.substring(0, name.length() - 1);

                names.add(name);
            }
        }

        // Concatenate các tên bằng dấu phẩy và khoảng trắng
        String concatenatedNames = String.join(", ", names);

        return concatenatedNames.substring(0, concatenatedNames.length() - 1);
        
    }

    public static void main(String[] args) {
        DoctorDAO dd = new  DoctorDAO();
        ArrayList<Doctor> list = dd.getAllDoctors();
        for (Doctor doctor : list) {
            System.out.println(doctor);
        }

//        System.out.println("concat: " + concatenateNames(""));
    }
}
