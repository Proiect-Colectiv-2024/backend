package com.rippleeffect.backend.controllers;

import com.rippleeffect.backend.models.Challenge;
import com.rippleeffect.backend.service.ChallengeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
@CrossOrigin(origins = "http://localhost:8081")
public class ChallengeController {
    private final ChallengeService challengeService;

    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public List<Challenge> getChallenges() {
        return challengeService.getChallenges();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Integer id) {
        Challenge player = challengeService.getChallengeById(id);
        return ResponseEntity.ok(player);
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<String> completeChallenge(@PathVariable Integer id) {
        boolean isUpdated = challengeService.completeChallenge(id);
        if (isUpdated) {
            return ResponseEntity.ok("Challenge status updated to 'completed'.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update challenge.");
        }
    }

    @PostMapping("/{id}/miss")
    public ResponseEntity<String> missChallenge(@PathVariable Integer id) {
        boolean isUpdated = challengeService.completeChallenge(id);
        if (isUpdated) {
            return ResponseEntity.ok("Challenge status updated to 'missed'.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update challenge.");
        }
    }
}
