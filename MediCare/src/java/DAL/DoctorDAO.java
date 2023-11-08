/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import Models.Certificate;
import Models.CertificateDoctor;
import Controllers.PasswordEncryption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Models.Department;
import Models.Doctor;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 *
 * @author hoang
 */
public class DoctorDAO extends DBContext {

    private int noOfRecords;

    public int getNoOfRecords() {
        return noOfRecords;
    }
//Method cua Thai 

    public ArrayList<Doctor> getListDoctor() {
        //@thaitrinh
        ArrayList<Doctor> list = new ArrayList<>();
        String SQL = "SELECT * FROM [Doctor]";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ResultSet rs = ps.executeQuery();
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
                        String.valueOf(rs.getInt("status")),
                        rs.getString("password"),
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
            statement.executeUpdate(); // Use executeUpdate to perform INSERT
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        } catch (NumberFormatException e) {
            System.out.println(e);
            System.out.println("parse fail");
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

    public void deleteDoctor(String id) {
        try {
            String sql = "UPDATE [dbo].[Doctor] SET [isDelete] = 1 WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
            System.out.println("Delete success");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void undoDoctor(Doctor d) {
        try {
            String sql = "UPDATE [dbo].[Doctor]\n"
                    + "            SET [isDelete] = 0"
                    + "        WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, d.getId());
            statement.executeUpdate();
            System.out.println("Delete success");
        } catch (SQLException ex) {
        }
    }

    public int doctorCount(List<Doctor> list) {
        return list.size();
    }

    public String autoGenerateID() {
        String id = null;
        DoctorDAO doc = new DoctorDAO();
        List<Doctor> list = doc.getAllDoctorsByCondition("", "", "", "");

        int maxId = 0;
        for (Doctor d : list) {
            try {
                int existedId = Integer.parseInt(d.getId());
                if (existedId > maxId) {
                    maxId = existedId;
                }
            } catch (NumberFormatException e) {
                // Handle invalid IDs here (e.g., log or ignore)
                System.err.println("Invalid ID: " + d.getId());
            }
        }

        maxId++;
        id = String.valueOf(maxId);
        return id;
    }

    public ArrayList<Doctor> pagingDoctor(String search, String BranchId, String ARId, String isDelete, int index) {
        ArrayList<Doctor> list = new ArrayList<>();

        String sql = "SELECT\n"
                + "    d.*,\n"
                + "    b.[name] AS branchName,\n"
                + "    a.[name] AS ARName,\n"
                + "    DC.Certificates AS Certificates,\n"
                + "    Department.id AS DepartmentId,\n"
                + "    Department.[name] AS departmentName,\n"
                + "    CV.education,\n"
                + "    CV.introduce,\n"
                + "    CV.workHistory,\n"
                + "    CV.startYear\n"
                + "FROM\n"
                + "    Doctor AS d\n"
                + "LEFT JOIN Branch AS b ON b.id = d.branchId\n"
                + "LEFT JOIN AcademicRank AS a ON a.id = d.ARId\n"
                + "LEFT JOIN DoctorCertificates AS DC ON d.id = DC.DoctorId\n"
                + "LEFT JOIN DoctorService AS DS ON DS.doctorId = d.id\n"
                + "LEFT JOIN ServiceTag AS ST ON ST.id = DS.serviceId\n"
                + "LEFT JOIN Department ON Department.id = ST.departmentId\n"
                + "LEFT JOIN CurriculumVitae AS CV ON CV.id = d.CVId\n"
                + "WHERE\n"
                + "    d.id IS NOT NULL\n"
                + "    AND d.isDelete LIKE ?\n"
                + "    AND d.displayName LIKE ?\n"
                + "	AND d.branchId like ?\n"
                + "	AND d.ARId LIKE ?\n"
                + "ORDER BY d.id\n"
                + "OFFSET ? ROWS FETCH NEXT 8 ROWS ONLY";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + isDelete + "%");
            st.setString(2, "%" + search + "%");
            st.setString(3, "%" + BranchId + "%");
            st.setString(4, "%" + ARId + "%");
            st.setInt(5, (index - 1) * 8);
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
                list.add(doc);
            }
        } catch (SQLException e) {
            System.out.println("Paging Doctor: " + e);
        }
        return list;
    }

    public ArrayList<Doctor> getAllDoctorsByCondition(String isDelete, String search, String BranchId, String ARId) {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    d.*,\n"
                + "    b.[name] AS branchName,\n"
                + "    a.[name] AS ARName,\n"
                + "    DC.Certificates AS Certificates,\n"
                + "    Department.id AS DepartmentId,\n"
                + "    Department.[name] AS departmentName,\n"
                + "    CV.education,\n"
                + "    CV.introduce,\n"
                + "    CV.workHistory,\n"
                + "    CV.startYear\n"
                + "FROM\n"
                + "    Doctor AS d\n"
                + "LEFT JOIN Branch AS b ON b.id = d.branchId\n"
                + "LEFT JOIN AcademicRank AS a ON a.id = d.ARId\n"
                + "LEFT JOIN DoctorCertificates DC ON d.id = DC.DoctorId\n"
                + "LEFT JOIN DoctorService AS DS ON DS.doctorId = d.id\n"
                + "LEFT JOIN ServiceTag AS ST ON ST.id = DS.serviceId\n"
                + "LEFT JOIN Department ON Department.id = ST.departmentId\n"
                + "LEFT JOIN CurriculumVitae AS CV ON CV.id = d.CVId\n"
                + "WHERE\n"
                + "    d.id IS NOT NULL\n"
                + "    AND d.isDelete LIKE ? \n"
                + "	AND d.displayName LIKE ?\n"
                + "	AND d.branchId like ?\n"
                + "	AND d.ARId LIKE ?\n"
                + "GROUP BY\n"
                + "    d.ARId,\n"
                + "    d.branchId,\n"
                + "    d.CVId,\n"
                + "    d.displayName,\n"
                + "    d.email,\n"
                + "    d.id,\n"
                + "    d.password,\n"
                + "    d.phone,\n"
                + "    d.profilePicture,\n"
                + "    d.salary,\n"
                + "    d.status,\n"
                + "    d.workplace,\n"
                + "    d.birthDate,\n"
                + "    d.gender,\n"
                + "    d.isDelete,\n"
                + "    b.name,\n"
                + "    a.name,\n"
                + "    Certificates,\n"
                + "    Department.id,\n"
                + "    Department.name,\n"
                + "    CV.education,\n"
                + "    CV.introduce,\n"
                + "    CV.workHistory,\n"
                + "    CV.startYear;";
        try {

            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + isDelete + "%");
            st.setString(2, "%" + search + "%");
            st.setString(3, "%" + BranchId + "%");
            st.setString(4, "%" + ARId + "%");
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
                d.setSalary(String.valueOf((int) rs.getFloat("salary")));
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

    public ArrayList<CertificateDoctor> getListCertOfDoc(String doctorId) {
        ArrayList<CertificateDoctor> list = new ArrayList<>();
        String sql = "  select c.certId , c.doctorId , cv.name from CertificateDoctor c\n"
                + "  full join Certificate  cv\n"
                + "  on c.certId = cv.id\n"
                + "  where c.DoctorId = ?\n"
                + "  order by c.DoctorId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CertificateDoctor cd = new CertificateDoctor();
                cd.setCertId(rs.getString("certId"));
                cd.setDoctorId(rs.getString("doctorId"));
                cd.setCertName(rs.getString("name"));
                list.add(cd);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("DoctorDAO.getListCertOfDoc: " + e);
        }
        return null;
    }
     

    //display doctor deleted + null + active
//method của Tu Binh
    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "SELECT\n"
                + "    d.*,\n"
                + "    b.[name] AS branchName,\n"
                + "    a.[name] AS ARName,\n"
                + "    DC.Certificates AS Certificates,\n"
                + "    Department.id AS DepartmentId,\n"
                + "    Department.[name] AS departmentName,\n"
                + "    CV.education,\n"
                + "    CV.introduce,\n"
                + "    CV.workHistory,\n"
                + "    CV.startYear\n"
                + "FROM\n"
                + "    Doctor AS d\n"
                + "LEFT JOIN Branch AS b ON b.id = d.branchId\n"
                + "LEFT JOIN AcademicRank AS a ON a.id = d.ARId\n"
                + "LEFT JOIN DoctorCertificates DC ON d.id = DC.DoctorId\n"
                + "LEFT JOIN DoctorService AS DS ON DS.doctorId = d.id\n"
                + "LEFT JOIN ServiceTag AS ST ON ST.id = DS.serviceId\n"
                + "LEFT JOIN Department ON Department.id = ST.departmentId\n"
                + "LEFT JOIN CurriculumVitae AS CV ON CV.id = d.CVId\n"
                + "WHERE\n"
                + "    d.id IS NOT NULL\n"
                + "GROUP BY\n"
                + "    d.ARId,\n"
                + "    d.branchId,\n"
                + "    d.CVId,\n"
                + "    d.displayName,\n"
                + "    d.email,\n"
                + "    d.id,\n"
                + "    d.password,\n"
                + "    d.phone,\n"
                + "    d.profilePicture,\n"
                + "    d.salary,\n"
                + "    d.status,\n"
                + "    d.workplace,\n"
                + "    d.birthDate,\n"
                + "    d.gender,\n"
                + "    d.isDelete,\n"
                + "    b.name,\n"
                + "    a.name,\n"
                + "    Certificates,\n"
                + "    Department.id,\n"
                + "    Department.name,\n"
                + "    CV.education,\n"
                + "    CV.introduce,\n"
                + "    CV.workHistory,\n"
                + "    CV.startYear;";

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
            return list;
        } catch (SQLException e) {
            System.out.println("getAllDoctors: " + e);
        }
        return null;
    }

    public ArrayList<Doctor> getAllDoctorPaginated(int offset, int noOfRecords) {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.*, b.[name] AS branchName, a.[name] AS ARName, \n"
                + "DC.Certificates AS Certificates, Department.id as DepartmentId, Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory, CV.startYear\n"
                + "FROM Doctor AS d\n"
                + "FULL JOIN Branch AS b On b.id = d.branchId\n"
                + "FULL JOIN AcademicRank AS a On a.id = d.ARId\n"
                + "FULL JOIN DoctorCertificates DC ON d.id = DC.DoctorId\n"
                + "FULL JOIN DoctorService AS DS ON DS.doctorId = d.id\n"
                + "FULL JOIN ServiceTag AS ST ON ST.id = DS.serviceId\n"
                + "FULL JOIN Department ON Department.id = ST.departmentId\n"
                + "FULL JOIN CurriculumVitae AS CV On CV.id = d.CVId\n"
                + "GROUP BY d.ARId, d.branchId, d.CVId, d.displayName, d.email, d.id, d.phone, d.profilePicture, d.salary, d.status, d.workplace,\n"
                + "b.[name], a.[name], DC.Certificates,\n"
                + "Department.id, Department.[name], CV.education, CV.introduce, CV.workHistory,\n"
                + "CV.startYear, d.[password], d.isDelete, d.gender, d.birthDate\n"
                + "HAVING d.id IS NOT NULL \n"
                + "ORDER BY d.id \n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, offset);
            st.setInt(2, noOfRecords);
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

            rs.close();
            sql
                    = "SELECT COUNT(*) FROM (\n"
                    + "    SELECT d.*, b.[name] AS branchName, a.[name] AS ARName, \n"
                    + "    DC.Certificates AS Certificates, Department.id as DepartmentId, Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory, CV.startYear\n"
                    + "    FROM Doctor AS d\n"
                    + "    FULL JOIN Branch AS b On b.id = d.branchId\n"
                    + "    FULL JOIN AcademicRank AS a On a.id = d.ARId\n"
                    + "    FULL JOIN DoctorCertificates DC ON d.id = DC.DoctorId\n"
                    + "    FULL JOIN DoctorService AS DS ON DS.doctorId = d.id\n"
                    + "    FULL JOIN ServiceTag AS ST ON ST.id = DS.serviceId\n"
                    + "    FULL JOIN Department ON Department.id = ST.departmentId\n"
                    + "    FULL JOIN CurriculumVitae AS CV On CV.id = d.CVId\n"
                    + "    GROUP BY d.ARId, d.branchId, d.CVId, d.displayName, d.email, d.id, d.phone, d.profilePicture, d.salary, d.status, d.workplace,\n"
                    + "    b.[name], a.[name], DC.Certificates,\n"
                    + "    Department.id, Department.[name], CV.education, CV.introduce, CV.workHistory,\n"
                    + "    CV.startYear, d.[password], d.isDelete, d.gender, d.birthDate\n"
                    + "    HAVING d.id IS NOT NULL) AS subquery;";
            try ( PreparedStatement psm = connection.prepareStatement(sql)) {

                ResultSet rst = psm.executeQuery();

                while (rst.next()) {
                    this.noOfRecords = rst.getInt(1);
                }
            } catch (Exception e) {
                System.out.println("getAllDoctorPaginated: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("getAllDoctorPaginated: " + e);
        }
        return list;
    }

    public Doctor getDoctorByDoctorId(String doctorId) {
        String sql = "SELECT d.*, b.[name] AS branchName, a.[name] AS ARName, \n"
                + "DC.Certificates AS Certificates, Department.id as DepartmentId, Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory, CV.startYear\n"
                + "FROM Doctor AS d\n"
                + "FULL JOIN Branch AS b On b.id = d.branchId\n"
                + "FULL JOIN AcademicRank AS a On a.id = d.ARId\n"
                + "FULL JOIN DoctorCertificates DC ON d.id = DC.DoctorId\n"
                + "FULL JOIN DoctorService AS DS ON DS.doctorId = d.id\n"
                + "FULL JOIN ServiceTag AS ST ON ST.id = DS.serviceId\n"
                + "FULL JOIN Department ON Department.id = ST.departmentId\n"
                + "FULL JOIN CurriculumVitae AS CV On CV.id = d.CVId\n"
                + "WHERE d.id = ?\n"
                + "GROUP BY d.ARId, d.branchId, d.CVId, d.displayName, d.email, d.id, d.phone, d.profilePicture, d.salary, d.status, d.workplace,\n"
                + "b.[name], a.[name], DC.Certificates,\n"
                + "Department.id, Department.[name], CV.education, CV.introduce, CV.workHistory,\n"
                + "CV.startYear, d.[password], d.isDelete, d.gender, d.birthDate;";

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
                        String.valueOf(rs.getDate("birthDate")),
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

    public Doctor login(String email, String password) {
        String SQL = "SELECT email, password FROM [Doctor] WHERE email = ?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, email);
            byte[] salt = PasswordEncryption.generateSalt();
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                //get that doctor
                if (PasswordEncryption.comparePasswords(password, rs.getString("password")));
                return new DoctorDAO().getDoctorByEmail(email);
            }
        } catch (SQLException e) {
            System.out.println("dal.UserDAO.Login(): " + e);
        }
        return null;
    }

    public Doctor login(String email) {
        String SQL = "SELECT email, password FROM [Doctor] WHERE email = ?";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                //get that doctor
                return new DoctorDAO().getDoctorByEmail(email);
            }
        } catch (SQLException e) {
            System.out.println("dal.UserDAO.Login(): " + e);
        }
        return null;
    }


    public Doctor getDoctorByEmail(String email) {
        String SQL = "SELECT id, email, displayName, branchId, phone, ARId, CVId, salary, workplace, profilePicture, status FROM [Doctor] WHERE email = ?";
        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("displayName"),
                        String.valueOf(rs.getInt("branchId")),
                        String.valueOf(rs.getString("phone")),
                        String.valueOf(rs.getInt("ARId")),
                        String.valueOf(rs.getInt("CVId")),
                        String.valueOf(rs.getFloat("salary")),
                        String.valueOf(rs.getString("workplace")),
                        rs.getString("profilePicture"),
                        String.valueOf(rs.getInt("status"))
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

    public ArrayList<Doctor> getTrendingDoctors() {// KHONG PHAI FUNCTION CUA TU BINH
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
                        rs.getString("id"),
                        rs.getString("email"),
                        rs.getString("displayName"),
                        rs.getString("phone"),
                        String.valueOf(rs.getFloat("salary")),
                        rs.getString("workplace"),
                        rs.getString("profilePicture"),
                        String.valueOf(rs.getString("status")),
                        String.valueOf(rs.getString("Certificates")),
                        rs.getString("branchName"),
                        rs.getString("ARName"),
                        String.valueOf(rs.getString("DepartmentId")),
                        rs.getString("departmentName"),
                        String.valueOf(rs.getString("education")),
                        String.valueOf(rs.getString("introduce")),
                        rs.getString("workHistory"),
                        String.valueOf(rs.getInt("startYear")),
                        String.valueOf(rs.getInt("ReviewCount"))
                );

                list.add(d);
            }
        } catch (SQLException e) {
            System.out.println("getTrendingDoctors: " + e);
        }
        return list;
    }

    public ArrayList<Doctor> getDoctorsByPattern(String pattern, int offset, int noOfRecords) {// KHONG PHAI FUNCTION CUA TU BINH
        ArrayList<Doctor> list = new ArrayList<>();
        String searchValue = "%" + pattern + "%";
        String SQL = "SELECT d.id, d.email, d.displayName, d.phone, d.salary, d.workplace, d.profilePicture, d.status, b.[name] AS branchName, a.[name] AS ARName,\n"
                + "DC.Certificates AS Certificates, Department.id as DepartmentId, Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory, CV.startYear\n"
                + "FROM Doctor AS d\n"
                + "FULL JOIN Branch AS b On b.id = d.branchId\n"
                + "FULL JOIN AcademicRank AS a On a.id = d.ARId\n"
                + "FULL JOIN DoctorCertificates DC ON d.id = DC.DoctorId\n"
                + "FULL JOIN DoctorService AS DS ON DS.doctorId = d.id\n"
                + "FULL JOIN ServiceTag AS ST ON ST.id = DS.serviceId\n"
                + "FULL JOIN Department ON Department.id = ST.departmentId\n"
                + "FULL JOIN CurriculumVitae AS CV On CV.id = d.CVId\n"
                + "WHERE d.displayName COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?\n"
                + "GROUP BY d.ARId, d.branchId, d.CVId, d.displayName, d.email, d.id, d.phone, d.profilePicture, d.salary, d.status, d.workplace,\n"
                + "b.[name], a.[name], DC.Certificates,  Department.[name], Department.id, CV.education, CV.introduce, CV.workHistory, CV.startYear,\n"
                + "d.[password], d.isDelete, d.gender, d.birthDate\n"
                + "HAVING d.id IS NOT NULL\n"
                + "ORDER BY d.id\n"
                + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";

        try ( PreparedStatement ps = connection.prepareStatement(SQL)) {
            ps.setString(1, searchValue);
            ps.setInt(2, offset);
            ps.setInt(3, noOfRecords);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Doctor d = new Doctor(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        String.valueOf(rs.getFloat(5)),
                        rs.getString(6),
                        rs.getString(7),
                        String.valueOf(rs.getInt(8)),
                        rs.getString(11),
                        String.valueOf(rs.getString(9)),
                        rs.getString(10),
                        String.valueOf(rs.getInt(12)),
                        rs.getString(13),
                        rs.getString(14),
                        String.valueOf(rs.getString(15)),
                        String.valueOf(rs.getString(16)),
                        String.valueOf(rs.getInt(17))
                );

                list.add(d);
            }
            rs.close();
            SQL = "SELECT COUNT(*) \n"
                    + "FROM (\n"
                    + "    SELECT ROW_NUMBER() OVER (ORDER BY COUNT(r.id) DESC) AS RowNum, d.id, d.email, d.password, d.displayName, b.[name] AS branchName, d.phone, a.[name] AS ARName,\n"
                    + "           COUNT(r.id) AS ReviewCount, DC.Certificates AS Certificates, Department.id as DepartmentId,\n"
                    + "           Department.[name] AS departmentName, CV.education, CV.introduce, CV.workHistory,\n"
                    + "           CV.startYear, d.salary, d.workplace, d.profilePicture, d.status,\n"
                    + "           d.isDelete, d.gender, d.birthDate\n"
                    + "    FROM Doctor AS d\n"
                    + "    FULL JOIN Branch AS b On b.id = d.branchId\n"
                    + "    FULL JOIN AcademicRank AS a On a.id = d.ARId\n"
                    + "    FULL JOIN DoctorCertificates DC ON d.id = DC.DoctorId\n"
                    + "    FULL JOIN DoctorService AS DS ON DS.doctorId = d.id\n"
                    + "    FULL JOIN ServiceTag AS ST ON ST.id = DS.serviceId\n"
                    + "    FULL JOIN Department ON Department.id = ST.departmentId\n"
                    + "    FULL JOIN CurriculumVitae AS CV On CV.id = d.CVId\n"
                    + "    LEFT JOIN Reviews AS r ON r.doctorId = d.id\n"
                    + "    WHERE d.displayName COLLATE SQL_Latin1_General_CP1_CI_AI LIKE ?\n"
                    + "    GROUP BY d.id, d.email, d.password, d.displayName, b.[name], d.phone, a.[name], DC.Certificates,\n"
                    + "             Department.id, Department.[name], CV.education, CV.introduce, CV.workHistory,\n"
                    + "             CV.startYear, d.salary, d.workplace, d.profilePicture, d.status,\n"
                    + "             d.isDelete, d.gender, d.birthDate\n"
                    + ") AS subquery;";
            try ( PreparedStatement psm = connection.prepareStatement(SQL)) {
                psm.setString(1, searchValue);

                ResultSet rst = psm.executeQuery();

                while (rst.next()) {
                    this.noOfRecords = rst.getInt(1);
                }
            } catch (Exception e) {
                System.out.println("getDoctorsByPattern: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("getDoctorsByPattern: " + e.getMessage());
        }
        return list;
    }

    public ArrayList<Doctor> getAllDoctorsByBranchIdAndServiceId(String branchId, String serviceId) {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.id, d.displayName FROM Doctor AS d\n"
                + "JOIN DoctorService AS DS \n"
                + "ON DS.doctorId = d.id\n"
                + "WHERE d.branchId = ? AND DS.serviceId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(branchId));
            st.setInt(2, Integer.parseInt(serviceId));

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(rs.getString("id"),
                        rs.getString("displayName"));
                list.add(d);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("getAllDoctorsByBranchIdAndServiceId: " + e);
        }
        return list;
    }

    public ArrayList<Doctor> getAllDoctorAvailable(String branchId, String serviceId, String appointmentDay, String appointmentTime) {
        ArrayList<Doctor> list = new ArrayList<>();
        String sql = "SELECT d.id, d.displayName\n"
                + "FROM Doctor AS d\n"
                + "JOIN DoctorService AS DS \n"
                + "ON DS.doctorId = d.id\n"
                + "JOIN DoctorSchedule AS DScd\n"
                + "ON d.id = DScd.DoctorID\n"
                + "JOIN ScheduleDetail AS ScdDt\n"
                + "ON DScd.id = ScdDt.ScheduleID\n"
                + "JOIN WorkingSlot AS WS\n"
                + "ON ScdDt.SlotID = WS.id\n"
                + "WHERE branchId = ? AND DS.serviceId = ? AND DScd.WorkDate = ? AND WS.startTime = ? AND d.isDelete = 0 AND ScdDt.slotStatus = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, Integer.parseInt(branchId));
            st.setInt(2, Integer.parseInt(serviceId));
            st.setString(3, appointmentDay);
            st.setString(4, appointmentTime);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor(rs.getString("id"),
                        rs.getString("displayName"));
                list.add(d);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("getAllDoctorAvailable: " + e);
        }
        return list;
    }
    
    public  ArrayList<Doctor> searchDoctorFuzzy(String searchTerm) throws IOException {
        ArrayList<Doctor> doctorList = new ArrayList<Doctor>();
        doctorList = new DoctorDAO().getAllDoctors();
        for (Doctor doctor : doctorList) {
            System.out.println(doctor.getDisplayName());
        }

        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory index = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(index, config);

        // Thêm tất cả tên bác sĩ vào chỉ mục
        for (Doctor doctor : doctorList) {
            Document doc = new Document();
            doc.add(new org.apache.lucene.document.StringField("name", doctor.getDisplayName(), org.apache.lucene.document.Field.Store.YES));
            writer.addDocument(doc);
        }

        writer.close();

        // Tạo danh sách result chứa các tên phù hợp với pattern
//        String searchTerm = "Liêm";
        int maxEdits = 2; // Số thay đổi tối đa cho phép
        FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term("name", searchTerm), maxEdits);
        WildcardQuery wildcardQuery = new WildcardQuery(new Term("name", "*"+searchTerm + "*"));

        BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();
        queryBuilder.add(fuzzyQuery, BooleanClause.Occur.SHOULD);
        queryBuilder.add(wildcardQuery, BooleanClause.Occur.SHOULD);

        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(index));
        TopDocs docs = searcher.search(queryBuilder.build(), 10);

        ArrayList<String> resultList = new ArrayList<String>();
        for (ScoreDoc scoreDoc : docs.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            String name = doc.get("name");
            if (!resultList.contains(name)) {
                resultList.add(name);
            }
        }

        // Lọc ra danh sách bác sĩ có displayName thuộc danh sách resultList
        ArrayList<Doctor> searchResults = new ArrayList<Doctor>();
        for (Doctor doctor : doctorList) {
            if (resultList.contains(doctor.getDisplayName())) {
                searchResults.add(doctor);
            }
        }

        // In kết quả
        System.out.println("Search Results:");
        for (Doctor doctor : searchResults) {
            System.out.println("Doctor ID: " + doctor.getId());
            System.out.println("Doctor Name: " + doctor.getDisplayName());
        }
        return searchResults;
    }

    public static void main(String[] args) {
        DoctorDAO dd = new DoctorDAO();
//        List<Doctor> list = dd.getAllDoctors();
//        for (Doctor doc : list) {
//            System.out.println(doc);
//        }
//        int Count = dd.countAllDoctor();
//        String id = dd.autoGenerateID();
//        System.out.println("ID :" + id);
//        System.out.println("Number Doctor IN THE LIST :" + Count);
//        List<Doctor> paging = dd.pagingDoctor("", "", "", "1", 1);
//        System.out.println("Paging doctor : ");
//        for (Doctor d : paging) {
//            System.out.println(d);
//        }
           String id = dd.autoGenerateID();
           System.out.println("ID : " + id);
           

    }
}
