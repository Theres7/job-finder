package com.jobs.jobservice.repository;

import com.jobs.jobservice.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
