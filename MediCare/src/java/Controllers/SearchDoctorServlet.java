/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.*;
import Models.*;
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
 * @author hoang
 */
@WebServlet(name="SearchDoctorServlet", urlPatterns={"/search-doctor"})
public class SearchDoctorServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String pattern = request.getParameter("pattern");
        DoctorDAO dd = new DoctorDAO();
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        ArrayList<Doctor> doctors = dd.getDoctorsByPattern(pattern, (page - 1) * recordsPerPage, recordsPerPage);
        int recordCount = dd.getNoOfRecords();
        int pageCount = (int)Math.ceil(recordCount * 1.0 / recordsPerPage);
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("currentPage", page);
        request.setAttribute("pattern", pattern);
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("user-search-result.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            out.print("<h1>How could you find this page?</h1>");
            out.print("<p>+1 easter egg</p>");
        }
    }
}