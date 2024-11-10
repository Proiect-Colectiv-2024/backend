package src.main.java.com.rippleeffect.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;
    private String picture;
    private int level;
    private String story;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Challenge> challenges;
    // Constructors, Getters, Setters, toString
    public User() {}

    public User(String username, String email, String password, String picture, int level, String story) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.level = level;
        this.story = story;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public void setChallenges(List<Challenge> challenges) {
        this.challenges = challenges;
    }
}
