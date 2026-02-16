package com.everycodeacademy.backend.review.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "code_submissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Lob
    private String description;

    @Column(nullable = false, length = 40)
    private String language;

    @Lob
    @Column(name = "source_code", nullable = false)
    private String sourceCode;

    @Column(name = "author_email", nullable = false, length = 100)
    private String authorEmail;

    @Column(name = "purpose", length = 120)
    private String purpose;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() { createdAt = LocalDateTime.now(); }
}
