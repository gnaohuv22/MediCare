/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Models;

/**
 *
 * @author tubinh
 */
public class Experience {
    private String id, doctorId, description;

    public Experience() {
    }

    public Experience(String description) {
        this.description = description;
    }
    
    public Experience(String id, String doctorId, String description) {
        this.id = id;
        this.doctorId = doctorId;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Experience{" + "id=" + id + ", doctorId=" + doctorId + ", description=" + description + '}';
    }
    

}
