/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AcademicRankDAO;
import DAL.AdminEmailContext;
import DAL.BranchDAO;
import DAL.CertificateDAO;
import DAL.CertificateDoctorDAO;
import DAL.DoctorDAO;
import Models.AcademicRank;
import Models.Branch;
import Models.Certificate;
import Models.CertificateDoctor;
import Models.Doctor;
import Models.Employee;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Asus
 */
@MultipartConfig
public class AdminDoctorController extends HttpServlet {

    private final String STATISTIC_REVIEW = "admin-reviews/admin-reviews.jsp";
    private final String REVIEW_PAGE = "admin-list-review";
    private final String NEED_EMPLOYEE = "admin-screen/admin-login.jsp";
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
            out.println("<title>Servlet AdminDoctorController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminDoctorController at " + request.getContextPath() + "</h1>");
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
        AcademicRankDAO ARDAO = new AcademicRankDAO();
        CertificateDAO CertDAO = new CertificateDAO();
        HttpSession session = request.getSession();
        Employee checkEmp = (Employee) session.getAttribute("EMPLOYEE");

        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        List<AcademicRank> listAR = ARDAO.getListAcademicRank();
        List<Branch> listBranch = BranchDAO.getAllBranches();
        List<Certificate> listCert = CertDAO.getListCertificate();
        String action = request.getParameter("action"); 
        String id = request.getParameter("id");     
         if ("edit".equals(action) || "add".equals(action) ) {
            DoctorDAO DocDAO = new DoctorDAO();
            CertificateDoctorDAO cdDao = new CertificateDoctorDAO();
            List<CertificateDoctor> listCertofDoc = cdDao.getCertificateByDoctorId(id);
            Doctor doc = DocDAO.getDoctorByDoctorId(id);
             request.setAttribute("listBranch", listBranch);
            request.setAttribute("listAR", listAR);
            request.setAttribute("listCert", listCert);
            request.setAttribute("action", action);
            request.setAttribute("doc", doc);
            request.setAttribute("listCert", listCert);
            request.setAttribute("listCertofDoc", listCertofDoc);
            request.setAttribute("listBranch", listBranch);
            request.setAttribute("listAR", listAR);
            request.setAttribute("action", action); // ddaay thoi em
            request.getRequestDispatcher("admin-doctors/admin-edit-doctor.jsp").forward(request, response);
        } else if ("profile".equals(action)) {
            DoctorDAO dao = new DoctorDAO();
            Doctor doc = dao.getDoctorByDoctorId(id);
            request.setAttribute("doc", doc);
            request.getRequestDispatcher("admin-doctors/admin-doctorprofile.jsp").forward(request, response);
        }
        String isDelete = request.getParameter("isDelete") == null ? "" : request.getParameter("isDelete");
        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        DoctorDAO dao = new DoctorDAO();
        System.out.println("Search :" + search);
        System.out.println("Is delete Status :" + isDelete);   
        String branch = request.getParameter("branch") == null ? "" : request.getParameter("branch");
        System.out.println("Branch status : " + branch);
        String academicRank = request.getParameter("academicRank") == null ? "" : request.getParameter("academicRank");
        System.out.println("AcademicRank status : " + academicRank);
         ArrayList<Doctor> listDoc = dao.getAllDoctorsByCondition(isDelete, search,branch, academicRank);
        int count = dao.doctorCount(listDoc);
        System.out.println("Count doctor  = " + count);
        String indexRaw = request.getParameter("index") == null ? "1" : request.getParameter("index");
        int index = Integer.parseInt(indexRaw);
        System.out.println("Index Paging : " + index);
        int endPage = (count / 8);
        if (count % 8 != 0) {
            endPage++;
        }
        int previous, after;
        if(endPage == 1){
            previous = after = 1;
        }
        else{
            previous = index + 1;
            after = index - 1;
        }
        List<Doctor> listPaging = dao.pagingDoctor(search, branch, academicRank, isDelete, index);
        System.out.println("listPaging site : " + listPaging.size());
        System.out.println("List paging : " + listPaging);
        String noti = (String) request.getSession().getAttribute("noti");
        request.setAttribute("listAR", listAR);
        request.setAttribute("branch", branch);
        request.setAttribute("academicRank", academicRank);
        request.setAttribute("listBranch", listBranch);
        request.setAttribute("search", search);
        request.setAttribute("noti", noti);
        request.setAttribute("previous", previous);
        request.setAttribute("after", after);
        request.setAttribute("isDelete", isDelete);
        request.setAttribute("listDoc", listDoc);
        request.setAttribute("listPaging", listPaging);
        request.setAttribute("endPage", endPage);
        request.getRequestDispatcher("admin-doctors/admin-doctors.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        System.out.println("Post action : " + action);
        String id = request.getParameter("id");
        String email = request.getParameter("email");
        String displayName = request.getParameter("displayName");
        String password = null;
        String sendPassword = null;
        String academicRank = request.getParameter("academicRank");
        String branch = request.getParameter("branch");
        String phone = request.getParameter("phone");
        String status = request.getParameter("status");
        String workplace = null;
        String salary = request.getParameter("salary");
        String certId = request.getParameter("certID");
        String[] certificates = request.getParameterValues("certificates");
        String birthDate = request.getParameter("birthDate");
        String gender = request.getParameter("gender");
        //file handle : for upload picture -----------------------------------------------------------------------------
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads"; // Đường dẫn lưu trữ tệp
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        Part filePart = request.getPart("avatarUpload");
        String fileName = getFileName(filePart); // Lấy tên tệp
        System.out.println("Path " + fileName.isEmpty());
        String imageFileName = null;
        if (!fileName.isEmpty()) {
            // Lưu tệp lên máy chủ
            System.out.println("Upload " + uploadPath);
            filePart.write(uploadPath + File.separator + fileName);

            response.getWriter().println("File uploaded successfully.");
            imageFileName = getFileName(filePart);
            System.out.println("File name : " + imageFileName);
        }

//--------------------------------------------------------------------------------------------
        //add doctor function : 
        if ("add".equals(action)) {

            boolean bool = true;
            if (!validateDisplayName(displayName)) {
                request.setAttribute("displayNameError", "Display name should be contains letters and space only");
                bool = false;
            } else {
                request.setAttribute("displayName", displayName);
            }

            DoctorDAO dao = new DoctorDAO();

            //check variable
            if (certificates == null || certificates.length == 0) {
                request.setAttribute("certificateError", "You must choose at least one Certificate");
                bool = false;
            }

            java.util.Random random = new java.util.Random();
            password = "" + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()))
                    + CHARACTERS.charAt(random.nextInt(CHARACTERS.length()));
            sendPassword = password;
            System.out.println("Send password : " + sendPassword);
            Doctor docCheck = dao.getDoctorByEmail(email);
            if (!validateEmail(email)) {
                request.setAttribute("EmailError", "Not a email, enter again");
                bool = false;
            } else if (docCheck != null) {
                request.setAttribute("EmailError", "Email Existed, input another one");
                bool = false;
            } else {
                request.setAttribute("email", email);
            }
            if (!validateSalary(salary)) {
                request.setAttribute("SalaryError", "Salary must be a double number bigger than 0");
                bool = false;
            } else {
                request.setAttribute("salary", salary);
            }
            if (!validatePhoneNumber(phone)) {
                request.setAttribute("PhoneError", "Phone number must have 10 digits and start with 0");
                bool = false;
            } else {
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
                try {
                    id = dao.autoGenerateID();
                    System.out.println("ID : " + id);
                    byte[] salt = PasswordEncryption.generateSalt();
                    String encPass = PasswordEncryption.encryptPassword(password, salt);
                    CertificateDoctorDAO CDDao = new CertificateDoctorDAO();
                    Doctor doctor = new Doctor(id, email, displayName, branch, phone, academicRank, "1", salary, workplace, imageFileName, status, encPass, birthDate, gender, "0");
                    dao.addDoctor(doctor);
                    for (String c : certificates) {
                        CDDao.addCertificateForDoctor(c, id);
                    }
                    request.getSession().setAttribute("noti", "Add doctor success");
                    response.sendRedirect("admin-doctor");
                } catch (ParseException ex) {
                    Logger.getLogger(AdminDoctorController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                CertificateDoctorDAO CDDao = new CertificateDoctorDAO();
                List<CertificateDoctor> CDList = CDDao.getCertificateByDoctorId(id);
                CertificateDAO CertDAO = new CertificateDAO();
                BranchDAO BranchDAO = new BranchDAO();
                AcademicRankDAO ARDAO = new AcademicRankDAO();
                List<AcademicRank> listAR = ARDAO.getListAcademicRank();
                List<Branch> listBranch = BranchDAO.getAllBranches();
                List<Certificate> listCert = CertDAO.getListCertificate();
                request.setAttribute("action", action);
                request.setAttribute("birthDate", birthDate);
                request.setAttribute("gender", gender);
                request.setAttribute("branch", branch);
                request.setAttribute("academicRank", academicRank);
                request.setAttribute("status", status);
                request.setAttribute("CDList", CDList);
                request.setAttribute("listBranch", listBranch);
                request.setAttribute("listAR", listAR);
                request.setAttribute("listCert", listCert);
                request.setAttribute("error", "Add doctor failed, try again!");
                request.getRequestDispatcher("admin-doctors/admin-edit-doctor.jsp").forward(request, response);
            }
        } //delete doctor function
        else if ("delete".equals(action)) {
            id = request.getParameter("id");
            System.out.println("doctorId" + id);
            DoctorDAO dao = new DoctorDAO();
            Doctor doc = dao.getDoctorById(id);
            dao.deleteDoctor(doc);
            response.sendRedirect("admin-doctor");
        } else if ("edit".equals(action)) {
            CertificateDoctorDAO cdDao = new CertificateDoctorDAO();
            List<CertificateDoctor> ClearListCd = cdDao.getCertificateByDoctorId(id);
            certificates = request.getParameterValues("certificates");
            boolean bool = true;
            if (certificates == null || certificates.length == 0) {
                request.setAttribute("certificateError", "You must choose at least one Certificate");
                bool = false;
            }

            DoctorDAO doc = new DoctorDAO();
            Doctor docError = new Doctor();
            if (!validateDisplayName(displayName.trim())) {
                request.setAttribute("displayNameError", "Display name should be contains letters and space only");
                bool = false;
            } else {

                docError.setDisplayName(displayName);
            }
            Doctor docCheck = doc.getDoctorByEmail(id);
            List<Doctor> list = doc.getAllDoctorsByCondition("", "", "","");
            List<String> listEmail = new ArrayList<>();

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
                Doctor doctor = new Doctor(id, email, displayName, branch, phone, academicRank, certId, salary, workplace, imageFileName, status, birthDate, gender, "0");
                doc.updateDoctor(doctor);
                for (String cNew : certificates) {
                    cdDao.addCertificateForDoctor(cNew, id);
                }
                AdminEmailContext.sendEmailnewPassword(email, sendPassword, displayName);
                request.getSession().setAttribute("noti", "Update doctor informations success");
                response.sendRedirect("admin-doctor");
            } else {
                request.setAttribute("action", action);
                docError.setId(id);
                docError.setCVId(certId);
                CertificateDoctorDAO CDDao = new CertificateDoctorDAO();
                List<CertificateDoctor> listCertofDoc = CDDao.getCertificateByDoctorId(id);
                System.out.println("Size: " + listCertofDoc.size());
                CertificateDAO CertDAO = new CertificateDAO();
                BranchDAO BranchDAO = new BranchDAO();
                AcademicRankDAO ARDAO = new AcademicRankDAO();
                List<AcademicRank> listAR = ARDAO.getListAcademicRank();
                List<Branch> listBranch = BranchDAO.getAllBranches();
                List<Certificate> listCert = CertDAO.getListCertificate();
                docError.setBirthDate(birthDate);
                docError.setGender(gender);
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

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

}