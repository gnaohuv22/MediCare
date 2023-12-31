/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Models;

/**
 *
 * @author tubinh
 */
public class ServiceTag {
    private String id, nametag, description, departmentId;

    public ServiceTag() {
    }

    public ServiceTag(String id) { // tubinh add
        this.id = id;
    }

    public ServiceTag(String id, String nametag, String description, String departmentId) {
        this.id = id;
        this.nametag = nametag;
        this.description = description;
        this.departmentId = departmentId;
    }
    public ServiceTag(String id, String nametag) {
        this.id = id;
        this.nametag = nametag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNametag() {
        return nametag;
    }

    public void setNametag(String nametag) {
        this.nametag = nametag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "ServiceTag{" + "id=" + id + ", nametag=" + nametag + ", description=" + description + ", departmentId=" + departmentId + '}';
    }
    
}
