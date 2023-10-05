/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AcademicRankDAO;
import DAL.BranchDAO;
import DAL.CertificateDAO;
import DAL.CertificateDoctorDAO;
import DAL.CurriculumVitaeDAO;
import DAL.DoctorDAO;
import Models.AcademicRank;
import Models.Branch;
import Models.Certificate;
import Models.CertificateDoctor;
import Models.CurriculumVitae;
import Models.Doctor;
import Models.GetEmployee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Session;

/*@WebServlet("/admin-editdoctor")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
/**
/**
 *
 * @author Asus
 */
public class AdminEditDoctorController extends HttpServlet {
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
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
            out.println("<title>Servlet AdminEditDoctorController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminEditDoctorController at " + request.getContextPath() + "</h1>");
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
        
        BranchDAO BranchDAO = new BranchDAO();
        DoctorDAO dao = new DoctorDAO();
        AcademicRankDAO ARDAO = new AcademicRankDAO();
        List<AcademicRank> listAR = ARDAO.getListAcademicRank();
        List<Branch> listBranch = BranchDAO.getAllBranches();
        CertificateDAO CertDAO = new CertificateDAO();
        String id = request.getParameter("id");
        CertificateDoctorDAO cdDao = new CertificateDoctorDAO();
        List<Certificate> listCert = CertDAO.getListCertificate();
        List<CertificateDoctor> listCertofDoc = cdDao.getCertificateByDoctorId(id);
        Doctor doc = dao.getDoctorById(id);
//        for (CertificateDoctor c : listCertofDoc) {
//            System.out.println(c);
//        }
//        System.out.println("listBranch" + listBranch.size());
//        System.out.println("listBranch" + listAR.size());
//        System.out.println("doctor: " + doc);
        request.setAttribute("doc", doc);
        request.setAttribute("listCert", listCert);
        request.setAttribute("listCertofDoc", listCertofDoc);
        request.setAttribute("listBranch", listBranch);
        request.setAttribute("listAR", listAR);
        request.getRequestDispatcher("admin-doctors/admin-edit-doctor.jsp").forward(request, response);

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
        Models.GetEmployee checkEmp = (Models.GetEmployee)session.getAttribute("EMPLOYEE");
        if (checkEmp==null){
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String displayName = request.getParameter("displayName");
        String academicRank = request.getParameter("academicRank");
        String branch = request.getParameter("branch");
        String phone = request.getParameter("phone");
        String workplace = null;
        String status = request.getParameter("status");
        String salary = request.getParameter("salary");
        CertificateDoctorDAO cdDao = new CertificateDoctorDAO();
        List<CertificateDoctor> ClearListCd = cdDao.getCertificateByDoctorId(id);
        String[] certificates = request.getParameterValues("certificates");
        boolean bool = true;
        if (certificates == null || certificates.length == 0) {
            request.setAttribute("certificateError", "You must choose at least one Certificate");
            bool = false;
        }

        /*
         Part filePart = request.getPart("avatarUpload");
        String fileName = extractFileName(filePart);
        //change the url here where to store image 
        String savePart = "E:\\Learn\\Semester_5\\swp391\\medicalserviceproject\\MediCare\\web\\image" + File.separator + fileName;
        File saveFile = new File(savePart);
        filePart.write(savePart + File.separator);
        System.out.println("Academic Rank : " + academicRank);
        System.out.println("Branch ID " + branch);
         */
        DoctorDAO doc = new DoctorDAO();
        Doctor docError = new Doctor();
        if (!validateDisplayName(displayName.trim())) {
            request.setAttribute("displayNameError", "Display name should be contains letters and space only");
            bool = false;
        } else {

            docError.setDisplayName(displayName);
        }
        Doctor docCheck = doc.getDoctorByEmail(id);
        List<Doctor> list = doc.getAllDoctors();
        List<String> listEmail = new ArrayList<>();
        System.out.println(docCheck);
        if (docCheck != null) {
            for (Doctor doctor : list) {
                if (!id.equals(doctor.getId())) {
                    listEmail.add(doctor.getEmail());
                }
            }
        }
        if (!validateEmail(email) && docCheck != null && docCheck.getCVId().isEmpty() && !docCheck.getCVId().equals(email) && listEmail.contains(email)) {
            request.setAttribute("EmailError", "Email Existed or not a email, input another one");
            bool = false;
        } else {
            docError.setEmail(email);
        }
        if (!validatePhoneNumber(phone)) {
            request.setAttribute("PhoneError", "Phone number must have 10 digits and start with 0");
            bool = false;
        } else {
            docError.setPhone(phone);
        }
        switch (Integer.parseInt(branch)) {
            case 1:
                workplace = "Hà Nội";
                break;
            case 2:
                workplace = "TP Hồ Chí Minh";
                break;
            case 3:
                workplace = "Nha Trang";
                break;
        }
        if (!validateSalary(salary)) {
            request.setAttribute("SalaryError", "Salary must be a double number bigger than 0");
            bool = false;
        } else {
            docError.setSalary(salary);
        }
        if (bool) {
            for (CertificateDoctor ClearCd : ClearListCd) {
                cdDao.deleteCertificateForDoctor(ClearCd);
            }
            Doctor doctor = new Doctor(id, email, displayName, branch, phone, academicRank, "1", salary, workplace, "../assets/img/doctor-03.jpg", status);
            doc.updateDoctor(doctor);
            for (String cNew : certificates) {
                cdDao.addCertificateForDoctor(cNew, id);
            }
            request.getSession().setAttribute("noti", "Update doctor informations success");
            response.sendRedirect("admin-doctor");
        } else {
            docError.setId(id);
            CertificateDoctorDAO CDDao = new CertificateDoctorDAO();
            List<CertificateDoctor> listCertofDoc = CDDao.getCertificateByDoctorId(id);
            System.out.println("Size: " + listCertofDoc.size());
            CertificateDAO CertDAO = new CertificateDAO();
            BranchDAO BranchDAO = new BranchDAO();
            AcademicRankDAO ARDAO = new AcademicRankDAO();
            List<AcademicRank> listAR = ARDAO.getListAcademicRank();
            List<Branch> listBranch = BranchDAO.getAllBranches();
            List<Certificate> listCert = CertDAO.getListCertificate();
            request.setAttribute("listBranch", listBranch);
            request.setAttribute("listAR", listAR);
            request.setAttribute("listCert", listCert);
            request.setAttribute("listCertofDoc", listCertofDoc);
            request.setAttribute("doc", docError);
            request.setAttribute("error", "Update failed, try again!");
            request.getRequestDispatcher("admin-doctors/admin-edit-doctor.jsp").forward(request, response);
            //response.sendRedirect("admin-editdoctor?id=" + id);
        }
    }

    private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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

    public static boolean validatePhoneNumber(String phoneNumber) {
        // Define a regular expression pattern for a 10-digit phone number
        String pattern = "^0\\d{9}$";

        // Compile the regular expression
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(phoneNumber);

        // Use the Matcher's find() method to check if the input matches the pattern
        if (matcher.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean validateSalary(String salary) {
        try {
            double number = Double.parseDouble(salary);
            if (number > 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public static boolean validateEmail(String email) {
        // Define a regular expression pattern for a valid email address
        String pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z]{2,})+$";

        // Compile the regular expression
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(email);

        // Use the Matcher's matches() method to check if the input matches the pattern
        return matcher.matches();
    }

    public static boolean validateDisplayName(String displayName) {
        // Define a regular expression pattern for display names
        String pattern = "^[a-zA-ZÀ-ỹ ]+$";;

        // Compile the regular expression
        Pattern regex = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher matcher = regex.matcher(displayName);

        // Use the Matcher's matches() method to check if the input matches the pattern
        return matcher.matches();
    }

}
