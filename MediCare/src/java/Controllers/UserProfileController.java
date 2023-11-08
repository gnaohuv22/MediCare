/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.FamilyProfileDAO;
import DAL.RelationshipDAO;
import DAL.UserDAO;
import Models.FamilyProfile;
import Models.Relationship;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author phuon
 */
@MultipartConfig
public class UserProfileController extends HttpServlet {

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
        List<FamilyProfile> fpList;
        UserDAO uDAO = new UserDAO();

        String id;

        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            id = String.valueOf(1);
        } else {
            id = String.valueOf(request.getParameter("id"));
        }
        System.out.println(id);

        RelationshipDAO rDAO = new RelationshipDAO();
        ArrayList<Relationship> rList = rDAO.getRelationshipList();

        String ownerId = uDAO.getIdByEmail(String.valueOf(session.getAttribute("email")));
        fpList = fpDAO.getFamilyProfileListByUserOwnerId(ownerId);
        if (session.getAttribute("email") == null) {
            response.sendRedirect("user-login");
        } else {
            if (!fpList.isEmpty()) {
                request.setAttribute("fpList", fpList);
                request.setAttribute("currentfp", fpList.get(Integer.parseInt(id) - 1));
            }
            request.setAttribute("rList", rList);
            request.getRequestDispatcher("user-profile.jsp").forward(request, response);
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
        FamilyProfileDAO fpDAO = new FamilyProfileDAO();
        HttpSession session = request.getSession();
        List<FamilyProfile> fpList;
        UserDAO uDAO = new UserDAO();

        String search;
        search = request.getParameter("search-profile");

        String ownerId = uDAO.getIdByEmail(String.valueOf(session.getAttribute("email")));

        RelationshipDAO rDAO = new RelationshipDAO();
        ArrayList<Relationship> rList = rDAO.getRelationshipList();

        fpList = fpDAO.getFamilyProfileListByUserOwnerId(ownerId);

        if (session.getAttribute("email") == null) {
            response.sendRedirect("user-login");
        } else {
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String birthDate = request.getParameter("birthDate");
            String gender = request.getParameter("gender");
            String medicalId = request.getParameter("medicalId");
            String identity = request.getParameter("identity");
            String address = request.getParameter("address");
            String ethnic = request.getParameter("ethnic");
            String email = request.getParameter("email");

            //add photo
            String method = request.getParameter("method");
            switch (method) {
                case "search":
                    fpList = fpDAO.getFamilyProfileListByUserName(search, ownerId);
                    request.setAttribute("fpList", fpList);
                    request.setAttribute("rList", rList);
                    request.getRequestDispatcher("user-profile.jsp").forward(request, response);
                    break;
                case "add":
                    Part filePart = request.getPart("photo");
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Extract file name
                    System.out.println(fileName);
                    String fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    String uploadPath = getServletContext().getRealPath("") + "assets\\client\\images\\profile";
                    String newFileName = name + "_" + birthDate + "." + fileType; // Replace 'newName' with the new name you want
                    System.out.println("method: " + method);
                    System.out.println("name: " + name);
                    if (!fileType.equals("jpg") && !fileType.equals("png")) {
                        // Handle invalid file type
                    } else {
                        if (!fileName.isEmpty()) {
                            System.out.println(uploadPath);// Replace with your directory path
                            File dir = new File(uploadPath);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            File file = new File(uploadPath + File.separator, newFileName);
                            System.out.println("Absolute Path: " + file.getAbsolutePath());
                            filePart.write(file.getAbsolutePath());
                        }
                    }

                    LocalDate date = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    String currentDate = date.format(formatter);
                    String relationId = request.getParameter("relation");
                    FamilyProfile fp = new FamilyProfile(email, name, birthDate, gender, address, identity, medicalId, ethnic, phone, newFileName, currentDate, relationId, ownerId);
                    System.out.println(fp.toString());
                    fpDAO.addNewUserProfile(fp);
                    request.setAttribute("fpList", fpList);
                    request.setAttribute("rList", rList);
                    response.sendRedirect("user-profile");
                    break;
                case "delete":
                    String profileId = request.getParameter("profileId");
                    fpDAO.deleteFamilyProfileByID(profileId);
                    request.setAttribute("fpList", fpList);
                    request.setAttribute("rList", rList);
                    response.sendRedirect("user-profile");
                    break;
                case "edit":
                    filePart = request.getPart("photo");
                    fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Extract file name
                    System.out.println(fileName);
                    fileType = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    uploadPath = getServletContext().getRealPath("") + "assets\\client\\images\\profile";
                    newFileName = name + "_" + birthDate + "." + fileType; // Replace 'newName' with the new name you want

                    System.out.println("method: " + method);
                    System.out.println("name: " + name);
                    if (!fileType.equals("jpg") && !fileType.equals("png")) {
                        // Handle invalid file type
                    }
                    else {
                        if (!fileName.isEmpty()) {
                            System.out.println(uploadPath);// Replace with your directory path
                            File dir = new File(uploadPath);
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            File file = new File(uploadPath + File.separator, newFileName);
                            System.out.println("Absolute Path: " + file.getAbsolutePath());
                            filePart.write(file.getAbsolutePath());
                        }
                    }
                    profileId = request.getParameter("profileId");
                    relationId = request.getParameter("relation");
                    fp = new FamilyProfile();
                    fp.setProfileId(profileId);
                    fp.setEmail(email);
                    fp.setName(name);
                    fp.setBirthDate(birthDate);
                    fp.setGender(gender);
                    fp.setAddress(address);
                    fp.setIdentity(identity);
                    fp.setMedicalId(medicalId);
                    fp.setEthnic(ethnic);
                    fp.setPhone(phone);
                    fp.setProfilePicture(newFileName);
                    fp.setRelationId(relationId);
                    fp.setOwnerId(ownerId);
                    System.out.println(fp.toString());
                    fpDAO.editFamilyProfileById(fp);
                    request.setAttribute("fpList", fpList);
                    request.setAttribute("rList", rList);
                    response.sendRedirect("user-profile");
                    break;
                default:
                    throw new AssertionError();
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

}
