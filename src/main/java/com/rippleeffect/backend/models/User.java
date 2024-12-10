package com.rippleeffect.backend.models;

import java.util.List;
import java.util.stream.Collectors;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String picture;
    private int level;
    private List<String> completedChallenges;
    private List<String> missedChallenges;

    public User(){}

    public User(Integer id, String username, String email, String picture, int level, List<String> completedChallenges, List<String> missedChallenges) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.picture = picture;
        this.level = level;
        this.completedChallenges = completedChallenges;
        this.missedChallenges = missedChallenges;
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

    public List<String> getCompletedChallenges() {
        return completedChallenges;
    }

    public void setCompletedChallenges(List<String> completedChallenges) {
        this.completedChallenges = completedChallenges;
    }

    public List<String> getMissedChallenges() {
        return missedChallenges;
    }

    public void setMissedChallenges(List<String> missedChallenges) {
        this.missedChallenges = missedChallenges;
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

    Collectors Collectors = null;

    // Extract the integer IDs from completed challenge URLs
    public List<Integer> extractCompletedChallengeIds() {
        return completedChallenges.stream()
                .map(url -> Integer.parseInt(url.substring(url.lastIndexOf("/") + 1)))
                .collect(Collectors.toList());
    }

    // Extract the integer IDs from missed challenge URLs
    public List<Integer> extractMissedChallengeIds() {
        return missedChallenges.stream()
                .map(url -> Integer.parseInt(url.substring(url.lastIndexOf("/") + 1)))
                .collect(Collectors.toList());
    }

}
