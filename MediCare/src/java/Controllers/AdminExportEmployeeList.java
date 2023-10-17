/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.EmployeeDAO;
import Models.Employee;
import Models.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author DELL
 */
@WebServlet(name="AdminExportEmployeeList", urlPatterns={"/admin-export-employee-list"})
public class AdminExportEmployeeList extends HttpServlet {
   
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
            out.println("<title>Servlet AdminExportEmployeeList</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminExportEmployeeList at " + request.getContextPath () + "</h1>");
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
        EmployeeDAO eDao = new EmployeeDAO();
        ArrayList<Employee> empList = eDao.getAllEmployee();

        // Create a new Excel:
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("User Data");

        // Create the name of column:
        Row headerRow = sheet.createRow(0);
        String[] columns = {"Id", "Email", "Mật khẩu", "Branch", "Tên", "Ngày sinh", "Giới tính", "Địa chỉ", "Nơi làm việc", "Tỉnh", "Số điện thoại", "Dân tộc", "Quyền", "Ngày tạo"};

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        // Write the data from Table User to the Excel:
        int rowNum = 1;
        for (Employee emp : empList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(emp.getId());
            row.createCell(1).setCellValue(emp.getEmail());
            row.createCell(2).setCellValue(emp.getPassword());
            row.createCell(3).setCellValue(emp.getBranch().getName());
            row.createCell(4).setCellValue(emp.getName());
            row.createCell(5).setCellValue(emp.getBirthDate());
            if(emp.getGender().equals('0'))
                row.createCell(6).setCellValue("Nam");
            else
                row.createCell(6).setCellValue("Nữ");
            row.createCell(7).setCellValue(emp.getAddress());
            row.createCell(8).setCellValue(emp.getWorkplace());
            row.createCell(9).setCellValue(emp.getProvince().getName());
            row.createCell(10).setCellValue(emp.getEthnic());
            row.createCell(11).setCellValue(emp.getPhone());
            row.createCell(12).setCellValue(emp.getEmployeeRole().getRole());
            row.createCell(13).setCellValue(emp.getCreateAt());
        }

        // Initialize headers and file:
        response.setHeader("Content-Disposition", "attachment; filename=All_Employee.xlsx");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        // Ghi dữ liệu tệp Excel vào luồng đầu ra của Servlet
        workbook.write(response.getOutputStream());
        workbook.close();
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
        processRequest(request, response);
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
