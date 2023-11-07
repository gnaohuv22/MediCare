/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class SubLevelMenu {
    private String id;
    private String content;
    private String parentId;
    private String locateId;
    private String href;
    private String icon;

    public SubLevelMenu() {
    }

    public SubLevelMenu(String id, String content, String parentId, String locateId, String href, String icon) {
        this.id = id;
        this.content = content;
        this.parentId = parentId;
        this.locateId = locateId;
        this.href = href;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLocateId() {
        return locateId;
    }

    public void setLocateId(String locateId) {
        this.locateId = locateId;
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
    
    
}
