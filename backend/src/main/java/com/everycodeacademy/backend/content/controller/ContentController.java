package com.everycodeacademy.backend.content.controller;

import com.everycodeacademy.backend.content.dto.*;
import com.everycodeacademy.backend.content.entity.ContentCategory;
import com.everycodeacademy.backend.content.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    private final ContentService contentService;

    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @PostMapping
    public ResponseEntity<ContentResponse> create(@Valid @RequestBody CreateContentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contentService.create(request));
    }

    @GetMapping
    public List<ContentResponse> getContents(@RequestParam(required = false) ContentCategory category) {
        return contentService.findAll(category);
    }

    @GetMapping("/{id}")
    public ContentResponse getContent(@PathVariable Long id) {
        return contentService.findById(id);
    }

    @PutMapping("/{id}")
    public ContentResponse update(@PathVariable Long id, @Valid @RequestBody UpdateContentRequest request) {
        return contentService.update(id, request);
    }

    @PatchMapping("/{id}/approval")
    public ContentResponse approval(@PathVariable Long id, @Valid @RequestBody UpdateApprovalRequest request, Authentication auth) {
        return contentService.updateApproval(id, auth.getName(), request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
