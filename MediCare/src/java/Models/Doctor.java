/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author tubinh
 */
public class Doctor {

    private String id;
    private String email;
    private String password;
    private String displayName;
    private Branch branch;
    private String phone;
    private AcademicRank academicRank;
    private Certificate cetificate;
    private String salary;
    private String workplace;
    private String profilePicture;
    private String status;
    private String birthDate;
    private String gender;
    private String isDelete;
    
    public Doctor() {
    }

    public Doctor(String id, String email, String password, String displayName, Branch branch, String phone, AcademicRank academicRank, Certificate cetificate, String salary, String workplace, String profilePicture, String status, String birthDate, String gender, String isDelete) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.branch = branch;
        this.phone = phone;
        this.academicRank = academicRank;
        this.cetificate = cetificate;
        this.salary = salary;
        this.workplace = workplace;
        this.profilePicture = profilePicture;
        this.status = status;
        this.birthDate = birthDate;
        this.gender = gender;
        this.isDelete = isDelete;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AcademicRank getAcademicRank() {
        return academicRank;
    }

    public void setAcademicRank(AcademicRank academicRank) {
        this.academicRank = academicRank;
    }

    public Certificate getCetificate() {
        return cetificate;
    }

    public void setCetificate(Certificate cetificate) {
        this.cetificate = cetificate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
    

}
