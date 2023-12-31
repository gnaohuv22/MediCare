/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Controllers.AdminException.EmailValidation;
import DAL.AdminEmailContext;
import DAL.BranchDAO;
import Models.Employee;
import DAL.EmployeeDAO;
import DAL.EmployeeRoleDAO;
import DAL.ProvinceDAO;
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
import jakarta.servlet.http.Part;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author DELL
 */
//@WebServlet(name = "Employee", urlPatterns = {"/admin-list-employee"})
public class AdminEmployee extends HttpServlet {

    private final String STATISTIC_EMPLOYEE = "admin-employees/admin-employees.jsp";
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final String REGISTER_EMPLOYEE = "admin-employees/admin-add-employee.jsp";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
    private final String NEED_LOGIN = "Bạn cần đăng nhập truy cập vào trang này";
    private final String NEED_ROLE = "Bạn cần có quyền để truy cập vào trang này";
    private final String[] ROLE = {"1", "2", "3"};
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
            try {
                //check if url is get list employee
                if (request.getParameter("add-employee") != null || request.getParameter("edit-employee") != null || request.getParameter("search-employee") != null || request.getParameter("delete-employee") != null || request.getParameter("restore-employee") != null) {
                    throw new AdminException.RedirecUrlException();
                }
                //get list employee
                EmployeeDAO eDao = new EmployeeDAO();
                EmployeeRoleDAO erdao = new EmployeeRoleDAO();
                BranchDAO bdao = new BranchDAO();
                String searchBranch = request.getParameter("searchBranch");
                if (searchBranch == null) {
                    searchBranch = "1";
                }
                request.setAttribute("searchBranch", searchBranch);
                String isDelete = request.getParameter("isDelete");
                if (isDelete == null) {
                    isDelete = "0";
                } else {
                    if (isDelete.equals("true")) {
                        isDelete = "1";
                    } else {
                        isDelete = "0";
                    }
                }
                request.setAttribute("isDelete", isDelete);
                int page = 1;
                final int recordsPerPage = 5;
                //set start page = 1
                if (request.getParameter("page") != null) {
                    page = Integer.parseInt(request.getParameter("page"));
                }
                ArrayList<Employee> list;
                ArrayList<String> titleList;
                if (request.getParameter("view-detail") == null) {
                    list = eDao.getListEmployee((page - 1) * recordsPerPage, recordsPerPage, searchBranch, isDelete);
                    titleList = eDao.getTitleTableEmployee();
                } else {
                    request.setAttribute("view_detail", true);
                    list = eDao.getMoreListEmployee((page - 1) * recordsPerPage, recordsPerPage, searchBranch, isDelete);
                    titleList = eDao.getMoreTitleTableEmployee();
                }
                //insert <th></th>, <td></td>;
                for (int i = 0; i < titleList.size(); i++) {
                    titleList.set(i, "<th>" + titleList.get(i) + "</th>");
                }
                int recordCount = eDao.countAllEmployee(searchBranch, isDelete);
                int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
                request.setAttribute("pageCount", pageCount);
                request.setAttribute("currentPage", page);
                request.setAttribute("TITLE_EMPLOYEE", titleList);
                request.setAttribute("ALL_EMPLOYEEROLE", erdao.getAllEmployeeRole());
                request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
                request.setAttribute("ALL_EMPLOYEE", list);
                request.setAttribute("IS_SEARCH", 0);
                request.getRequestDispatcher(STATISTIC_EMPLOYEE).forward(request, response);
            } catch (AdminException.RedirecUrlException e) {
                try {
                    //check if url is get an employee
                    if (request.getParameter("search-employee") != null || request.getParameter("delete-employee") != null || request.getParameter("restore-employee") != null) {
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
                    //check if action is edit or add employee
                    if (emp != null) {
                        request.setAttribute("edit_employee", true);
                        request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
                    } else {
                        request.setAttribute("add_employee", true);
                        request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
                    }
                } catch (AdminException.RedirecUrlException ex) {
                    try {
                        //check if url is search employee
                        if (request.getParameter("delete-employee") != null || request.getParameter("restore-employee") != null) {
                            throw new AdminException.RedirecUrlException();
                        }
                        String keyId = request.getParameter("searchId");
                        request.setAttribute("searchId", keyId);
                        String keyName = request.getParameter("searchName");
                        request.setAttribute("searchName", keyName);
                        String keyRole = request.getParameter("searchRole");
                        //search all role
                        if (keyRole.equals("Select Role")) {
                            keyRole = "";
                        }
                        request.setAttribute("searchRole", keyRole);
                        String keyBranch = request.getParameter("searchBranch");
                        // search all branch
                        if (keyBranch == null) {
                            keyBranch = "1";
                        }
                        request.setAttribute("searchBranch", keyBranch);
                        String isDelete = request.getParameter("isDelete");
                        //display employee are working
                        if (isDelete == null) {
                            isDelete = "0";
                        } else {
                            if (isDelete.equals("true")||isDelete.equals("1")) {
                                isDelete = "1";
                            } else {
                                isDelete = "0";
                            }
                        }
                        request.setAttribute("isDelete", isDelete);
                        EmployeeDAO eDao = new EmployeeDAO();
                        EmployeeRoleDAO erdao = new EmployeeRoleDAO();
                        int page = 1;
                        final int recordsPerPage = 5;
                        if (request.getParameter("page") != null) {
                            page = Integer.parseInt(request.getParameter("page"));
                        }
                        BranchDAO bdao = new BranchDAO();
                        ArrayList<Employee> list;
                        ArrayList<String> titleList;
                        //display detail table
                        if (request.getParameter("view-detail") == null) {
                            list = eDao.searchEmployee(keyId, keyName, keyRole, keyBranch, (page - 1) * recordsPerPage, recordsPerPage, isDelete);
                            titleList = eDao.getTitleTableEmployee();
                        } else {
                            request.setAttribute("view_detail", true);
                            list = eDao.searchMoreEmployee(keyId, keyName, keyRole, keyBranch, (page - 1) * recordsPerPage, recordsPerPage, isDelete);
                            titleList = eDao.getMoreTitleTableEmployee();
                        }
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
                        request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
                        request.setAttribute("ALL_EMPLOYEE", list);
                        request.setAttribute("IS_SEARCH", 1);
                        request.getRequestDispatcher(STATISTIC_EMPLOYEE).forward(request, response);
                    } catch (AdminException.RedirecUrlException e3) {
                        try {
                            //check if delete employee
                            if (request.getParameter("restore-employee") != null) {
                                throw new AdminException.RedirecUrlException();
                            }
                            String id = request.getParameter("id");
                            //check if delete is succesfully
                            if (edao.deleteEmployee(id,checkEmp.getId())) {
                                request.setAttribute("MESSAGE", "Xóa thành công");
                            } else {
                                request.setAttribute("MESSAGE", "Xóa không thành công");
                            }
                            ArrayList<Employee> list;
                            ArrayList<String> titleList;
                            String search_employee = request.getParameter("search-employee");
                            //if list is in state search then return search
                            if (search_employee != null) {
                                String keyId = request.getParameter("searchId");
                                request.setAttribute("searchId", keyId);
                                String keyName = request.getParameter("searchName");
                                request.setAttribute("searchName", keyName);
                                String keyRole = request.getParameter("searchRole");
                                //search all role
                                if (keyRole.equals("Select Role")) {
                                    keyRole = "";
                                }
                                request.setAttribute("searchRole", keyRole);
                                String keyBranch = request.getParameter("searchBranch");
                                // search all branch
                                if (keyBranch == null) {
                                    keyBranch = "1";
                                }
                                request.setAttribute("searchBranch", keyBranch);
                                String isDelete = request.getParameter("isDelete");
                                //display employee are working
                                if (isDelete == null) {
                                    isDelete = "0";
                                } else {
                                    if (isDelete.equals("true")) {
                                        isDelete = "1";
                                    } else {
                                        isDelete = "0";
                                    }
                                }
                                request.setAttribute("isDelete", isDelete);
                                EmployeeDAO eDao = new EmployeeDAO();
                                EmployeeRoleDAO erdao = new EmployeeRoleDAO();
                                int page = 1;
                                final int recordsPerPage = 5;
                                if (request.getParameter("page") != null) {
                                    page = Integer.parseInt(request.getParameter("page"));
                                }
                                BranchDAO bdao = new BranchDAO();
                                //display detail table
                                if (request.getParameter("view-detail") == null) {
                                    list = eDao.searchEmployee(keyId, keyName, keyRole, keyBranch, (page - 1) * recordsPerPage, recordsPerPage, isDelete);
                                    titleList = eDao.getTitleTableEmployee();
                                } else {
                                    request.setAttribute("view_detail", true);
                                    list = eDao.searchMoreEmployee(keyId, keyName, keyRole, keyBranch, (page - 1) * recordsPerPage, recordsPerPage, isDelete);
                                    titleList = eDao.getMoreTitleTableEmployee();
                                }
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
                                request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
                                request.setAttribute("ALL_EMPLOYEE", list);
                                request.setAttribute("IS_SEARCH", 1);
                            } else {
                                EmployeeDAO eDao = new EmployeeDAO();
                                EmployeeRoleDAO erdao = new EmployeeRoleDAO();
                                BranchDAO bdao = new BranchDAO();
                                String searchBranch = request.getParameter("searchBranch");
                                if (searchBranch == null) {
                                    searchBranch = "1";
                                }
                                request.setAttribute("searchBranch", searchBranch);
                                String isDelete = request.getParameter("isDelete");
                                if (isDelete == null) {
                                    isDelete = "0";
                                } else {
                                    if (isDelete.equals("true")) {
                                        isDelete = "1";
                                    } else {
                                        isDelete = "0";
                                    }
                                }
                                request.setAttribute("isDelete", isDelete);
                                int page = 1;
                                final int recordsPerPage = 5;
                                //set start page = 1
                                if (request.getParameter("page") != null) {
                                    page = Integer.parseInt(request.getParameter("page"));
                                }
                                if (request.getParameter("view-detail") == null) {
                                    list = eDao.getListEmployee((page - 1) * recordsPerPage, recordsPerPage, searchBranch, isDelete);
                                    titleList = eDao.getTitleTableEmployee();
                                } else {
                                    request.setAttribute("view_detail", true);
                                    list = eDao.getMoreListEmployee((page - 1) * recordsPerPage, recordsPerPage, searchBranch, isDelete);
                                    titleList = eDao.getMoreTitleTableEmployee();
                                }
                                //insert <th></th>, <td></td>;
                                for (int i = 0; i < titleList.size(); i++) {
                                    titleList.set(i, "<th>" + titleList.get(i) + "</th>");
                                }
                                int recordCount = eDao.countAllEmployee(searchBranch, isDelete);
                                int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
                                request.setAttribute("pageCount", pageCount);
                                request.setAttribute("currentPage", page);
                                request.setAttribute("TITLE_EMPLOYEE", titleList);
                                request.setAttribute("ALL_EMPLOYEEROLE", erdao.getAllEmployeeRole());
                                request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
                                request.setAttribute("ALL_EMPLOYEE", list);
                                request.setAttribute("IS_SEARCH", 0);
                            }
                            request.getRequestDispatcher(STATISTIC_EMPLOYEE).forward(request, response);
                        } catch (AdminException.RedirecUrlException e4) {
                            //restore
                            String id = request.getParameter("id");
                            //check if restore is succesfully
                            if (edao.restoreEmployee(id,checkEmp.getId())) {
                                request.setAttribute("MESSAGE", "Khôi phục thành công");
                            } else {
                                request.setAttribute("MESSAGE", "Khôi phục không thành công");
                            }
                            ArrayList<Employee> list;
                            ArrayList<String> titleList;
                            String search_employee = request.getParameter("search-employee");
                            //if list is in state search then return search
                            if (search_employee != null) {
                                String keyId = request.getParameter("searchId");
                                request.setAttribute("searchId", keyId);
                                String keyName = request.getParameter("searchName");
                                request.setAttribute("searchName", keyName);
                                String keyRole = request.getParameter("searchRole");
                                //search all role
                                if (keyRole.equals("Select Role")) {
                                    keyRole = "";
                                }
                                request.setAttribute("searchRole", keyRole);
                                String keyBranch = request.getParameter("searchBranch");
                                // search all branch
                                if (keyBranch == null) {
                                    keyBranch = "1";
                                }
                                request.setAttribute("searchBranch", keyBranch);
                                String isDelete = request.getParameter("isDelete");
                                //display employee are working
                                if (isDelete == null) {
                                    isDelete = "0";
                                } else {
                                    if (isDelete.equals("true")) {
                                        isDelete = "1";
                                    } else {
                                        isDelete = "0";
                                    }
                                }
                                request.setAttribute("isDelete", isDelete);
                                EmployeeDAO eDao = new EmployeeDAO();
                                EmployeeRoleDAO erdao = new EmployeeRoleDAO();
                                int page = 1;
                                final int recordsPerPage = 5;
                                if (request.getParameter("page") != null) {
                                    page = Integer.parseInt(request.getParameter("page"));
                                }
                                BranchDAO bdao = new BranchDAO();
                                //display detail table
                                if (request.getParameter("view-detail") == null) {
                                    list = eDao.searchEmployee(keyId, keyName, keyRole, keyBranch, (page - 1) * recordsPerPage, recordsPerPage, isDelete);
                                    titleList = eDao.getTitleTableEmployee();
                                } else {
                                    request.setAttribute("view_detail", true);
                                    list = eDao.searchMoreEmployee(keyId, keyName, keyRole, keyBranch, (page - 1) * recordsPerPage, recordsPerPage, isDelete);
                                    titleList = eDao.getMoreTitleTableEmployee();
                                }
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
                                request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
                                request.setAttribute("ALL_EMPLOYEE", list);
                                request.setAttribute("IS_SEARCH", 1);
                            } else {
                                EmployeeDAO eDao = new EmployeeDAO();
                                EmployeeRoleDAO erdao = new EmployeeRoleDAO();
                                BranchDAO bdao = new BranchDAO();
                                String searchBranch = request.getParameter("searchBranch");
                                if (searchBranch == null) {
                                    searchBranch = "1";
                                }
                                request.setAttribute("searchBranch", searchBranch);
                                String isDelete = request.getParameter("isDelete");
                                if (isDelete == null) {
                                    isDelete = "0";
                                } else {
                                    if (isDelete.equals("true")) {
                                        isDelete = "1";
                                    } else {
                                        isDelete = "0";
                                    }
                                }
                                request.setAttribute("isDelete", isDelete);
                                int page = 1;
                                final int recordsPerPage = 5;
                                //set start page = 1
                                if (request.getParameter("page") != null) {
                                    page = Integer.parseInt(request.getParameter("page"));
                                }
                                if (request.getParameter("view-detail") == null) {
                                    list = eDao.getListEmployee((page - 1) * recordsPerPage, recordsPerPage, searchBranch, isDelete);
                                    titleList = eDao.getTitleTableEmployee();
                                } else {
                                    request.setAttribute("view_detail", true);
                                    list = eDao.getMoreListEmployee((page - 1) * recordsPerPage, recordsPerPage, searchBranch, isDelete);
                                    titleList = eDao.getMoreTitleTableEmployee();
                                }
                                //insert <th></th>, <td></td>;
                                for (int i = 0; i < titleList.size(); i++) {
                                    titleList.set(i, "<th>" + titleList.get(i) + "</th>");
                                }
                                int recordCount = eDao.countAllEmployee(searchBranch, isDelete);
                                int pageCount = (int) Math.ceil(recordCount * 1.0 / recordsPerPage);
                                request.setAttribute("pageCount", pageCount);
                                request.setAttribute("currentPage", page);
                                request.setAttribute("TITLE_EMPLOYEE", titleList);
                                request.setAttribute("ALL_EMPLOYEEROLE", erdao.getAllEmployeeRole());
                                request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
                                request.setAttribute("ALL_EMPLOYEE", list);
                                request.setAttribute("IS_SEARCH", 0);
                            }
                            request.getRequestDispatcher(STATISTIC_EMPLOYEE).forward(request, response);
                        }
                    }
                }
            }
            processRequest(request, response);
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
            request.setAttribute("add_employee", true);
            String id = null;
            String email = null;
            String password = null;
            String sendPassword = null;
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
            String createBy = null;
            String modifyAt = null;
            String modifyBy = null;
            boolean error = false;
            RegisterError msg = new RegisterError();
            try {
                id = edao.generateId();
                //check if input is empty
                if (id.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                //generate id if duplicate
                while (edao.getEmployeeById(id) != null) {
                    id = Integer.parseInt(id) + 1 + "";
                }
            } catch (AdminException.EmptyStringException e) {
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
                if (email.length()>255)
                    throw new AdminException.LengthException(1, 255);
                String regexPattern = "/^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+)*$/";
                //check email pattern
                if (AdminException.EmailValidation.patternMatches(email, regexPattern)) {
                    error = true;
//                        msg.setEmailError(EmailValidation.getMessage("Email isn't valiable! ", "email@fpt.edu.vn"));
                    msg.setEmailError(AdminException.EmailValidation.getMessage("Không hợp lệ! ", "Thử theo mẫu: email@fpt.edu.vn"));
                }
                String checkEmail = edao.getEmployeeIsDeleteByEmail(email);
                //check if email is duplicate
                if (checkEmail != null) {
//                    if (checkEmail.equals("1")) {
                    throw new AdminException.DuplicateException();
//                    } else {
//                        throw new AdminException.RedirecUrlException();
//                    }
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setEmailError(e.getMessage());
            } catch (AdminException.DuplicateException e) {
                error = true;
                msg.setEmailError(e.getMessage());
            } catch (AdminException.LengthException e){
                error = true;
                msg.setEmailError(e.getMessage());
            } catch (Exception e) {
                error = true;
                msg.setEmailError(e.toString());
            }
            try {
                Random random = new Random();
                password = "" + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                        + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                        + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                        + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                        + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                        + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
                sendPassword = password;
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
                if (name.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setNameError(e.getMessage());
            }catch (AdminException.LengthException e){
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
//                Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
                Date date = (Date) new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
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
                if (address.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setAddressError(e.getMessage());
            }catch (AdminException.LengthException e) {
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
                if (workplace.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException e) {
                error = true;
                msg.setWorkplaceError(e.getMessage());
            }catch (AdminException.LengthException e) {
                error = true;
                msg.setWorkplaceError(e.getMessage());
            }
            provinceId = request.getParameter("provinceId");
            request.setAttribute("provinceId", provinceId);
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
            }catch (AdminException.LengthException e) {
                error = true;
                msg.setEthnicError(e.getMessage());
            }
            roleId = request.getParameter("roleId");
            request.setAttribute("roleId", roleId);
            createAt ="";
            createBy = checkEmp.getId();
            //------
            //file handle : 
//            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads"; // Đường dẫn lưu trữ tệp
//            File uploadDir = new File(uploadPath);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdir();
//            }
//
//            Part filePart = request.getPart("file");
//            String fileName = getFileName(filePart); // Lấy tên tệp
//            System.out.println("Path " + fileName.isEmpty());
//            String imageFileName = null;
//            if (!fileName.isEmpty()) {
//                // Lưu tệp lên máy chủ
//                System.out.println("Upload " + uploadPath);
//                filePart.write(uploadPath + File.separator + fileName);
//
//                response.getWriter().println("File uploaded successfully.");
//                imageFileName = getFileName(filePart);
//                System.out.println("File name : " + imageFileName);
//            }
            //-----
            EmployeeRoleDAO erdao = new EmployeeRoleDAO();
            BranchDAO bdao = new BranchDAO();
            ProvinceDAO pdao = new ProvinceDAO();
            request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
            request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
            request.setAttribute("ALL_EMPLOYEEROLE", erdao.getAllEmployeeRole());
            Branch branch = new Branch(branchId, "", "", "");
            Province province = new Province(provinceId, "");
            EmployeeRole employeeRole = new EmployeeRole(roleId, "");
            Employee emp = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole, createAt, createBy, modifyAt, modifyBy);
            request.setAttribute("ADD_EMPLOYEE", emp);
            if (error) {
                request.setAttribute("MESSAGE", "Không đăng kí được!");
                request.setAttribute("REGISTER_ERROR", msg);
                request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
            } else {
                EmployeeDAO eDao = new EmployeeDAO();
                //return massage
                if (eDao.addEmployee(emp)) {
                    AdminEmailContext.sendEmailnewPassword(email, sendPassword, name);
                    request.setAttribute("MESSAGE", "Đăng kí thành công!");
                    request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Đăng kí không thành công!");
                    request.getRequestDispatcher(REGISTER_EMPLOYEE).forward(request, response);
                }
            }
        } catch (AdminException.RedirecUrlException e) {
            request.setAttribute("edit_employee", true);
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
            String createAt = "";
            String createBy = null;
            String modifyAt = null;
            String modifyBy = null;
            EmployeeDAO eDao = new EmployeeDAO();
            boolean error = false;
            RegisterError msg = new RegisterError();
            id = request.getParameter("id");
            email = request.getParameter("email");
            String checkId = eDao.getEmployeeIdByEmail(email);
            //check if user change email
            if (checkId != null && !checkId.equals(id)) {
                try {
                    //check empty email
                    if (email.trim().isEmpty()) {
                        throw new AdminException.EmptyStringException();
                    }
                    //example@abc
                    String regexPattern = "/^([a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+)*$/";
                    //check pattern
                    if (EmailValidation.patternMatches(email, regexPattern)) {
                        error = true;
                        msg.setEmailError(EmailValidation.getMessage("Không hợp lệ! ", "Thử theo mẫu: email@fpt.edu.vn"));
                    }
                    if (email.length()>255){
                        throw new AdminException.LengthException(1, 255);
                    }
                    Models.Employee emp = edao.getEmployeeByEmail(email);
                    //check duplicate
                    if (emp != null) {
                        throw new AdminException.DuplicateException();
                    }
                } catch (AdminException.EmptyStringException ex) {
                    error = true;
                    msg.setEmailError(ex.getMessage());
                } catch (AdminException.DuplicateException ex) {
                    error = true;
                    msg.setEmailError(ex.getMessage());
                }catch (AdminException.LengthException ex) {
                    error = true;
                    msg.setEmailError(ex.getMessage());
                }
            }
            password = request.getParameter("password");
            branchId = request.getParameter("branchId");
            try {
                name = request.getParameter("name");
                if (name.isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (name.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setNameError(ex.getMessage());
            }catch (AdminException.LengthException ex) {
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
            try {
                workplace = request.getParameter("workplace");
                if (workplace.trim().isEmpty()) {
                    throw new AdminException.EmptyStringException();
                }
                if (workplace.length()>255){
                    throw new AdminException.LengthException(1, 255);
                }
            } catch (AdminException.EmptyStringException ex) {
                error = true;
                msg.setWorkplaceError(ex.getMessage());
            } catch (AdminException.LengthException ex) {
                error = true;
                msg.setWorkplaceError(ex.getMessage());
            }
            provinceId = request.getParameter("provinceId");
            try {
                phone = request.getParameter("phone");
                String pattern = "^\\d{10}$";
                //check if phone is number
                if (!phone.matches(pattern)) {
                    throw new NumberFormatException();
                }
                //check length of phone
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
                request.setAttribute("ethnic", ethnic);
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
            roleId = request.getParameter("roleId");
            request.setAttribute("roleId", roleId);
            modifyBy = checkEmp.getId();
            EmployeeRoleDAO erdao = new EmployeeRoleDAO();
            BranchDAO bdao = new BranchDAO();
            ProvinceDAO pdao = new ProvinceDAO();
            request.setAttribute("ALL_BRANCH", bdao.getAllBranches());
            request.setAttribute("ALL_PROVINCE", pdao.getAllProvinceId());
            request.setAttribute("ALL_EMPLOYEEROLE", erdao.getAllEmployeeRole());
            Branch branch = new Branch(branchId, "", "", "");
            Province province = new Province(provinceId, "");
            EmployeeRole employeeRole = new EmployeeRole(roleId, "");
            Employee emp = new Employee(id, email, password, branch, name, birthDate, gender, address, workplace, province, phone, ethnic, employeeRole, createAt, createBy, modifyAt, modifyBy);
            request.setAttribute("EDIT_EMPLOYEE", emp);
            if (error) {
                request.setAttribute("MESSAGE", "Không sửa được!");
                request.setAttribute("EDIT_ERROR", msg);
                request.getRequestDispatcher(REGISTER_EMPLOYEE + "?id=" + id).forward(request, response);
            } else {
                //return massage
                if (eDao.setEmployeeById(emp)) {
                    request.setAttribute("MESSAGE", "Sửa thành công!");
                    request.getRequestDispatcher(REGISTER_EMPLOYEE + "?id=" + id).forward(request, response);
                } else {
                    request.setAttribute("MESSAGE", "Sửa không thành công!");
                    request.getRequestDispatcher(REGISTER_EMPLOYEE + "?id=" + id).forward(request, response);
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
    
    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }
}
