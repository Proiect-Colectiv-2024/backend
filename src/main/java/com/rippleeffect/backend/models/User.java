package com.rippleeffect.backend.models;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String picture;
    private int level;
    private List<Challenge> completedChallenges;
    private List<Challenge> missedChallenges;
    private List<Challenge> inProgressChallenges;

    public User(){}

    public User(Integer id, String username, String email, String picture, int level, List<Challenge> completedChallenges, List<Challenge> missedChallenges, List<Challenge> inProgressChallenges) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.picture = picture;
        this.level = level;
        this.completedChallenges = completedChallenges;
        this.missedChallenges = missedChallenges;
        this.inProgressChallenges = inProgressChallenges;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<Challenge> getCompletedChallenges() {
        return completedChallenges;
    }

    public void setCompletedChallenges(List<Challenge> completedChallenges) {
        this.completedChallenges = completedChallenges;
    }

    public List<Challenge> getMissedChallenges() {
        return missedChallenges;
    }

    public void setMissedChallenges(List<Challenge> missedChallenges) {
        this.missedChallenges = missedChallenges;
    }

    public List<Challenge> getInProgressChallenges() {
        return inProgressChallenges;
    }

    public void setInProgressChallenges(List<Challenge> inProgressChallenges) {
        this.inProgressChallenges = inProgressChallenges;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", level=" + level +
                ", completedChallenges=" + completedChallenges +
                ", missedChallenges=" + missedChallenges +
                '}';
    }
}
