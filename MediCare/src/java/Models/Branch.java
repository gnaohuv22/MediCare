/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class Branch {
    private String id;
    private String name;
    private String description;
    private String locateId;

    public Branch() {
    }

    public Branch(String id, String name, String description, String locateId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.locateId = locateId;
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

    public String getLocateId() {
        return locateId;
    }

    public void setLocateId(String locateId) {
        this.locateId = locateId;
    }
    
}
