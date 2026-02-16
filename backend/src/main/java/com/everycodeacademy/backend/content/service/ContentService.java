package com.everycodeacademy.backend.content.service;

import com.everycodeacademy.backend.content.dto.*;
import com.everycodeacademy.backend.content.entity.ContentApprovalStatus;
import com.everycodeacademy.backend.content.entity.ContentArticle;
import com.everycodeacademy.backend.content.entity.ContentCategory;
import com.everycodeacademy.backend.content.repository.ContentArticleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContentService {

    private final ContentArticleRepository repository;

    public ContentService(ContentArticleRepository repository) {
        this.repository = repository;
    }

    public ContentResponse create(CreateContentRequest request) {
        ContentArticle article = ContentArticle.builder()
                .title(request.title())
                .summary(request.summary())
                .body(request.body())
                .sourceUrl(request.sourceUrl())
                .author(request.author())
                .category(request.category())
                .approvalStatus(ContentApprovalStatus.PENDING)
                .build();
        return toResponse(repository.save(article));
    }

    public List<ContentResponse> findAll(ContentCategory category) {
        List<ContentArticle> list = category == null ? repository.findAll() : repository.findByCategoryOrderByCreatedAtDesc(category);
        return list.stream().map(this::toResponse).toList();
    }

    public ContentResponse findById(Long id) {
        return toResponse(repository.findById(id).orElseThrow(() -> new NoSuchElementException("Content not found")));
    }

    public ContentResponse update(Long id, UpdateContentRequest request) {
        ContentArticle article = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Content not found"));
        if (request.title() != null) article.setTitle(request.title());
        if (request.summary() != null) article.setSummary(request.summary());
        if (request.body() != null) article.setBody(request.body());
        if (request.sourceUrl() != null) article.setSourceUrl(request.sourceUrl());
        if (request.author() != null) article.setAuthor(request.author());
        if (request.category() != null) article.setCategory(request.category());
        return toResponse(repository.save(article));
    }

    public ContentResponse updateApproval(Long id, String adminEmail, UpdateApprovalRequest request) {
        ContentArticle article = repository.findById(id).orElseThrow(() -> new NoSuchElementException("Content not found"));
        article.setApprovalStatus(request.status());
        article.setApprovalComment(request.comment());
        article.setApprovedBy(adminEmail);
        article.setApprovedAt(LocalDateTime.now());
        return toResponse(repository.save(article));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ContentResponse toResponse(ContentArticle article) {
        return new ContentResponse(article.getId(), article.getTitle(), article.getSummary(), article.getBody(), article.getSourceUrl(),
                article.getAuthor(), article.getCategory(), article.getApprovalStatus(), article.getApprovalComment(), article.getApprovedBy(),
                article.getApprovedAt(), article.getCreatedAt(), article.getUpdatedAt());
    }
}
