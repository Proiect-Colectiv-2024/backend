package com.rippleeffect.backend.service;

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

        if (!user.getMissedChallenges().contains(challengeId) &&
                !user.getCompletedChallenges().contains(challengeId)) {
            user.getCompletedChallenges().add("http://localhost:8081/challenges/" + challengeId);
            user.getMissedChallenges().remove("http://localhost:8081/challenges/" + challengeId);
            // Write the updated users back to the JSON file
            jsonFileReader.writeUsersToFile(FILE_PATH, users);
            return true;
        }

        return false; // Challenge already completed or missed
    }

    public boolean missChallengeForUser(Integer userId, Integer challengeId) {
        User user = getUserById(userId);

        if (!user.getMissedChallenges().contains(challengeId) &&
                !user.getCompletedChallenges().contains(challengeId)) {
            user.getMissedChallenges().add("http://localhost:8081/challenges/" + challengeId);
            user.getCompletedChallenges().remove("http://localhost:8081/challenges/" + challengeId);

            // Write the updated users back to the JSON file
            jsonFileReader.writeUsersToFile(FILE_PATH, users);
            return true;
        }

        return false; // Challenge already completed or missed
    }

    public User getUserByUsername(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with id: " + username));
    }
}