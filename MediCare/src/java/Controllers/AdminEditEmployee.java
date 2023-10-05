/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.EmployeeDAO;
import Models.Employee;
import Models.RegisterError;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

/**
 *
 * @author DELL
 */
@WebServlet(name="EditEmployee", urlPatterns={"/admin-edit-employee"})
public class AdminEditEmployee extends HttpServlet {
    private final String EDIT_EMPLOYEE_PAGE = "admin-get-employee-to-edit";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditEmployee</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditEmployee at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
    String newPassword = null;
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
    String createAt = "";
    EmployeeDAO eDao = new EmployeeDAO();
                boolean error=false;
                RegisterError msg = new RegisterError();
                id = request.getParameter("id");
                    
                email = request.getParameter("email");
                String checkEmail = eDao.getEmployeeIdByEmail(email);
                if (checkEmail!=null&&!checkEmail.equals(id)){
                    try{
                        if (email.trim().isEmpty()) throw new AdminException.EmptyStringException();
                        String regexPattern = "/^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+)*$/";
                        if (EmailValidation.patternMatches(email, regexPattern)){
                            error = true;
                            msg.setEmailError(EmailValidation.getMessage("Không hợp lệ! ", "Thử theo mẫu: email@fpt.edu.vn"));
                        }
                        EmployeeDAO edao = new EmployeeDAO();
                        Models.GetEmployee emp = edao.getEmployeeByEmail(email);
                        if (emp != null) throw new AdminException.DuplicateException();
                    }catch(AdminException.EmptyStringException e){
                        error = true;
                        msg.setEmailError(e.getMessage());
                    }catch(AdminException.DuplicateException e){
                        error = true;
                        msg.setEmailError(e.getMessage());
                    }
                }
                
                newPassword = request.getParameter("newPassword");
                if (!newPassword.isEmpty()){
                    try{
                        if (newPassword.trim().isEmpty()) throw new AdminException.EmptyStringException();
                        if (newPassword.length()<6||newPassword.length()>20) throw new AdminException.LengthException(6,20);
                        byte[] salt = PasswordEncryption.generateSalt();
                        String encryptedPassword = PasswordEncryption.encryptPassword(newPassword, salt);
                        password = encryptedPassword;
                    }catch(AdminException.EmptyStringException e){
                        error = true;
                        msg.setPasswordError(e.getMessage());
                    } catch (AdminException.LengthException e) {
                        error = true;
                        msg.setPasswordError(e.getMessage());
                    }
                }else{
                    password = request.getParameter("password");
                }
                branchId = request.getParameter("branchId");
                try{
                    name = request.getParameter("name");
                    if (name.trim().isEmpty()) throw new AdminException.EmptyStringException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setNameError(e.getMessage());
                }
                try{
                    birthDate = request.getParameter("birthDate");
                    if (birthDate.trim().isEmpty()) throw new AdminException.EmptyStringException();
                    long millis=System.currentTimeMillis();  
                    Date currentdate = new Date(millis);      
                    Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
                    if (date.compareTo(currentdate)>0) throw new AdminException.BirthDateException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setBirthDateError(e.getMessage());
                } catch (ParseException e) {    
                    error = true;
                    msg.setBirthDateError(e.getMessage());
                }catch (AdminException.BirthDateException e){
                    error = true;
                    msg.setBirthDateError(e.getMessage());
                }
                gender = request.getParameter("gender");
                try{
                    address = request.getParameter("address");
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
                try{
                    phone = request.getParameter("phone");
                    Integer.parseInt(phone);
                    if (phone.trim().length()!=10) throw new AdminException.LackLengthException(10);
                }catch(NumberFormatException e){
                    error = true;
                    msg.setPhoneError("The phone number must be number");
                }catch(AdminException.LackLengthException e){
                    error = true;
                    msg.setPhoneError(e.getMessage());
                }
                try{
                    ethnic = request.getParameter("ethnic");
                    if (ethnic.trim().isEmpty()) throw new AdminException.EmptyStringException();
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setEthnicError(e.getMessage());
                }
                roleId = request.getParameter("roleId");
                Employee emp = new Employee(id, email, password, branchId, name, birthDate, gender, address, workplace, provinceId, phone, ethnic, roleId, createAt);
                
                request.setAttribute("EDIT_EMPLOYEE", emp);
                if (error){
                    request.setAttribute("MESSAGE", "Không sửa được!");
                    request.setAttribute("EDIT_ERROR", msg);
                    request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE+"?id="+id).forward(request, response);
                }else{
                //return massage
                if (eDao.setEmployeeById(emp)) {
                    request.setAttribute("MESSAGE", "Sửa thành công!");
                    request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE+"?id="+id).forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Sửa không thành công!");
                    request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE+"?id="+id).forward(request, response);
                }
                    }
    }
    //------------------
    private static class EmptyStringException extends AdminException {

        public EmptyStringException() {
        }
        public String getMessage(){
//            return "The input must not be empty";
            return "Giá trị nhập vào trống";
        }
    }

    private static class LengthException extends AdminException {
        String msg;
        public LengthException(int a, int b) {
//            msg = "Input must be " + a + " to " + b +" characters long";
            msg = "Giá trị phải có độ dài từ " + a + " tới " + b +" kí tự";
        }
        public String getMessage(){
            return msg;
        }
    }

    private static class LackLengthException extends AdminException {
        String msg;
        public LackLengthException(int i) {
//            msg = "Input must have "+ i +" characters long";
            msg = "Giá trị phải có độ dài "+ i +" kí tự";
        }
        public String getMessage(){
            return msg;
        }
    }

    private static class EmailValidation {

        public EmailValidation() {
        }
        public static boolean patternMatches(String emailAddress, String regexPattern) {
            return Pattern.compile(regexPattern)
            .matcher(emailAddress)
            .matches();
        }
        public static String getMessage(String msg1, String exPattern){
            return msg1 + "Example: " + exPattern;
        }
    }

    private static class DuplicateException extends AdminException {
        String msg;
        public DuplicateException() {
//            msg = "ID aready exist in Database";
              msg = "ID đã tồn tại";
        }
        public String getMessage(){
            return msg;
        }
    }
}
        
