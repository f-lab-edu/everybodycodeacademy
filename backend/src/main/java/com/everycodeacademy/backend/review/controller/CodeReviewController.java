package com.everycodeacademy.backend.review.controller;

import com.everycodeacademy.backend.review.dto.*;
import com.everycodeacademy.backend.review.service.CodeReviewService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/code-reviews")
public class CodeReviewController {

    private final CodeReviewService codeReviewService;

    public CodeReviewController(CodeReviewService codeReviewService) {
        this.codeReviewService = codeReviewService;
    }

    @PostMapping("/submissions")
    public ResponseEntity<SubmissionResponse> createSubmission(@Valid @RequestBody CreateSubmissionRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(codeReviewService.createSubmission(request));
    }

    @GetMapping("/submissions")
    public List<SubmissionResponse> getSubmissions() { return codeReviewService.findSubmissions(); }

    @GetMapping("/submissions/{submissionId}")
    public SubmissionResponse getSubmission(@PathVariable Long submissionId) { return codeReviewService.findSubmission(submissionId); }

    @PostMapping("/submissions/{submissionId}/reviews")
    public ResponseEntity<ReviewResponse> createReview(@PathVariable Long submissionId, @Valid @RequestBody CreateReviewRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(codeReviewService.createReview(submissionId, request));
    }

    @GetMapping("/submissions/{submissionId}/reviews")
    public List<ReviewResponse> getReviews(@PathVariable Long submissionId) { return codeReviewService.findReviews(submissionId); }

    @PostMapping("/submissions/{submissionId}/likes")
    public LikeResponse toggleLike(@PathVariable Long submissionId, @Valid @RequestBody LikeRequest request) {
        return codeReviewService.toggleLike(submissionId, request);
    }

    @PostMapping("/submissions/{submissionId}/ai-feedback")
    public AiFeedbackResponse aiFeedback(@PathVariable Long submissionId, @Valid @RequestBody AiFeedbackRequest request) {
        return codeReviewService.generateAiFeedback(submissionId, request);
    }
}
