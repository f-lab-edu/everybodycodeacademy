package com.everycodeacademy.backend.content.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "content_articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String title;

    @Column(length = 300)
    private String summary;

    @Lob
    @Column(nullable = false)
    private String body;

    @Column(name = "source_url", length = 300)
    private String sourceUrl;

    @Column(nullable = false, length = 60)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ContentCategory category;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", nullable = false, length = 20)
    private ContentApprovalStatus approvalStatus;

    @Column(name = "approval_comment", length = 500)
    private String approvalComment;

    @Column(name = "approved_by", length = 100)
    private String approvedBy;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        if (approvalStatus == null) approvalStatus = ContentApprovalStatus.PENDING;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
