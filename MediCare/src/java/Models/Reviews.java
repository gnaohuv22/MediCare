/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Models;

/**
 *
 * @author tubinh
 */
public class Reviews {
    private String id, userId, doctorId, appointmentId, rating, reviewContent, createdAt;
    User user;
    Doctor doctor;
    Appointments appointment;
    public Reviews() {
    }

    public Reviews(String id, String userId, String doctorId, String appointmentId, String rating, String reviewContent, String createdAt) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.appointmentId = appointmentId;
        this.rating = rating;
        this.reviewContent = reviewContent;
        this.createdAt = createdAt;
    }

    public Reviews(String id, User user, Doctor doctor, Appointments appointment, String rating, String reviewContent, String createdAt) {
        this.id = id;
        this.rating = rating;
        this.reviewContent = reviewContent;
        this.createdAt = createdAt;
        this.user = user;
        this.doctor = doctor;
        this.appointment = appointment;
    }

    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Appointments getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointments appointment) {
        this.appointment = appointment;
    }
    
    @Override
    public String toString() {
        return "Reviews{" + "id=" + id + ", userId=" + userId + ", doctorId=" + doctorId + ", appointmentId=" + appointmentId + ", rating=" + rating + ", reviewContent=" + reviewContent + ", createdAt=" + createdAt + '}';
    }
    
}
