package com.jobs.reviewservice.repository;

import com.jobs.reviewservice.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByCompanyId(Long companyId);
}
