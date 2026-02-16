package com.everycodeacademy.backend.review.repository;

import com.everycodeacademy.backend.review.entity.CodeReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeReviewRepository extends JpaRepository<CodeReview, Long> {
    List<CodeReview> findBySubmissionIdOrderByCreatedAtAsc(Long submissionId);
}
