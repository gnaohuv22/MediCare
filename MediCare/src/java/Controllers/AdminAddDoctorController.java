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
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.math3.analysis.function.Ceil;

/*@WebServlet("/admin-adddoctor")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)
/**
 *
 * @author Asus
 * @MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 *
 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
 */
public class AdminAddDoctorController extends HttpServlet {

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
            out.println("<title>Servlet AdminAddDoctorController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminAddDoctorController at " + request.getContextPath() + "</h1>");
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
        Models.GetEmployee checkEmp = (Models.GetEmployee)session.getAttribute("EMPLOYEE");
        if (checkEmp==null){
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        BranchDAO BranchDAO = new BranchDAO();
        AcademicRankDAO ARDAO = new AcademicRankDAO();
        CertificateDAO CertDAO = new CertificateDAO();
        List<AcademicRank> listAR = ARDAO.getListAcademicRank();
        List<Branch> listBranch = BranchDAO.getAllBranches();
        List<Certificate> listCert = CertDAO.getListCertificate();
        request.setAttribute("listBranch", listBranch);
        request.setAttribute("listAR", listAR);
        request.setAttribute("listCert", listCert);
        request.getRequestDispatcher("admin-doctors/admin-add-doctor.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String displayName = request.getParameter("displayName");
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmedpassword");
        String academicRank = request.getParameter("academicRank");
        String branch = request.getParameter("branch");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");
        String workplace = null;
        String salary = request.getParameter("salary");
        String[]certificates  = request.getParameterValues("certificates");
        
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
        boolean bool = true;
        if (!validateDisplayName(displayName)) {
            request.setAttribute("displayNameError", "Display name should be contains letters and space only");
            bool = false;
        } else {
            request.setAttribute("displayName", displayName);
        }
       
        
        
        DoctorDAO dao = new DoctorDAO();
        Doctor docCheck = dao.getDoctorById(id);
        CurriculumVitaeDAO CVDao = new CurriculumVitaeDAO();
        //check variable
        if(certificates == null || certificates.length == 0){
             request.setAttribute("certificateError", "You must choose at least one Certificate");
            bool = false;
        }
        
        if (!password.equals(confirmedPassword)) {
            request.setAttribute("PasswordError", "Password is not equals to Confirmed password, please enter correct password");
            bool = false;
        }

        if (docCheck != null) {
            request.setAttribute("IdError", "ID Existed, input another one");
            bool = false;
        }
        else {
            request.setAttribute("id", id);
        }
        docCheck = dao.getDoctorByEmail(email);
         if (!validateEmail(email)) {
            request.setAttribute("EmailError", "Not a email, enter again");
            bool = false;
        }
         else if (docCheck != null) {
            request.setAttribute("EmailError", "Email Existed, input another one");
            bool = false;
        }
         else {
            request.setAttribute("email", email);
        }
       
        if (!validateSalary(salary)) {
            request.setAttribute("SalaryError", "Salary must be a double number bigger than 0");
            bool = false;
        }
        else {
            request.setAttribute("salary", salary);
        }
        if (!validatePhoneNumber(phone)) {
            request.setAttribute("PhoneError", "Phone number must have 10 digits and start with 0");
            bool = false;
        }
        else {
            request.setAttribute("phone", phone);
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
        if (bool == true) {     
            byte[] salt = PasswordEncryption.generateSalt();
            String encPass = PasswordEncryption.encryptPassword(password, salt);
            CertificateDoctorDAO CDDao = new CertificateDoctorDAO();
            Doctor doctor = new Doctor(id, email, displayName, branch, phone, academicRank, "1", salary, workplace, "../assets/img/doctor-03.jpg", status, encPass);
            dao.addDoctor(doctor);
            for(String c : certificates){
                CDDao.addCertificateForDoctor(c, id);
            }
            request.getSession().setAttribute("noti", "Update doctor informations success");
            response.sendRedirect("admin-doctor");
        } else {
            CertificateDoctorDAO CDDao = new CertificateDoctorDAO();
            List<CertificateDoctor> CDList = CDDao.getCertificateByDoctorId(id);
            CertificateDAO CertDAO = new CertificateDAO();
            BranchDAO BranchDAO = new BranchDAO();
            AcademicRankDAO ARDAO = new AcademicRankDAO();
            List<AcademicRank> listAR = ARDAO.getListAcademicRank();
            List<Branch> listBranch = BranchDAO.getAllBranches();
            List<Certificate> listCert = CertDAO.getListCertificate();
            request.setAttribute("CDList",CDList);
            request.setAttribute("listBranch", listBranch);
            request.setAttribute("listAR", listAR);
            request.setAttribute("listCert", listCert);
            request.setAttribute("error", "Add doctor failed, try again!");
            request.getRequestDispatcher("admin-doctors/admin-add-doctor.jsp").forward(request, response);
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

    /*private String extractFileName(Part part) {//This method will print the file name.
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
     */
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
    public static boolean nullCheck(String[] array) {
        for (String element : array) {
            if (element == null) {
                return true;
            }
        }
        return false;
    }
}
