/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class Appointments {
    private String id, appointmentDay, appointmentTime;;
    private User user;
    private Doctor doctor;
    private ServiceTag serviceTag;
    private String plannedAt, createdAt;
    private String status;
    private String symptoms;
    private Branch branch; // tubinh add
    private FamilyProfile profile; // tubinh add

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    
    
    public String getAppointmentDay() {
        return appointmentDay;
    }

    public void setAppointmentDay(String appointmentDay) {
        this.appointmentDay = appointmentDay;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    
    public FamilyProfile getProfile() {
        return profile;
    }

    public void setProfile(FamilyProfile profile) {
        this.profile = profile;
    }
    
    public Appointments() {
    }

    public Appointments(Doctor doctor, ServiceTag serviceTag, String plannedAt,Branch branch, String symptoms) {
        this.doctor = doctor;
        this.serviceTag = serviceTag;
        this.plannedAt = plannedAt;
        this.branch = branch;
        this.symptoms = symptoms;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ServiceTag getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(ServiceTag serviceTag) {
        this.serviceTag = serviceTag;
    }

    public String getPlannedAt() {
        return plannedAt;
    }

    public void setPlannedAt(String plannedAt) {
        this.plannedAt = plannedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Appointments(String id, User user, Doctor doctor, ServiceTag serviceTag, String plannedAt, String status) {
        this.id = id;
        this.user = user;
        this.doctor = doctor;
        this.serviceTag = serviceTag;
        this.plannedAt = plannedAt;
        this.status = status;
    }

      public Appointments(String id, User user, Doctor doctor, ServiceTag serviceTag, String plannedAt, String status, Branch branch, String createdAt, String symptoms, FamilyProfile profile, String appointmentDay, String appointmentTime) {
        this.id = id;
        this.plannedAt = plannedAt;
        this.status = status;
        this.createdAt = createdAt;
        this.symptoms = symptoms;
        this.user = user;
        this.doctor = doctor;
        this.serviceTag = serviceTag;
        this.branch = branch;
        this.profile = profile;
        this.appointmentDay = appointmentDay;
        this.appointmentTime = appointmentTime;
    }
    
}
