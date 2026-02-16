package com.everycodeacademy.backend.review.repository;

import com.everycodeacademy.backend.review.entity.CodeLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeLikeRepository extends JpaRepository<CodeLike, Long> {
    Optional<CodeLike> findBySubmissionIdAndUserEmail(Long submissionId, String userEmail);
    long countBySubmissionId(Long submissionId);
}
