package src.main.java.com.rippleeffect.backend.model;

import jakarta.persistence.*;

@Entity
public class Challenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cid;

    private String description;
    private String status; // Options: "completed", "in progress", "missed"
    private String type; // Options: "daily" or "weekly"

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors, Getters, Setters, toString
    public Challenge() {}

    public Challenge(String description, String status, String type, User user) {
        this.description = description;
        this.status = status;
        this.type = type;
        this.user = user;
    }

    // Getters and Setters

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Challenge{cid=" + cid + ", description='" + description + "', status='" + status + "', type='" + type + "', user=" + user + "}";
    }

}
