/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Models;

/**
 *
 * @author tubinh
 */
public class ScheduleDetail {
    private String id, scheduleId, slotId, slotStatus, startTime, endTime, workDate, isDelete;

    public ScheduleDetail() {
    }

    public ScheduleDetail(String id, String scheduleId, String slotId, String slotStatus, String startTime) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.slotId = slotId;
        this.slotStatus = slotStatus;
        this.startTime = startTime;
    }

    public ScheduleDetail(String slotId, String startTime) {
        this.slotId = slotId;
        this.startTime = startTime;
    }

    public ScheduleDetail(String slotId, String slotStatus, String startTime) {
        this.slotId = slotId;
        this.slotStatus = slotStatus;
        this.startTime = startTime;
    }
    
    public ScheduleDetail(String slotId, String startTime, String endTime, int n) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSlotId() {
        return slotId;
    }

    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    public String getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(String slotStatus) {
        this.slotStatus = slotStatus;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public ScheduleDetail(String id, String scheduleId, String slotId, String slotStatus, String workDate, int n) {
        this.id = id;
        this.scheduleId = scheduleId;
        this.slotId = slotId;
        this.slotStatus = slotStatus;
        this.workDate = workDate;
    }
    
    @Override
    public String toString() {
        return "ScheduleDetail{" + "id=" + id + ", scheduleId=" + scheduleId + ", slotId=" + slotId + ", slotStatus=" + slotStatus + ", startTime=" + startTime + '}';
    }
    
}
