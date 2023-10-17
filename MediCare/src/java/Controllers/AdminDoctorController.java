/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AcademicRankDAO;
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
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
public class AdminDoctorController extends HttpServlet {

    private final String STATISTIC_REVIEW = "admin-reviews/admin-reviews.jsp";
    private final String REVIEW_PAGE = "admin-list-review";
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
        HttpSession session = request.getSession();
        Employee checkEmp = (Employee) session.getAttribute("EMPLOYEE");
        //check login
        if (checkEmp == null) {
            request.setAttribute("MESSAGE", checkEmp);
            request.getRequestDispatcher(NEED_EMPLOYEE).forward(request, response);
        }
        String action = request.getParameter("action");
        System.out.println("DoGet Action : " + action);
        String id = request.getParameter("id");
        System.out.println("DoGet ID : " + id);
        if ("add".equals(action)) {
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
        if ("edit".equals(action)) {
            BranchDAO BranchDAO = new BranchDAO();
            DoctorDAO DocDAO = new DoctorDAO();
            AcademicRankDAO ARDAO = new AcademicRankDAO();
            List<AcademicRank> listAR = ARDAO.getListAcademicRank();
            List<Branch> listBranch = BranchDAO.getAllBranches();
            CertificateDAO CertDAO = new CertificateDAO();
            CertificateDoctorDAO cdDao = new CertificateDoctorDAO();
            List<Certificate> listCert = CertDAO.getListCertificate();
            List<CertificateDoctor> listCertofDoc = cdDao.getCertificateByDoctorId(id);
            Doctor doc = DocDAO.getDoctorByDoctorId(id);
            System.out.println("Doctor : " + doc);
            request.setAttribute("doc", doc);
            request.setAttribute("listCert", listCert);
            request.setAttribute("listCertofDoc", listCertofDoc);
            request.setAttribute("listBranch", listBranch);
            request.setAttribute("listAR", listAR);
            request.getRequestDispatcher("admin-doctors/admin-edit-doctor.jsp").forward(request, response);
        }
        if ("profile".equals(action)) {
            DoctorDAO dao = new DoctorDAO();
            Doctor doc = dao.getDoctorByDoctorId(id);
            request.setAttribute("doc", doc);
            request.getRequestDispatcher("admin-doctors/admin-doctorprofile.jsp").forward(request, response);
        }
        DoctorDAO dao = new DoctorDAO();
        ArrayList<Doctor> listDoc = dao.getAllDoctors();
        String noti = (String) request.getSession().getAttribute("noti");
        request.setAttribute("noti", noti);
        request.setAttribute("listDoc", listDoc);
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
        String password = request.getParameter("password");
        String confirmedPassword = request.getParameter("confirmedpassword");
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
            Doctor docCheck = dao.getDoctorById(id);
            //check variable
            if (certificates == null || certificates.length == 0) {
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
            } else {
                request.setAttribute("id", id);
            }
            docCheck = dao.getDoctorByEmail(email);
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
                    byte[] salt = PasswordEncryption.generateSalt();
                    String encPass = PasswordEncryption.encryptPassword(password, salt);
                    CertificateDoctorDAO CDDao = new CertificateDoctorDAO();
                    Doctor doctor = new Doctor(id, email, displayName, branch, phone, academicRank, "1", salary, workplace, "../assets/img/doctor-03.jpg", status, encPass, birthDate, gender, "0");
                    dao.addDoctor(doctor);
                    for (String c : certificates) {
                        CDDao.addCertificateForDoctor(c, id);
                    }
                    request.getSession().setAttribute("noti", "Update doctor informations success");
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
                request.getRequestDispatcher("admin-doctors/admin-add-doctor.jsp").forward(request, response);
            }
        }
        //delete doctor function
        if ("delete".equals(action)) {
            id = request.getParameter("id");
            System.out.println("doctorId" + id);
            DoctorDAO dao = new DoctorDAO();
            Doctor doc = dao.getDoctorById(id);
            dao.deleteDoctor(doc);
            response.sendRedirect("admin-doctor");
        }
        if ("edit".equals(action)) {
            id = request.getParameter("id");
            email = request.getParameter("email");
            displayName = request.getParameter("displayName");
            academicRank = request.getParameter("academicRank");
            certId = request.getParameter("certID");
            branch = request.getParameter("branch");
            phone = request.getParameter("phone");
            workplace = null;
            status = request.getParameter("status");
            salary = request.getParameter("salary");
            CertificateDoctorDAO cdDao = new CertificateDoctorDAO();
            List<CertificateDoctor> ClearListCd = cdDao.getCertificateByDoctorId(id);
            gender = request.getParameter("gender");
            birthDate = request.getParameter("birthDate");
            System.out.println("Birthdate Input : " + birthDate);
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
            List<Doctor> list = doc.getAllDoctors();
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
                Doctor doctor = new Doctor(id, email, displayName, branch, phone, academicRank, certId, salary, workplace, "../assets/img/doctor-03.jpg", status, birthDate, gender, "0");
                doc.updateDoctor(doctor);
                for (String cNew : certificates) {
                    cdDao.addCertificateForDoctor(cNew, id);
                }
                request.getSession().setAttribute("noti", "Update doctor informations success");
                response.sendRedirect("admin-doctor");
            } else {
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

}
