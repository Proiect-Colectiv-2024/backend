package src.main.java.com.rippleeffect.backend.repository;

import src.main.java.com.rippleeffect.backend.model.Challenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeRepository extends JpaRepository<Challenge, Long> {
    List<Challenge> findByUserId(Long userId);
    List<Challenge> findByType(String type); // type could be "daily" or "weekly"
    List<Challenge> findByStatus(String status); // status could be "completed", "in progress", etc.
    Optional<Challenge> findFirstByUserIdAndTypeAndStatusOrderByCidDesc(Long userId, String type, String status);
    Optional<Challenge> findByUserIdAndTypeAndStatus(Long userId, String type, String status);
}
