/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Models;

/**
 *
 * @author tubinh
 */
public class Branch {
    private String id, name, description, localId;

    public Branch() {
    }

    public Branch(String id, String name, String description, String localId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.localId = localId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    @Override
    public String toString() {
        return "Branch{" + "id=" + id + ", name=" + name + ", description=" + description + ", localId=" + localId + '}';
    }
    
}
