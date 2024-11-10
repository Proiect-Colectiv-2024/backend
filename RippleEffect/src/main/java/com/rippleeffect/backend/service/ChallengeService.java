package src.main.java.com.rippleeffect.backend.service;

import src.main.java.com.rippleeffect.backend.model.Challenge;
import src.main.java.com.rippleeffect.backend.model.User;
import src.main.java.com.rippleeffect.backend.repository.ChallengeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ChallengeService {

    private final ChallengeRepository challengeRepository;

    @Autowired
    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public Optional<Challenge> getChallengeById(Long id) {
        return challengeRepository.findById(id);
    }

    public List<Challenge> getChallengesByUserId(Long userId) {
        return challengeRepository.findByUserId(userId);
    }

    public List<Challenge> getChallengesByType(String type) {
        return challengeRepository.findByType(type);
    }

    public List<Challenge> getChallengesByStatus(String status) {
        return challengeRepository.findByStatus(status);
    }

    public Challenge updateChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    public void deleteChallenge(Long id) {
        challengeRepository.deleteById(id);
    }

    private static final List<String> DAILY_CHALLENGES = Arrays.asList(
            "Pay for someone’s coffee.",
            "Give someone a compliment.",
            "Smile at a stranger.",
            "Send a thank-you note to someone.",
            "Help someone with their groceries."
    );

    private static final List<String> WEEKLY_CHALLENGES = Arrays.asList(
            "Volunteer at a local charity.",
            "Donate clothes you no longer need.",
            "Help a neighbor with yard work.",
            "Organize a community clean-up.",
            "Cook a meal for a friend or family member."
    );

    // Get or create the current active (in-progress) daily/weekly challenge for a user
    public Challenge getOrCreateChallenge(Long userId, String type) {
        // Check if there's an active challenge of the specified type for this user
        Optional<Challenge> activeChallenge = challengeRepository.findFirstByUserIdAndTypeAndStatusOrderByCidDesc(
                userId, type, "in progress");

        if (activeChallenge.isPresent()) {
            return activeChallenge.get();
        } else {
            Challenge newChallenge = new Challenge();
            newChallenge.setDescription(generateChallengeDescription(type));  // Get a random challenge description
            newChallenge.setStatus("in progress");
            newChallenge.setType(type);

            // userId din data de baza de date
            newChallenge.setUser(new User(userId));
            return challengeRepository.save(newChallenge);
        }
    }

    // Utility method to generate a description for a new challenge (daily or weekly)
    private String generateChallengeDescription(String type) {
        Random random = new Random();
        if ("daily".equals(type)) {
            int randomIndex = random.nextInt(DAILY_CHALLENGES.size());
            return DAILY_CHALLENGES.get(randomIndex);  // randomly select a daily challenge
        } else if ("weekly".equals(type)) {
            int randomIndex = random.nextInt(WEEKLY_CHALLENGES.size());
            return WEEKLY_CHALLENGES.get(randomIndex);  // randomly select a weekly challenge
        }
        return "No challenge available.";
    }

    //mark a challenge as completed
    public Challenge markChallengeAsCompleted(Long challengeId) {
        Optional<Challenge> challengeOptional = challengeRepository.findById(challengeId);

        if (challengeOptional.isPresent()) {
            Challenge challenge = challengeOptional.get();
            challenge.setStatus("completed");
            return challengeRepository.save(challenge);
        } else {
            throw new RuntimeException("Challenge not found with ID: " + challengeId);
        }
    }

}

