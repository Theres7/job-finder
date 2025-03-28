package com.jobs.jobservice.controller;

import com.jobs.jobservice.model.Job;
import com.jobs.jobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public ResponseEntity<List<Job>> findAll() {
        List<Job> jobs = jobService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createJob(@RequestBody Job job) {
        boolean isJobCreated = jobService.createJob(job);
        if (isJobCreated) {
            return new ResponseEntity<>("Job created", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Job could not be created, please try again!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> findById(@PathVariable Long id) {
        Job job = jobService.getJobById(id);
        if (job != null) {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {
        if (id == null || updatedJob == null) {
            return new ResponseEntity<>("Either id or job data is invalid!", HttpStatus.BAD_REQUEST);
        }
        return jobService.updateJob(id, updatedJob)
                ? new ResponseEntity<>("Job updated", HttpStatus.OK)
                : new ResponseEntity<>("Failed to update the Job!", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id) {
        if (jobService.getJobById(id) == null) {
            return new ResponseEntity<>("Job not found", HttpStatus.NOT_FOUND);
        }
        boolean deleted = jobService.deleteJobById(id);
        return deleted
                ? new ResponseEntity<>("Job deleted", HttpStatus.OK)
                : new ResponseEntity<>("Something went wrong, please try again!", HttpStatus.NOT_FOUND);
    }
}
