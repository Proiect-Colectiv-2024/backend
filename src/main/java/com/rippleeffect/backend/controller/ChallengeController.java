package com.rippleeffect.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/challenges")
public class ChallengeController {

    private final ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @PostMapping
    public ResponseEntity<Challenge> createChallenge(@RequestBody Challenge challenge) {
        Challenge createdChallenge = challengeService.saveChallenge(challenge);
        return ResponseEntity.ok(createdChallenge);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Challenge> getChallengeById(@PathVariable Long id) {
        Challenge challenge = challengeService.getChallengeById(id);
        if (challenge != null) {
            return ResponseEntity.ok(challenge);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Challenge>> getAllChallenges() {
        List<Challenge> challenges = challengeService.getAllChallenges();
        return ResponseEntity.ok(challenges);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Challenge> updateChallenge(@PathVariable Long id, @RequestBody Challenge challengeDetails) {
        Challenge updatedChallenge = challengeService.updateChallenge(id, challengeDetails);
        if (updatedChallenge != null) {
            return ResponseEntity.ok(updatedChallenge);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
