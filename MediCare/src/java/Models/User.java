/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author tubinh
 */
public class User {

    private String id;
    private String email;
    private String password;
    private String name;
    private String birthDate;
    private String gender;
    private String address;
    private String identity;
    private String medicalId;
    private String ethinic;
    private String phone;
    private String profilePicture;
    private String createdAt, provinceId;

    public User() {
    }

    public User(String id, String email, String password, String name, String birthDate, String gender, String address, String identity, String medicalId, String ethinic, String phone, String profilePicture, String createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.identity = identity;
        this.medicalId = medicalId;
        this.ethinic = ethinic;
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

    public User(String id, String email, String password, String name, String birthDate, String gender, String address, String provinceId, String identity, String medicalId, String ethinic, String phone, String profilePicture, String createdAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.provinceId = provinceId;
        this.identity = identity;
        this.medicalId = medicalId;
        this.ethinic = ethinic;
        this.phone = phone;
        this.profilePicture = profilePicture;
        this.createdAt = createdAt;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
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

    public String getEthinic() {
        return ethinic;
    }

    public void setEthinic(String ethinic) {
        this.ethinic = ethinic;
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

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", address=" + address + ", identity=" + identity + ", medicalId=" + medicalId + ", ethinic=" + ethinic + ", phone=" + phone + ", profilePicture=" + profilePicture + ", createdAt=" + createdAt + '}';
    }

}
