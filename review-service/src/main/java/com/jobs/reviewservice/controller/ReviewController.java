package com.jobs.reviewservice.controller;

import com.jobs.reviewservice.model.Review;
import com.jobs.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        boolean isReviewAdded = reviewService.addReview(companyId, review);
        if (!isReviewAdded)
            return new ResponseEntity<>("Something went wrong, please try again!", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>("Review added!", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable("id") String reviewId) {
        Review review = reviewService.getCompanyReviewById(reviewId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateReview(@PathVariable("id") String reviewId, @RequestBody Review review) {
        boolean isReviewUpdated = reviewService.updateCompanyReview(reviewId, review);
        if (!isReviewUpdated) {
            return new ResponseEntity<>("Review updated!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") String reviewId) {
        boolean isReviewDeleted = reviewService.deleteCompanyReview(reviewId);
        if (!isReviewDeleted) {
            return new ResponseEntity<>("Something went wrong!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Review deleted!", HttpStatus.OK);
    }
}
