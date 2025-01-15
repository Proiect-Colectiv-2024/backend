package com.rippleeffect.backend.models;

public class Post {

    private Integer id;

    private String username;

    private String postDescription;

    public Post() {
    }

    public Post(String username, String postDescription) {
        this.username = username;
        this.postDescription = postDescription;
    }

    // Getters and Setters
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

    public String getPostDescription() {
        return postDescription;
    }

    public void setPostDescription(String postDescription) {
        this.postDescription = postDescription;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", postDescription='" + postDescription + '\'' +
                '}';
    }
}
