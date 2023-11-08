/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author Asus
 */
public class Base64Encoding {

    public static String convertImageToBase64(Part filePart) {
        String imageFileName = null;
        try {
            if (!filePart.getSubmittedFileName().equals("")) {
                if (filePart.getSubmittedFileName().toLowerCase().endsWith(".jpg") || filePart.getSubmittedFileName().toLowerCase().endsWith(".png")) {
                    if (filePart.getSize() < 50 * 1024) {
                        InputStream imgStr = filePart.getInputStream();
                        byte[] fileContent = IOUtils.toByteArray(imgStr);
                        imageFileName = Base64.getEncoder().encodeToString(fileContent);
                    }
                }
            }
        } catch (IOException e) {
        }
        return imageFileName;
    }

    public static boolean isFileValid(Part filePart) {
        if (!filePart.getSubmittedFileName().equals("")) {
            if (filePart.getSubmittedFileName().toLowerCase().endsWith(".jpg") || filePart.getSubmittedFileName().toLowerCase().endsWith(".png")) {
                if (filePart.getSize() < 50 * 1024) {
                    return true;
                }
            }
            // Tất cả các kiểm tra đều hợp lệ
        }
        return false;
    }
}
