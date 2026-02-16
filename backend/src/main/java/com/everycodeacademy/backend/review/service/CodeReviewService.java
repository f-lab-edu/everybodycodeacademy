package com.everycodeacademy.backend.review.service;

import com.everycodeacademy.backend.review.dto.*;
import com.everycodeacademy.backend.review.entity.CodeLike;
import com.everycodeacademy.backend.review.entity.CodeReview;
import com.everycodeacademy.backend.review.entity.CodeSubmission;
import com.everycodeacademy.backend.review.openai.OpenAiReviewClient;
import com.everycodeacademy.backend.review.repository.CodeLikeRepository;
import com.everycodeacademy.backend.review.repository.CodeReviewRepository;
import com.everycodeacademy.backend.review.repository.CodeSubmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CodeReviewService {

    private final CodeSubmissionRepository submissionRepository;
    private final CodeReviewRepository reviewRepository;
    private final CodeLikeRepository likeRepository;
    private final OpenAiReviewClient openAiReviewClient;

    public CodeReviewService(CodeSubmissionRepository submissionRepository, CodeReviewRepository reviewRepository,
                             CodeLikeRepository likeRepository, OpenAiReviewClient openAiReviewClient) {
        this.submissionRepository = submissionRepository;
        this.reviewRepository = reviewRepository;
        this.likeRepository = likeRepository;
        this.openAiReviewClient = openAiReviewClient;
    }

    public SubmissionResponse createSubmission(CreateSubmissionRequest request) {
        CodeSubmission submission = CodeSubmission.builder()
                .title(request.title())
                .description(request.description())
                .language(request.language())
                .sourceCode(request.sourceCode())
                .authorEmail(request.authorEmail())
                .purpose(request.purpose())
                .build();
        return toSubmissionResponse(submissionRepository.save(submission));
    }

    public List<SubmissionResponse> findSubmissions() {
        return submissionRepository.findAll().stream().map(this::toSubmissionResponse).toList();
    }

    public SubmissionResponse findSubmission(Long id) {
        return toSubmissionResponse(submissionRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Submission not found")));
    }

    public ReviewResponse createReview(Long submissionId, CreateReviewRequest request) {
        if (!submissionRepository.existsById(submissionId)) throw new NoSuchElementException("Submission not found");
        CodeReview review = CodeReview.builder()
                .submissionId(submissionId)
                .reviewerEmail(request.reviewerEmail())
                .comment(request.comment())
                .build();
        return toReviewResponse(reviewRepository.save(review));
    }

    public List<ReviewResponse> findReviews(Long submissionId) {
        return reviewRepository.findBySubmissionIdOrderByCreatedAtAsc(submissionId).stream().map(this::toReviewResponse).toList();
    }

    public LikeResponse toggleLike(Long submissionId, LikeRequest request) {
        if (!submissionRepository.existsById(submissionId)) throw new NoSuchElementException("Submission not found");
        boolean liked;
        CodeLike existing = likeRepository.findBySubmissionIdAndUserEmail(submissionId, request.userEmail()).orElse(null);
        if (existing == null) {
            likeRepository.save(CodeLike.builder().submissionId(submissionId).userEmail(request.userEmail()).build());
            liked = true;
        } else {
            likeRepository.delete(existing);
            liked = false;
        }
        return new LikeResponse(submissionId, likeRepository.countBySubmissionId(submissionId), liked);
    }

    public AiFeedbackResponse generateAiFeedback(Long submissionId, AiFeedbackRequest request) {
        CodeSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new NoSuchElementException("Submission not found"));
        return openAiReviewClient.generateFeedback(submissionId, submission, request.focus());
    }

    private SubmissionResponse toSubmissionResponse(CodeSubmission submission) {
        return new SubmissionResponse(submission.getId(), submission.getTitle(), submission.getDescription(), submission.getLanguage(),
                submission.getSourceCode(), submission.getAuthorEmail(), submission.getPurpose(),
                likeRepository.countBySubmissionId(submission.getId()), submission.getCreatedAt());
    }

    private ReviewResponse toReviewResponse(CodeReview review) {
        return new ReviewResponse(review.getId(), review.getSubmissionId(), review.getReviewerEmail(), review.getComment(), review.getCreatedAt());
    }
}
