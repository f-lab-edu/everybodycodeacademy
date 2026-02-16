package com.everycodeacademy.backend.community.controller;

import com.everycodeacademy.backend.community.dto.*;
import com.everycodeacademy.backend.community.service.CommunityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/community/posts")
public class CommunityController {

    private final CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @PostMapping
    public ResponseEntity<PostResponse> createPost(@Valid @RequestBody CreatePostRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(communityService.createPost(request));
    }

    @GetMapping
    public List<PostResponse> getPosts() { return communityService.findPosts(); }

    @GetMapping("/{postId}")
    public PostResponse getPost(@PathVariable Long postId) { return communityService.findPost(postId); }

    @PutMapping("/{postId}")
    public PostResponse updatePost(@PathVariable Long postId, @Valid @RequestBody UpdatePostRequest request) {
        return communityService.updatePost(postId, request);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        communityService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId, @Valid @RequestBody CreateCommentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(communityService.createComment(postId, request));
    }

    @GetMapping("/{postId}/comments")
    public List<CommentResponse> getComments(@PathVariable Long postId) { return communityService.findComments(postId); }
}
