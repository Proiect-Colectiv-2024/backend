package com.rippleeffect.backend.service;

import com.rippleeffect.backend.models.Challenge;
import com.rippleeffect.backend.utils.JsonFileReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {
    private final JsonFileReader jsonFileReader;
    private final List<Challenge> challenges;

    private static final String FILE_PATH = "data/challenges.json";

    public ChallengeService(JsonFileReader jsonFileReader) {
        this.jsonFileReader = jsonFileReader;
        this.challenges = jsonFileReader.readChallengesFromFile();
    }

    public List<Challenge> getChallenges() {
        return challenges;
    }

    public  Challenge getChallengeById(Integer id) {
        return challenges.stream()
                .filter(challenge -> challenge.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Challenge not found with id: " + id));
    }

    public boolean completeChallenge(Integer id) {
        // Find the challenge by ID
        Challenge challenge = getChallengeById(id);

        // Update the status if it's "in progress"
        if ("in progress".equalsIgnoreCase(challenge.getStatus())) {
            challenge.setStatus("completed");
            System.out.println("Updating challenge: " + challenge); // Debugging log

            // Write the updated challenges back to the JSON file
            jsonFileReader.writeChallengesToFile(FILE_PATH, challenges);
            return true;
        }

        return false; // Challenge was not in "in progress" state
    }

    public boolean missChallenge(Integer id) {
        // Find the challenge by ID
        Challenge challenge = getChallengeById(id);

        // Update the status if it's "in progress"
        if ("in progress".equalsIgnoreCase(challenge.getStatus())) {
            challenge.setStatus("missed");
            System.out.println("Updating challenge: " + challenge); // Debugging log

            // Write the updated challenges back to the JSON file
            jsonFileReader.writeChallengesToFile(FILE_PATH, challenges);
            return true;
        }

        return false; // Challenge was not in "in progress" state
    }

}
