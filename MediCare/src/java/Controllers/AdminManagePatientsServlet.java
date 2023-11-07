/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.BranchDAO;
import DAL.DoctorDAO;
import DAL.FamilyProfileDAO;
import DAL.ServiceTagDAO;
import Models.Appointments;
import Models.FamilyProfile;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author tubinh
 */
@WebServlet(name = "AdminManagePatientsServlet", urlPatterns = {"/admin-manage-patients"})
public class AdminManagePatientsServlet extends HttpServlet {

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
            out.println("<title>Servlet AdminManagePatientsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminManagePatientsServlet at " + request.getContextPath() + "</h1>");
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
        FamilyProfileDAO fpd = new FamilyProfileDAO();
        ArrayList<FamilyProfile> patients = fpd.getAllPatients();
        request.setAttribute("patients", patients);
        request.getRequestDispatcher("admin-patients/admin-manage-patients.jsp").forward(request, response);
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
        String patientId = request.getParameter("patientId");
        String action = request.getParameter("action");
        FamilyProfileDAO fpd = new FamilyProfileDAO();
        FamilyProfile p = new FamilyProfile();
        if (patientId != null) {
            p = fpd.getPatientInfoById(patientId);
            System.out.println("doPost - Patient get by profileId: " + p);
        }

        switch (action) {
            case "edit-patient": {
                try ( PrintWriter out = response.getWriter()) {
                    out.println("<div class=\"booking-container\">\n"
                            + "<div class=\"content\">\n"
                            + "                <div class=\"row\">\n"
                            + "                    <div class=\"col-sm-7 col-6\">\n"
                            + "                        <h4 class=\"page-title\">Hồ sơ bệnh nhân</h4>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "                <div class=\"card-box profile-header\">\n"
                            + "                    <div class=\"row\">\n"
                            + "                        <div class=\"col-md-12\">\n"
                            + "                            <div class=\"profile-view\">\n"
                            + "                                <div class=\"profile-img-wrap\">\n"
                            + "                                    <div class=\"profile-img\">\n"
                            + "                                        <img class=\"avatar\" src=\"/MediCare/assets/admin/imgages/user.jpg\" alt=\"\">"
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                                <div class=\"profile-basic\">\n"
                            + "                                    <div class=\"row\">\n"
                            + "                                        <div class=\"col-md-5\">\n"
                            + "                                            <div class=\"profile-info-left\">\n"
                            + "                                                <h3 class=\"user-name m-t-0 mb-0\">" + p.getName() + "</h3>\n"
                            //                                                        + "                                                <small class=\"text-muted\">"+"Khám tại chi nhánh: "+p.getBranch().getId()+"-"+p.getBranch().getName()+"</small>\n"
                            + "                                                <div class=\"staff-id\">ID : " + p.getProfileId() + "</div>\n"
                            + "                                                <div class=\"staff-msg\"></div>\n"
                            + "                                            </div>\n"
                            + "                                        </div>\n"
                            + "                                        <div class=\"col-md-7\">\n"
                            + "                                            <ul class=\"personal-info\">\n"
                            + "                                                <li>\n"
                            + "                                                    <span class=\"title\">Số điện thoại:</span>\n"
                            + "                                                    <span class=\"text\"><a href=\"#\">" + p.getPhone() + "</a></span>\n"
                            + "                                                </li>\n"
                            + "                                                <li>\n"
                            + "                                                    <span class=\"title\">Email:</span>\n"
                            + "                                                    <span class=\"text\"><a href=\"#\">" + p.getEmail() + "</a></span>\n"
                            + "                                                </li>\n"
                            + "                                                <li>\n"
                            + "                                                    <span class=\"title\">Ngày sinh:</span>\n"
                            + "                                                    <span class=\"text\">" + p.getBirthDate() + "</span>\n"
                            + "                                                </li>\n"
                            + "                                                <li>\n"
                            + "                                                    <span class=\"title\">Địa chỉ:</span>\n");
                    if (p.getAddress() == null) {
                        out.println("                                                    <span class=\"text\">" + "-" + "</span>\n");

                    } else {
                        out.println("                                                    <span class=\"text\">" + p.getAddress() + "</span>\n");
                    }
                    out.println("                                                </li>\n"
                            + "                                                <li>\n"
                            + "                                                    <span class=\"title\">Giới tính:</span>\n"
                            + "                                                    <span class=\"text\">" + p.getGender() + "</span>\n"
                            + "                                                </li>\n"
                            + "                                                <li>\n"
                            + "                                                    <span class=\"title\">Dân tộc:</span>\n"
                            + "                                                    <span class=\"text\">" + (p.getEthnic() == null ? "-" : p.getEthnic()) + "</span>\n"
                            + "                                                </li>\n"
                            + "                                                <li>\n"
                            + "                                                    <span class=\"title\">Số BHYT:</span>\n"
                            + "                                                    <span class=\"text\">" + (p.getMedicalId() == null ? "-" : p.getMedicalId()) + "</span>\n"
                            + "                                                </li>\n"
                            + "                                            </ul>\n"
                            + "                                        </div>\n"
                            + "                                    </div>\n"
                            + "                                </div>\n"
                            + "                            </div>                        \n"
                            + "                        </div>\n"
                            + "                    </div>\n"
                            + "                </div>\n"
                            + "				<div class=\"profile-tabs\">\n"
                            + "					<ul class=\"nav nav-tabs nav-tabs-bottom\">\n"
                            + "						<li class=\"nav-item\"><a class=\"nav-link active\" href=\"#about-cont\" data-toggle=\"tab\">Các lần khám</a></li>\n"
                            //                            + "						<li class=\"nav-item\"><a class=\"nav-link\" href=\"#bottom-tab2\" data-toggle=\"tab\">Profile</a></li>\n"
                            //                            + "						<li class=\"nav-item\"><a class=\"nav-link\" href=\"#bottom-tab3\" data-toggle=\"tab\">Messages</a></li>\n"
                            + "					</ul>\n"
                            + "\n"
                            + "					<div class=\"tab-content\">\n"
                            + "						<div class=\"tab-pane show active\" id=\"about-cont\">\n"
                    );
                    for (Appointments a : p.getAppointments()) {
                        out.println(
                                "                <div class=\"row\">\n"
                                + "                    <div class=\"col-md-12\">\n"
                                + "                        <div class=\"card-box\">\n"
                                + "                            <h3 class=\"card-title\">Khám vào " + a.getPlannedAt() + "</h3>\n"
                                + "                            <div class=\"experience-box\">\n"
                                + "                                            <ul class=\"personal-info\">\n"
                                + "                                                <li>\n"
                                + "                                                    <span class=\"title\">Khám tại chi nhánh:</span>\n"
                                + "                                                    <span class=\"text\">" + new BranchDAO().getBranchByBranchId(a.getBranch().getId()).getName()+ "</span>\n"
                                + "                                                </li>\n"
                                + "                                                <li>\n"
                                + "                                                    <span class=\"title\">Bác sĩ khám:</span>\n"
                                + "                                                    <span class=\"text\">" + new DoctorDAO().getDoctorByDoctorId(a.getDoctor().getId()).getDisplayName() + "</span>\n"
                                + "                                                </li>\n"
                                + "                                                <li>\n"
                                + "                                                    <span class=\"title\">Chuyên khoa khám:</span>\n"
                                + "                                                    <span class=\"text\">" + new ServiceTagDAO().getServiceTagByServiceTagId(a.getServiceTag().getId()).getNametag()+ "</span>\n"
                                + "                                                </li>\n"
                                + "                                                <li>\n"
                                + "                                                    <span class=\"title\">Triệu chứng bệnh nhân khai báo:</span>\n"
                                + "                                                    <span class=\"text\">" + a.getSymptoms() + "</span>\n"
                                + "                                                </li>\n"
                                + "                                                <li>\n"
                                + "                                                    <span class=\"title\">Bệnh án (file pdf):</span>\n"
                                + "                                                    <span class=\"text\">" +"Sẽ thêm sau"+ "</span>\n"
                                + "                                                </li>\n"
                                + "                                </ul>\n"
                                + "                            </div>\n"
                                + "                        </div>\n"
                                + "                        </div>\n"
                                + "                </div>\n"
                                + "                    </div>\n");
                    }

                    out.println(
                            //                            + "						</div>\n"
                            //                            + "						<div class=\"tab-pane\" id=\"bottom-tab2\">\n"
                            //                            + "							Tab content 2\n"
                            //                            + "						</div>\n"
                            //                            + "						<div class=\"tab-pane\" id=\"bottom-tab3\">\n"
                            //                            + "							Tab content 3\n"
                            //                            + "						</div>\n"
                            "					</div>\n"
                            + "				</div>\n"
                            + "            </div>"
                            + "            </div>");
                    break;
                }
            }
            case "save": {

                break;
            }
            default:
                throw new AssertionError();
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
