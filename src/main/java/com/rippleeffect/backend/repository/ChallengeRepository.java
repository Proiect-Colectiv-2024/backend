package com.rippleeffect.backend.repository;

import com.rippleeffect.backend.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByUserId(Long userId);
    List<Challenge> findByType(String type); // type could be "daily" or "weekly"
    List<Challenge> findByStatus(String status); // status could be "completed", "in progress", etc.
}
