/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hoang
 */
public class AccessLog {
    private String id, ipAddress, accessTime, accessCount;

    public AccessLog() {
    }

    public AccessLog(String id, String ipAddress, String accessTime, String accessCount) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.accessTime = accessTime;
        this.accessCount = accessCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public String getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(String accessCount) {
        this.accessCount = accessCount;
    }
    
    
}
