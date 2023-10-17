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

        String ownerId = uDAO.getIdByEmail(String.valueOf(session.getAttribute("email")));
        fpList = (List<FamilyProfile>) request.getAttribute("fpList");
        if (request.getAttribute("fpList") == null) {
            fpList = fpDAO.getFamilyProfileListByUserOwnerId(ownerId);
        }
        if (session.getAttribute("email") == null) {
            response.sendRedirect("user-login");
        } else {
            if (!fpList.isEmpty()) {
                request.setAttribute("fpList", fpList);
                request.setAttribute("currentfp", fpList.get(Integer.parseInt(id) - 1));
            }
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

        String id;
        String ownerId = uDAO.getIdByEmail(String.valueOf(session.getAttribute("email")));

        if (request.getParameter("id") == null) {
            id = String.valueOf(1);
        } else {
            id = String.valueOf(request.getParameter("id"));
        }

        fpList = fpDAO.getFamilyProfileListByUserName(search, ownerId);

        if (session.getAttribute("email") == null) {
            response.sendRedirect("user-login");
        } else {
            String method = request.getParameter("method");
            if (method.equals("search")) {
                FamilyProfile fd;
                if (getIndexById(id, fpList) == -1) {
                    fd = null;
                } else {
                    int i = getIndexById(id, fpList);
                    fd = fpList.get(i);
                }

                request.setAttribute("fpList", fpList);
                request.setAttribute("currentfp", fd);
                request.getRequestDispatcher("user-profile.jsp").forward(request, response);
            } else if (method.equals("add")) {
                String profileId = String.valueOf(fpDAO.getLatestIdByOwnerId(ownerId));
                String name = request.getParameter("name");
                String phone = request.getParameter("phone");
                String birthDate = request.getParameter("birthDate");
                String gender = request.getParameter("gender");
                String medicalId = request.getParameter("medicalId");
                String identity = request.getParameter("identity");
                String ethnic = request.getParameter("ethnic");
                String email = request.getParameter("email");
            } else {
                throw new AssertionError();
            }

        }
    }

    

    private int getIndexById(String id, List<FamilyProfile> fpList) {
        if (fpList == null || fpList.isEmpty()) {
            return -1;
        }
        for (FamilyProfile fp : fpList) {
            if (fp.getProfileId().equals(id)) {
                return fpList.indexOf(fp);
            }
        }
        return -1;
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
