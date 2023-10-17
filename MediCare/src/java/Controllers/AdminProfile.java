/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.EmployeeDAO;
import Models.Employee;
import Models.RegisterError;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DELL
 */
@WebServlet(name="EditEmployeeProfile", urlPatterns={"/admin-edit-profile"})
public class AdminProfile extends HttpServlet {
    private final String EMPLOYEE_PROFILE_PAGE = "admin-profile/admin-profile.jsp";
    private final String EMPLOYEE_EDIT_PROFILE_PAGE = "admin-profile/admin-edit-profile.jsp";
   
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
        request.getRequestDispatcher(EMPLOYEE_EDIT_PROFILE_PAGE).forward(request, response);
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
        String save = request.getParameter("save");
        HttpSession session = request.getSession();
        //check if user is editting profile
        if (save!=null){
            request.getRequestDispatcher(EMPLOYEE_EDIT_PROFILE_PAGE).forward(request, response);
        }
        else{
        Employee currentEmp = (Employee)session.getAttribute("EMPLOYEE");
        Employee emp = currentEmp;
        EmployeeDAO eDao = new EmployeeDAO();
        String id = currentEmp.getId();
        String email;
        String name;
        String password;
        String birthDate;
        String gender;
        String newPassword;
        String confirmPassword;
        String phone;
//        String branchId = "";
        String address = "";
//        String workplace = "";
//        String provinceId = "";
//        String ethnic = "";
//        String roleId = "";
//        String createAt ="";
                boolean error=false;
                RegisterError msg = new RegisterError();
                email = request.getParameter("email");
                String checkEmail = eDao.getEmployeeIdByEmail(email);
                if (checkEmail!=null&&!checkEmail.equals(id)){
                    try{
                        if (email.trim().isEmpty()) throw new AdminException.EmptyStringException();
                        String regexPattern = "/^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+)*$/";
                        if (AdminException.EmailValidation.patternMatches(email, regexPattern)){
                            error = true;
                            msg.setEmailError(AdminException.EmailValidation.getMessage("Không hợp lệ! ", "Thử theo mẫu: email@fpt.edu.vn"));
                        }
                        EmployeeDAO edao = new EmployeeDAO();
                        String check = edao.getEmployeeIdByEmail(email);
                        if (check != null) throw new AdminException.DuplicateException();
                        if (error) throw new Exception();
                        emp.setEmail(email);
                    }catch(AdminException.EmptyStringException e){
                        error = true;
                        msg.setEmailError(e.getMessage());
                    }catch(AdminException.DuplicateException e){
                        error = true;
                        msg.setEmailError(e.getMessage());
                    } catch (Exception ex) {
                Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
                }
                newPassword = request.getParameter("newPassword");
                if (!newPassword.equals("")){
                    try{
                        if (newPassword.length()<6||newPassword.length()>20) throw new AdminException.LengthException(6,20);
                        try{
                            confirmPassword = request.getParameter("confirmPassword");
                            if (confirmPassword.trim().isEmpty()) throw new AdminException.EmptyStringException();
                            if (!confirmPassword.equals(newPassword)) throw new AdminException.NotMatchPasswordExcepton();
                            byte[] salt = PasswordEncryption.generateSalt();
                            String encryptedPassword = PasswordEncryption.encryptPassword(newPassword, salt);
                            if (currentEmp.getPassword().equals(newPassword)) throw new AdminException.PasswordNotChange();
                            password = encryptedPassword;
                            if (error) throw new Exception();
                            emp.setPassword(password);
                        }catch(AdminException.NotMatchPasswordExcepton e){
                            error = true;
                            msg.setPasswordError(e.getMessage());
                        }catch(AdminException.PasswordNotChange e){
                            error = true;
                            msg.setPasswordError(e.getMessage());
                        } 
                    }catch(AdminException.EmptyStringException e){
                        error = true;
                        msg.setPasswordError(e.getMessage());
                    } catch (AdminException.LengthException e) {
                        error = true;
                        msg.setPasswordError(e.getMessage());
                    }catch (Exception ex) {
                            Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try{
                    name = request.getParameter("name");
                    if (name.trim().isEmpty()) throw new AdminException.EmptyStringException();
                    if (error) throw new Exception();
                    emp.setName(name);
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setNameError(e.getMessage());
                } catch (Exception ex) {
                Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
                try{
                    address = request.getParameter("address");
                    if (address.trim().isEmpty()) throw new AdminException.EmptyStringException();
                    if (error) throw new Exception();
                    emp.setAddress(address);
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setAddressError(e.getMessage());
                } catch (Exception ex) {
                Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
                try{
                    birthDate = request.getParameter("birthDate");
                    if (birthDate.trim().isEmpty()) throw new AdminException.EmptyStringException();
                    long millis=System.currentTimeMillis();  
                    Date currentdate = new Date(millis);      
                    Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
                    if (date.compareTo(currentdate)>0) throw new AdminException.BirthDateException();
                    if (error) throw new Exception();
                    emp.setBirthDate(birthDate);
                }catch(AdminException.EmptyStringException e){
                    error = true;
                    msg.setBirthDateError(e.getMessage());
                } catch (ParseException e) {    
                    error = true;
                    msg.setBirthDateError(e.getMessage());
                }catch (AdminException.BirthDateException e){
                    error = true;
                    msg.setBirthDateError(e.getMessage());
                } catch (Exception ex) {
                Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
                try{
                    gender = request.getParameter("gender");
                    if (error) throw new Exception();
                    emp.setGender(gender);
                } catch (Exception ex) {
                Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
//                try{
//                    address = request.getParameter("address");
//                    if (address.trim().isEmpty()) throw new Exception.EmptyStringException();
//                }catch(Exception.EmptyStringException e){
//                    error = true;
//                    msg.setAddressError(e.getMessage());
//                }
                try{
                    phone = request.getParameter("phone");
                    Integer.parseInt(phone);
                    if (phone.trim().length()!=10) throw new AdminException.LackLengthException(10);
                    if (error) throw new Exception();
                    emp.setPhone(phone);
                }catch(NumberFormatException e){
                    error = true;
//                    msg.setPhoneError("The phone number must be number");
                    msg.setPhoneError("Số điện thoại phải là số");
                }catch(AdminException.LackLengthException e){
                    error = true;
                    msg.setPhoneError(e.getMessage());
                } catch (Exception ex) {
                Logger.getLogger(AdminProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
                if (error){
                    request.setAttribute("MESSAGE", "Không sửa được!");
                    request.setAttribute("EDIT_ERROR", msg);
                    request.getRequestDispatcher(EMPLOYEE_EDIT_PROFILE_PAGE).forward(request, response);
                }else{
                //return massage
                if (eDao.setEmployeeById(emp)) {
                    request.setAttribute("MESSAGE", "Sửa thành công!");
                    session.setAttribute("EMPLOYEE", currentEmp);
                    request.getRequestDispatcher(EMPLOYEE_EDIT_PROFILE_PAGE).forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Sửa không thành công!");
                    request.getRequestDispatcher(EMPLOYEE_EDIT_PROFILE_PAGE).forward(request, response);
                }
                }
        }
    }
    
    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
