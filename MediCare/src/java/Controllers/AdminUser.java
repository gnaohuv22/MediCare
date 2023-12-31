/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AdminEmailContext;
import DAL.EmployeeDAO;
import DAL.ProvinceDAO;
import DAL.UserDAO;
import Models.Province;
import Models.RegisterError;

import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author DELL
 */
//@WebServlet(name = "User", urlPatterns = {"/admin-list-user"})
public class AdminUser extends HttpServlet {

    private final String REGISTER_USER = "admin-patients/admin-add-patient.jsp";
    private final String STATISTIC_USER = "admin-patients/admin-patients.jsp";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final String NEED_LOGIN = "Bạn cần đăng nhập truy cập vào trang này";
    private final String NEED_ROLE = "Bạn cần có quyền để truy cập vào trang này";
    private final String[] ROLE = {"1", "2", "4"};
//    private final String ROLE = "1";
//    private final String NEED_LOGIN = "Bạn cần đăng nhập để truy cập vào nội dung này";
//    private final String NEED_ROLE = "Bạn cần quyền để truy cập vào nội dung này";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StatisticUser</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticUser at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Models.Employee checkEmp = (Models.Employee) session.getAttribute("EMPLOYEE");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", NEED_LOGIN);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        } else {
            try {
                String empRole = checkEmp.getEmployeeRole().getId();
                //check the role of employee
                for (String roleNeed : ROLE) {
                    if (empRole.equals(roleNeed)) {
                        throw new Exception();
                    }
                }
                request.setAttribute("MESSAGE", NEED_ROLE);
                request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
            } catch (Exception e) {
            }
        }
        try {
            //check if url is get list user
            if (request.getParameter("add-user") != null || request.getParameter("edit-user") != null || request.getParameter("search-user") != null) {
                throw new AdminException.RedirecUrlException();
            }
            UserDAO udao = new UserDAO();
            ProvinceDAO pdao = new ProvinceDAO();
            //get list user
            int page = 1;
            final int recordsPerPage = 5;
            //set start page = 1
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            ArrayList<User> list;
            ArrayList<String> titleList;
            if (request.getParameter("view-detail") == null) {
                list = udao.getListUser((page - 1) * recordsPerPage, recordsPerPage);
                titleList = udao.getTitleTableUser();
            } else {
                request.setAttribute("view_detail", true);
                list = udao.getMoreListUser((page - 1) * recordsPerPage, recordsPerPage);
                titleList = udao.getMoreTitleTableUser();
            }
            //insert <th></th>
            for (int i = 0; i < titleList.size(); i++) {
                titleList.set(i, "<th>" + titleList.get(i) + "</th>");
            }
            int recordCount = udao.countAllUser();
            int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);
            request.setAttribute("TITLE_USER", titleList);
            request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
            request.setAttribute("ALL_USER", list);
            request.setAttribute("IS_SEARCH", 0);
            request.getRequestDispatcher(STATISTIC_USER).forward(request, response);
        } catch (AdminException.RedirecUrlException e) {
            try {
                //get user to edit or add user
                if (request.getParameter("search-user") != null) {
                    throw new AdminException.RedirecUrlException();
                }
                UserDAO udao = new UserDAO();
                ProvinceDAO pdao = new ProvinceDAO();
                String id = request.getParameter("id");
                User user = udao.getUserById(id);
                request.setAttribute("EDIT_USER", user);
                request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
                if (user == null) {
                    request.setAttribute("add_user", true);
                    request.getRequestDispatcher(REGISTER_USER).forward(request, response);
                } else {
                    System.out.println(user.getName());
                    request.setAttribute("edit_user", true);
                    request.getRequestDispatcher(REGISTER_USER).forward(request, response);
                }
            } catch (AdminException.RedirecUrlException e2) {
                //--------------------search user--------------
                String searchId = request.getParameter("searchId");
                request.setAttribute("searchId", searchId);
                String searchEmail = request.getParameter("searchEmail");
                request.setAttribute("searchEmail", searchEmail);
                String searchName = request.getParameter("searchName");
                request.setAttribute("searchName", searchName);
                String searchBirthDate = request.getParameter("searchBirthDate");
                request.setAttribute("searchBirthDate", searchBirthDate);
                String searchGender = request.getParameter("searchGender");
                if (searchGender==null) searchGender="";
                request.setAttribute("searchGender", searchGender);
                String searchAddress = request.getParameter("searchAddress");
                request.setAttribute("searchAddress", searchAddress);
                String searchProvince = request.getParameter("searchProvince");
                //search all role
                if (searchProvince.equals("all")) {
                    searchProvince = "";
                }
                Province keyProvince = new Province(searchProvince,"");
                request.setAttribute("searchProvince", searchProvince);
                String searchIdentity = request.getParameter("searchIdentity");
                request.setAttribute("searchIdentity", searchIdentity);
                String searchMedicalId = request.getParameter("searchMedicalId");
                request.setAttribute("searchMedicalId", searchMedicalId);
                String searchEthnic = request.getParameter("searchEthnic");
                request.setAttribute("searchEthnic", searchEthnic);
                String searchPhone = request.getParameter("searchPhone");
                request.setAttribute("searchPhone", searchPhone);
                User user = new User(searchId,searchEmail,searchName,searchBirthDate,searchGender,searchAddress,keyProvince,searchIdentity,searchMedicalId,searchEthnic,searchPhone);
                ArrayList<User> list;
                ArrayList<String> titleList;
                UserDAO udao = new UserDAO();
                ProvinceDAO pdao = new ProvinceDAO();
                //get list user
                int page = 1;
                final int recordsPerPage = 5;
                //set start page = 1
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                //display detail table
                if (request.getParameter("view-detail") == null) {
                    list = udao.searchListUser(user, (page - 1) * recordsPerPage, recordsPerPage);
                    titleList = udao.getTitleTableUser();
                } else {
                    request.setAttribute("view_detail", true);
                    list = udao.searchMoreListUser(user, (page - 1) * recordsPerPage, recordsPerPage);
                    titleList = udao.getMoreTitleTableUser();
                }
                //insert <th></th>
                for (int i = 0; i < titleList.size(); i++) {
                    titleList.set(i, "<th>" + titleList.get(i) + "</th>");
                }
                int recordCount = udao.getNumberRecord();
                int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
                request.setAttribute("pageCount", pageCount);
                request.setAttribute("currentPage", page);
                request.setAttribute("TITLE_USER", titleList);
                request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
                request.setAttribute("ALL_USER", list);
                request.setAttribute("IS_SEARCH", 1);
                request.getRequestDispatcher(STATISTIC_USER).forward(request, response);
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Models.Employee checkEmp = (Models.Employee) session.getAttribute("EMPLOYEE");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        } else {
            try {
                String empRole = checkEmp.getEmployeeRole().getId();
                //check the role of employee
                for (String roleNeed : ROLE) {
                    if (empRole.equals(roleNeed)) {
                        throw new Exception();
                    }
                }
                request.setAttribute("MESSAGE", NEED_ROLE);
                request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
            } catch (Exception e) {
            }
        }
        try {
            //check if url is direct to add or edit employee
            if (request.getParameter("edit-user") != null) {
                throw new AdminException.RedirecUrlException();
            }
            request.setAttribute("add_user", true);
            String id = null;
            String email = null;
            String password = null;
            String sendPassword;
            String name = null;
            String birthDate = null;
            String gender;
            String address = null;
            String provinceId;
            String identity = null;
            String medicalId = null;
            String ethnic = null;
            String phone = null;
            String profilePicture = null;
            String createdAt = "";
            EmployeeDAO edao = new EmployeeDAO();
            UserDAO udao = new UserDAO();
            ProvinceDAO pdao = new ProvinceDAO();
            request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
            boolean error = false;
            RegisterError msg = new RegisterError();
            try {
                id = udao.generateId();
                //generate id if duplicate
                while (udao.getUserById(id) != null) {
                    id = Integer.parseInt(id) + 1 + "";
                }
            } catch (Exception e) {
                error = true;
                msg.setIdError("Lỗi");
            }
            try {
                email = request.getParameter("email");
                request.setAttribute("email", email);
                //check if input is empty
                if (email.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (email.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
                String regexPattern = "/^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+)*$/";
                if (AdminException.EmailValidation.patternMatches(email, regexPattern)) {
                    error = true;
//                        msg.setEmailError(EmailValidation.getMessage("Email isn't valiable! ", "email@fpt.edu.vn"));
                    msg.setEmailError(AdminException.EmailValidation.getMessage("Không hợp lệ! ", "Thử theo mẫu: email@fpt.edu.vn"));
                }
                String checkUser = udao.getUserIdByEmail(email);
                if (checkUser != null) {
                    throw new AdminException.DuplicateException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setEmailError(e.getMessage());
            } catch (AdminException.DuplicateException e) {
                error = true;
                msg.setEmailError(e.getMessage());
            } catch (AdminException.LengthException e) {
                error = true;
                msg.setEmailError(e.getMessage());
            }
            Random random = new Random();
            password = "" + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            sendPassword = password;
            byte[] salt = PasswordEncryption.generateSalt();
            String encryptedPassword = PasswordEncryption.encryptPassword(password, salt);
            password = encryptedPassword;

            try {
                name = request.getParameter("name");
                request.setAttribute("name", name);
                //check if input is empty
                if (name.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (name.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setNameError(e.getMessage());
            } catch (AdminException.LengthException e) {
                error = true;
                msg.setNameError(e.getMessage());
            }
            try {
                birthDate = request.getParameter("birthDate");
                request.setAttribute("birthDate", birthDate);
                //check if input is empty
                if (birthDate.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                long millis = System.currentTimeMillis();
                Date currentdate = new Date(millis);
                Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
                //check if date is avaliable
                if (date.compareTo(currentdate) > 0) {
                    throw new AdminException.BirthDateException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setBirthDateError(e.getMessage());
            } catch (ParseException e) {
                error = true;
                msg.setBirthDateError(e.getMessage());
            } catch (AdminException.BirthDateException e) {
                error = true;
                msg.setBirthDateError(e.getMessage());
            }
            gender = request.getParameter("gender");
            request.setAttribute("gender", gender);
            try {
                address = request.getParameter("address");
                request.setAttribute("address", address);
                //check if input is empty
                if (address.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (address.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setAddressError(e.getMessage());
            } catch (AdminException.LengthException e) {
                error = true;
                msg.setAddressError(e.getMessage());
            }
            provinceId = request.getParameter("provinceId");
            request.setAttribute("provinceId", provinceId);
            try {
                identity = request.getParameter("identity");
                request.setAttribute("identity", identity);
                //check if input is empty
                if (identity.isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (identity.trim().length() != 12) {
                    throw new AdminException.LackLengthException(12);
                }
                String pattern = "^\\d{12}$";
                //check if identity is number
                if (!identity.matches(pattern)) {
                    throw new NumberFormatException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setIdentityError(e.getMessage());
            } catch (NumberFormatException e) {
                error = true;
                msg.setIdentityError("CCCD phải là số");
            } catch (AdminException.LackLengthException e) {
                error = true;
                msg.setIdentityError(e.getMessage());
            }
            try {
                medicalId = request.getParameter("medicalId");
                request.setAttribute("medicalId", medicalId);
                //check if input is empty
                if (medicalId.isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                //check length of medicalId
                if (medicalId.trim().length() != 10) {
                    throw new AdminException.LackLengthException(10);
                }
                String pattern = "^\\d{10}$";
                //check if medicalId is number
                if (!medicalId.matches(pattern)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                error = true;
                msg.setMedicalIdError("Số BHYT phải là số");
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setMedicalIdError(e.getMessage());
            }catch (AdminException.LackLengthException e){
                error = true;
                msg.setMedicalIdError(e.getMessage());
            }
            try {
                ethnic = request.getParameter("ethnic");
                request.setAttribute("ethnic", ethnic);
                //check if input is empty
                if (ethnic.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (ethnic.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setEthnicError(e.getMessage());
            } catch (AdminException.LengthException e) {
                error = true;
                msg.setEthnicError(e.getMessage());
            }
            try {
                phone = request.getParameter("phone");
                request.setAttribute("phone", phone);
                //check length of phone
                if (phone.trim().length() != 10) {
                    throw new AdminException.LackLengthException(10);
                }
                String pattern = "^\\d{10}$";
                //check if phone is number
                if (!phone.matches(pattern)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                error = true;
                msg.setPhoneError("Số điện thoại phải là số");
            } catch (AdminException.LackLengthException e) {
                error = true;
                msg.setPhoneError(e.getMessage());
            }
            Province province = new Province(provinceId, "");
            String createBy = checkEmp.getId();
            String modifyAt = "";
            String modifyBy = "";
            User user = new User(id, email, password, name, birthDate, gender, address, province, identity, medicalId, ethnic, phone, profilePicture, createdAt,createBy,modifyAt,modifyBy);
            request.setAttribute("ADD_USER", user);
            profilePicture = request.getParameter("profilePicture");
            request.setAttribute("profilePicture", profilePicture);
            //validate
            if (error) {
                request.setAttribute("MESSAGE", "Không đăng kí được!");
                request.setAttribute("REGISTER_ERROR", msg);
                request.getRequestDispatcher(REGISTER_USER).forward(request, response);
            } else {
                //return massage
                if (udao.addUserByAdmin(user)) {
                    AdminEmailContext.sendEmailnewPassword(email, sendPassword, name);
                    request.setAttribute("MESSAGE", "Đăng kí thành công!");
                    request.getRequestDispatcher(REGISTER_USER).forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Đăng kí không thành công!");
                    request.getRequestDispatcher(REGISTER_USER).forward(request, response);
                }
            }
        } catch (AdminException.RedirecUrlException e) {
            request.setAttribute("edit_user", true);
            ProvinceDAO pdao = new ProvinceDAO();
            request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
            String id = null;
            String email = null;
            String password = null;
            String newPassword;
            String name = null;
            String birthDate = null;
            String gender;
            String address = null;
            String provinceId;
            String identity = null;
            String medicalId = null;
            String ethnic = null;
            String phone = null;
            String profilePicture;
            String createdAt = "";
            UserDAO udao = new UserDAO();
            boolean error = false;
            RegisterError msg = new RegisterError();
            id = request.getParameter("id");
//      request.setAttribute("id", id);
            email = request.getParameter("email");
            //check if user id exist
            if (!email.equals(udao.getUserEmailById(id))) {
                try {
//                    request.setAttribute("email", email);
                    if (email.trim().isEmpty()) {
                        throw new AdminException.EmptyStringException();
                    }
                    String regexPattern = "/^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+)*$/";
                    if (AdminException.EmailValidation.patternMatches(email, regexPattern)) {
                        error = true;
//                        msg.setEmailError(EmailValidation.getMessage("Email isn't valiable! ", "email@fpt.edu.vn"));
                        msg.setEmailError(AdminException.EmailValidation.getMessage("Không hợp lệ! ", "Thử theo mẫu: email@fpt.edu.vn"));
                    }
                    String checkUser = udao.getUserIdByEmail(email);
                    if (checkUser != null) {
                        throw new AdminException.DuplicateException();
                    }
                } catch (AdminException.EmptyStringException ex) {
                    error = true;
                    msg.setEmailError(ex.getMessage());
                } catch (AdminException.DuplicateException ex) {
                    error = true;
                    msg.setEmailError(ex.getMessage());
                }
            }

            try {
                name = request.getParameter("name");
//                    request.setAttribute("name", name);
                if (name.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (name.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setNameError(ex.getMessage());
            } catch (AdminException.LengthException ex) {
                error = true;
                msg.setNameError(ex.getMessage());
            }
            try {
                birthDate = request.getParameter("birthDate");
//                    request.setAttribute("birthDate", birthDate);
                if (birthDate.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                long millis = System.currentTimeMillis();
                Date currentdate = new Date(millis);
                Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
                if (date.compareTo(currentdate) > 0) {
                    throw new AdminException.BirthDateException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setBirthDateError(ex.getMessage());
            } catch (ParseException ex) {
                error = true;
                msg.setBirthDateError(ex.getMessage());
            } catch (AdminException.BirthDateException ex) {
                error = true;
                msg.setBirthDateError(ex.getMessage());
            }
            gender = request.getParameter("gender");
//                request.setAttribute("gender", gender);
            try {
                address = request.getParameter("address");
//                    request.setAttribute("address", address);
                if (address.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (address.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setAddressError(ex.getMessage());
            } catch (AdminException.LengthException ex) {
                error = true;
                msg.setAddressError(ex.getMessage());
            }
            provinceId = request.getParameter("provinceId");
//                request.setAttribute("provinceId", provinceId);
            try {
                identity = request.getParameter("identity");
//                    request.setAttribute("identity", identity);
                if (identity.isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (identity.trim().length() != 12) {
                    throw new AdminException.LackLengthException(12);
                }
                String pattern = "^\\d{12}$";
                //check if identity is number
                if (!identity.matches(pattern)) {
                    throw new NumberFormatException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setIdentityError(ex.getMessage());
            } catch (NumberFormatException ex) {
                error = true;
                msg.setIdentityError("CCCD phải là số");
            } catch (AdminException.LackLengthException ex) {
                error = true;
                msg.setIdentityError(ex.getMessage());
            }
            try {
                medicalId = request.getParameter("medicalId");
//                    request.setAttribute("medicalId", medicalId);
                if (medicalId.isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (medicalId.length()!=10){
                    throw new AdminException.LackLengthException(10);
                }
                String pattern = "^\\d{10}$";
                //check if medicalId is number
                if (!medicalId.matches(pattern)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                error = true;
                msg.setMedicalIdError("Số BHYT phải là số");
//                    msg.setPhoneError("The phone number must be number");
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setMedicalIdError(ex.getMessage());
            }catch(AdminException.LackLengthException ex){
                error = true;
                msg.setMedicalIdError(ex.getMessage());
            }
            try {
                ethnic = request.getParameter("ethnic");
//                    request.setAttribute("ethnic", ethnic);
                if (ethnic.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (ethnic.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setEthnicError(ex.getMessage());
            } catch (AdminException.LengthException ex) {
                error = true;
                msg.setEthnicError(ex.getMessage());
            }
            try {
                phone = request.getParameter("phone");
                if (phone.trim().length() != 10) {
                    throw new AdminException.LackLengthException(10);
                }
                String pattern = "^\\d{10}$";
                //check if phone is number
                if (!phone.matches(pattern)) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                error = true;
                msg.setPhoneError("Số điện thoại phải là số");
//                    msg.setPhoneError("The phone number must be number");
            } catch (AdminException.LackLengthException ex) {
                error = true;
                msg.setPhoneError(ex.getMessage());
            }

            profilePicture = request.getParameter("profilePicture");
            request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
            Province province = new Province(provinceId, "");
            String createBy = "";
            String modifyAt = "";
            String modifyBy = checkEmp.getId();
            User user = new User(id, email, password, name, birthDate, gender, address, province, identity, medicalId, ethnic, phone, profilePicture, createdAt,createBy,modifyAt,modifyBy);
            request.setAttribute("EDIT_USER", user);
            if (error) {
                request.setAttribute("MESSAGE", "Không sửa được!");
                request.setAttribute("EDIT_ERROR", msg);
                request.getRequestDispatcher(REGISTER_USER).forward(request, response);
            } else {
                //return massage
                if (udao.setUserById(user)) {
                    request.setAttribute("MESSAGE", "Sửa thành công!");
                    request.getRequestDispatcher(REGISTER_USER).forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Sửa không thành công!");
                    request.getRequestDispatcher(REGISTER_USER).forward(request, response);
                }
            }
        }
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
