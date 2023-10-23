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
    private String id, scheduleId, slotId, slotStatus, startTime;

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

    @Override
    public String toString() {
        return "ScheduleDetail{" + "id=" + id + ", scheduleId=" + scheduleId + ", slotId=" + slotId + ", slotStatus=" + slotStatus + ", startTime=" + startTime + '}';
    }
    
}
