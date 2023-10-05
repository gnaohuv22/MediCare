/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Teacher
 */
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class AdminDispatchController extends HttpServlet {
    private final String HOME = "admin-screen/admin-home.jsp";
    private final String LOGIN_EMPLOYEE = "admin-login-employee";
    private final String REGISTER_EMPLOYEE = "admin-register-employee";
    private final String GET_INFO_REGISTER_DOCTOR = "admin-get-id-name";
    private final String EDIT_EMPLOYEE = "admin-edit-employee";
    private final String NULL_CONTROLLER = "NullServlet";
    private final String NEED_LOGIN = "Bạn cần đăng nhập để truy cập vào nội dung này";
    private final String LOGIN_PAGE = "admin-screen/admin-login.jsp";
    private final String SAVE_EMPLOYEE_PROFILE = "admin-edit-profile";
    private final String SEARCH_EMPLOYEE = "admin-search-employee";
    private final String FORGOT_PASSWORD = "admin-send-email-forgot-password";
    
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
        String url = HOME;
        HttpSession session = request.getSession();
        Models.GetEmployee emp = (Models.GetEmployee)session.getAttribute("EMPLOYEE");
        
        //Which button did user click?
        String button = request.getParameter("btAction");
        
        try {
            if(button == null){
                url = NULL_CONTROLLER;
            }
            else if (button.equals("LoginEmployee")){
                url = LOGIN_EMPLOYEE;
            }
            else if (button.equals("Forgot password")){
                url = FORGOT_PASSWORD;
            }
                //check role
            else if (emp==null) {
                request.setAttribute("MESSAGE", NEED_LOGIN);
                url = LOGIN_PAGE;
            }
            else if (button.equals("Register Employee")){
                url = REGISTER_EMPLOYEE;
            }
            else if (button.equals("Choose Id Register Doctor")){
                url = GET_INFO_REGISTER_DOCTOR;
            }
            else if (button.equals("Edit Employee")){
                url = EDIT_EMPLOYEE;
            }
            else if (button.equals("Save employee profile")){
                url = SAVE_EMPLOYEE_PROFILE;
            }
            else if (button.equals("Search Employee")){
                url = SEARCH_EMPLOYEE;
            }
        }
        finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
