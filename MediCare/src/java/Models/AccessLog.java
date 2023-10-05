/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class AccessLog {
    private String id;
    private String name;
    private String accessCount;

    public AccessLog() {
    }

    public AccessLog(String id, String name, String accessCount) {
        this.id = id;
        this.name = name;
        this.accessCount = accessCount;
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

    public String getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(String accessCount) {
        this.accessCount = accessCount;
    }
    
}
