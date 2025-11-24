package com.jobs.jobapplication.controller;

import com.jobs.jobapplication.model.JobApplication;
import com.jobs.jobapplication.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/job-applications")
public class JobApplicationController {

    private final JobApplicationService applicationService;

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplication>> getAllApplicationsByJobId(@PathVariable("jobId") Long jobId) {
        List<JobApplication> applications = applicationService.getApplicationsByJob(jobId);
        return ResponseEntity.ok(applications);
    }

    @PostMapping("/job/{jobId}")
    public ResponseEntity<JobApplication> applyForJob(
            @PathVariable("jobId") Long jobId,
            @Valid @RequestBody JobApplication application) {
        JobApplication createdApplication = applicationService.applyForJob(jobId, application);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApplication);
    }

//    @GetMapping("/applicant/{email}")
//    public ResponseEntity<List<JobApplication>> getAllApplicationsByApplicantEmail(
//            @PathVariable("email") String email) {
//        List<JobApplication> applications = applicationService.getApplicationsByApplicant(email);
//        return ResponseEntity.ok(applications);
//    }

    @GetMapping("/applicant")
    public ResponseEntity<List<JobApplication>> getAllApplicationsByApplicantEmail(
            @RequestParam("email") String email) {
        List<JobApplication> applications = applicationService.getApplicationsByApplicant(email);
        return ResponseEntity.ok(applications);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplication> getApplicationById(@PathVariable("id") Long id) {
        JobApplication application = applicationService.getApplicationById(id);
        return ResponseEntity.ok(application);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
