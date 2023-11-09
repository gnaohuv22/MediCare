/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.layout.font.FontProvider;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author hoang
 */
public class DoctorProfileToPDFServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/pdf");

        try {
            //Get HTML string from JSP file
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/doctor-profile.jsp");

            StringWriter writer = new StringWriter();
            PrintWriter out = new PrintWriter(writer);
            dispatcher.include(request, new HttpServletResponseWrapper(response) {
                @Override
                public PrintWriter getWriter() {
                    return out;
                }
            });
            
            //Create font
            String fontPath = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-Regular.otf");
            FontProgram fontProgram = FontProgramFactory.createFont(fontPath);
            
            //Create FontProvider and add the font
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFont(fontProgram, "UTF-8");
            
            //Create ConverterProperties and set the FontProvider
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(fontProvider);

            //Convert HTML to PDF
            ByteArrayInputStream htmlStream = new ByteArrayInputStream(writer.toString().getBytes("UTF-8"));
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(htmlStream, pdfStream, converterProperties);

            //Send PDF to client
            byte[] pdfBytes = pdfStream.toByteArray();
            try (ServletOutputStream sos = response.getOutputStream()) {
                sos.write(pdfBytes);
                sos.flush();
            }
        } catch (ServletException | IOException e) {
            System.out.println("DoctorProfileToPDFServlet: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
