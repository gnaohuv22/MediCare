/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author DELL
 */
public class GetReviews {
    private Reviews reviews;
    private User user;
    private Doctor doctor;

    public GetReviews() {
    }

    public GetReviews(Reviews reviews, User user, Doctor doctor) {
        this.reviews = reviews;
        this.user = user;
        this.doctor = doctor;
    }
 
    public Reviews getReviews() {
        return reviews;
    }

    public void setReviews(Reviews reviews) {
        this.reviews = reviews;
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
    
    
}
