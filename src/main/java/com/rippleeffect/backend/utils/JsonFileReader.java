package com.rippleeffect.backend.utils;

import com.rippleeffect.backend.models.Challenge;
import com.rippleeffect.backend.models.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.io.File;

@Component
public class JsonFileReader {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Value("${challenges.file.path}")
    private String challengesFilePath;
    @Value("data/users.json")
    private String usersFilePath;

    public List<Challenge> readChallengesFromFile() {
        try {
            return objectMapper.readValue(new File(challengesFilePath), new TypeReference<List<Challenge>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + challengesFilePath, e);
        }
    }

    public List<User> readUsersFromFile() {
        try {
            return objectMapper.readValue(new File(usersFilePath), new TypeReference<List<User>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read users file: " + usersFilePath, e);
        }
    }

    public void writeChallengesToFile(String challengesFilePath, List<Challenge> challenges) {
        try {
            File file = new File(challengesFilePath);
            objectMapper.writeValue(file, challenges);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write challenges to file: " + challengesFilePath, e);
        }
    }
    public void writeUsersToFile(String usersFilePath, List<User> users) {
        try {
            File file = new File(usersFilePath);
            objectMapper.writeValue(file, users);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write users to file: " + usersFilePath, e);
        }
    }

}
