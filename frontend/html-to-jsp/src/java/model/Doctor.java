/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hoang
 */
public class Doctor {
    private String id, email, displayName, branchId, phone, ARId, CVId, salary, workplace, profilePicture, status;

    public Doctor() {
    }

    public Doctor(String id, String email, String displayName, String branchId, String phone, String ARId, String CVId, String salary, String workplace, String profilePicture, String status) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.branchId = branchId;
        this.phone = phone;
        this.ARId = ARId;
        this.CVId = CVId;
        this.salary = salary;
        this.workplace = workplace;
        this.profilePicture = profilePicture;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getARId() {
        return ARId;
    }

    public void setARId(String ARId) {
        this.ARId = ARId;
    }

    public String getCVId() {
        return CVId;
    }

    public void setCVId(String CVId) {
        this.CVId = CVId;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
