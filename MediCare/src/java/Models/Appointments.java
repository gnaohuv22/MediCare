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
    private String id;
    private User user;
    private Doctor doctor;
    private ServiceTag serviceTag;
    private String plannedAt;
    private String status;
    private String symptoms;
    private Branch branch; // tubinh add
    private FamilyProfile profile; // tubinh add

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

}