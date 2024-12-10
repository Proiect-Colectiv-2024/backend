package com.rippleeffect.backend.models;

public class Challenge {
    private Integer id;
    private String description;
    private String status;
    private String type;

    public Challenge() {}

    public Challenge(Integer id, String description, String status, String type) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
