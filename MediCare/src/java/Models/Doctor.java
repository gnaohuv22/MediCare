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

    private String id, email, displayName, branchId, phone, ARId, CVId, salary, workplace, profilePicture, status, certificates;
    private String branchName, ARName, departmentId, departmentName, education, introduce, workHistory, startYear, password;
    private String gender, isDelete, birthDate;
    public Doctor() {
    }

    public Doctor(String id, String email, String password, String displayName, String branchName, String phone, String ARName, String certificates, String departmentId, String departmentName, String education, String introduce, String workHistory, String startYear, String salary, String workplace, String profilePicture, String status) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
        this.phone = phone;
        this.salary = salary;
        this.workplace = workplace;
        this.profilePicture = profilePicture;
        this.status = status;
        this.certificates = certificates;
        this.branchName = branchName;
        this.ARName = ARName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.education = education;
        this.introduce = introduce;
        this.workHistory = workHistory;
        this.startYear = startYear;
        this.password = password;
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

    public Doctor(String id, String email, String displayName, String branchId, String phone, String ARId, String CVId, String salary, String workplace, String profilePicture, String status, String certificates) {
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
        this.certificates = certificates;
    }

    public Doctor(String id, String email, String displayName, String branchId, String phone, String ARId, String CVId, String salary, String workplace, String profilePicture, String status, String branchName, String ARName, String certificates) {
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
        this.branchName = branchName;
        this.ARName = ARName;
        this.certificates = certificates;
    }

    public Doctor(String id, String email, String displayName, String branchId, String phone, String ARId, String CVId, String salary, String workplace, String profilePicture, String status, String branchName, String ARName, String certificates, String departmentId, String departmentName) {
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
        this.certificates = certificates;
        this.branchName = branchName;
        this.ARName = ARName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public Doctor(String id, String email, String displayName, String branchId, 
            String phone, String ARId, String CVId, String salary, String workplace, String profilePicture, 
            String status, String branchName, String ARName, String certificates, String departmentId, String departmentName, 
            String education, String introduce, String workHistory, String startYear) {
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
        this.certificates = certificates;
        this.branchName = branchName;
        this.ARName = ARName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.education = education;
        this.introduce = introduce;
        this.workHistory = workHistory;
        this.startYear = startYear;
    }
    public Doctor(String id, String email, String displayName, String branchId, 
            String phone, String ARId, String CVId, String salary, String workplace, String profilePicture, 
            String status, String password, String branchName, String ARName, String certificates, String departmentId, String departmentName, 
            String education, String introduce, String workHistory, String startYear) {
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
        this.certificates = certificates;
        this.branchName = branchName;
        this.ARName = ARName;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.education = education;
        this.introduce = introduce;
        this.workHistory = workHistory;
        this.startYear = startYear;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCertificates() {
        return certificates;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getARName() {
        return ARName;
    }

    public void setARName(String ARName) {
        this.ARName = ARName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(String workHistory) {
        this.workHistory = workHistory;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    
    @Override
    public String toString() {
        return "Doctor{" + "id=" + id + ", email=" + email + ", displayName=" + displayName + ", branchId=" + branchId + ", phone=" + phone + ", ARId=" + ARId + ", CVId=" + CVId + ", salary=" + salary + ", workplace=" + workplace + ", profilePicture=" + profilePicture + ", status=" + status + ", certificates=" + certificates + ", branchName=" + branchName + ", ARName=" + ARName + ", departmentId=" + departmentId + ", departmentName=" + departmentName + ", education=" + education + ", introduce=" + introduce + ", workHistory=" + workHistory + ", startYear=" + startYear + ", password=" + password + '}';
    }


    

    


    
    


}
