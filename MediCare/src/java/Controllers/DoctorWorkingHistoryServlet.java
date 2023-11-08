/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package Controllers;

import DAL.AppointmentDAO;
import DAL.AppointmentsDAO;
import Models.Appointments;
import Models.Doctor;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author hoang
 */
public class DoctorWorkingHistoryServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("doctorLoggedIn") == null) {
            session.setAttribute("error", "Bạn cần đăng nhập để truy cập trang này.");
            response.sendRedirect("user-login");
        } else {
            Doctor d = (Doctor)session.getAttribute("d");
            AppointmentsDAO ad = new AppointmentsDAO();
            
            ArrayList<Appointments> appointments = ad.getListAppointmentByDoctor(d.getId());
            Set<Integer> statusSet = new HashSet<>();
            Set<String> serviceSet = new HashSet<>();
            for (Appointments a : appointments) {
                int status = Integer.parseInt(a.getStatus());
                statusSet.add(status);
                serviceSet.add(a.getSt().getNametag());
            }
            List<Integer> sortedStatusSet = new ArrayList<>(statusSet);
            Collections.sort(sortedStatusSet);
            
            request.setAttribute("statusSet", sortedStatusSet);
            request.setAttribute("serviceSet", serviceSet);
            request.setAttribute("appointments", appointments);
            request.getRequestDispatcher("doctor-appointment-history.jsp").forward(request, response);
            
            
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }
}
