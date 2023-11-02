/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.AppointmentsDAO;
import Models.Appointments;
import Models.Doctor;
import com.google.gson.Gson;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author hoang
 */
public class GetAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Doctor d = (Doctor) session.getAttribute("d");
        String date = request.getParameter("day");
        System.out.println("day:" + date);

        AppointmentsDAO ad = new AppointmentsDAO();
        ArrayList<Appointments> appointments = ad.getAppointmentsByDay(date, d.getId());
        String json = new Gson().toJson(appointments);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        int status = Integer.parseInt(request.getParameter("status"));

        AppointmentsDAO ad = new AppointmentsDAO();

        boolean updateSuccess = ad.updateAppointmentStatus(id, status);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        if (updateSuccess) {
            response.getWriter().write("Trạng thái đã được cập nhật thành công!");
        } else {
            response.getWriter().write("Có lỗi xảy ra khi cập nhật trạng thái.");
        }

    }
}
