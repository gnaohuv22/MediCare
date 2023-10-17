/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Controllers.AdminException.EmailValidation;
import DAL.BranchDAO;
import Models.Employee;
import DAL.EmployeeDAO;
import DAL.EmployeeRoleDAO;
import DAL.ProvinceDAO;
import DAL.SubLevelMenuDAO;
import Models.Branch;
import Models.EmployeeRole;
import Models.Province;
import Models.RegisterError;
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

/**
 *
 * @author DELL
 */
@WebServlet(name = "Employee", urlPatterns = {"/admin-list-employee"})
public class AdminEmployee extends HttpServlet {

    private final String STATISTIC_EMPLOYEE = "admin-employees/admin-employees.jsp";
    private final String EDIT_EMPLOYEE_PAGE = "admin-employees/admin-edit-employee.jsp";
    private final String REGISTER_EMPLOYEE = "admin-employees/admin-add-employee.jsp";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
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
            out.println("<title>Servlet StatisticEmployee</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StatisticEmployee at " + request.getContextPath() + "</h1>");
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
        EmployeeDAO edao = new EmployeeDAO();
        Models.Employee checkEmp = (Models.Employee) session.getAttribute("EMPLOYEE");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        try {
            //check if url is direct to add or edit employee
            if (request.getParameter("add-employee") != null || request.getParameter("edit-employee") != null || request.getParameter("search-employee") != null) {
                throw new AdminException.RedirecUrlException();
            }
            //get list employee
            EmployeeDAO eDao = new EmployeeDAO();
            EmployeeRoleDAO erdao = new EmployeeRoleDAO();
            SubLevelMenuDAO slmDao = new SubLevelMenuDAO();
            int page = 1;
            final int recordsPerPage = 5;
            //set start page = 1
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            ArrayList<Employee> list = eDao.getListEmployee((page - 1) * recordsPerPage, recordsPerPage);
            ArrayList<String> titleList = slmDao.getTitleTable("titleTableEmployee");
            //insert <th></th>
            for (int i = 0; i < titleList.size(); i++) {
                titleList.set(i, "<th>" + titleList.get(i) + "</th>");
            }
            int recordCount = eDao.countAllEmployee();
            int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("currentPage", page);
            request.setAttribute("TITLE_EMPLOYEE", titleList);
            request.setAttribute("ALL_EMPLOYEEROLE", erdao.getAllEmployeeRole());
            request.setAttribute("ALL_EMPLOYEE", list);
            request.setAttribute("IS_SEARCH", 0);
            request.getRequestDispatcher(STATISTIC_EMPLOYEE).forward(request, response);
        } catch (AdminException.RedirecUrlException e) {
            try {
                if (request.getParameter("search-employee") != null) {
                    throw new AdminException.RedirecUrlException();
                }
                EmployeeRoleDAO erdao = new EmployeeRoleDAO();
                BranchDAO bdao = new BranchDAO();
                ProvinceDAO pdao = new ProvinceDAO();
                String id = request.getParameter("id");
                Employee emp = edao.getEmployeeById(id);
                request.setAttribute("EDIT_EMPLOYEE", emp);
                request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
                request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
                request.setAttribute("ALL_EMPLOYEEROLE", erdao.getAllEmployeeRole());
                if (emp!=null){
                    request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE).forward(request, response);
                }
                request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
            } catch (AdminException.RedirecUrlException ex) {
                String keyId = request.getParameter("searchId");
                request.setAttribute("searchId", keyId);
                String keyName = request.getParameter("searchName");
                request.setAttribute("searchName", keyName);
                String keyRole = request.getParameter("searchRole");
                if (keyRole.equals("Select Role")) {
                    keyRole = "";
                }
                request.setAttribute("searchRole", keyRole);
                EmployeeDAO eDao = new EmployeeDAO();
                EmployeeRoleDAO erdao = new EmployeeRoleDAO();
                int page = 1;
                final int recordsPerPage = 5;
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                SubLevelMenuDAO slmDao = new SubLevelMenuDAO();
                ArrayList<Employee> list = eDao.searchEmployee(keyId, keyName, keyRole, (page - 1) * recordsPerPage, recordsPerPage);
                ArrayList<String> titleList = slmDao.getTitleTable("titleTableEmployee");
                //insert <th></th>
                for (int i = 0; i < titleList.size(); i++) {
                    titleList.set(i, "<th>" + titleList.get(i) + "</th>");
                }
                int recordCount = eDao.getNumberRecord();
                int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
                request.setAttribute("pageCount", pageCount);
                request.setAttribute("currentPage", page);
                request.setAttribute("TITLE_EMPLOYEE", titleList);
                request.setAttribute("ALL_EMPLOYEEROLE", erdao.getAllEmployeeRole());
                request.setAttribute("ALL_EMPLOYEE", list);
                request.setAttribute("IS_SEARCH", 1);
                request.getRequestDispatcher(STATISTIC_EMPLOYEE).forward(request, response);
            }
        }
        processRequest(request, response);
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
        EmployeeDAO edao = new EmployeeDAO();
        Models.Employee checkEmp = (Models.Employee) session.getAttribute("EMPLOYEE");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        try {
            String action = request.getParameter("add-employee");
            if (action == null) {
                throw new AdminException.RedirecUrlException();
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
            boolean error = false;
            RegisterError msg = new RegisterError();
            try {
                id = request.getParameter("id");
                request.setAttribute("id", id);
                //check if input is empty
                if (id.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                Employee emp = edao.getEmployeeById(id);
                //check if id is duplicate
                if (emp != null) {
                    throw new AdminException.DuplicateException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setIdError(e.getMessage());
            } catch (NumberFormatException e) {
                error = true;
//                    msg.setIdError("The ID must be number");
                msg.setIdError("ID phải là số");
            } catch (AdminException.DuplicateException e) {
                error = true;
                msg.setIdError(e.getMessage());
            }
            try {
                email = request.getParameter("email");
                request.setAttribute("email", email);
                //check if input is empty
                if (email.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                String regexPattern = "/^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+)*$/";
                //check email pattern
                if (AdminException.EmailValidation.patternMatches(email, regexPattern)) {
                    error = true;
//                        msg.setEmailError(EmailValidation.getMessage("Email isn't valiable! ", "email@fpt.edu.vn"));
                    msg.setEmailError(AdminException.EmailValidation.getMessage("Không hợp lệ! ", "Thử theo mẫu: email@fpt.edu.vn"));
                }
                Employee emp = edao.getEmployeeByEmail(email);
                //check if email is duplicate
                if (emp != null) {
                    throw new AdminException.DuplicateException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setEmailError(e.getMessage());
            } catch (AdminException.DuplicateException e) {
                error = true;
                msg.setEmailError(e.getMessage());
            }
            try {
                password = request.getParameter("password");
                request.setAttribute("password", password);
                //check if input is empty
                if (password.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                //check length of password
                if (password.length() < 6 || password.length() > 20) {
                    throw new AdminException.LengthException(6, 20);
                }
                byte[] salt = PasswordEncryption.generateSalt();
                String encryptedPassword = PasswordEncryption.encryptPassword(password, salt);
                password = encryptedPassword;
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setPasswordError(e.getMessage());
            } catch (AdminException.LengthException e) {
                error = true;
                msg.setPasswordError(e.getMessage());
            }
            branchId = request.getParameter("branchId");
            request.setAttribute("branchId", branchId);
            try {
                name = request.getParameter("name");
                request.setAttribute("name", name);
                //check if input is empty
                if (name.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException e) {
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
                //check if birthdate is avaliable
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
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setAddressError(e.getMessage());
            }
            try {
                workplace = request.getParameter("workplace");
                request.setAttribute("workplace", workplace);
                //check if input is empty
                if (workplace.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setWorkplaceError(e.getMessage());
            }
            provinceId = request.getParameter("provinceId");
            request.setAttribute("provinceId", provinceId);
            try {
                phone = request.getParameter("phone");
                request.setAttribute("phone", phone);
                Long.parseLong(phone);
                //check length of phone
                if (phone.trim().length() != 10) {
                    throw new AdminException.LackLengthException(10);
                }
            } catch (NumberFormatException e) {
                error = true;
                msg.setPhoneError("Số điện thoại phải là số");
//                    msg.setPhoneError("The phone number must be number");
            } catch (AdminException.LackLengthException e) {
                error = true;
                msg.setPhoneError(e.getMessage());
            }
            try {
                ethnic = request.getParameter("ethnic");
                request.setAttribute("ethnic", ethnic);
                //check if input is empty
                if (ethnic.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setEthnicError(e.getMessage());
            }
            roleId = request.getParameter("roleId");
            request.setAttribute("roleId", roleId);
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            createAt = date + "";
            EmployeeRoleDAO erdao = new EmployeeRoleDAO();
            BranchDAO bdao = new BranchDAO();
            ProvinceDAO pdao = new ProvinceDAO();
            request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
            request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
            request.setAttribute("ALL_EMPLOYEEROLE", erdao.getAllEmployeeRole());
            if (error) {
                request.setAttribute("MESSAGE", "Không đăng kí được!");
                request.setAttribute("REGISTER_ERROR", msg);
                request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
            } else {
                Branch branch = new Branch(branchId, "", "", "");
                Province province = new Province(provinceId, "");
                EmployeeRole employeeRole = new EmployeeRole(roleId, "");
                Employee emp = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole, createAt);
                EmployeeDAO eDao = new EmployeeDAO();
                //return massage
                if (eDao.addEmployee(emp)) {
                    request.setAttribute("MESSAGE", "Đăng kí thành công!");
                    request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Đăng kí không thành công!");
                    request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
                }
            }
        } catch (AdminException.RedirecUrlException e) {
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
            boolean error = false;
            RegisterError msg = new RegisterError();
            id = request.getParameter("id");
            email = request.getParameter("email");
            String checkEmail = eDao.getEmployeeIdByEmail(email);
            if (checkEmail != null && !checkEmail.equals(id)) {
                try {
                    if (email.trim().isEmpty()) {
                        throw new AdminException.EmptyStringException();
                    }
                    String regexPattern = "/^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+)*$/";
                    if (EmailValidation.patternMatches(email, regexPattern)) {
                        error = true;
                        msg.setEmailError(EmailValidation.getMessage("Không hợp lệ! ", "Thử theo mẫu: email@fpt.edu.vn"));
                    }
                    Models.Employee emp = edao.getEmployeeByEmail(email);
                    if (emp != null) {
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

            newPassword = request.getParameter("newPassword");
            if (!newPassword.isEmpty()) {
                try {
                    if (newPassword.trim().isEmpty()) {
                        throw new AdminException.EmptyStringException();
                    }
                    if (newPassword.length() < 6 || newPassword.length() > 20) {
                        throw new AdminException.LengthException(6, 20);
                    }
                    byte[] salt = PasswordEncryption.generateSalt();
                    String encryptedPassword = PasswordEncryption.encryptPassword(newPassword, salt);
                    password = encryptedPassword;
                } catch (AdminException.EmptyStringException ex) {
                    error = true;
                    msg.setPasswordError(ex.getMessage());
                } catch (AdminException.LengthException ex) {
                    error = true;
                    msg.setPasswordError(ex.getMessage());
                }
            } else {
                password = request.getParameter("password");
            }
            branchId = request.getParameter("branchId");
            try {
                name = request.getParameter("name");
                if (name.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setNameError(ex.getMessage());
            }
            try {
                birthDate = request.getParameter("birthDate");
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
            try {
                address = request.getParameter("address");
                if (address.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setAddressError(ex.getMessage());
            }
            try {
                workplace = request.getParameter("workplace");
                request.setAttribute("workplace", workplace);
                if (workplace.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setWorkplaceError(ex.getMessage());
            }
            provinceId = request.getParameter("provinceId");
            try {
                phone = request.getParameter("phone");
                Integer.parseInt(phone);
                if (phone.trim().length() != 10) {
                    throw new AdminException.LackLengthException(10);
                }
            } catch (NumberFormatException ex) {
                error = true;
                msg.setPhoneError("The phone number must be number");
            } catch (AdminException.LackLengthException ex) {
                error = true;
                msg.setPhoneError(ex.getMessage());
            }
            try {
                ethnic = request.getParameter("ethnic");
                if (ethnic.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setEthnicError(ex.getMessage());
            }
            roleId = request.getParameter("roleId");
            Branch branch = new Branch(branchId, "", "", "");
            Province province = new Province(provinceId, "");
            EmployeeRole employeeRole = new EmployeeRole(roleId, "");
            Employee emp = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole, createAt);

            request.setAttribute("EDIT_EMPLOYEE", emp);
            if (error) {
                request.setAttribute("MESSAGE", "Không sửa được!");
                request.setAttribute("EDIT_ERROR", msg);
                request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE + "?id=" + id).forward(request, response);
            } else {
                //return massage
                if (eDao.setEmployeeById(emp)) {
                    request.setAttribute("MESSAGE", "Sửa thành công!");
                    request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE + "?id=" + id).forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Sửa không thành công!");
                    request.getRequestDispatcher(EDIT_EMPLOYEE_PAGE + "?id=" + id).forward(request, response);
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
