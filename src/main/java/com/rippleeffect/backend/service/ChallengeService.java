package com.rippleeffect.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
