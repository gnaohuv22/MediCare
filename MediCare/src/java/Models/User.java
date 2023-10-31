/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class User {

    private String id;
    private String email;
    private String password;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private Province province;
    private String identity;
    private String medicalId;
    private String ethnic;
    private String phone;
    private String profilePicture;
    private String createdAt;
    private String createBy;
    private String modifyAt;
    private String modifyBy;

    public User() {
    }

    public User(String id, String email, String password, String name, String birthDate, String gender, String address, Province province, String identity, String medicalId, String ethnic, String phone, String profilePicture, String createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.province = province;
        this.identity = identity;
        this.medicalId = medicalId;
        this.ethnic = ethnic;
        this.phone = phone;
        this.profilePicture = profilePicture;
        this.createdAt = createdAt;
    }

    public User(String email, String password, String name, String birthDate, String gender, String address, String phone, String createdAt) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.createdAt = createdAt;
    }

    public User(String id, String email, String password, String name, String birthDate, String gender, String address, Province province, String identity, String medicalId, String ethnic, String phone, String profilePicture) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.province = province;
        this.identity = identity;
        this.medicalId = medicalId;
        this.ethnic = ethnic;
        this.phone = phone;
        this.profilePicture = profilePicture;
    }

    public User(String id, String email, String password, String name, String birthDate, String gender, String address, Province province, String identity, String medicalId, String ethnic, String phone, String profilePicture, String createdAt, String createBy, String modifyAt, String modifyBy) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.province = province;
        this.identity = identity;
        this.medicalId = medicalId;
        this.ethnic = ethnic;
        this.phone = phone;
        this.profilePicture = profilePicture;
        this.createdAt = createdAt;
        this.createBy = createBy;
        this.modifyAt = modifyAt;
        this.modifyBy = modifyBy;
    }

    public User(String id, String email, String name, String birthDate, String gender, String address, Province province, String identity, String medicalId, String ethnic, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.province = province;
        this.identity = identity;
        this.medicalId = medicalId;
        this.ethnic = ethnic;
        this.phone = phone;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public User(String email, String password, String name, String createdAt) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.createdAt = createdAt;
    }

    public User(String id) {
        this.id = id;
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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMedicalId() {
        return medicalId;
    }

    public void setMedicalId(String medicalId) {
        this.medicalId = medicalId;
    }

    public String getEthnic() {
        return ethnic;
    }

    public void setEthnic(String ethnic) {
        this.ethnic = ethnic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
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
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", address=" + address + ", identity=" + identity + ", medicalId=" + medicalId + ", ethinic=" + ethnic + ", phone=" + phone + ", profilePicture=" + profilePicture + ", createdAt=" + createdAt + '}';
    }

}
