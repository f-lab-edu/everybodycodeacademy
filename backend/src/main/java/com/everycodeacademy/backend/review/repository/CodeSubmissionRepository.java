package com.everycodeacademy.backend.review.repository;

import com.everycodeacademy.backend.review.entity.CodeSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeSubmissionRepository extends JpaRepository<CodeSubmission, Long> {
}
