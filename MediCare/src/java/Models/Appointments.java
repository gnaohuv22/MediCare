/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hoang
 */
public class Appointments {
    private String id, userId, doctorId, serviceId, plannedAt, status, branchId, createdAt, symptoms, profileId;
    private ServiceTag st;
    private FamilyProfile fp;

    public Appointments() {
    }

    public Appointments(String id, String plannedAt, String status, String branchId, String createdAt, String symptoms, ServiceTag st, FamilyProfile fp) {
        this.id = id;
        this.plannedAt = plannedAt;
        this.status = status;
        this.branchId = branchId;
        this.createdAt = createdAt;
        this.symptoms = symptoms;
        this.st = st;
        this.fp = fp;
    }

    public Appointments(String id, String userId, String doctorId, String serviceId, String plannedAt, String status) {
        this.id = id;
        this.userId = userId;
        this.doctorId = doctorId;
        this.serviceId = serviceId;
        this.plannedAt = plannedAt;
        this.status = status;
    }
    
    public Appointments(String doctorId, String serviceId, String plannedAt,String branchId, String symptoms) {
        this.doctorId = doctorId;
        this.serviceId = serviceId;
        this.plannedAt = plannedAt;
        this.branchId = branchId;
        this.symptoms = symptoms;
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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    @Override
    public String toString() {
        return "Appointments{" + "id=" + id + ", userId=" + userId + ", doctorId=" + doctorId + ", serviceId=" + serviceId + ", plannedAt=" + plannedAt + ", status=" + status + ", branchId=" + branchId + ", createdAt=" + createdAt + ", symptoms=" + symptoms + ", profileId=" + profileId + '}';
    }

    public ServiceTag getSt() {
        return st;
    }

    public void setSt(ServiceTag st) {
        this.st = st;
    }

    public FamilyProfile getFp() {
        return fp;
    }

    public void setFp(FamilyProfile fp) {
        this.fp = fp;
    }
    
}
