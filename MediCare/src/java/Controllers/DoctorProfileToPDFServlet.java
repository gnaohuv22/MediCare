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
//        HttpSession session = request.getSession();

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
            String fontPath1 = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-Regular.otf");
            String fontPath2 = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-Light.otf");
            String fontPath3 = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-Medium.otf");
            String fontPath4 = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-Black.otf");
            String fontPath5 = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-Bold.otf");
            String fontPath6 = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-ExtraBold.otf");
            String fontPath7 = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-SemiBold.otf");
            String fontPath8 = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-Thin.otf");
            String fontPath9 = getServletContext().getRealPath("assets/fonts/Inter Desktop/Inter-Italic.otf");

            FontProgram fontProgram1 = FontProgramFactory.createFont(fontPath1);
            FontProgram fontProgram2 = FontProgramFactory.createFont(fontPath2);
            FontProgram fontProgram3 = FontProgramFactory.createFont(fontPath3);
            FontProgram fontProgram4 = FontProgramFactory.createFont(fontPath4);
            FontProgram fontProgram5 = FontProgramFactory.createFont(fontPath5);
            FontProgram fontProgram6 = FontProgramFactory.createFont(fontPath6);
            FontProgram fontProgram7 = FontProgramFactory.createFont(fontPath7);
            FontProgram fontProgram8 = FontProgramFactory.createFont(fontPath8);
            FontProgram fontProgram9 = FontProgramFactory.createFont(fontPath9);

            //Create FontProvider and add the font
            FontProvider fontProvider = new FontProvider();
            fontProvider.addFont(fontProgram1);
            fontProvider.addFont(fontProgram2);
            fontProvider.addFont(fontProgram3);
            fontProvider.addFont(fontProgram4);
            fontProvider.addFont(fontProgram5);
            fontProvider.addFont(fontProgram6);
            fontProvider.addFont(fontProgram7);
            fontProvider.addFont(fontProgram8);
            fontProvider.addFont(fontProgram9);

            //Create ConverterProperties and set the FontProvider
            ConverterProperties converterProperties = new ConverterProperties();
            converterProperties.setFontProvider(fontProvider);

            String htmlString = writer.toString();
            System.out.println(htmlString); // In ra mã HTML

            //Convert HTML to PDF
            ByteArrayInputStream htmlStream = new ByteArrayInputStream(htmlString.getBytes("UTF-8"));
            ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
            HtmlConverter.convertToPdf(htmlStream, pdfStream, converterProperties);

            //Send PDF to client
            byte[] pdfBytes = pdfStream.toByteArray();
            try ( ServletOutputStream sos = response.getOutputStream()) {
                sos.write(pdfBytes);
                sos.flush();
            }
//            session.removeAttribute("numberOfRatings");
//            session.removeAttribute("overallRating");
        } catch (ServletException | IOException e) {
            System.out.println("DoctorProfileToPDFServlet: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
