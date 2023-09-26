/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Models;

/**
 *
 * @author tubinh
 */
public class Certificate {
    private String id;
    private String name;
    private String wage;

    public Certificate() {
    }

    public Certificate(String certId, String name, String wage) {
        this.id = certId;
        this.name = name;
        this.wage = wage;
    }

    public Certificate(String certId, String name) {
        this.id = certId;
        this.name = name;
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

    public String getWage() {
        return wage;
    }

    public void setWage(String wage) {
        this.wage = wage;
    }

    @Override
    public String toString() {
        return "Certificate{" + "Id=" + id + ", name=" + name + ", wage=" + wage + '}';
    }

    
    
}
