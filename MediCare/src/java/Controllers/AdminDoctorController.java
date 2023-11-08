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
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;

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
    private final String DEFAULT_PIC = "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RrqtmDVo+WT7f3iR5ixqUn2HmRd1wsWta8+rvkf8O2ShJ89Z1JHtOWJ81+o1CRMkuFb77inHFHhUTmfSXc93d4DWZFtynPuJubsfM7HQNY9o+WBr5jE5Y7om6e+zIVsx3DuWgJMkA+cdSAfUMz7R6TpdRkUmqxahFWUPxnUuoI4wc8W7WY9wW/AqsY5tymUuAcRO0ew5jmNOlNQYL8t9QQyw2pxajwADM4uatvXFctQqz5O6lPKWBxJz8UewZD0p3OtymTSJ1vPuZrir7+wCfMVqUPYdft5jadrhNGsB2G2vJ6pOCOMjr3G1XwGXtwTmfSmi64TbekCly1L3LDjng73FuF6vgcj7MJOY5iE5Y7oOu/aF7M0tC82qewAQD56/GPw3PpVBKVhQORBzB4sWHXBcVj0ipFQU46wlLv60+Kr4jmI6tLbSnFnJKQVE8QGLpqy67dNUqazn4TJWsfpz1fDL0t3OdZMm26lSXFZqiSA6gflWP3SffzE0kVb7F0eVuYlW5WIym0H8y/FHzwdvpbuf6t4BpCMNSskToy28uNSfGHyPMTuh6l4JYkaElWSpkxII40pBUfjl6XsOpGk33RJm6yCJjYV6idyfgcDZzD7pOduqlQ4AOpDTjxHrIA+R9LsuKafbcSclIUFA+rXikyhOo8KWk5h5hDmfrSDzD7oKX3/SR3nPMR4jSPfmr6+lxtxo1l+G6N6A8TmfA0IP9vi/TmHpnf8ACNKlZOee4U237kJ9LjbjQlI7/orpQJz72p1v3LPMI7MaUXO+6TbhVnn/AMWoe4AelxtxoBc3ejJpOf4Jbw+IP15hHZqxd+hW8K5eFWqkRqF4PKkrdb3cjI7knVmMsb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OSp/WezG9/vjkqf1nsxvf745Kn9Z7Mb3++OTp/WezGia1KnZtnml1ZLQkmSt37pe6G5IGWv2f9cH/AP/Z";

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
        CertificateDoctorDAO cdDao = new CertificateDoctorDAO();
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        List<CertificateDoctor> listCertofDoc = cdDao.getCertificateByDoctorId(id);
        if ("edit".equals(action) || "add".equals(action)) {
            DoctorDAO DocDAO = new DoctorDAO();
            Doctor doc = DocDAO.getDoctorById(id);
            request.setAttribute("listBranch", listBranch);
            request.setAttribute("listAR", listAR);
            request.setAttribute("listCert", listCert);
            request.setAttribute("action", action);
            request.setAttribute("doc", doc);
            request.setAttribute("listCert", listCert);
            request.setAttribute("listCertofDoc", listCertofDoc);
            request.setAttribute("listBranch", listBranch);
            request.setAttribute("listAR", listAR);
            request.setAttribute("action", action);
            request.getRequestDispatcher("admin-doctors/admin-edit-doctor.jsp").forward(request, response);
        } else if ("profile".equals(action)) {
            request.setAttribute("listCert", listCert);
            DoctorDAO dao = new DoctorDAO();
            Doctor doc2 = dao.getDoctorById(id);
            Doctor doc = dao.getDoctorByDoctorId(id);
            List<CertificateDoctor> listCertOfDoctor = dao.getListCertOfDoc(id);
            request.setAttribute("doc2", doc2);
            request.setAttribute("doc", doc);
            String intro = doc2.getGender();
            System.out.println("Intro : " + intro);
            request.setAttribute("listCertOfDoctor", listCertOfDoctor);
            request.getRequestDispatcher("admin-doctors/admin-doctorprofile.jsp").forward(request, response);
        }

        String isDelete = request.getParameter("isDelete") == null ? "" : request.getParameter("isDelete");
        String search = request.getParameter("search") == null ? "" : request.getParameter("search");
        DoctorDAO dao = new DoctorDAO();
        Doctor doc = dao.getDoctorByDoctorId(id);

        String branch = request.getParameter("branch") == null ? "" : request.getParameter("branch");

        String academicRank = request.getParameter("academicRank") == null ? "" : request.getParameter("academicRank");

        ArrayList<Doctor> listDoc = dao.getAllDoctorsByCondition(isDelete, search, branch, academicRank);
        int count = dao.doctorCount(listDoc);

        String indexRaw = request.getParameter("index") == null ? "1" : request.getParameter("index");
        int index = Integer.parseInt(indexRaw);

        int endPage = (count / 8);
        if (count % 8 != 0) {
            endPage++;
        }
        int previous, after;
        previous = index - 1;
        after = index + 1;
        List<Doctor> listPaging = dao.pagingDoctor(search, branch, academicRank, isDelete, index);
        System.out.println("listPaging site : " + listPaging.size());
        System.out.println("doc : " + doc);
        System.out.println("List paging : " + listPaging);
        String noti = (String) request.getSession().getAttribute("noti");
        request.setAttribute("index", index);
        request.setAttribute("endPage", endPage);
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

        Part filePart = request.getPart("avatarUpload");
        System.out.println("filePart value at add : " + filePart);
        String imageFileName = "";

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
                request.setAttribute("certificateError", "Bạn phải chọn ít nhất 1 chứng chỉ");
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
                request.setAttribute("EmailError", "Không đúng định dạng email");
                bool = false;
            } else if (docCheck != null) {
                request.setAttribute("EmailError", "Email đã tồn tại trong hệ thống");
                bool = false;
            } else {
                request.setAttribute("email", email);
            }
            if (!validateSalary(salary)) {
                request.setAttribute("SalaryError", "Lương phải là số và lớn hơn 0");
                bool = false;
            } else {
                request.setAttribute("salary", salary);
            }
            if (!validatePhoneNumber(phone)) {
                request.setAttribute("PhoneError", "Số điện thoại phải có dạng 1 dãy số 10 con số và bắt đầu bằng số 0");
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
            System.out.println("Kết quả so sánh : " + (filePart.getSubmittedFileName().equals("")));

            if (!filePart.getSubmittedFileName().equals("")) {
                if (filePart.getSubmittedFileName().toLowerCase().endsWith(".jpg") || filePart.getSubmittedFileName().toLowerCase().endsWith(".png")) {
                    if (filePart.getSize() < 50 * 1024) {
                        InputStream imgStr = filePart.getInputStream();
                        byte[] fileContent = IOUtils.toByteArray(imgStr);
                        imageFileName = Base64.getEncoder().encodeToString(fileContent);
                    } else {
                        request.setAttribute("profilePictureError", "Ảnh không được có kích thước vượt quá 50kb, chọn ảnh có kích thước nhỏ hơn để tải lên");
                        bool = false;
                    }
                } else {
                    request.setAttribute("profilePictureError", "Ảnh phải có định dạng PNG hoặc JPG");
                    bool = false;
                }
            } else {
                imageFileName = DEFAULT_PIC;
            }
            System.out.println("Base64: " + imageFileName);

            if (bool == true) {
                try {
                    id = dao.autoGenerateID();
                    System.out.println("ID : " + id);
                    byte[] salt = PasswordEncryption.generateSalt();
                    String encPass = PasswordEncryption.encryptPassword(password, salt);
                    CertificateDoctorDAO CDDao = new CertificateDoctorDAO();
                    System.out.println("Default pic : " + imageFileName);
                    imageFileName = "data:image/png;base64, " + imageFileName;
                    Doctor doctor = new Doctor(id, email, displayName, branch, phone, academicRank, "1", salary, workplace, imageFileName, status, encPass, birthDate, gender, "0");
                    dao.addDoctor(doctor);
                    for (String c : certificates) {
                        CDDao.addCertificateForDoctor(c, id);
                    }
                    request.getSession().setAttribute("noti", "Thêm bác sĩ mới thành công");
                    response.sendRedirect("admin-doctor?isDelete=0");
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
            System.out.println("doctorId " + id);
            DoctorDAO dao = new DoctorDAO();
            dao.deleteDoctor(id);
            response.sendRedirect("admin-doctor?isDelete=0");
            //-----------Undo a deleted doctor
        } else if ("undo".equals(action)) {
            id = request.getParameter("id");
            System.out.println("doctorId" + id);
            DoctorDAO dao = new DoctorDAO();
            Doctor doc = dao.getDoctorById(id);
            dao.undoDoctor(doc);
            response.sendRedirect("admin-doctor?isDelete=0");
            //-----------Edit doctor profile : 
        } else if ("edit".equals(action)) {
            CertificateDoctorDAO cdDao = new CertificateDoctorDAO();
            List<CertificateDoctor> ClearListCd = cdDao.getCertificateByDoctorId(id);
            certificates = request.getParameterValues("certificates");
            boolean bool = true;
            if (certificates == null || certificates.length == 0) {
                request.setAttribute("certificateError", "Bạn phải chọn ít nhất 1 chứng chỉ");
                bool = false;
            }
            String isDelete = request.getParameter("isDelete");
            System.out.println("Is Delete Status at AdminDoctorController :  " + isDelete);
            DoctorDAO doc = new DoctorDAO();
            Doctor docError = new Doctor();
            if (!validateDisplayName(displayName.trim())) {
                request.setAttribute("displayNameError", "Tên bác sĩ chỉ nên chứa chữ cái và khoảng trống");
                bool = false;
            } else {

                docError.setDisplayName(displayName);
            }
            Doctor docCheck = doc.getDoctorByEmail(id);
            List<Doctor> list = doc.getAllDoctorsByCondition("", "", "", "");
            List<String> listEmail = new ArrayList<>();

            if (docCheck != null) {
                for (Doctor doctor : list) {
                    if (!id.equals(doctor.getId())) {
                        listEmail.add(doctor.getEmail());
                    }
                }
            }
            if (!validateEmail(email) && docCheck != null && docCheck.getCVId().isEmpty() && !docCheck.getCVId().equals(email) && listEmail.contains(email)) {
                request.setAttribute("EmailError", "Email đã tồn tại trong hệ thống, hoặc không đúng định dạng");
                bool = false;
            } else {
                docError.setEmail(email);
            }
            if (!validatePhoneNumber(phone)) {
                request.setAttribute("PhoneError", "Số điện thoại phải có dạng 1 dãy số 10 con số và bắt đầu bằng số 0");
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
                request.setAttribute("SalaryError", "Lương phải là số và lớn hơn 0");
                bool = false;
            } else {
                docError.setSalary(salary);
            }

            if ((!filePart.getSubmittedFileName().equals(""))) {
                if (filePart.getSubmittedFileName().toLowerCase().endsWith(".jpg") || filePart.getSubmittedFileName().toLowerCase().endsWith(".png")) {
                    if (filePart.getSize() < 50 * 1024) {
                        InputStream imgStr = filePart.getInputStream();
                        byte[] fileContent = IOUtils.toByteArray(imgStr);
                        imageFileName = Base64.getEncoder().encodeToString(fileContent);
                    } else {
                        request.setAttribute("profilePictureError", "Ảnh không được có kích thước vượt quá 50kb, chọn ảnh có kích thước nhỏ hơn để tải lên");
                        bool = false;
                    }
                } else {
                    request.setAttribute("profilePictureError", "Ảnh phải có định dạng là JPG hoặc PNG");
                    bool = false;
                }
            } else {
                imageFileName = doc.getDoctorByDoctorId(id).getProfilePicture();
            }

            System.out.println("Base64: " + imageFileName);
            System.out.println("Kết quả so sánh : " + (filePart.getSubmittedFileName().equals("")));
            if (bool) {

                for (CertificateDoctor ClearCd : ClearListCd) {
                    cdDao.deleteCertificateForDoctor(ClearCd);
                }
                if (imageFileName.startsWith("data:image/png;base64,")) {
                    imageFileName = imageFileName.replace("data:image/png;base64,", "");
                }
                imageFileName = "data:image/png;base64, " + imageFileName;
                System.out.println("ImageFileName before adding to db : " + imageFileName);
                Doctor doctor = new Doctor(id, email, displayName, branch, phone, academicRank, certId, salary, workplace, imageFileName, status, birthDate, gender, isDelete);
                doc.updateDoctor(doctor);
                for (String cNew : certificates) {
                    cdDao.addCertificateForDoctor(cNew, id);
                }
                AdminEmailContext.sendEmailnewPassword(email, sendPassword, displayName);
                request.getSession().setAttribute("noti", "Cập nhật thông tin bác sĩ thành công");
                response.sendRedirect("admin-doctor?isDelete=0");
            } else {
                request.setAttribute("isDelete", isDelete);
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
            String cleanedSalary = salary.replace(".", "");
            double number = Double.parseDouble(cleanedSalary);
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
