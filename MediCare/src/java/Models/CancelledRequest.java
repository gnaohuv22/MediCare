/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class CancelledRequest {
    private String id;
    private String appointmentId;
    private String totalRefund;
    private String userId;
    private String specialistId;
    private String cancelledAt;

    public CancelledRequest() {
    }

    public CancelledRequest(String id, String appointmentId, String totalRefund, String userId, String specialistId, String cancelledAt) {
        this.id = id;
        this.appointmentId = appointmentId;
        this.totalRefund = totalRefund;
        this.userId = userId;
        this.specialistId = specialistId;
        this.cancelledAt = cancelledAt;
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

    public String getTotalRefund() {
        return totalRefund;
    }

    public void setTotalRefund(String totalRefund) {
        this.totalRefund = totalRefund;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public String getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(String cancelledAt) {
        this.cancelledAt = cancelledAt;
    }
    
}
