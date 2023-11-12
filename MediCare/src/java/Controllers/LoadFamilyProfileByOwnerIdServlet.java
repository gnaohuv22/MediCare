/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AppointmentDAO;
import DAL.BillingHistoryDAO;
import DAL.FamilyProfileDAO;
import DAL.UserDAO;
import Models.Appointments;
import Models.BillingHistory;
import Models.FamilyProfile;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
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
     * @throws java.text.ParseException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        FamilyProfileDAO fpDAO = new FamilyProfileDAO();
        HttpSession session = request.getSession();
        UserDAO uDAO = new UserDAO();
        AppointmentDAO aDAO = new AppointmentDAO();
        BillingHistoryDAO bhDAO = new BillingHistoryDAO();
        
        String id;
        String method = request.getParameter("method");
        switch (method) {
            case "profile":
                String ownerId = uDAO.getUserIdByEmail((String) session.getAttribute("email"));
                if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
                    id = String.valueOf(1);
                } else {
                    id = String.valueOf(request.getParameter("id"));
                }
                FamilyProfile fp = fpDAO.getFamilyProfileById(id, ownerId);
                try ( PrintWriter out = response.getWriter()) {
                    out.println(generateProfileHtml(fp));
                }
                break;
            case "appointment":
                if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
                    id = String.valueOf(1);
                } else {
                    id = String.valueOf(request.getParameter("id"));
                }
                Appointments a = aDAO.getAppointmentById(id);
                try ( PrintWriter out = response.getWriter()) {
                    out.println(generateAppointmentHtml(a));
                }
                break;
            case "payment":
                if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
                    id = String.valueOf(1);
                } else {
                    id = String.valueOf(request.getParameter("id"));
                }
                BillingHistory bh = bhDAO.getListBillingHistoryById(id);
                try ( PrintWriter out = response.getWriter()) {
                    out.println(generateBillingHistoryHtml(bh));
                }
                break;
            default:
                throw new AssertionError();
        }
        
    }
    
    private String generateProfileHtml(FamilyProfile fp) {
        String html = "<div class=\"profile-display-header\">\n"
                + "                        <div class=\"profile-display-pics \">\n"
                + (fp.getProfilePicture() == null || fp.getProfilePicture().isEmpty() ? "<img src=\"https://www.svgrepo.com/show/497407/profile-circle.svg\" width=\"50px\" height=\"50px\" alt=\"client-img\"/> \n" : "<img class='profile-pics' src='" + fp.getProfilePicture() + "' alt='client-img'/>")
                + "                        </div>\n"
                + "                        <div class=\"profile-display-info\">\n"
                + "                            <h2>" + (fp.getName() != null ? fp.getName() : "--") + "</h2>\n"
                + "                            <span>Patient Code:</span><span id=\"display-id\">" + fp.getProfileId() + "</span>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    <div class=\"profile-display-body\">\n"
                + "                        <h3>Thông tin cơ bản</h3>\n"
                + "                        <div class=\"profile-display-basic\">\n"
                + "                            <span>Họ và tên:</span><span id=\"display-name\">" + (fp.getName() != null ? fp.getName() : "--") + "</span>\n"
                + "                            <span>Điện thoại:</span><span id=\"display-phone\">" + (fp.getPhone() != null ? fp.getPhone() : "--") + "</span>\n"
                + "                            <span>Ngày sinh:</span><span id=\"display-dob\">" + (fp.getBirthDate() != null ? fp.getBirthDate() : "--") + "</span>\n"
                + "                            <span>Giới tính:</span><span id=\"display-gender\">" + (fp.getGender() != null ? fp.getGender() : "--") + "</span>\n"
                + "                            <span>Địa chỉ:</span><span id=\"display-address\">" + (fp.getAddress() != null ? fp.getAddress() : "--") + "</span>\n"
                + "                        </div>\n"
                + "                        <h3>Thông tin bổ sung</h3>\n"
                + "                        <div class=\"profile-display-addition\">\n"
                + "                            <span>Mã BHYT:</span><span id=\"display-medical-id\">" + (fp.getMedicalId() != null ? fp.getMedicalId() : "--") + "</span>\n"
                + "                            <span>Số CMND/CCCD:</span><span id=\"display-identity\">" + (fp.getIdentity() != null ? fp.getIdentity() : "--") + "</span>\n"
                + "                            <span>Dân tộc:</span><span id=\"display-ethnic\">" + (fp.getEthnic() != null ? fp.getEthnic() : "--") + "</span>\n"
                + "                            <span>Email:</span><span id=\"display-email\">" + (fp.getEmail() != null ? fp.getEmail() : "--") + "</span>\n"
                + "                             <input type=\"hidden\" name=\"relation\" id=\"display-relation\" value=\"" + fp.getRelationship().getId() + "\">"
                + "                        </div>\n"
                + (fp.getRelationship().getId().equals("0") ? "<div></div>" : "<button id=\"deleteProfileButton\" onclick=\"openDeleteProfileForm()\">Delete Profile</button>\n")
                + "                        <button id=\"editProfileButton\" onclick=\"openEditProfileForm()\">Edit Profile</button>"
                + "                    </div>";
        return html;
    }
    
    private String generateAppointmentHtml(Appointments a) throws ParseException {
        String[] dt = a.getPlannedAt().split(" ");
        String date = dt[0];
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date as = formatter1.parse(date);
        
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String output = formatter2.format(as);
        
        String html = "<div class=\"profile-display-header\">\n"
                + "                        <div class=\"profile-display-pics \">\n"
                + (a.getDoctor().getProfilePicture() == null || a.getDoctor().getProfilePicture().isEmpty() ? "<img src=\"https://www.svgrepo.com/show/497407/profile-circle.svg\" width=\"50px\" height=\"50px\" alt=\"client-img\"/> \n" : "<img class='profile-pics' src='" + a.getDoctor().getProfilePicture() + "' alt='client-img'/>")
                + "                        </div>\n"
                + "                        <div class=\"profile-display-info\">\n"
                + "                            <h2>" + (a.getDoctor().getDisplayName() != null ? a.getDoctor().getDisplayName() : "--") + "</h2>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    <div class=\"profile-display-body\">\n"
                + "                        <h3>Thông tin đặt khám</h3>\n"
                + "                        <div class=\"profile-display-addition\">\n"
                + "                            <span>Mã phiếu khám:</span><span id=\"appointment-id\">" + a.getId() + "</span>\n"
                + "                            <span>Ngày khám:</span><span id=\"planned-date\">" + output + "</span>\n"
                + "                        </div>\n"
                + "                        <h3>Thông tin bệnh nhân</h3>\n"
                + "                        <div class=\"profile-display-basic\">\n"
                + "                            <span>Họ và tên:</span><span id=\"display-name\">" + (a.getFp().getName() != null ? a.getFp().getName() : "--") + "</span>\n"
                + "                            <span>Điện thoại:</span><span id=\"display-phone\">" + (a.getFp().getPhone() != null ? a.getFp().getPhone() : "--") + "</span>\n"
                + "                            <span>Ngày sinh:</span><span id=\"display-dob\">" + (a.getFp().getBirthDate() != null ? a.getFp().getBirthDate() : "--") + "</span>\n"
                + "                            <span>Giới tính:</span><span id=\"display-gender\">" + (a.getFp().getGender() != null ? a.getFp().getGender() : "--") + "</span>\n"
                + "                            <span>Địa chỉ:</span><span id=\"display-address\">" + (a.getFp().getAddress() != null ? a.getFp().getAddress() : "Chưa cập nhật") + "</span>\n"
                + "                        </div>\n"
                               + (a.getStatus().equals("0") ? "<button id=\"deleteProfileButton\" onclick=\"openCancelAppointmentForm()\">Hủy cuộc hẹn</button>\n" : "")
                + "                    </div>";
        return html;
    }
    
    private String generateBillingHistoryHtml(BillingHistory bh) throws ParseException {
        String[] dt = bh.getAppointment().getPlannedAt().split(" ");
        String planDate = dt[0];
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");
        Date as = formatter1.parse(planDate);
        
        SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
        String outputPlanDate = formatter2.format(as);
        
        String html = "<div class=\"profile-display-header\">\n"
                + "                        <div class=\"profile-display-pics \">\n"
                + (bh.getAppointment().getFp().getProfilePicture() == null || bh.getAppointment().getFp().getProfilePicture().isEmpty() ? "<img src=\"https://www.svgrepo.com/show/497407/profile-circle.svg\" width=\"50px\" height=\"50px\" alt=\"client-img\"/> \n" : "<img class='profile-pics' src='" + bh.getAppointment().getFp().getProfilePicture() + "' alt='client-img'/>")
                + "                        </div>\n"
                + "                        <div class=\"profile-display-info\">\n"
                + "                            <h2>" + (bh.getAppointment().getFp().getName() != null ? bh.getAppointment().getFp().getName() : "--") + "</h2>\n"
                + "                        </div>\n"
                + "                    </div>\n"
                + "                    <div class=\"profile-display-body\">\n"
                + "                        <h3>Thông tin thanh toán</h3>\n"
                + "                        <div class=\"profile-display-addition\">\n"
                + "                            <span>Mã thanh toán:</span><span id=\"payment-id\">" + bh.getId() + "</span>\n"
                + "                            <span>Ngày thanh toán:</span><span id=\"planned-date\">" + outputPlanDate + "</span>\n"
                + "                            <span>Tổng tiền:</span><span id=\"planned-date\">" + bh.getTotalCash() + "K</span>\n"
                + "                        </div>\n"
                + "                    <div class=\"profile-display-body\">\n"
                + "                        <h3>Thông tin cuộc hẹn</h3>\n"
                + "                        <div class=\"profile-display-addition\">\n"
                + "                            <span>Mã phiếu khám:</span><span id=\"appointment-id\">" + bh.getAppointment().getId() + "</span>\n"
                + "                            <span>Ngày khám:</span><span id=\"planned-date\">" + outputPlanDate + "</span>\n"
                + "                        </div>\n"
                + "                        <h3>Thông tin bệnh nhân</h3>\n"
                + "                        <div class=\"profile-display-basic\">\n"
                + "                            <span>Họ và tên:</span><span id=\"display-name\">" + (bh.getAppointment().getFp().getName() != null ? bh.getAppointment().getFp().getName() : "--") + "</span>\n"
                + "                            <span>Điện thoại:</span><span id=\"display-phone\">" + (bh.getAppointment().getFp().getPhone() != null ? bh.getAppointment().getFp().getPhone() : "--") + "</span>\n"
                + "                            <span>Email:</span><span id=\"display-email\">" + (bh.getAppointment().getFp().getEmail() != null ? bh.getAppointment().getFp().getEmail() : "Chưa cập nhật") + "</span>\n"
                + "                        </div>\n"
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(LoadFamilyProfileByOwnerIdServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(LoadFamilyProfileByOwnerIdServlet.class.getName()).log(Level.SEVERE, null, ex);
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
