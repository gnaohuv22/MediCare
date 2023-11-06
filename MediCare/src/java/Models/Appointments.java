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

    private String id, appointmentDay, appointmentTime;
    private User user;
    private Doctor doctor;
    private ServiceTag serviceTag;
    private String plannedAt, createdAt;
    private String status;
    private String symptoms;
    private Branch branch; // tubinh add
    private FamilyProfile profile; // tubinh add
    private String createBy, modifyAt, modifyBy;
    private String userId, doctorId, serviceId, branchId, profileId;
    private ServiceTag st;
    private FamilyProfile fp;

    public Appointments(String id, User user, Doctor doctor, ServiceTag serviceTag, String plannedAt, String status, String createBy, String modifyAt, String modifyBy) {
        this.id = id;
        this.user = user;
        this.doctor = doctor;
        this.serviceTag = serviceTag;
        this.plannedAt = plannedAt;
        this.status = status;
        this.createBy = createBy;
        this.modifyAt = modifyAt;
        this.modifyBy = modifyBy;
    }

    public Appointments(String plannedAt, String symptoms, ServiceTag st, FamilyProfile fp) {
        this.plannedAt = plannedAt;
        this.symptoms = symptoms;
        this.st = st;
        this.fp = fp;
    }
    

    //thu
    public Appointments(Doctor doctor, ServiceTag serviceTag, String status, Branch branch) {
        this.doctor = doctor;
        this.serviceTag = serviceTag;
        this.status = status;
        this.branch = branch;
    }

    // tu binh
    public Appointments(Doctor doctor, FamilyProfile profile) {
        this.doctor = doctor;
        this.profile = profile;
    }

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

    public Appointments(Doctor doctor, ServiceTag serviceTag, String plannedAt, Branch branch, String symptoms) {
        this.doctor = doctor;
        this.serviceTag = serviceTag;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifyAt() {
        return modifyAt;
    }

    public void setModifyAt(String modifyAt) {
        this.modifyAt = modifyAt;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Override
    public String toString() {
        return "Appointments{" + "id=" + id + ", appointmentDay=" + appointmentDay + ", appointmentTime=" + appointmentTime + ", user=" + user + ", doctor=" + doctor + ", serviceTag=" + serviceTag + ", plannedAt=" + plannedAt + ", createdAt=" + createdAt + ", status=" + status + ", symptoms=" + symptoms + ", branch=" + branch + ", profile=" + profile + ", createBy=" + createBy + ", modifyAt=" + modifyAt + ", modifyBy=" + modifyBy + '}';
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
