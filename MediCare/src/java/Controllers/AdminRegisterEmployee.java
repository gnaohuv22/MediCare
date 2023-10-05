/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Controllers.AdminException.EmailValidation;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import Models.GetEmployee;
import Models.RegisterError;
import DAL.EmployeeDAO;
import Models.Employee;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hoang
 */
@WebServlet(name = "RegisterEmployeeControllerController", urlPatterns = {"/admin-register-employee"})
public class AdminRegisterEmployee extends HttpServlet {

    private final String REGISTER_EMPLOYEE = "admin-get-id-name";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Models.GetEmployee checkEmp = (Models.GetEmployee)session.getAttribute("EMPLOYEE");
        if (checkEmp==null){
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
    String id = null;
    String email = null;
    String password = null;
    String branchId;
    String name = null;
    String birthDate = null;
    String gender;
    String address = null;
    String workplace = null;
    String provinceId;
    String phone = null;
    String ethnic = null;
    String roleId;
    String createAt;
                boolean error=false;
                RegisterError msg = new RegisterError();
                try{
                    id = request.getParameter("id");
                    request.setAttribute("id", id);
                    if (id.trim().isEmpty()) throw new AdminException.EmptyStringException();
                    EmployeeDAO edao = new EmployeeDAO();
                    GetEmployee emp = edao.getEmployeeById(id);
                    if (emp != null) throw new AdminException.DuplicateException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setAddressError(e.getMessage());
                }catch(NumberFormatException e){
                    error = true;
//                    msg.setIdError("The ID must be number");
                    msg.setIdError("ID phải là số");
                }catch(AdminException.DuplicateException e){
                    error = true;
                    msg.setIdError(e.getMessage());
                }
                try{
                    email = request.getParameter("email");
                    request.setAttribute("email", email);
                    if (email.trim().isEmpty()) throw new AdminException.EmptyStringException();
                    String regexPattern = "/^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+)*$/";
                    if (EmailValidation.patternMatches(email, regexPattern)){
                        error = true;
//                        msg.setEmailError(EmailValidation.getMessage("Email isn't valiable! ", "email@fpt.edu.vn"));
                        msg.setEmailError(EmailValidation.getMessage("Không hợp lệ! ", "Thử theo mẫu: email@fpt.edu.vn"));
                    }
                    EmployeeDAO edao = new EmployeeDAO();
                    GetEmployee emp = edao.getEmployeeByEmail(email);
                    if (emp != null) throw new AdminException.DuplicateException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setEmailError(e.getMessage());
                }catch(AdminException.DuplicateException e){
                    error = true;
                    msg.setEmailError(e.getMessage());
                }
                try{
                    password = request.getParameter("password");
                    request.setAttribute("password", password);
                    if (password.trim().isEmpty()) throw new AdminException.EmptyStringException();
                    if (password.length()<6||password.length()>20) throw new AdminException.LengthException(6,20);
                    byte[] salt = PasswordEncryption.generateSalt();
                    String encryptedPassword = PasswordEncryption.encryptPassword(password, salt);
                    password = encryptedPassword;
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setPasswordError(e.getMessage());
                } catch (AdminException.LengthException e) {
                    error = true;
                    msg.setPasswordError(e.getMessage());
                }
                branchId = request.getParameter("branchId");
                request.setAttribute("branchId", branchId);
                try{
                    name = request.getParameter("name");
                    request.setAttribute("name", name);
                    if (name.trim().isEmpty()) throw new AdminException.EmptyStringException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setNameError(e.getMessage());
                }
                try{
                    birthDate = request.getParameter("birthDate");
                    request.setAttribute("birthDate", birthDate);
                    if (birthDate.trim().isEmpty()) throw new AdminException.EmptyStringException();
                    long millis=System.currentTimeMillis();  
                    Date currentdate = new Date(millis);      
                    Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
                    if (date.compareTo(currentdate)>0) throw new AdminException.BirthDateException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setBirthDateError(e.getMessage());
                }catch (ParseException e) {    
                    error = true;
                    msg.setBirthDateError(e.getMessage());
                }catch (AdminException.BirthDateException e){
                    error = true;
                    msg.setBirthDateError(e.getMessage());
                }
                gender = request.getParameter("gender");
                request.setAttribute("gender", gender);
                try{
                    address = request.getParameter("address");
                    request.setAttribute("address", address);
                    if (address.trim().isEmpty()) throw new AdminException.EmptyStringException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setAddressError(e.getMessage());
                }
                try{
                    workplace = request.getParameter("workplace");
                    request.setAttribute("workplace", workplace);
                    if (workplace.trim().isEmpty()) throw new AdminException.EmptyStringException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setWorkplaceError(e.getMessage());
                }
                provinceId = request.getParameter("provinceId");
                request.setAttribute("provinceId", provinceId);
                try{
                    phone = request.getParameter("phone");
                    request.setAttribute("phone", phone);
                    Long.parseLong(phone);
                    if (phone.trim().length()!=10) throw new AdminException.LackLengthException(10);
                }catch(NumberFormatException e){
                    error = true;
                    msg.setPhoneError("Số điện thoại phải là số");
//                    msg.setPhoneError("The phone number must be number");
                }catch(AdminException.LackLengthException e){
                    error = true;
                    msg.setPhoneError(e.getMessage());
                }
                try{
                    ethnic = request.getParameter("ethnic");
                    request.setAttribute("ethnic", ethnic);
                    if (ethnic.trim().isEmpty()) throw new AdminException.EmptyStringException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setEthnicError(e.getMessage());
                }
                roleId = request.getParameter("roleId");
                request.setAttribute("roleId", roleId);
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                createAt = date+"";
                if (error){
                    request.setAttribute("MESSAGE", "Không đăng kí được!");
                    request.setAttribute("REGISTER_ERROR", msg);
                    request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
                }else{
                    Employee emp = new Employee(id, email, password, branchId, name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt);
                    EmployeeDAO eDao = new EmployeeDAO();
                    //return massage
                    if (eDao.registerEmployee(emp)) {
                        request.setAttribute("MESSAGE", "Đăng kí thành công!");
                        request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
                    } else {
                        request.setAttribute("MESSAGE", "Đăng kí không thành công!");
                        request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
                    }
                }
            }
//    private static class Exception.EmptyStringException extends Exception {

//        public Exception.EmptyStringException() {
//        }
//        public String getMessage(){
////            return "The input must not be empty";
//            return "Giá trị nhập vào trống";
//        }
//    }
//
//    private static class Exception.LengthException extends Exception {
//        String msg;
//        public Exception.LengthException(int a, int b) {
////            msg = "Input must be " + a + " to " + b +" characters long";
//            msg = "Giá trị phải có độ dài từ " + a + " tới " + b +" kí tự";
//        }
//        public String getMessage(){
//            return msg;
//        }
//    }
//
//    private static class LackException.LengthException extends Exception {
//        String msg;
//        public LackException.LengthException(int i) {
////            msg = "Input must have "+ i +" characters long";
//            msg = "Giá trị phải có độ dài "+ i +" kí tự";
//        }
//        public String getMessage(){
//            return msg;
//        }
//    }
//
//    private static class EmailValidation {
//
//        public EmailValidation() {
//        }
//        public static boolean patternMatches(String emailAddress, String regexPattern) {
//            return Pattern.compile(regexPattern)
//            .matcher(emailAddress)
//            .matches();
//        }
//        public static String getMessage(String msg1, String exPattern){
//            return msg1 + "Example: " + exPattern;
//        }
//    }
//
//    private static class Exception.DuplicateException extends Exception {
//        String msg;
//        public Exception.DuplicateException() {
////            msg = "ID aready exist in Database";
//              msg = "ID đã tồn tại";
//        }
//        public String getMessage(){
//            return msg;
//        }
//    }
}
