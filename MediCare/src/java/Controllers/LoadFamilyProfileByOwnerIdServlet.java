/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.FamilyProfileDAO;
import DAL.UserDAO;
import Models.FamilyProfile;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author phuon
 */
public class LoadFamilyProfileByOwnerIdServlet extends HttpServlet {

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
        FamilyProfileDAO fpDAO = new FamilyProfileDAO();
        HttpSession session = request.getSession();
        UserDAO uDAO = new UserDAO();

        String id;
        String method = request.getParameter("method");
        switch (method) {
            case "profile":
                String ownerId = uDAO.getIdByEmail((String) session.getAttribute("email"));
                if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
                    id = String.valueOf(1);
                } else {
                    id = String.valueOf(request.getParameter("id"));
                }

                try ( PrintWriter out = response.getWriter()) {
                    out.println(generateProfileHtml(fpDAO.getFamilyProfileById(id, ownerId)));
                }
                break;
            default:
                throw new AssertionError();
        }

    }

    private String generateProfileHtml(FamilyProfile fp) {
        String html = "<div class=\"profile-display-header\">\n"
                + "                        <div class=\"profile-display-pics \">\n"
                + "                            <img src=\"https://www.svgrepo.com/show/497407/profile-circle.svg\" width=\"50px\" height=\"50px\" alt=\"client-img\"/> \n"
                + "                        </div>\n"
                + "                        <div class=\"profile-display-info\">\n"
                + "                            <h2>" + (fp.getName() != null ? fp.getName() : "--") + "</h2>\n"
                + "                            <span>Patient Code: " + fp.getProfileId() + "</span>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    <div class=\"profile-display-body\">\n"
                + "                        <h3>Thông tin cơ bản</h3>\n"
                + "                        <div class=\"profile-display-basic\">\n"
                + "                            <span>Họ và tên:</span><span>" + (fp.getName() != null ? fp.getName() : "--") + "</span>\n"
                + "                            <span>Điện thoại:</span><span>" + (fp.getPhone() != null ? fp.getPhone() : "--") + "</span>\n"
                + "                            <span>Ngày sinh:</span><span>" + (fp.getBirthDate() != null ? fp.getBirthDate() : "--") + "</span>\n"
                + "                            <span>Giới tính:</span><span>" + (fp.getGender() != null ? fp.getGender() : "--") + "</span>\n"
                + "                            <span>Địa chỉ:</span><span>" + (fp.getAddress() != null ? fp.getAddress() : "--") + "</span>\n"
                + "                        </div>\n"
                + "                        <h3>Thông tin bổ sung</h3>\n"
                + "                        <div class=\"profile-display-addition\">\n"
                + "                            <span>Mã BHYT:</span><span>" + (fp.getMedicalId() != null ? fp.getMedicalId() : "--") + "</span>\n"
                + "                            <span>Số CMND/CCCD:</span><span>" + (fp.getIdentity() != null ? fp.getIdentity() : "--") + "</span>\n"
                + "                            <span>Dân tộc:</span><span>" + (fp.getEthnic() != null ? fp.getEthnic() : "--") + "</span>\n"
                + "                            <span>Email:</span><span>" + (fp.getEmail() != null ? fp.getEmail() : "--") + "</span>\n"
                + "                        </div>\n"
                + (fp.getRelationship().getId().equals("0")? "<div></div>":"<button id=\"deleteProfileButton\" onclick=\"openDeleteProfileForm()\">Delete Profile</button>\n")
                + "                        <button id=\"editProfileButton\" onclick=\"openEditProfileForm()\">Edit Profile</button>"
                + "                    </div>";
        return html;
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
