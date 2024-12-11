package com.rippleeffect.backend.service;

import com.rippleeffect.backend.models.Challenge;
import com.rippleeffect.backend.models.User;
import com.rippleeffect.backend.utils.JsonFileReader;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final JsonFileReader jsonFileReader;
    private final List<User> users;

    private static final String FILE_PATH = "data/users.json";

    public UserService(JsonFileReader jsonFileReader) {
        this.jsonFileReader = jsonFileReader;
        this.users = jsonFileReader.readUsersFromFile();
    }

    public List<User> getUsers() {
        return users;
    }

    public User getUserById(Integer id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public boolean completeChallengeForUser(Integer userId, Integer challengeId) {
        User user = getUserById(userId);

        // Find the challenge in inProgressChallenges
        Challenge challengeToComplete = user.getInProgressChallenges().stream()
                .filter(challenge -> challenge.getId().equals(challengeId))
                .findFirst()
                .orElse(null);

        if (challengeToComplete == null) {
            return false; // Challenge not found in inProgressChallenges
        }

        // Update challenge status and move to completedChallenges
        challengeToComplete.setStatus("completed");
        user.getInProgressChallenges().remove(challengeToComplete);
        user.getCompletedChallenges().add(challengeToComplete);

        // Write the updated user back to the JSON file
        jsonFileReader.writeUsersToFile(FILE_PATH, users);
        return true;
    }


    public boolean missChallengeForUser(Integer userId, Integer challengeId) {
        User user = getUserById(userId);

        // Find the challenge in inProgressChallenges
        Challenge challengeToMiss = user.getInProgressChallenges().stream()
                .filter(challenge -> challenge.getId().equals(challengeId))
                .findFirst()
                .orElse(null);

        if (challengeToMiss == null) {
            return false; // Challenge not found in inProgressChallenges
        }

        // Update challenge status and move to missedChallenges
        challengeToMiss.setStatus("missed");
        user.getInProgressChallenges().remove(challengeToMiss);
        user.getMissedChallenges().add(challengeToMiss);

        // Write the updated user back to the JSON file
        jsonFileReader.writeUsersToFile(FILE_PATH, users);
        return true;
    }


    public User getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with id: " + username));
    }
}