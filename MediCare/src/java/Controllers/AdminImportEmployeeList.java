/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.BranchDAO;
import DAL.EmployeeDAO;
import DAL.EmployeeRoleDAO;
import DAL.ProvinceDAO;
import Models.Employee;
import Models.EmployeeRole;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author DELL
 */
@WebServlet(name = "AdminImportEmployeeList", urlPatterns = {"/admin-import-employee-list"})
public class AdminImportEmployeeList extends HttpServlet {

    private final String STATISTIC_EMPLOYEE = "admin-list-employee";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
    private final String NEED_LOGIN = "Bạn cần đăng nhập truy cập vào trang này";
    private final String NEED_ROLE = "Bạn cần có quyền để truy cập vào trang này";
    private final String[] ROLE = {"1", "2", "3"};
    private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

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
            out.println("<title>Servlet AdminImportEmployeeList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminImportEmployeeList at " + request.getContextPath() + "</h1>");
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
                BranchDAO bdao = new BranchDAO();
                ProvinceDAO pdao = new ProvinceDAO();
                EmployeeRoleDAO erdao = new EmployeeRoleDAO();
                String id = "";
                String password;
                String sendPassword;
                String branchId;
                String provinceId;
                String role;
                try {
                    String excelFilePath = request.getParameter("link");
                    String[] split = excelFilePath.split("/");
                    List<Employee> list = ReadExcel.readExcel(excelFilePath);
                    String error = "lỗi ở hàng thông tin thứ";
                    String isError = error;
                    String errorNull = "thông tin trống ở nhân viên id";
                    String isErrorNull = errorNull;
                    for (int i = 0; i < list.size(); i++) {
                        Employee emp = list.get(i);
                        try {
                            id = edao.generateId();
                            //check if input is empty
                            if (id.trim().isEmpty()) {
                                id = "1";
                            }
                            //generate id if duplicate
                            while (edao.getEmployeeById(id) != null) {
                                id = Integer.parseInt(id) + 1 + "";
                            }
                        } catch (NumberFormatException e2) {
                        }
                        emp.setId(id);
                        if (emp.getEmail() == null || emp.getEmail().isEmpty()) {
                            errorNull += " " + id;
                        }
                        java.util.Random random = new java.util.Random();
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
                        emp.setPassword(password);
                        branchId = bdao.getBranchId(emp.getBranch().getName());
                        emp.getBranch().setId(id);
                        if (emp.getBranch().getId() == null || emp.getBranch().getId().isEmpty()) {
                            errorNull += " " + id;
                        }
                        if (emp.getName() == null || emp.getName().isEmpty()) {
                            errorNull += " " + id;
                        }
                        if (emp.getBirthDate() == null || emp.getBirthDate().isEmpty()) {
                            errorNull += " " + id;
                        }
                        if (emp.getGender() == null || emp.getGender().isEmpty()) {
                            errorNull += " " + id;
                        } else if (emp.getGender().equalsIgnoreCase("Nữ") || emp.getGender().equals("1")) {
                            emp.setGender("1");
                        } else if (emp.getGender().equalsIgnoreCase("Nam") || emp.getGender().equals("0")) {
                            emp.setGender("0");
                        } else {
                            emp.setGender("");
                            error += " " + id;
                        }
                        if (emp.getAddress() == null || emp.getAddress().isEmpty()) {
                            errorNull += " " + id;
                        }
                        if (emp.getWorkplace() == null || emp.getWorkplace().isEmpty()) {
                            errorNull += " " + id;
                        }
                        provinceId = pdao.getProvinceId(emp.getProvince().getName());
                    System.out.println(emp.getProvince().getName()+"->"+provinceId);
                        emp.getProvince().setId(provinceId);
                        if (emp.getProvince().getId() == null || emp.getProvince().getId().isEmpty()) {
                            errorNull =errorNull+ " " + id;
                        }
                        if (emp.getPhone() == null || emp.getPhone().isEmpty()) {
                            errorNull += " " + id;
                        }
                        if (emp.getEthnic() == null || emp.getEthnic().isEmpty()) {
                            errorNull += " " + id;
                        }
                        role = erdao.getRoleIdByName(emp.getEmployeeRole().getRole());
                        emp.getEmployeeRole().setId(id);
                        if (emp.getEmployeeRole().getId() == null || emp.getEmployeeRole().getId().isEmpty()) {
                            errorNull += " " + id;
                        }
                        emp.setCreateBy(checkEmp.getId());
                        System.out.println(emp.toString());
                    if (!edao.addEmployee(emp)){
                        error+= " "+i;
                    }
                    }
                    if (!error.equals(isError)) {
                        request.setAttribute("IMPORT_EXCEL_ERROR", error);
                    }
                    if (!errorNull.equals(isErrorNull)) {
                        request.setAttribute("IMPORT_EXCEL_ERRORNULL", errorNull);
                    }
                    request.getRequestDispatcher(STATISTIC_EMPLOYEE).forward(request, response);
                } catch (IOException e2) {
                    request.setAttribute("IMPORT_EXCEL_ERROR", "Đường dẫn không hợp lệ");
                    request.getRequestDispatcher(STATISTIC_EMPLOYEE).forward(request, response);
                }
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
