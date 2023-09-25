/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hoang
 */
public class BannerDetails {
    private String id, number, information, bannerId;

    public BannerDetails() {
    }

    public BannerDetails(String id, String number, String information, String bannerId) {
        this.id = id;
        this.number = number;
        this.information = information;
        this.bannerId = bannerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }
    
    @Override
    public String toString() {
        return "{" + id + ", " + number + ", " + information + ", " + bannerId + "}";
    }
}
