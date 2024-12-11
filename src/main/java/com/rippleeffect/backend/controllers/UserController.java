package com.rippleeffect.backend.controllers;

import com.rippleeffect.backend.models.User;
import com.rippleeffect.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        try {
            User user = userService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        try {
            User user = userService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/{userId}/challenges/{challengeId}/complete")
    public ResponseEntity<String> completeUserChallenge(@PathVariable Integer userId, @PathVariable Integer challengeId) {
        boolean isUpdated = userService.completeChallengeForUser(userId, challengeId);
        if (isUpdated) {
            return ResponseEntity.ok("Challenge marked as completed for user.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Challenge already completed or missed.");
        }
    }

    @PostMapping("/{userId}/challenges/{challengeId}/miss")
    public ResponseEntity<String> missUserChallenge(@PathVariable Integer userId, @PathVariable Integer challengeId) {
        boolean isUpdated = userService.missChallengeForUser(userId, challengeId);
        if (isUpdated) {
            return ResponseEntity.ok("Challenge marked as missed for user.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Challenge already completed or missed.");
        }
    }
}
