package com.jobs.jobapplication.service.impl;

import com.jobs.jobapplication.dto.JobDto;
import com.jobs.jobapplication.dto.JobStatus;
import com.jobs.jobapplication.exception.ResourceNotFoundException;
import com.jobs.jobapplication.model.ApplicationStatus;
import com.jobs.jobapplication.model.JobApplication;
import com.jobs.jobapplication.repository.JobApplicationRepository;
import com.jobs.jobapplication.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final WebClient.Builder webClientBuilder;

    @Override
    public JobApplication applyForJob(Long jobId, JobApplication application) {
        JobDto job = getJobById(jobId);
        if (job == null || job.getStatus() == JobStatus.CLOSED) {
            throw new ResourceNotFoundException("Job not found or closed with id " + jobId);
        }

        application.setJobId(jobId);
        application.setStatus(ApplicationStatus.APPLIED);
        return applicationRepository.save(application);
    }

    @Override
    public JobApplication getApplicationById(Long id) {
        return applicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with id " + id));
    }

    @Override
    public List<JobApplication> getApplicationsByJob(Long jobId) {
        JobDto job = getJobById(jobId);
        if (job == null || job.getStatus() == JobStatus.CLOSED) {
            throw new ResourceNotFoundException("Job not found or closed with id " + jobId);
        }

        return applicationRepository.findByJobId(jobId);
    }

    @Override
    public List<JobApplication> getApplicationsByApplicant(String email) {
        return applicationRepository.findByApplicantEmail(email);
    }

    @Override
    public JobApplication updateApplicationStatus(Long applicationId, ApplicationStatus status) {
        JobApplication application = getApplicationById(applicationId);
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    @Override
    public void deleteApplication(Long id) {
        if (!applicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Application not found with id " + id);
        }
        applicationRepository.deleteById(id);
    }

    private JobDto getJobById(Long id) {
        try {
            return webClientBuilder.build()
                    .get()
                    .uri("http://JOB-SERVICE/api/jobs/{id}", id)
                    .retrieve()
                    .bodyToMono(JobDto.class)
                    .block();
        } catch (WebClientResponseException e) {
            return null;
        }
    }
}
