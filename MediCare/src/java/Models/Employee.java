/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hoang
 */
public class Employee {

    private String id, email, password;
    Branch branch;
    String name, birthDate, gender, address, workplace;
    Province province;
    String phone, ethnic;
    EmployeeRole employeeRole;
    String createAt, createBy, modifyAt, modifyBy;
    String isDelete;

    public Employee() {
    }

    public Employee(String id, String email, String password, Branch branch, String name, String birthDate, String gender, String address, String workplace, Province province, String phone, String ethnic, EmployeeRole employeeRole) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.branch = branch;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.workplace = workplace;
        this.province = province;
        this.phone = phone;
        this.ethnic = ethnic;
        this.employeeRole = employeeRole;
    }

    public Employee(String id, String email, String password, Branch branch, String name, String birthDate, String gender, String address, String workplace, Province province, String phone, String ethnic, EmployeeRole employeeRole, String createAt, String createBy, String modifyAt, String modifyBy) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.branch = branch;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.workplace = workplace;
        this.province = province;
        this.phone = phone;
        this.ethnic = ethnic;
        this.employeeRole = employeeRole;
        this.createAt = createAt;
        this.createBy = createBy;
        this.modifyAt = modifyAt;
        this.modifyBy = modifyBy;
    }

    public Employee(String id, String email, Branch branch, String name, String birthDate, String gender, String address, String workplace, Province province, String phone, String ethnic, EmployeeRole employeeRole, String isDelete) {
        this.id = id;
        this.email = email;
        this.branch = branch;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.workplace = workplace;
        this.province = province;
        this.phone = phone;
        this.ethnic = ethnic;
        this.employeeRole = employeeRole;
        this.isDelete = isDelete;
    }

    public Employee(String id, String email, Branch branch, String name, String birthDate, String gender, String address, String workplace, Province province, String phone, String ethnic, EmployeeRole employeeRole, String createAt, String createBy, String modifyAt, String modifyBy, String isDelete) {
        this.id = id;
        this.email = email;
        this.branch = branch;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.workplace = workplace;
        this.province = province;
        this.phone = phone;
        this.ethnic = ethnic;
        this.employeeRole = employeeRole;
        this.createAt = createAt;
        this.createBy = createBy;
        this.modifyAt = modifyAt;
        this.modifyBy = modifyBy;
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

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public EmployeeRole getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", email=" + email + ", password=" + password + ", branch=" + branch + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", address=" + address + ", workplace=" + workplace + ", province=" + province + ", phone=" + phone + ", ethnic=" + ethnic + ", employeeRole=" + employeeRole + ", createAt=" + createAt + ", createBy=" + createBy + ", modifyAt=" + modifyAt + ", modifyBy=" + modifyBy + ", isDelete=" + isDelete + '}';
    }

}
