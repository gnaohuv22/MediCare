/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hoang
 */
public class BillingHistory {
    private String id, appointmentId, totalCash, createdAt;
    Appointments appointment;

    public BillingHistory() {
        appointment = new Appointments();
    }

    public BillingHistory(String id, String appointmentId, String totalCash, String createdAt) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.totalCash = totalCash;
        this.createdAt = createdAt;
    }

    public Appointments getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointments appointment) {
        this.appointment = appointment;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getTotalCash() {
        return totalCash;
    }

    public void setTotalCash(String totalCash) {
        this.totalCash = totalCash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    
    
}
