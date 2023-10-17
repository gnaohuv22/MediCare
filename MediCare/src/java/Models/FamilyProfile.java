/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hoang
 */
public class FamilyProfile {

    private String profileId, email, name, birthDate, gender, address, provinceId, identity, medicalId, ethnic, phone, profilePicture, createdAt, relationId, ownerId;
    private Relationship relationship;

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public FamilyProfile() {
    }

    public FamilyProfile(String profileId, String email, String name, String birthDate, String gender, String address, String provinceId, String identity, String medicalId, String ethnic, String phone, String profilePicture, String createdAt, String relationId, String ownerId, Relationship relationship) {
        this.profileId = profileId;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.provinceId = provinceId;
        this.identity = identity;
        this.medicalId = medicalId;
        this.ethnic = ethnic;
        this.phone = phone;
        this.profilePicture = profilePicture;
        this.createdAt = createdAt;
        this.relationId = relationId;
        this.ownerId = ownerId;
        this.relationship = relationship;
    }

    public FamilyProfile( String email, String name, String birthDate, String gender, String address, String identity, String medicalId, String ethnic, String phone, String createdAt, String relationId, String ownerId) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.identity = identity;
        this.medicalId = medicalId;
        this.ethnic = ethnic;
        this.phone = phone;
        this.createdAt = createdAt;
        this.relationId = relationId;
        this.ownerId = ownerId;
    }
    
    

    public FamilyProfile(String profileId) {
        this.profileId = profileId;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
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

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "FamilyProfile{" + "profileId=" + profileId + ", email=" + email + ", name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", address=" + address + ", provinceId=" + provinceId + ", identity=" + identity + ", medicalId=" + medicalId + ", ethnic=" + ethnic + ", phone=" + phone + ", profilePicture=" + profilePicture + ", createdAt=" + createdAt + ", relationId=" + relationId + ", ownerId=" + ownerId + '}';
    }
}
