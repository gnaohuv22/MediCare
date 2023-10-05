/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class GetAppointments {
    Appointments appointments;
    User user;
    Doctor doctor;
    ServiceTag serviceTag;

    public GetAppointments() {
    }

    public GetAppointments(Appointments appointments, User user, Doctor doctor, ServiceTag serviceTag) {
        this.appointments = appointments;
        this.user = user;
        this.doctor = doctor;
        this.serviceTag = serviceTag;
    }

    public Appointments getAppointments() {
        return appointments;
    }

    public void setAppointments(Appointments appointments) {
        this.appointments = appointments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public ServiceTag getServiceTag() {
        return serviceTag;
    }

    public void setServiceTag(ServiceTag serviceTag) {
        this.serviceTag = serviceTag;
    }
    
}
