/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import DAL.EmployeeDAO;
import DAL.UserDAO;
import Models.Branch;
import Models.Employee;
import Models.EmployeeRole;
import Models.Province;
import Models.User;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author DELL
 */
public class EmployeeDAOUnitTest {
    
    public EmployeeDAOUnitTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    //CountAllEmployee
//    @Test
//    public void givenCountAllEmployeeValidValueThenReturnValueGreaterOrEqualZero(){
//        String branchId = "1";
//        String isDelete = "0";
//        int notExptected = -1;
//        EmployeeDAO edao = new EmployeeDAO();
//        int actual = edao.countAllEmployee(branchId, isDelete);
//        Assert.assertNotEquals(notExptected,actual);
//    }
//    @Test
//    public void givenCountAllEmployeeBrandIdEmptyValueThenReturnValueGreaterOrEqualZero(){
//        String branchId = "";
//        String isDelete = "0";
//        int notExptected = -1;
//        EmployeeDAO edao = new EmployeeDAO();
//        int actual = edao.countAllEmployee(branchId, isDelete);
//        Assert.assertNotEquals(notExptected,actual);
//    }
//    @Test
//    public void givenCountAllEmployeeIsDeleteEmptyValueThenReturnValueGreaterOrEqualZero(){
//        String branchId = "1";
//        String isDelete = "";
//        int notExptected = -1;
//        EmployeeDAO edao = new EmployeeDAO();
//        int actual = edao.countAllEmployee(branchId, isDelete);
//        Assert.assertNotEquals(notExptected,actual);
//    }
//    @Test
//    public void givenCountAllEmployeeBranchIdWrongValueThenReturn0(){
//        String branchId = "abc";
//        String isDelete = "0";
//        int exptected = 0;
//        EmployeeDAO edao = new EmployeeDAO();
//        int actual = edao.countAllEmployee(branchId, isDelete);
//        Assert.assertEquals(exptected,actual);
//    }
//    @Test
//    public void givenCountAllEmployeeIsDeleteWrongValueThenReturn0(){
//        String branchId = "1";
//        String isDelete = "def";
//        int exptected = 0;
//        EmployeeDAO edao = new EmployeeDAO();
//        int actual = edao.countAllEmployee(branchId, isDelete);
//        Assert.assertEquals(exptected,actual);
//    }
//    @Test
//    public void givenCountAllEmployeeInvalidLowerBoundaryBranchIdValueThenReturn0(){
//        String branchId = "0";
//        String isDelete = "0";
//        int exptected = 0;
//        EmployeeDAO edao = new EmployeeDAO();
//        int actual = edao.countAllEmployee(branchId, isDelete);
//        Assert.assertEquals(exptected,actual);
//    }
//    @Test
//    public void givenCountAllEmployeeInvalidUpperBoundaryBranchIdValueThenReturn0(){
//        String branchId = "4";
//        String isDelete = "0";
//        int exptected = 0;
//        EmployeeDAO edao = new EmployeeDAO();
//        int actual = edao.countAllEmployee(branchId, isDelete);
//        Assert.assertEquals(exptected,actual);
//    }
//    @Test
//    public void givenCountAllEmployeeInvalidLowerBoudaryIsDeleteValueThenReturn0(){
//        String branchId = "1";
//        String isDelete = "-1";
//        int exptected = 0;
//        EmployeeDAO edao = new EmployeeDAO();
//        int actual = edao.countAllEmployee(branchId, isDelete);
//        Assert.assertEquals(exptected,actual);
//    }
//    @Test
//    public void givenCountAllEmployeeInvalidUpperBoudaryIsDeleteValueThenReturn0(){
//        String branchId = "1";
//        String isDelete = "2";
//        int exptected = 0;
//        EmployeeDAO edao = new EmployeeDAO();
//        int actual = edao.countAllEmployee(branchId, isDelete);
//        Assert.assertEquals(exptected,actual);
//    }
    //searchEmployee
//    @Test
//    public void givenSearchEmployeeValidInputThenReturnListHasSizeGreaterThan0(){
//        String keyId="";
//        String keyName="";
//        String keyRoleId=""; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertNotEquals(0, actual.size());
//                
//    }
//    @Test
//    public void givenSearchEmployeeInvalidIdThenReturnListHasSize0(){
//        String keyId="0";
//        String keyName="";
//        String keyRoleId=""; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeNotExsistIdThenReturnListHasSize0(){
//        String keyId="abc";
//        String keyName="";
//        String keyRoleId=""; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeNotExsistNameThenReturnListHasSize0(){
//        String keyId="";
//        String keyName="xyz";
//        String keyRoleId=""; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeExsistNameThenReturnListHasSizeGreaterThan0(){
//        String keyId="";
//        String keyName="thu";
//        String keyRoleId=""; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertNotEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeExsistRoleThenReturnListHasGreaterThan0(){
//        String keyId="";
//        String keyName="";
//        String keyRoleId="1"; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertNotEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeNotExsistRoleThenReturnListHasSize0(){
//        String keyId="";
//        String keyName="";
//        String keyRoleId="0"; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeNotValidRoleThenReturnListHasSize0(){
//        String keyId="";
//        String keyName="";
//        String keyRoleId="abc"; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeExsistBranchThenReturnListHasGreaterThan0(){
//        String keyId="";
//        String keyName="";
//        String keyRoleId=""; 
//        String keyBranchId="1";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertNotEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeNotExsistBranchThenReturnListHasSize0(){
//        String keyId="";
//        String keyName="";
//        String keyRoleId=""; 
//        String keyBranchId="0";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeNotValidBranchThenReturnListHasSize0(){
//        String keyId="";
//        String keyName="";
//        String keyRoleId=""; 
//        String keyBranchId="abc";
//        int offset=0;
//        int fetch=5;
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeNotValidIsDeleteThenReturnListHasSize0(){
//        String keyId="";
//        String keyName="";
//        String keyRoleId=""; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="-1";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenSearchEmployeeValidIsDeleteThenReturnListHasGreaterThan0(){
//        String keyId="";
//        String keyName="";
//        String keyRoleId=""; 
//        String keyBranchId="";
//        int offset=0;
//        int fetch=5;
//        String isDelete="0";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.searchEmployee(keyId, keyName, keyRoleId, keyBranchId, offset, fetch, isDelete);
//        Assert.assertNotEquals(0, actual.size());    
//    }
    //getEmployeeById
//    @Test
//    public void givenGetEmployeeByIdEmptyInputThenReturnNull(){
//        String id = "";
//        EmployeeDAO edao = new EmployeeDAO();
//        Employee actual = edao.getEmployeeById(id);
//        Assert.assertNull(actual);
//    }
//    @Test
//    public void givenGetEmployeeByIdValidInputThenReturnAObject(){
//        String id = "1";
//        EmployeeDAO edao = new EmployeeDAO();
//        Employee actual = edao.getEmployeeById(id);
//        Assert.assertNotNull(actual);
//    }
//    @Test
//    public void givenGetEmployeeByNotExistIdInputThenReturnNull(){
//        String id = "0";
//        EmployeeDAO edao = new EmployeeDAO();
//        Employee actual = edao.getEmployeeById(id);
//        Assert.assertNull(actual);
//    }
//    @Test
//    public void givenGetEmployeeByFalseTypeInputThenReturnNull(){
//        String id = "abc";
//        EmployeeDAO edao = new EmployeeDAO();
//        Employee actual = edao.getEmployeeById(id);
//        Assert.assertNull(actual);
//    }
    //getEmployeeByEmail
//    public void givenGetEmployeeByEmailEmptyInputThenReturnNull(){
//        String gmail="";
//        EmployeeDAO edao = new EmployeeDAO();
//        Employee actual = edao.getEmployeeByEmail(gmail);
//        Assert.assertNull(actual);
//    }
//    @Test
//    public void givenGetEmployeeByEmailValidInputThenReturnAObject(){
//        String gmail="vietthu002@gmail.com";
//        EmployeeDAO edao = new EmployeeDAO();
//        Employee actual = edao.getEmployeeByEmail(gmail);
//        Assert.assertNotNull(actual);
//    }
//    public void givenGetEmployeeByEmailNotExsistEmailThenReturnNull(){
//        String gmail="vietthu@gmail.com";
//        EmployeeDAO edao = new EmployeeDAO();
//        Employee actual = edao.getEmployeeByEmail(gmail);
//        Assert.assertNull(actual);
//    }
//    public void givenGetEmployeeByEmailWrongPatternEmailThenReturnNull(){
//        String gmail="abc";
//        EmployeeDAO edao = new EmployeeDAO();
//        Employee actual = edao.getEmployeeByEmail(gmail);
//        Assert.assertNull(actual);
//    }
    //checkEmployeeId
//    @Test
//    public void givenCheckEmployeeIdValidIdThenReturnTrue(){
//        String id = "1";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertTrue(edao.checkEmployeeId(id));
//    }
//    @Test
//    public void givenCheckEmployeeIdNotExsistIdThenReturnFalse(){
//        String id = "0";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.checkEmployeeId(id));
//    }
//    @Test
//    public void givenCheckEmployeeIdInvalidIdThenReturnFalse(){
//        String id = "abc";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.checkEmployeeId(id));
//    }
    //getEmployeeByEmail
//    @Test
//    public void givenGetEmployeeByEmailValidEmailThenReturnTrue(){
//        String email = "vietthu002@gmail.com";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertTrue(edao.checkEmployeeEmail(email));
//    }
//    public void givenGetEmployeeByEmailEmptyEmailThenReturnFalse(){
//        String email = "";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.checkEmployeeEmail(email));
//    }
//    public void givenGetEmployeeByEmailInvalidEmailThenReturnFalse(){
//        String email = "abc";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.checkEmployeeEmail(email));
//    }
    //setEmployeeById
//    @Test
//    public void givenSetEmployeeByIdValidInputThenReturnTrue(){
//        Employee emp = new Employee("1","Hoangvu@gmail.com","123456",new Branch("1","","",""),"Hoang Vu","2003-01-01","1","VN","VN",new Province("1",""),"0123456789","Kinh",new EmployeeRole("3",""));
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertTrue(edao.setEmployeeById(emp));
//    }
//    @Test
//    public void givenSetEmployeeByIdInvalidIdInputThenReturnFalse(){
//        Employee emp = new Employee("","Hoangvu@gmail.com","123456",new Branch("1","","",""),"Hoang Vu","2003-01-01","1","VN","VN",new Province("1",""),"0123456789","Kinh",new EmployeeRole("3",""));
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.setEmployeeById(emp));
//    }
//    public void givenSetEmployeeByIdDuplicateInputThenReturnFalse(){
//        Employee emp = new Employee("1","vietthu002@gmail.com","123456",new Branch("1","","",""),"Hoang Vu","2003-01-01","1","VN","VN",new Province("1",""),"0123456789","Kinh",new EmployeeRole("3",""));
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.setEmployeeById(emp));
//    }
    //setEmployeePasswordById
//    @Test
//    public void givenSetEmployeePasswordByIdValidInputThenReturnTrue(){
//        String id = "1";
//        String password="1234";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertTrue(edao.setEmployeePasswordById(id,password));
//    }
//    @Test
//    public void givenSetEmployeeByIdInvalidIdInputThenReturnFalse(){
//        String id = "abc";
//        String password="1234";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.setEmployeePasswordById(id,password));
//    }
    //deleteEmployeeById
//    @Test
//    public void givenDeleteEmployeeByIdExsistIdThenReturnTrue(){
//        String id = "1";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertTrue(edao.deleteEmployeeById(id));
//    }
//    public void givenDeleteEmployeeByIdNotExsistIdThenReturnFalse(){
//        String id = "100";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.deleteEmployeeById(id));
//    }
//    public void givenDeleteEmployeeByIdInvalidIdThenReturnFalse(){
//        String id = "abc";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.deleteEmployeeById(id));
//    }
    //restoreEmployee
//    @Test
//    public void givenRestoreEmployeeByIdExsistIdThenReturnTrue(){
//        String id = "1";
//        String modifyId = "2";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertTrue(edao.restoreEmployee(id,modifyId));
//    }
//    public void givenRestoreEmployeeByIdInvalidIdThenReturnFalse(){
//        String id = "100";
//        String modifyId = "2";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.restoreEmployee(id,modifyId));
//    }
//    public void givenRestoreEmployeeByIdInvalidModfifyIdThenReturnFalse(){
//        String id = "1";
//        String modifyId = "100";
//        EmployeeDAO edao = new EmployeeDAO();
//        Assert.assertFalse(edao.restoreEmployee(id,modifyId));
//    }
    //getListEmployee
//    @Test
//    public void givenGetListEmployeeValidValueThenReturnListHasSizeGreaterthan0(){
//        int offset=0;
//        int fetch=5;
//        String keyBranchId="";
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.getListEmployee(offset, fetch, keyBranchId, isDelete);
//        Assert.assertNotEquals(0, actual.size());    
//    }
//    @Test
//    public void givenGetListEmployeeInvalidFetchThenReturnNullValue(){
//        int offset=0;
//        int fetch=-1;
//        String keyBranchId="0";
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.getListEmployee(offset, fetch, keyBranchId, isDelete);
//        Assert.assertNull(actual);    
//    }
//    @Test
//    public void givenGetListEmployeeInvalidBrandIdThenReturnListHasSize0(){
//        int offset=0;
//        int fetch=5;
//        String keyBranchId="0";
//        String isDelete="";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.getListEmployee(offset, fetch, keyBranchId, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenGetListEmployeeInvalidIdDeleteThenReturnListHasSize0(){
//        int offset=0;
//        int fetch=5;
//        String keyBranchId="";
//        String isDelete="2";
//        EmployeeDAO edao = new EmployeeDAO();
//        List<Employee> actual = edao.getListEmployee(offset, fetch, keyBranchId, isDelete);
//        Assert.assertEquals(0, actual.size());    
//    }
    //getListUser
//    @Test
//    public void givenGetListUserThenReturnListHasSizeGreaterThan0(){
//        int offset=0;
//        int fetch=5;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.getListUser(offset, fetch);
//        Assert.assertNotEquals(0, actual.size());    
//    }
//    @Test
//    public void givenGetListUserInvalidOffsetThenReturnNull(){
//        int offset=-1;
//        int fetch=5;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.getListUser(offset, fetch);
//        Assert.assertNull(actual);    
//    }
//    @Test
//    public void givenGetListUserZeroValueFetchThenReturnListHasSize0(){
//        int offset=0;
//        int fetch=0;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.getListUser(offset, fetch);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenGetListUserInvalidFetchThenReturnNull(){
//        int offset=0;
//        int fetch=-1;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.getListUser(offset, fetch);
//        Assert.assertNull(actual);    
//    }
    //searchListUser
//    @Test
//    public void givenSearchListUserValidInputThenReturnListHasSizeGreaterThan0(){
//        User user = new User("",  "",  "",  "",  "",  "",  "", new Province("",""),  "",  "",  "",  "",  "", "");
//        int offset=0;
//        int fetch=5;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.searchListUser(user,offset, fetch);
//        Assert.assertNotEquals(0, actual.size());
//    }
//    @Test
//    public void givenSearchListUserNotExsistUserThenReturnListHasSizeEqual0(){
//        User user = new User("",  "abcdefghiklm",  "",  "",  "",  "",  "", new Province("",""),  "",  "",  "",  "",  "", "");
//        int offset=0;
//        int fetch=5;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.searchListUser(user,offset, fetch);
//        Assert.assertEquals(0, actual.size());
//    }
//    @Test
//    public void givenSearchListUserInvalidOffsetThenReturnNull(){
//        User user = new User("",  "",  "",  "",  "",  "",  "", new Province("",""),  "",  "",  "",  "",  "", "");
//        int offset=-1;
//        int fetch=5;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.searchListUser(user,offset, fetch);
//        Assert.assertNull(actual);
//    }
//    @Test
//    public void givenSearchListUserInvalidFetchThenReturnNull(){
//        User user = new User("",  "",  "",  "",  "",  "",  "", new Province("",""),  "",  "",  "",  "",  "", "");
//        int offset=5;
//        int fetch=-1;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.searchListUser(user,offset, fetch);
//        Assert.assertNull(actual);
//    }
    //getMoreListUser
//    @Test
//    public void givenGetListUserThenReturnListHasSizeGreaterThan0(){
//        int offset=0;
//        int fetch=5;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.getMoreListUser(offset, fetch);
//        Assert.assertNotEquals(0, actual.size());    
//    }
//    @Test
//    public void givenGetListUserInvalidOffsetThenReturnNull(){
//        int offset=-1;
//        int fetch=5;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.getMoreListUser(offset, fetch);
//        Assert.assertNull(actual);    
//    }
//    @Test
//    public void givenGetListUserZeroValueFetchThenReturnListHasSize0(){
//        int offset=0;
//        int fetch=0;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.getMoreListUser(offset, fetch);
//        Assert.assertEquals(0, actual.size());    
//    }
//    @Test
//    public void givenGetListUserInvalidFetchThenReturnNull(){
//        int offset=0;
//        int fetch=-1;
//        UserDAO udao = new UserDAO();
//        List<User> actual = udao.getMoreListUser(offset, fetch);
//        Assert.assertNull(actual);    
//    }
}
