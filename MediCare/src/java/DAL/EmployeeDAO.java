/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;
import Controllers.PasswordEncryption;
import Models.Branch;
import Models.Employee;
import Models.EmployeeRole;
import Models.Province;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class EmployeeDAO extends DBContext {
    private int numberRecord;
    public int getNumberRecord(){
        return this.numberRecord;
    }
    public int countAllEmployee(String branchId,String isDelete){
        String SQL = "select COUNT(id) from Employee " +
"      WHERE branchId like ? AND  isDelete like ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, "%"+branchId+"%");
            pstm.setString(2, "%"+isDelete+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        }catch (Exception e) {
            System.out.println("countAllEmployee " + e.getMessage());
        }
        return -1;
    }
    public int countAll(String column,String table){
        String SQL = "select COUNT(?) from ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, column);
            pstm.setString(2, table);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                String number = rs.getString(1);
                return Integer.parseInt(number);
            }
        }catch (Exception e) {
            System.out.println("countAll " + e.getMessage());
        }
        return -1;
    }
    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> list = new ArrayList<>();
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
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                Employee employee = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole);
                list.add(employee);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getAllEmployee " + e.getMessage());
        }
        return null;
    }
    public ArrayList<Employee> getListEmployee(int offset, int fetch, String searchBranch, String isDelete) {
        ArrayList<Employee> list = new ArrayList<>();
        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name[eName], birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, isDelete," +
"       Branch.name[bName], Province.name[pName]" +
"       FROM Employee  " +
"       join Branch on Employee.branchId=Branch.id  " +
"       join Province on Employee.provinceId=Province.id " +
"       join EmployeeRole on Employee.roleId=EmployeeRole.id " +
"       WHERE branchId like ? AND  isDelete like ? " +
"	GROUP BY Employee.id, email, password, branchId, Employee.name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, isDelete, " +
"       Branch.name, Province.name" +
"       ORDER BY COUNT(Employee.id) DESC " +
"       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, "%"+searchBranch+"%");
            pstm.setString(2, "%"+isDelete+"%");
            pstm.setInt(3, offset);
            pstm.setInt(4, fetch);
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
                String branchName = rs.getString("bName");
                String branchDescription = "";
                String branchLocateId = "";
                String provinceName =rs.getString("pName");
                String EmployeeRole = "";
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                Employee employee = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole);
                list.add(employee);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListEmployee " + e.getMessage());
        }
        return null;
    }
    public ArrayList<Employee> getMoreListEmployee(int offset, int fetch, String searchBranch, String isDelete) {
        ArrayList<Employee> list = new ArrayList<>();
        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name[eName], birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, " +
"	createAt, CreateBy, modifyAt, modifyBy, isDelete," +
"       Branch.name[bName], Province.name[pName], EmployeeRole.role" +
"       FROM Employee  " +
"       join Branch on Employee.branchId=Branch.id  " +
"       join Province on Employee.provinceId=Province.id " +
"       join EmployeeRole on Employee.roleId=EmployeeRole.id " +
"       WHERE branchId like ? AND  isDelete like ? " +
"	GROUP BY Employee.id, email, password, branchId, Employee.name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, " +
"	createAt, CreateBy, modifyAt, modifyBy, isDelete," +
"       Branch.name, Province.name, EmployeeRole.role" +
"       ORDER BY COUNT(Employee.id) DESC " +
"       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, "%"+searchBranch+"%");
            pstm.setString(2, "%"+isDelete+"%");
            pstm.setInt(3, offset);
            pstm.setInt(4, fetch);
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
                String createBy = rs.getString("createBy");
                String modifyAt = rs.getString("modifyAt");
                String modifyBy = rs.getString("modifyBy");
                String branchName = rs.getString("bName");
                String branchDescription = "";
                String branchLocateId = "";
                String provinceName =rs.getString("pName");
                String EmployeeRole = "";
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                Employee employee = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole, createAt, createBy, modifyAt, modifyBy);
                list.add(employee);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getMoreListEmployee " + e.getMessage());
        }
        return null;
    }
    
    public Employee getEmployeeByEmail(String email) {
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
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                Employee employee = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole);
                return employee;
            }
        }catch (Exception e) {
            System.out.println("getEmployeeByEmail " + e.getMessage());
        }
        return null;
    }
    public Employee getEmployeeById(String id) {
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
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                Employee employee = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole);
                return employee;
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
            pstm.setString(3,emp.getBranch().getId());
            pstm.setString(4,emp.getName());
            pstm.setString(5,emp.getBirthDate());
            pstm.setString(6,emp.getGender());
            pstm.setString(7,emp.getAddress());
            pstm.setString(8,emp.getWorkplace());
            pstm.setString(9,emp.getProvince().getId());
            pstm.setString(10,emp.getPhone());
            pstm.setString(11,emp.getEthnic());
            pstm.setString(12,emp.getEmployeeRole().getId());
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
    public boolean addEmployee(Employee emp) {
        String SQL = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setInt(1, Integer.parseInt(emp.getId()));
            pstm.setString(2, emp.getEmail());
            pstm.setString(3, emp.getPassword());
            pstm.setInt(4, Integer.parseInt(emp.getBranch().getId()));
            pstm.setString(5, emp.getName());
            pstm.setString(6, emp.getBirthDate());
            pstm.setString(7, emp.getGender());
            pstm.setString(8, emp.getAddress());
            pstm.setString(9, emp.getWorkplace());
            pstm.setInt(10, Integer.parseInt(emp.getProvince().getId()));
            pstm.setString(11, emp.getPhone());
            pstm.setString(12, emp.getEthnic());
            pstm.setInt(13, Integer.parseInt(emp.getEmployeeRole().getId()));
            pstm.execute();
            return true;
        } catch (Exception e) {
            System.out.println("addEmployee " + e.getMessage());
        }
        return false;
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
    
    //thu
    public ArrayList<Employee> searchEmployee(String keyId, String keyName, String keyRoleId, String keyBranchId, int offset, int fetch, String isDelete) {
        ArrayList<Employee> list = new ArrayList<>();
        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name[eName], birthDate, gender, address, workplace, provinceId, phone, ethnic, " +
        "Branch.name[bName], Province.name[pName], EmployeeRole.role[erRole] " +
        "FROM Employee " +
        "join Branch on Employee.branchId=Branch.id " +
        "join Province on Employee.provinceId=Province.id "+
        "join EmployeeRole on Employee.roleId=EmployeeRole.id "+
        "WHERE Employee.id like ? AND Employee.name like ? AND EmployeeRole.id like ? AND branchId like ? AND isDelete like ? " +
        "GROUP BY Employee.id, email, password, branchId, Employee.name, birthDate, gender, address, workplace, provinceId, phone, ethnic, " +
"       Branch.name, Province.name, EmployeeRole.role " +
"	HAVING Employee.id IS NOT NULL" +
"       ORDER BY COUNT(Employee.id) DESC" +
"       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String SQL2 = "SELECT count(*) " +
        "FROM Employee " +
        "join Branch on Employee.branchId=Branch.id " +
        "join Province on Employee.provinceId=Province.id "+
        "join EmployeeRole on Employee.roleId=EmployeeRole.id "+
        "WHERE Employee.id like ? AND Employee.name like ? AND EmployeeRole.id like ? AND branchId like ?  AND isDelete like ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL2)){
            pstm.setString(1, "%"+keyId+"%");
            pstm.setString(2, "%"+keyName+"%");
            pstm.setString(3, "%"+keyRoleId+"%");
            pstm.setString(4, "%"+keyBranchId+"%");
            pstm.setString(5,"%"+isDelete+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                numberRecord = rs.getInt(1);
            }
        }catch(Exception e) {
            System.out.println("searchEmployee " + e.getMessage());
        }
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, "%"+keyId+"%");
            pstm.setString(2, "%"+keyName+"%");
            pstm.setString(3, "%"+keyRoleId+"%");
            pstm.setString(4, "%"+keyBranchId+"%");
            pstm.setString(5,"%"+isDelete+"%");
            pstm.setInt(6, offset);
            pstm.setInt(7, fetch);
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
                String roleId = "";
                String branchName = rs.getString("bName");
                String branchDescription = "";
                String branchLocateId = "";
                String provinceName =rs.getString("pName");
                String EmployeeRole = rs.getString("erRole");
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                Employee employee = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole);
                list.add(employee);
            }
            return list;
        } catch (Exception e) {
            System.out.println("searchEmployee " + e.getMessage());
        }
        return null;
    }
    //thu
    public ArrayList<Employee> searchMoreEmployee(String keyId, String keyName, String keyRoleId, String keyBranchId, int offset, int fetch, String isDelete) {
        ArrayList<Employee> list = new ArrayList<>();
        String SQL = "SELECT Employee.id[eId], email, password, branchId, Employee.name[eName], birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt, CreateBy, modifyAt, modifyBy," +
"        Branch.name[bName], Province.name[pName], EmployeeRole.role[erRole]  " +
"        FROM Employee  " +
"        join Branch on Employee.branchId=Branch.id  " +
"        join Province on Employee.provinceId=Province.id " +
"        join EmployeeRole on Employee.roleId=EmployeeRole.id " +
"        WHERE Employee.id like ? AND Employee.name like ? AND EmployeeRole.id like ? AND branchId like ? AND isDelete like ?" +
"        GROUP BY Employee.id, email, password, branchId, Employee.name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt, CreateBy, modifyAt, modifyBy," +
"       Branch.name, Province.name, EmployeeRole.role  " +
"	HAVING Employee.id IS NOT NULL " +
"       ORDER BY COUNT(Employee.id) DESC" +
"       OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        String SQL2 = "SELECT count(*) " +
        "FROM Employee " +
        "join Branch on Employee.branchId=Branch.id " +
        "join Province on Employee.provinceId=Province.id "+
        "join EmployeeRole on Employee.roleId=EmployeeRole.id "+
        "WHERE Employee.id like ? AND Employee.name like ? AND EmployeeRole.id like ? AND branchId like ? AND isDelete like ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL2)){
            pstm.setString(1, "%"+keyId+"%");
            pstm.setString(2, "%"+keyName+"%");
            pstm.setString(3, "%"+keyRoleId+"%");
            pstm.setString(4, "%"+keyBranchId+"%");
            pstm.setString(5,"%"+isDelete+"%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                numberRecord = rs.getInt(1);
            }
        }catch(Exception e) {
            System.out.println("searchMoreEmployee " + e.getMessage());
        }
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, "%"+keyId+"%");
            pstm.setString(2, "%"+keyName+"%");
            pstm.setString(3, "%"+keyRoleId+"%");
            pstm.setString(4, "%"+keyBranchId+"%");
            pstm.setString(5,"%"+isDelete+"%");
            pstm.setInt(6, offset);
            pstm.setInt(7, fetch);
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
                Branch branch = new Branch(branchId,branchName,branchDescription,branchLocateId);
                Province province = new Province(provinceId,provinceName);
                EmployeeRole employeeRole = new EmployeeRole(roleId,EmployeeRole);
                Employee employee = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole);
                list.add(employee);
            }
            return list;
        } catch (Exception e) {
            System.out.println("searchMoreEmployee " + e.getMessage());
        }
        return null;
    }
    //thu
    public String generateId(){
        String SQL = "select top(1) id from Employee order by id DESC";
        String number = null;
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                number = rs.getString(1);
            }
            number = Integer.parseInt(number)+1+"";
            return number;
        }catch (Exception e) {
            System.out.println("generateId employee " + e.getMessage());
        }
        return null;
    }
    //thu
    public ArrayList<String> getTitleTableEmployee() {
        ArrayList<String> list = new ArrayList<>();
        String SQL = "SELECT name FROM NewsCategory WHERE parentId = (select id from NewsCategory Where name like 'titleTableEmployee')";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("name");
                list.add(title);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getTitleTableEmployee " + e.getMessage());
        }
        return null;
    }
    //thu
    public ArrayList<String> getMoreTitleTableEmployee() {
        ArrayList<String> list = new ArrayList<>();
        String SQL = "SELECT name FROM NewsCategory WHERE parentId = (select id from NewsCategory WHERE name like 'titleTableEmployee') " +
" OR parentId = (select id from NewsCategory WHERE name like 'MoreTitleTableEmployee')" +
" OR parentId = (select id from NewsCategory WHERE name like 'LoadMoreTitle')";
        try ( PreparedStatement pstm = connection.prepareStatement(SQL)) {
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String title = rs.getString("name");
                list.add(title);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getMoreTitleTableEmployee " + e.getMessage());
        }
        return null;
    }
    //thu
    public boolean deleteEmployee(String id){
        String SQL = "UPDATE employee SET isDelete = '1' WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("deleteEmployee " + e.getMessage());
        }
        return false;
    }
    //thu
    public boolean restoreEmployee(String id){
        String SQL = "UPDATE employee SET isDelete = '0' WHERE id = ?";
        try (PreparedStatement pstm = connection.prepareStatement(SQL)) {
            pstm.setString(1, id);
            pstm.execute();
            return true;
        }catch (Exception e) {
            System.out.println("deleteEmployee " + e.getMessage());
        }
        return false;
    }
}
