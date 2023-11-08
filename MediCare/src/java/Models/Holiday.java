/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author tubinh
 */
public class Holiday {

    private String id, name, fromDate, toDate, createdAt, modyfyAt, createdBy, modifyBy;

    public Holiday() {
    }

    public Holiday(String id, String name, String fromDate, String toDate, String createdAt, String modyfyAt, String createdBy, String modifyBy) {
        this.id = id;
        this.name = name;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.createdAt = createdAt;
        this.modyfyAt = modyfyAt;
        this.createdBy = createdBy;
        this.modifyBy = modifyBy;
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

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModyfyAt() {
        return modyfyAt;
    }

    public void setModyfyAt(String modyfyAt) {
        this.modyfyAt = modyfyAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(String modifyBy) {
        this.modifyBy = modifyBy;
    }

    @Override
    public String toString() {
        return "Holiday{" + "id=" + id + ", name=" + name + ", fromDate=" + fromDate + ", toDate=" + toDate + ", createdAt=" + createdAt + ", modyfyAt=" + modyfyAt + ", createdBy=" + createdBy + ", modifyBy=" + modifyBy + '}';
    }

}
