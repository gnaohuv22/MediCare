/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hoang
 */
public class NewsCategory {
    private String id, name, parentId, href, icon, locateId;

    public NewsCategory() {
    }
    
    public NewsCategory(String id, String name, String parentId, String href, String icon, String locateId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.href = href;
        this.icon = icon;
        this.locateId = locateId;
    }
    
    public NewsCategory(String id, String name, String parentId, String href) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.href = href;
    }
    
    public NewsCategory(String id, String name, String href) {
        this.id = id;
        this.name = name;
        this.href = href;
    }

    public NewsCategory(String id, String name, String href, String icon, String locateId) {
        this.id = id;
        this.name = name;
        this.href = href;
        this.icon = icon;
        this.locateId = locateId;
    }
    //thu

    public NewsCategory(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLocateId() {
        return locateId;
    }

    public void setLocateId(String locateId) {
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    
}
