package ru.jogging.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "morningjogging")
public class MorningJogging implements Serializable {
    private Long id;
    private User user;
    private Date dateJogging;
    private double numberOfMinutes;

    public MorningJogging() {
    }

    @Id
    @GeneratedValue
    @Column(nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "datejogging", nullable = false)
    public Date getDateJogging() {
        return dateJogging;
    }

    public void setDateJogging(Date dateJogging) {
        this.dateJogging = dateJogging;
    }

    @Column(name = "numberofminutes", nullable = false)
    public double getNumberOfMinutes() {
        return numberOfMinutes;
    }

    public void setNumberOfMinutes(double numberOfMinutes) {
        this.numberOfMinutes = numberOfMinutes;
    }
}
