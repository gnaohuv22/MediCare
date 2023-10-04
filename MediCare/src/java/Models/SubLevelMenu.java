/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author hoang
 */
public class SubLevelMenu {
    private String id, content, parentId, categoryId, category, href, icon;

    public SubLevelMenu() {
    }

    public SubLevelMenu(String id, String content, String parentId, String categoryId, String category, String href, String icon) {
        this.id = id;
        this.content = content;
        this.parentId = parentId;
        this.categoryId = categoryId;
        this.category = category;
        this.href = href;
        this.icon = icon;
    }

    public SubLevelMenu(String id, String content, String parentId, String categoryId, String href, String icon) {
        this.id = id;
        this.content = content;
        this.parentId = parentId;
        this.categoryId = categoryId;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
