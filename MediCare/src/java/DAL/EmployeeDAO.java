/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import java.util.ArrayList;
import Models.GetEmployee;
import Controllers.PasswordEncryption;
import Models.AcademicRank;
import Models.Appointments;
import Models.Branch;
import Models.Certificate;
import Models.Doctor;
import Models.Employee;
import Models.EmployeeRole;
import Models.GetAppointments;
import Models.GetNews;
import Models.GetReviews;
import Models.GetUser;
import Models.News;
import Models.NewsCategory;
import Models.Province;
import Models.Reviews;
import Models.ServiceTag;
import Models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author hoang
 */
public class EmployeeDAO extends DBContext {
    
    public ArrayList<GetEmployee> getAllEmployee() {
        ArrayList<GetEmployee> list = new ArrayList<>();
        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name[eName], birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt," +
        "Branch.name[bName], Province.name[pName], EmployeeRole.role[erRole] " +
        "FROM Employee " +
        "join Branch on Employee.branchId=Branch.id " +
        "join Province on Employee.provinceId=Province.id "+
        "join EmployeeRole on Employee.roleId=EmployeeRole.id ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("eId");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String branchId = rs.getString("branchId");
                String name = rs.getString("eName");
                String birthDate = rs.getString("birthDate");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String workplace = rs.getString("workplace");
                String provinceId = rs.getString("provinceId");
                String phone = rs.getString("phone");
                String ethnic = rs.getString("ethnic");
                String roleId = rs.getString("roleId");
                String createAt = rs.getString("createAt");
                String branchName = rs.getString("bName");
                String branchDescription = "";
                String branchLocateId = "";
                String provinceName =rs.getString("pName");
                String EmployeeRole = rs.getString("erRole");
                Employee employee = new Employee(id, email, password, branchId, name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt);
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                GetEmployee emp= new GetEmployee(employee,branch,province,employeeRole);
                list.add(emp);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllEmployee " + e.getMessage());
        }
        return null;
    }
    
    public ArrayList<GetReviews> getAllReview() {
        ArrayList<GetReviews> list = new ArrayList<>();
        String SQL = "SELECT Reviews.id[rId], Reviews.userId[rUserId], doctorId, appointmentId, rating, reviewContent, Reviews.createdAt,"
+" [User].name[uName], Doctor.displayName[dName]"
+" FROM Reviews"
+" JOIN [User] on userId = [User].id"
+" JOIN Doctor on doctorId = Doctor.id";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("rId");
                String userId = rs.getString("rUserId");
                String doctorId = rs.getString("doctorId");
                String appointmentId = rs.getString("appointmentId");
                String rating = rs.getString("rating");
                String reviewContent = rs.getString("reviewContent");
                String createdAt = rs.getString("createdAt");
                String userName = rs.getString("uName");
                String doctorName = rs.getString("dName");
                Reviews review= new Reviews(id, userId, doctorId, appointmentId, rating, reviewContent, createdAt);
                User user = new User(userId,"","",userName,"","","","","","","","","","");
                Doctor doctor = new Doctor(doctorId,"","",doctorName,"","","","","","","");
                GetReviews getReviews = new GetReviews(review,user,doctor);
                list.add(getReviews);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllReview " + e.getMessage());
        }
        return null;
    }
    public ArrayList<GetAppointments> getAllAppointment() {
        ArrayList<GetAppointments> list = new ArrayList<>();
        String SQL = "SELECT Appointments.id, userId, doctorId, serviceId, plannedAt, Appointments.status,"
+" [User].name[uName], Doctor.displayName[dName], ServiceTag.nametag[nameTag]"
+" FROM Appointments"
+" JOIN [User] on userId = [User].id"
+" JOIN Doctor on doctorId = Doctor.id"
+" JOIN ServiceTag on serviceId = ServiceTag.id";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String userId = rs.getString("userId");
                String doctorId = rs.getString("doctorId");
                String serviceId = rs.getString("serviceId");
                String plannedAt = rs.getString("plannedAt");
                String status = rs.getString("status");
                String userEmail = "";
                String userName = rs.getString("uName");
                String doctorEmail = "";
                String doctorName = rs.getString("dName");
                String serviceName = rs.getString("nameTag");
                Appointments appointments= new Appointments(id, userId, doctorId, serviceId, plannedAt, status);
                User user = new User(userId,userEmail,"",userName,"","","","","","","","","","");
                Doctor doctor = new Doctor(doctorId,doctorEmail,doctorName,"","","","","","","","");
                ServiceTag serviceTag = new ServiceTag(serviceId,serviceName,"","");
                GetAppointments getAppointments = new GetAppointments(appointments,user,doctor,serviceTag);
                list.add(getAppointments);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllAppointment " + e.getMessage());
        }
        return null;
    }
    public ArrayList<GetNews> getAllNews() {
        ArrayList<GetNews> list = new ArrayList<>();
        String SQL = "SELECT [News].[id][newsId],[title],[content],[author],[categoryId],[createdAt],[lastModified],[viewCount],[coverImage],\n" +
        "[NewsCategory].name[cName]\n" +
        "FROM [News]\n" +
        "JOIN [NewsCategory] on [News].categoryId = [NewsCategory].id";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("newsId");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String author = rs.getString("author");
                String categoryId = rs.getString("categoryId");
                String createdAt = rs.getString("createdAt");
                String lastModified = rs.getString("lastModified");
                String viewCount = rs.getString("viewCount");
                String coverImage = rs.getString("coverImage");
                String categoryName = rs.getString("cName");
                News news= new News(id, title, content, author, categoryId, createdAt, lastModified, viewCount,coverImage);
                NewsCategory newsCategory = new NewsCategory(categoryId, categoryName);
                GetNews getNews = new GetNews(news,newsCategory);
                list.add(getNews);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllNews " + e.getMessage());
        }
        return null;
    }
    public GetEmployee getEmployeeByEmail(String email) {
//        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt," +
//        " Branch.name[bName], Branch.description[bDescrip], Branch.locateId[bLocateId], Province.name[pName], EmployeeRole.role[erRole] " +
//        " FROM Employee" +
//        " join Branch on Employee.branchId=Branch.id" +
//        " join Province on Employee.provinceId=Province.id"+
//        " join EmployeeRole on Employee.roleId=EmployeeRole.id"+
//        " WHERE email = ?";
        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name[eName], birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt," +
        "Branch.name[bName], Province.name[pName], EmployeeRole.role[erRole] " +
        "FROM Employee " +
        "join Branch on Employee.branchId=Branch.id " +
        "join Province on Employee.provinceId=Province.id "+
        "join EmployeeRole on Employee.roleId=EmployeeRole.id "+
        " WHERE email = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("eId");
                String password = rs.getString("password");
                String branchId = rs.getString("branchId");
                String name = rs.getString(5);
                String birthDate = rs.getString("birthDate");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String workplace = rs.getString("workplace");
                String provinceId = rs.getString("provinceId");
                String phone = rs.getString("phone");
                String ethnic = rs.getString("ethnic");
                String roleId = rs.getString("roleId");
                String createAt = rs.getString("createAt");
                String branchName = rs.getString("bName");
                String branchDescription = "";
                String branchLocateId = "";
                String provinceName =rs.getString("pName");
                String EmployeeRole = rs.getString("erRole");
                Employee employee = new Employee(id, email, password, branchId, name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt);
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                GetEmployee emp= new GetEmployee(employee,branch,province,employeeRole);
                return emp;
            }
        }catch (Exception e) {
            System.out.println("getEmployeeByEmail " + e.getMessage());
        }
        return null;
    }
    public GetEmployee getEmployeeById(String id) {
        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt," +
        " Branch.name[bName], Branch.description[bDescrip], Branch.locateId[bLocateId], Province.name[pName], EmployeeRole.role[erRole] " +
        " FROM Employee" +
        " join Branch on Employee.branchId=Branch.id" +
        " join Province on Employee.provinceId=Province.id"+
        " join EmployeeRole on Employee.roleId=EmployeeRole.id"+
        " Where Employee.id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String email = rs.getString("email");
                String password = rs.getString("password");
                String branchId = rs.getString("branchId");
                String name = rs.getString(5);
                String birthDate = rs.getString("birthDate");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String workplace = rs.getString("workplace");
                String provinceId = rs.getString("provinceId");
                String phone = rs.getString("phone");
                String ethnic = rs.getString("ethnic");
                String roleId = rs.getString("roleId");
                String createAt = rs.getString("createAt");
                String branchName = rs.getString("bName");
                String branchDescription = "";
                String branchLocateId = "";
                String provinceName =rs.getString("pName");
                String EmployeeRole = rs.getString("erRole");
                Employee employee = new Employee(id, email, password, branchId, name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt);
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                GetEmployee emp= new GetEmployee(employee,branch,province,employeeRole);
                return emp;
            }
        }catch (Exception e) {
            System.out.println("getEmployeeById " + e.getMessage());
        }
        return null;
    }
    public boolean checkEmployeeId(String id) {
        String SQL = "SELECT id "
        +" FROM Employee"
        +" Where id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        }catch (Exception e) {
            System.out.println("checkEmployeeId " + e.getMessage());
        }
        return false;
    }
    public boolean checkEmployeeEmail(String email) {
        String SQL = "SELECT id "
        +" FROM Employee"
        +" Where email = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        }catch (Exception e) {
            System.out.println("checkEmployeeEmail " + e.getMessage());
        }
        return false;
    }
    public String getEmployeeIdByEmail(String email) {
        String SQL = "SELECT id "
        +" FROM Employee"
        +" Where email = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                return id;
            }
        }catch (Exception e) {
            System.out.println("getEmployeeIdByEmail " + e.getMessage());
        }
        return null;
    }
    
    public Boolean setEmployeeById(Employee emp) {
        String SQL = "UPDATE Employee SET email = ?, password = ?, branchId = ?, name = ?, birthDate = ?, gender = ?, address = ?, workplace = ?, provinceId = ?, phone = ?, ethnic = ?, roleId = ? WHERE id = ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,emp.getEmail());
            pstm.setString(2,emp.getPassword());
            pstm.setString(3,emp.getBranchId());
            pstm.setString(4,emp.getName());
            pstm.setString(5,emp.getBirthDate());
            pstm.setString(6,emp.getGender());
            pstm.setString(7,emp.getAddress());
            pstm.setString(8,emp.getWorkplace());
            pstm.setString(9,emp.getProvinceId());
            pstm.setString(10,emp.getPhone());
            pstm.setString(11,emp.getEthnic());
            pstm.setString(12,emp.getRoleId());
            pstm.setString(13,emp.getId());
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("setEmployeeById " + e.getMessage());
        }
        return false;
    }
    public Boolean setEmployeePasswordById(String id, String password) {
        String SQL = "UPDATE Employee SET password = ? WHERE id = ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,password);
            pstm.setString(2,id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("setEmployeePasswordById " + e.getMessage());
        }
        return false;
    }
    public Boolean deleteEmployeeById(String id) {
        String SQL = "DELETE Employee WHERE id = ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("deleteEmployeeById " + e.getMessage());
        }
        return false;
    }
    public boolean checkLogin(String email, String password) {
        String SQL = "SELECT password FROM Employee WHERE email = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, email);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String storedPassword = rs.getString("password");
                return PasswordEncryption.comparePasswords(password, storedPassword);
            }
        } catch (Exception e) {
            System.out.println("login " + e.getMessage());
        }
        return false;
    }
    public boolean registerEmployee(Employee emp) {
        String SQL = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, Integer.parseInt(emp.getId()));
            pstm.setString(2, emp.getEmail());
            pstm.setString(3, emp.getPassword());
            pstm.setInt(4, Integer.parseInt(emp.getBranchId()));
            pstm.setString(5, emp.getName());
            pstm.setString(6, emp.getBirthDate());
            pstm.setString(7, emp.getGender());
            pstm.setString(8, emp.getAddress());
            pstm.setString(9, emp.getWorkplace());
            pstm.setInt(10, Integer.parseInt(emp.getProvinceId()));
            pstm.setString(11, emp.getPhone());
            pstm.setString(12, emp.getEthnic());
            pstm.setInt(13, Integer.parseInt(emp.getRoleId()));
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("registerEmployee " + e.getMessage());
        }
        return false;
    }
    
    public Doctor getDoctorByDoctorId(String doctorId) {
        String sql = "SELECT id, email, displayName, branchId, phone, ARId, CVId, salary, workplace, profilePicture, status"
                + "WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, doctorId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String email = rs.getString(2);
                String displayName = rs.getString(3);
                String branchId = rs.getString(4);
                String phone = rs.getString(5);
                String ARId = rs.getString(6);
                String CVId = rs.getString(7);
                String salary = rs.getString(8);
                String workplace = rs.getString(9);
                String profilePicture = rs.getString(10);
                String status = rs.getString(11);
                Doctor dr = new Doctor(doctorId, email, displayName, branchId, phone, ARId, CVId, salary, workplace, profilePicture, status);
                return dr;
            }
        } catch (SQLException e) {
            System.out.println("getDoctorByDoctorId: " + e);
        }
        return null;
    }
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
    
    public ArrayList<String> getAllEmployeeId() {
        ArrayList<String> list = new ArrayList<>();
        String SQL = "SELECT id FROM Employee";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                list.add(id);
            }
        } catch (Exception e) {
            System.out.println("getAllEmployeeId " + e.getMessage());
        }
        return list;
    }
    public ArrayList<AcademicRank> getAllAcademicRank() {
        ArrayList<AcademicRank> list = new ArrayList<>();
        String SQL = "SELECT id,name,wage FROM AcademicRank";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String wage = rs.getString(3);
                AcademicRank obj = new AcademicRank(id,name,wage);
                list.add(obj);
            }
        } catch (Exception e) {
            System.out.println("getAllAcademicRank " + e.getMessage());
        }
        return list;
    }
    public ArrayList<EmployeeRole> getAllEmployeeRole() {
        ArrayList<EmployeeRole> list = new ArrayList<>();
        String SQL = "SELECT id,role FROM EmployeeRole";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String role = rs.getString(2);
                EmployeeRole obj = new EmployeeRole(id,role);
                list.add(obj);
            }
        } catch (Exception e) {
            System.out.println("getAllEmployeeRole " + e.getMessage());
        }
        return list;
    }
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
    public ArrayList<Province> getAllProvinceId() {
        ArrayList<Province> list = new ArrayList<>();
        String SQL = "SELECT id,name FROM Province";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                Province obj = new Province(id,name);
                list.add(obj);
            }
        } catch (Exception e) {
            System.out.println("getAllProvinceId" + e.getMessage());
        }
        return list;
    }
    public ArrayList<GetUser> getAllUser() {
        ArrayList<GetUser> list = new ArrayList<>();
        String SQL = "SELECT [User].id[uId],email,password, [User].name[uName],birthDate,gender,address,provinceId,[identity],medicalId,ethnic,phone,profilePicture,createdAt "
                + " , Province.name[pName]"
                + " FROM [User]"
                + " JOIN Province on [User].provinceId = Province.id";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString("uId");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String uName = rs.getString("uName");
                String birthDate = rs.getString("birthDate");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String provinceId = rs.getString("provinceId");
                String identity = rs.getString("identity");
                String medicalId = rs.getString("medicalId");
                String ethnic = rs.getString("ethnic");
                String phone = rs.getString("phone");
                String profilePicture = rs.getString("profilePicture");
                String createdAt = rs.getString("createdAt");
                String pName = rs.getString("pName");
                User user = new User(id,email,password, uName,birthDate,gender,address,provinceId,identity,medicalId,ethnic,phone,profilePicture,createdAt);
                Province province = new Province(provinceId,pName);
                GetUser getUser = new GetUser(user,province);
                list.add(getUser);
            }
        } catch (Exception e) {
            System.out.println("getAllUser" + e.getMessage());
        }
        return list;
    }
    public boolean createUser(User obj) {
        String SQL = "INSERT INTO User VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, Integer.parseInt(obj.getId()));
            pstm.setString(2, obj.getEmail());
            pstm.setString(3, obj.getPassword());
            pstm.setString(4, obj.getName());
            pstm.setString(5, obj.getBirthDate());
            pstm.setString(6, obj.getGender());
            pstm.setString(7, obj.getAddress());
            pstm.setInt(8, Integer.parseInt(obj.getProvinceId()));
            pstm.setString(9, obj.getIdentity());
            pstm.setInt(10, Integer.parseInt(obj.getMedicalId()));
            pstm.setString(11, obj.getEthnic());
            pstm.setString(12, obj.getPhone());
            pstm.setString(13, obj.getProfilePicture());
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("createUser " + e.getMessage());
        }
        return false;
    }
    public boolean editUserById(User obj) {
        String SQL = "UPDATE User SET email = ?, password = ?, name = ?, birthDate = ?, gender = ?, address = ?, provinceId = ?, [identity] = ?, medicalId = ?, ethnic = ?, phone = ?, profilePicture = ? WHERE id = ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, obj.getEmail());
            pstm.setString(2, obj.getPassword());
            pstm.setString(3, obj.getName());
            pstm.setString(4, obj.getBirthDate());
            pstm.setString(5, obj.getGender());
            pstm.setString(6, obj.getAddress());
            pstm.setInt(7, Integer.parseInt(obj.getProvinceId()));
            pstm.setString(8, obj.getIdentity());
            pstm.setInt(9, Integer.parseInt(obj.getMedicalId()));
            pstm.setString(10, obj.getEthnic());
            pstm.setString(11, obj.getPhone());
            pstm.setString(12, obj.getProfilePicture());
            pstm.setInt(13, Integer.parseInt(obj.getId()));
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("editUser " + e.getMessage());
        }
        return false;
    }
    public Boolean deleteReview(String id) {
        String SQL = "DELETE Reviews WHERE id = ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("deleteReview " + e.getMessage());
        }
        return false;
    }
    public Boolean deleteAppointmentById(String id) {
        String getReviewSQL = "SELECT id FROM Reviews WHERE appointmentId=?";
        String delAppointmentSQL = "DELETE Appointments WHERE id = ? ";
        try{
            PreparedStatement pstm = connection.prepareStatement(getReviewSQL);
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                deleteReview(rs.getString("id"));
            }
            pstm = connection.prepareStatement(delAppointmentSQL);
            pstm.setString(1, id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("deleteAppointmentById " + e.getMessage());
        }
        return false;
    }
    public Boolean deleteNewsById(String id) {
        String SQL = "DELETE News WHERE id = ? ";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("deleteNewsById " + e.getMessage());
        }
        return false;
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
    public String getRoleIdByName(String role) {
        String SQL = "SELECT id FROM EmployeeRole WHERE role = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,role);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String id = rs.getString("id");
                return id;
            }
        }catch (Exception e) {
            System.out.println("getRoleIdByName " + e.getMessage());
        }
        return "";
    }
    public Province getProvinceById(String id) {
        String SQL = "SELECT name FROM Province WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String name = rs.getString(2);
                Province obj = new Province(id,name);
                return obj;
            }
        }catch (Exception e) {
            System.out.println("getProvinceById " + e.getMessage());
        }
        return null;
    }
    public EmployeeRole getEmployeeRoleById(String id) {
        String SQL = "SELECT role FROM EmployeeRole WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1,id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String name = rs.getString(2);
                EmployeeRole obj = new EmployeeRole(id,name);
                return obj;
            }
        }catch (Exception e) {
            System.out.println("getEmployeeRoleById " + e.getMessage());
        }
        return null;
    }
    public ArrayList<GetEmployee> searchEmployee(String keyId, String keyName, String keyRoleId) {
        ArrayList<GetEmployee> list = new ArrayList<>();
//        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt," +
//        "Branch.name[bName], Branch.description[bDescrip], Branch.locateId[bLocateId], Province.name[pName], EmployeeRole.role[erRole] " +
//        "FROM Employee " +
//        "join Branch on Employee.branchId=Branch.id " +
//        "join Province on Employee.provinceId=Province.id "+
//        "join EmployeeRole on Employee.roleId=EmployeeRole.id "+
//        "WHERE Employee.id like ? AND Employee.name like ? AND EmployeeRole.role like ?";
        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name[eName], birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt," +
        "Branch.name[bName], Province.name[pName], EmployeeRole.role[erRole] " +
        "FROM Employee " +
        "join Branch on Employee.branchId=Branch.id " +
        "join Province on Employee.provinceId=Province.id "+
        "join EmployeeRole on Employee.roleId=EmployeeRole.id "+
        "WHERE Employee.id like ? AND Employee.name like ? AND EmployeeRole.role like ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, "%"+keyId+"%");
            pstm.setString(2, "%"+keyName+"%");
            pstm.setString(3, "%"+keyRoleId+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString("eId");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String branchId = rs.getString("branchId");
                String name = rs.getString(5);
                String birthDate = rs.getString("birthDate");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String workplace = rs.getString("workplace");
                String provinceId = rs.getString("provinceId");
                String phone = rs.getString("phone");
                String ethnic = rs.getString("ethnic");
                String roleId = rs.getString("roleId");
                String createAt = rs.getString("createAt");
                String branchName = rs.getString("bName");
                String branchDescription = "";
                String branchLocateId = "";
                String provinceName =rs.getString("pName");
                String EmployeeRole = rs.getString("erRole");
                Employee employee = new Employee(id, email, password, branchId, name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt);
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                GetEmployee emp= new GetEmployee(employee,branch,province,employeeRole);
                list.add(emp);
            }
            return list;
        } catch (Exception e) {
            System.out.println("searchEmployee " + e.getMessage());
        }
        return null;
    }
}
