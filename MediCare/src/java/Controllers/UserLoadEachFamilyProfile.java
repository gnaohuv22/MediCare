/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.FamilyProfileDAO;
import Models.FamilyProfile;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
public class UserLoadEachFamilyProfile extends HttpServlet {

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
            out.println("<title>Servlet UserLoadEachFamilyProfile</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserLoadEachFamilyProfile at " + request.getContextPath() + "</h1>");
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
        String profileId = request.getParameter("profileId");
        String ownerId = request.getParameter("ownerId");
        System.out.println("ProfileId = " + profileId);

        FamilyProfileDAO fpd = new FamilyProfileDAO();
        FamilyProfile profile = fpd.getFamilyProfileByProfileId(profileId);

        ArrayList<FamilyProfile> profiles = (ArrayList<FamilyProfile>) fpd.getFamilyProfileListByUserOwnerIdForBooking(ownerId);

        System.out.println("Ajax - doPOST - Load Family Profile:");
        response.setContentType("text/html;charset=UTF-8");

        try ( PrintWriter out = response.getWriter()) {
            out.println(
                    "                        <!--FOR USER: If logged in, they can choose profile for booking fast: start-->\n"
                    + "                        <h2>Chọn khách hàng</h2>\n"
                    + "                        <div id=\"family-profiles-container\" class=\"family-profiles-container\">\n");
            for (FamilyProfile p : profiles) {
                out.println(
                        "                                <div class=\"each-family-profile" + (p.getProfileId().equals(profileId) ? " each-family-profile-active" : "") + "\" data-profile-id=\"" + p.getProfileId() + "\" data-ownerID =\"" + ownerId + "\" onclick=\"handleProfileClick(this)\">\n"
                        + "                                    <img src=\"./assets/client/images/human.png\" alt=\"alt\"/>\n"
                        + "                                    <span class=\"relation-each-family-profile\">" + p.getRelationship().getRelation() + "</span>\n"
                        + "                                    <span class=\"name-each-family-profile\">" + p.getName() + "</span>\n"
                        + "                                </div>\n");

            }
            out.println("<div id=\"add-family-profile\" class=\"add-family-profile\" data-ownerID =\"${requestScope.ownerId}\" onclick=\"openAddProfileForm()\">\n"
                    + "                                <img src=\"./assets/client/images/add-icon.png\" alt=\"alt\"/>\n"
                    + "                            </div>");
            out.println("                        </div>\n"
                    + "                        <!--FOR USER: If logged in, they can choose profile for booking fast: end-->\n");
            out.println("<h2 class=\"booking-header\">Bước 1/3 - Nhập thông tin người khám</h2>\n"
                    + "                    <div class=\"row booking-info-input\">\n"
                    + "                        <!-- Cột 1 -->\n"
                    + "                        <div class=\"col-md-6\">\n"
                    + "                            <div> \n"
                    + "                                <input type=\"text\" name=\"patientName\" id=\"patientName\" placeholder=\"Họ và tên (*)\" required value=\"" + profile.getName() + "\"/> \n"
                    + "                                <input type=\"radio\" name=\"gender\" checked value=\"1\"> Nam &nbsp;\n");
            if (profile.getGender().equals("Female")) {
                out.println("<input type=\"radio\" checked name=\"gender\" value=\"0\"> Nữ<br>\n");
            } else {
                out.println("<input type=\"radio\" name=\"gender\" value=\"0\"> Nữ<br>\n");
            }
            out.println(
                    "                                <p style=\"color: red;\" id=\"inputNameError\" class=\"error-message-input-step-2\"></p>\n"
                    + "\n"
                    + "                                <input type=\"date\" id=\"birthDate\" name=\"birthDate\" placeholder=\"Ngày sinh (*)\" value=\"" + profile.getBirthDate() + "\" max=\"2023-10-08\" required> (Birth date - Thông tin bắt buộc)<br>\n"
                    + "                                <p style=\"color: red;\" id=\"inputBirthDateError\" class=\"error-message-input-step-2\"></p>\n"
                    + "\n"
                    + "                                <input type=\"tel\" id=\"phone\" name=\"phone\" placeholder=\"Eg: 0123456789 (*)\" pattern=\"[0-9]{10}\" required value=\"" + profile.getPhone() + "\" maxlength=\"10\"><br>\n"
                    + "                                <p style=\"color: red;\" id=\"inputPhoneError\" class=\"error-message-input-step-2\"></p> \n"
                    + "\n"
                    + "                                <input type=\"text\" id=\"email\" value=\"" + profile.getEmail() + "\" name=\"email\" maxlength=\"32\" placeholder=\"Vui lòng nhập đúng email để xác nhận thông tin lịch hẹn (*)\" required>\n"
                    + "                                <p style=\"color: red;\" id=\"inputEmailError\" class=\"error-message-input-step-2\"></p> \n"
                    + "                                <input onclick=\"getCheckboxValue()\" style=\" margin:10px;\" type=\"checkbox\" id=\"confirm-edit-profile\" name=\"confirm-edit-profile\" value=\"edittrue\"> "
                    + "<span class=\"confirm-edit-profile-text\" onclick=\"toggleCheckbox()\">Chỉnh sửa thông tin trong profile như thông tin trên</span>\n"
                    + "</div>\n"
                    + "                        </div>\n"
                    + "\n"
                    + "                        <!-- Cột 2 -->\n"
                    + "                        <div class=\"col-md-6\">\n"
                    + "                            <div>\n"
                    + "                                <textarea id=\"description\" name=\"description\" rows=\"4\" cols=\"60\" placeholder=\"Vui lòng mô tả rõ triệu chứng của bạn và nhu cầu thăm khám (*)\" required></textarea>\n"
                    + "                                <p style=\"color: red;\" id=\"inputSymptomsError\" class=\"error-message-input-step-2\"></p>\n"
                    + "                            </div>\n"
                    + "                        </div>\n"
                    + "                    </div>\n"
                    + "\n"
                    + "                    <div class=\"col-md-12 mt-3 text-center btn-booking\">\n"
                    + "                        <div id=\"step-1-to-2\" class=\"btn btn-primary btn-booking-hover\" onclick=\"handleSubmit(this)\">Tiếp tục</div>\n"
                    + "                    </div>");
        }
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
