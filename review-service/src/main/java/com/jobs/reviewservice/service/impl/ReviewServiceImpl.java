package com.jobs.reviewservice.service.impl;

import com.jobs.reviewservice.model.Review;
import com.jobs.reviewservice.repository.ReviewRepository;
import com.jobs.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getCompanyReviewById(String reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateCompanyReview(String reviewId, Review updatedreview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            review.setTitle(updatedreview.getTitle());
            review.setContent(updatedreview.getContent());
            review.setRating(updatedreview.getRating());
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompanyReview(String reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            reviewRepository.delete(review);
            return true;
        }
        return false;
    }
}
