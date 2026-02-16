package com.everycodeacademy.backend.community.service;

import com.everycodeacademy.backend.community.dto.*;
import com.everycodeacademy.backend.community.entity.CommunityComment;
import com.everycodeacademy.backend.community.entity.CommunityPost;
import com.everycodeacademy.backend.community.repository.CommunityCommentRepository;
import com.everycodeacademy.backend.community.repository.CommunityPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommunityService {

    private final CommunityPostRepository postRepository;
    private final CommunityCommentRepository commentRepository;

    public CommunityService(CommunityPostRepository postRepository, CommunityCommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    public PostResponse createPost(CreatePostRequest request) {
        CommunityPost post = CommunityPost.builder().title(request.title()).body(request.body()).author(request.author()).build();
        return toPostResponse(postRepository.save(post));
    }

    public List<PostResponse> findPosts() {
        return postRepository.findAll().stream().map(this::toPostResponse).toList();
    }

    public PostResponse findPost(Long id) {
        return toPostResponse(postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Post not found")));
    }

    public PostResponse updatePost(Long id, UpdatePostRequest request) {
        CommunityPost post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Post not found"));
        if (request.title() != null) post.setTitle(request.title());
        if (request.body() != null) post.setBody(request.body());
        return toPostResponse(postRepository.save(post));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public CommentResponse createComment(Long postId, CreateCommentRequest request) {
        if (!postRepository.existsById(postId)) throw new NoSuchElementException("Post not found");
        CommunityComment comment = CommunityComment.builder()
                .postId(postId)
                .parentCommentId(request.parentCommentId())
                .body(request.body())
                .author(request.author())
                .build();
        return toCommentResponse(commentRepository.save(comment));
    }

    public List<CommentResponse> findComments(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedAtAsc(postId).stream().map(this::toCommentResponse).toList();
    }

    private PostResponse toPostResponse(CommunityPost post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getBody(), post.getAuthor(), post.getCreatedAt(), post.getUpdatedAt());
    }

    private CommentResponse toCommentResponse(CommunityComment comment) {
        return new CommentResponse(comment.getId(), comment.getPostId(), comment.getParentCommentId(), comment.getBody(), comment.getAuthor(), comment.getCreatedAt());
    }
}
