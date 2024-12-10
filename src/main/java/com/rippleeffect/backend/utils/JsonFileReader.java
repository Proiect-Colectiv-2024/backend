package com.rippleeffect.backend.utils;

import com.rippleeffect.backend.models.Challenge;
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
    private String filePath;

    public List<Challenge> readChallengesFromFile() {
        try {
            return objectMapper.readValue(new File(filePath), new TypeReference<List<Challenge>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file: " + filePath, e);
        }
    }

    public void writeChallengesToFile(String filePath, List<Challenge> challenges) {
        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, challenges);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write challenges to file: " + filePath, e);
        }
    }

}
