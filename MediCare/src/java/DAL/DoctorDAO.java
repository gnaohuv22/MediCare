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

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
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
                + "\n"
                + "GROUP BY d.ARId, d.branchId, d.CVId, d.displayName, d.email, d.id, d.phone, d.profilePicture, d.salary, d.status, d.workplace, \n"
                + "b.[name], a.[name], DC.Certificates,  Department.[name], Department.id, CV.education, CV.introduce, CV.workHistory, CV.startYear, d.[password]\n"
                + "HAVING d.id IS NOT NULL";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Doctor d = new Doctor(rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("displayName"),
                        String.valueOf(rs.getInt("branchId")),
                        rs.getString("phone"),
                        String.valueOf(rs.getInt("ARId")),
                        String.valueOf(rs.getInt("CVId")),
                        String.valueOf(rs.getFloat("salary")),
                        rs.getString("workplace"),
                        rs.getString("profilePicture"),
                        String.valueOf(rs.getBoolean("status")),
                        rs.getString("password"),
                        rs.getString("branchName"),
                        rs.getString("ARName"),
                        DoctorDAO.concatenateNames(rs.getString("Certificates")),
                        String.valueOf(rs.getInt("DepartmentId")),
                        rs.getString("departmentName"),
                        rs.getString("education"),
                        rs.getString("introduce"),
                        rs.getString("workHistory"),
                        String.valueOf(rs.getInt("startYear")));
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
                return new Doctor(rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("displayName"),
                        String.valueOf(rs.getInt("branchId")),
                        rs.getString("phone"),
                        String.valueOf(rs.getInt("ARId")),
                        String.valueOf(rs.getInt("CVId")),
                        String.valueOf(rs.getFloat("salary")),
                        rs.getString("workplace"),
                        rs.getString("profilePicture"),
                        String.valueOf(rs.getBoolean("status")),
                        rs.getString("password"),
                        rs.getString("branchName"),
                        rs.getString("ARName"),
                        DoctorDAO.concatenateNames(rs.getString("Certificates")),
                        String.valueOf(rs.getInt("DepartmentId")),
                        rs.getString("departmentName"),
                        rs.getString("education"),
                        rs.getString("introduce"),
                        rs.getString("workHistory"),
                        String.valueOf(rs.getInt("startYear")));
            }
        } catch (SQLException e) {
            System.out.println("getDoctorByDoctorId: " + e);
        }
        return null;
    }

    public boolean login(String email, String password) {
        String SQL = "SELECT * FROM [Doctor] WHERE email = ? AND password = ?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, email);
            pstm.setString(2, password);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("dal.UserDAO.Login(): " + e);
        }
        return false;
    }

    public Doctor getDoctorByEmail(String email) {
        String SQL = "SELECT * FROM [Doctor] WHERE email = ?";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
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

    public static String concatenateNames(String jsonString) {
        if (jsonString == null) {
            return "";
        } else if (jsonString.equals("")) {
            return "";
        } else {
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

    }

    public ArrayList<Doctor> getTrendingDoctors() {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "SELECT TOP(5) d.id, d.email, d.password, d.displayName, b.[name] AS branchName, d.phone, a.[name] AS ARName, "
                + "COUNT(r.id) AS ReviewCount, DC.Certificates AS Certificates, Department.id as DepartmentId, "
                + "Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory, CV.startYear, "
                + "d.salary, d.workplace, d.profilePicture, d.status "
                + "FROM Doctor AS d "
                + "FULL JOIN Branch AS b On b.id = d.branchId "
                + "FULL JOIN AcademicRank AS a On a.id = d.ARId "
                + "FULL JOIN DoctorCertificates DC ON d.id = DC.DoctorId "
                + "FULL JOIN DoctorService AS DS ON DS.doctorId = d.id "
                + "FULL JOIN ServiceTag AS ST ON ST.id = DS.serviceId "
                + "FULL JOIN Department ON Department.id = ST.departmentId "
                + "FULL JOIN CurriculumVitae AS CV On CV.id = d.CVId "
                + "LEFT JOIN Reviews AS r ON r.doctorId = d.id "
                + "WHERE d.id IS NOT NULL "
                + "GROUP BY d.id, d.email, d.password, d.displayName, b.[name], d.phone, a.[name], DC.Certificates,"
                + "Department.id, Department.[name], CV.education, CV.introduce, CV.workHistory,"
                + "CV.startYear, d.salary, d.workplace, d.profilePicture, d.status "
                + "ORDER BY COUNT(r.id) DESC;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(9),
                        String.valueOf(rs.getInt(10)),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        String.valueOf(rs.getInt(15)),
                        String.valueOf(rs.getFloat(16)),
                        rs.getString(17),
                        rs.getString(18),
                        String.valueOf(rs.getInt(19))
                );

                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println("getTrendingDoctors: " + e);
        }
        return list;
    }
    
    public ArrayList<Doctor> getDoctorsByPattern(String pattern) {
        ArrayList<Doctor> list = new ArrayList<>();
        String searchValue = "%" + pattern + "%";
        String SQL = "SELECT d.id, d.email, d.password, d.displayName, b.[name] AS branchName, d.phone, a.[name] AS ARName, "
                + "COUNT(r.id) AS ReviewCount, DC.Certificates AS Certificates, Department.id as DepartmentId, "
                + "Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory, CV.startYear, "
                + "d.salary, d.workplace, d.profilePicture, d.status "
                + "FROM Doctor AS d "
                + "FULL JOIN Branch AS b On b.id = d.branchId "
                + "FULL JOIN AcademicRank AS a On a.id = d.ARId "
                + "FULL JOIN DoctorCertificates DC ON d.id = DC.DoctorId "
                + "FULL JOIN DoctorService AS DS ON DS.doctorId = d.id "
                + "FULL JOIN ServiceTag AS ST ON ST.id = DS.serviceId "
                + "FULL JOIN Department ON Department.id = ST.departmentId "
                + "FULL JOIN CurriculumVitae AS CV On CV.id = d.CVId "
                + "LEFT JOIN Reviews AS r ON r.doctorId = d.id "
                + "WHERE d.displayName COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ? "
                + "GROUP BY d.id, d.email, d.password, d.displayName, b.[name], d.phone, a.[name], DC.Certificates,"
                + "Department.id, Department.[name], CV.education, CV.introduce, CV.workHistory,"
                + "CV.startYear, d.salary, d.workplace, d.profilePicture, d.status "
                + "ORDER BY COUNT(r.id) DESC;";
        
        try (PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, searchValue);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(9),
                        String.valueOf(rs.getInt(10)),
                        rs.getString(11),
                        rs.getString(12),
                        rs.getString(13),
                        rs.getString(14),
                        String.valueOf(rs.getInt(15)),
                        String.valueOf(rs.getFloat(16)),
                        rs.getString(17),
                        rs.getString(18),
                        String.valueOf(rs.getInt(19))
                );

                list.add(d);
            }
            
        } catch (SQLException e) {
            System.out.println("getDoctorsByPattern: " + e.getMessage());
        }
        return list;
    }

    public static void main(String[] args) {
        DoctorDAO dd = new DoctorDAO();
        ArrayList<Doctor> list = dd.getDoctorsByPattern("a");
        for (Doctor doctor : list) {
            System.out.println(doctor);
        }

//        System.out.println("concat: " + concatenateNames(""));
    }

    
}
