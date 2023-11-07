/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.AdminEmailContext;
import DAL.EmployeeDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author DELL
 */
@WebServlet(name="AdminForgotPassword", urlPatterns={"/admin-forgot-password"})
public class AdminForgotPassword extends HttpServlet {
    private final String LOGIN_PAGE = "admin-screen/admin-login.jsp";
   
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
        EmployeeDAO eDao = new EmployeeDAO();
        Random generator = new Random();
        String email = request.getParameter("email").trim();
        if (email==null){
            request.setAttribute("MESSAGE","Nhập email để sử dụng quên mật khẩu");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
        String checkId = eDao.getEmployeeIdByEmail(email);
        if (checkId==null){
            request.setAttribute("MESSAGE","Tài khoản không tồn tại");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }else{
        String password = ""+generator.nextInt(9)+generator.nextInt(9)+generator.nextInt(9)+generator.nextInt(9)+generator.nextInt(9)+generator.nextInt(9);
        byte[] salt = PasswordEncryption.generateSalt();
        String encryptedPassword = PasswordEncryption.encryptPassword(password, salt);
        if(eDao.setEmployeePasswordById(checkId,encryptedPassword)){
            request.setAttribute("MESSAGE","Thành công! Hãy kiểm tra email để lấy mật khẩu mới");
            AdminEmailContext.sendEmailForgotPassword(email,password);
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }else{
            request.setAttribute("MESSAGE","Thất bại! Có vẻ có lỗi gì đó xảy ra! Hãy liên hệ với bộ phận hỗ trợ");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }
        }
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
