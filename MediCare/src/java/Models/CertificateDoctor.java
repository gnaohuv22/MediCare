/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hoang
 */
public class CertificateDoctor {
    private String certId, doctorId;
    private String certName;

    public CertificateDoctor(String certId, String doctorId, String certName) {
        this.certId = certId;
        this.doctorId = doctorId;
        this.certName = certName;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public CertificateDoctor() {
    }

    public CertificateDoctor(String certId, String doctorId) {
        this.certId = certId;
        this.doctorId = doctorId;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    
    
}
