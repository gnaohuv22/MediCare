/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Doctor;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                Doctor d = new Doctor(rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("displayName"),
                        String.valueOf(rs.getInt("branchId")),
                        rs.getString("phone"),
                        String.valueOf(rs.getInt("ARId")),
                        String.valueOf(rs.getInt("CVId")),
                        String.valueOf(rs.getFloat("salary")),
                        rs.getString("workplace"),
                        rs.getString("profilePicture"),
                        String.valueOf(rs.getInt("status")),
                        String.valueOf(rs.getDate("birthDate")),
                        String.valueOf(rs.getInt("gender")),
                        String.valueOf(rs.getInt("isDelete"))
                );
                list.add(d);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("getListDoctor: " + e.getMessage());
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
                Doctor d = new Doctor(rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("displayName"),
                        String.valueOf(rs.getInt("branchId")),
                        rs.getString("phone"),
                        String.valueOf(rs.getInt("ARId")),
                        String.valueOf(rs.getInt("CVId")),
                        String.valueOf(rs.getFloat("salary")),
                        rs.getString("workplace"),
                        rs.getString("profilePicture"),
                        String.valueOf(rs.getInt("status")),
                        String.valueOf(rs.getDate("birthDate")),
                        String.valueOf(rs.getInt("gender")),
                        String.valueOf(rs.getInt("isDelete"))
                );
                return d;
            }
        } catch (SQLException e) {
            System.out.println("getDoctorByEmail: " + e.getMessage());
        }
        return null;
    }

    public Doctor getDoctorByCertID(String CertId) {
        String SQL = "SELECT * FROM [Doctor] WHERE CVId = ?";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, CertId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Doctor d = new Doctor(rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("displayName"),
                        String.valueOf(rs.getInt("branchId")),
                        rs.getString("phone"),
                        String.valueOf(rs.getInt("ARId")),
                        String.valueOf(rs.getInt("CVId")),
                        String.valueOf(rs.getFloat("salary")),
                        rs.getString("workplace"),
                        rs.getString("profilePicture"),
                        String.valueOf(rs.getInt("status")),
                        String.valueOf(rs.getDate("birthDate")),
                        String.valueOf(rs.getInt("gender")),
                        String.valueOf(rs.getInt("isDelete"))
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
        String sql = "SELECT \n" +
"                     d.*, \n" +
"                     b.[name] AS branchName, \n" +
"                     a.[name] AS ARName, \n" +
"                     DC.Certificates AS Certificates, \n" +
"                     Department.id AS DepartmentId, \n" +
"                     Department.[name] AS departmentName, \n" +
"                     CV.education, \n" +
"                     CV.introduce, \n" +
"                     CV.workHistory, \n" +
"                     CV.startYear \n" +
"                 FROM \n" +
"                     Doctor AS d \n" +
"                 LEFT JOIN Branch AS b ON b.id = d.branchId \n" +
"                 LEFT JOIN AcademicRank AS a ON a.id = d.ARId \n" +
"                 LEFT JOIN DoctorCertificates DC ON d.id = DC.DoctorId \n" +
"                 LEFT JOIN DoctorService AS DS ON DS.doctorId = d.id \n" +
"                 LEFT JOIN ServiceTag AS ST ON ST.id = DS.serviceId \n" +
"                 LEFT JOIN Department ON Department.id = ST.departmentId \n" +
"                 LEFT JOIN CurriculumVitae AS CV ON CV.id = d.CVId \n" +
"                 WHERE \n" +
"                     d.id IS NOT NULL \n" +
"                     AND d.isDelete = 0 or d.isDelete is null\n" +
"                 GROUP BY \n" +
"                     d.ARId, \n" +
"                     d.branchId, \n" +
"                     d.CVId, \n" +
"                     d.displayName, \n" +
"                     d.email, \n" +
"                     d.id, \n" +
"                     d.password, \n" +
"                     d.phone, \n" +
"                     d.profilePicture, \n" +
"                     d.salary, \n" +
"                     d.status, \n" +
"                     d.workplace, \n" +
"                     d.birthDate, \n" +
"                     d.gender, \n" +
"                     d.isDelete, \n" +
"                     b.name, \n" +
"                     a.name, \n" +
"                     Certificates, \n" +
"                     Department.id, \n" +
"                     Department.name, \n" +
"                     CV.education, \n" +
"                     CV.introduce, \n" +
"                     CV.workHistory, \n" +
"                     CV.startYear";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Doctor doc = new Doctor();
                doc.setId(rs.getString("id"));
                doc.setEmail(rs.getString("email"));
                doc.setDisplayName(rs.getString("displayName"));
                doc.setBranchId(String.valueOf(rs.getInt("branchId")));
                doc.setPhone(rs.getString("phone"));
                doc.setARId(String.valueOf(rs.getInt("ARId")));
                doc.setCVId(String.valueOf(rs.getInt("CVId")));
                doc.setSalary(String.valueOf(rs.getFloat("salary")));
                doc.setWorkplace(rs.getString("workplace"));
                doc.setProfilePicture(rs.getString("profilePicture"));
                doc.setStatus(String.valueOf(rs.getInt("status")));
                doc.setCertificates(rs.getString("Certificates"));
                doc.setBranchName(rs.getString("branchName"));
                doc.setARName(rs.getString("ARName"));
                doc.setDepartmentId(rs.getString("DepartmentId"));
                doc.setDepartmentName(rs.getString("DepartmentName"));
                doc.setEducation(rs.getString("education"));
                doc.setIntroduce(rs.getString("introduce"));
                doc.setWorkHistory(rs.getString("workHistory"));
                doc.setStartYear(rs.getString("startYear"));
                doc.setPassword(rs.getString("password"));
                doc.setBirthDate(String.valueOf(rs.getDate("birthDate")));
                doc.setGender(String.valueOf(rs.getInt("gender")));
                doc.setIsDelete(String.valueOf(rs.getInt("isDelete")));
//                Doctor d = new Doctor(
//                        rs.getString("id"),
//                        rs.getString("email"),
//                        rs.getString("displayName"),
//                        String.valueOf(rs.getInt("branchId")),
//                        rs.getString("phone"),
//                        String.valueOf(rs.getInt("ARId")),
//                        String.valueOf(rs.getInt("CVId")),
//                        String.valueOf(rs.getFloat("salary")),
//                        rs.getString("workplace"),
//                        rs.getString("profilePicture"),
//                        String.valueOf(rs.getInt("status")),
//                         rs.getString("Certificates"),
//                          rs.getString("branchName"),
//                        rs.getString("ARName"),
//                        rs.getString("DepartmentId"),
//                        rs.getString("DepartmentName"),
//                         rs.getString("education"),
//                        rs.getString("introduce"),
//                        rs.getString("workHistory"),
//                        rs.getString("startYear"),
//                        rs.getString("password"),
//                        String.valueOf(rs.getDate("birthDate")),
//                        String.valueOf(rs.getInt("gender")),
//                        String.valueOf(rs.getInt("isDelete")));

                list.add(doc);
            }
        } catch (SQLException e) {
            System.out.println("getAllDoctors: " + e);
        }
        return list;
    }

    public Doctor getDoctorById(String Id) {
        String SQL = "select id, email, displayName, branchId, phone, ARId, CVId, salary, workplace, profilePicture, [status], [password], birthDate, gender, isDelete from Doctor where id = ?";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, Id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getString("id"));
                d.setDisplayName(rs.getString("displayName"));
                d.setEmail(rs.getString("email"));
                d.setBranchId(String.valueOf(rs.getInt("branchId")));
                d.setPhone(rs.getString("phone"));
                d.setARId(String.valueOf(rs.getInt("ARId")));
                d.setCVId(String.valueOf(rs.getInt("CVId")));
                d.setSalary(String.valueOf(rs.getFloat("salary")));
                d.setWorkHistory(rs.getString("workplace"));
                d.setProfilePicture(rs.getString("profilePicture"));
                d.setStatus(String.valueOf(rs.getInt("status")));
                d.setBirthDate(String.valueOf(rs.getDate("birthDate")));
                d.setGender(String.valueOf(rs.getInt("gender")));
                d.setIsDelete(String.valueOf(rs.getInt("isDelete")));
                d.setPassword(rs.getString("password"));
                return d;
            }
        } catch (SQLException e) {
            System.out.println("getDoctorByEmail: " + e.getMessage());
        }
        return null;
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
                + "GROUP BY d.ARId, d.branchId, d.CVId, d.displayName, d.email, d.id, d.phone, d.profilePicture, d.salary, d.status, d.workplace, d.birthDate, d.gender , d.isDelete,  \n"
                + "b.[name], a.[name], DC.Certificates,  Department.[name], Department.id, CV.education, CV.introduce, CV.workHistory, CV.startYear, d.[password]";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Doctor d = new Doctor();
                d.setId(rs.getString("id"));
                d.setEmail(rs.getString("email"));
                d.setPassword(rs.getString("password"));
                d.setDisplayName(rs.getString("displayName"));
                d.setBranchId(String.valueOf(rs.getInt("branchId")));
                d.setPhone(rs.getString("phone"));
                d.setARId(String.valueOf(rs.getInt("ARId")));
                d.setCVId(String.valueOf(rs.getInt("CVId")));
                d.setSalary(String.valueOf(rs.getFloat("salary")));
                d.setWorkplace(rs.getString("workplace"));
                d.setProfilePicture(rs.getString("profilePicture"));
                d.setStatus(String.valueOf(rs.getInt("status")));
                d.setBirthDate(String.valueOf(rs.getDate("birthDate")));
                d.setGender(String.valueOf(rs.getInt("gender")));
                d.setIsDelete(String.valueOf(rs.getInt("isDelete")));
                d.setBranchName(rs.getString("branchName"));
                d.setARName(rs.getString("ARName"));
                d.setCertificates(rs.getString("Certificates"));
                d.setDepartmentId(rs.getString("DepartmentId"));
                d.setEducation(rs.getString("education"));
                d.setIntroduce(rs.getString("introduce"));
                d.setWorkHistory(rs.getString("workHistory"));
                d.setStartYear(rs.getString("startYear"));
                return d;

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
        DoctorDAO dd = new DoctorDAO();
        List<Doctor> list = dd.getAllDoctors();
        for (Doctor doc : list) {
            System.out.println(doc);
        }

//        System.out.println("concat: " + concatenateNames(""));
    }

    public void addDoctor(Doctor d) throws ParseException {
        try {
            String sql = "INSERT INTO [dbo].[Doctor] "
                    + "([id], [email], [password], [displayName], [branchId], [phone], [ARId], [CVId], [salary], [workplace], [profilePicture], [status], [birthDate], [gender], [isDelete]) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println(d.getDisplayName());
            statement.setString(1, d.getId());
            statement.setString(2, d.getEmail());
            statement.setString(3, d.getPassword());
            statement.setString(4, d.getDisplayName());
            statement.setInt(5, Integer.parseInt(d.getBranchId()));
            statement.setString(6, d.getPhone());
            statement.setInt(7, Integer.parseInt(d.getARId()));
            statement.setInt(8, Integer.parseInt(d.getCVId()));
            statement.setFloat(9, Float.parseFloat(d.getSalary()));
            statement.setString(10, d.getWorkplace());
            statement.setString(11, d.getProfilePicture());
            statement.setString(12, d.getStatus());
            statement.setString(13, d.getBirthDate());
            statement.setInt(14, Integer.parseInt(d.getGender()));
            statement.setString(15, d.getIsDelete());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("pasre fail");
        }
    }

    public int countAllDoctor() {
        String SQL = "select COUNT(id) from Doctor";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        } catch (Exception e) {
            System.out.println("countAllDoctor " + e.getMessage());
        }
        return -1;
    }

    public void updateDoctor(Doctor d) {
        try {
            String sql = "UPDATE [dbo].[Doctor]\n"
                    + "           SET [email] = ?"
                    + "           ,[displayName] = ?"
                    + "           ,[branchId] = ?"
                    + "           ,[phone] = ?"
                    + "         ,[ARId] = ?"
                    + "           ,[CVId] = ?"
                    + "           ,[salary] = ?"
                    + "        ,[workplace] = ?"
                    + "        ,[profilePicture] = ?"
                    + "        ,[status] = ?"
                    + "        ,[birthDate] = ?"
                    + "        ,[gender] = ?"
                    + "        WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, d.getEmail());
            statement.setString(2, d.getDisplayName());
            statement.setString(3, d.getBranchId());
            statement.setString(4, d.getPhone());
            statement.setString(5, d.getARId());
            statement.setString(6, d.getCVId());
            statement.setString(7, d.getSalary());
            statement.setString(8, d.getWorkplace());
            statement.setString(9, d.getProfilePicture());
            statement.setString(10, d.getStatus());
            statement.setString(11, d.getBirthDate());
            statement.setInt(12, Integer.parseInt(d.getGender()));
            statement.setString(13, d.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("update fail");
        }
    }

    public void deleteDoctor(Doctor d) {
        try {
            String sql = "UPDATE [dbo].[Doctor]\n"
                    + "            SET [isDelete] = 1"
                    + "        WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, d.getId());
            statement.executeUpdate();
            System.out.println("Delete success");
        } catch (SQLException ex) {
        }
    }

}
