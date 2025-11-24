package com.jobs.jobapplication.service;


import com.jobs.jobapplication.dto.JobStatus;
import com.jobs.jobapplication.model.ApplicationStatus;
import com.jobs.jobapplication.model.JobApplication;

import java.util.List;

public interface JobApplicationService {
    JobApplication applyForJob(Long jobId, JobApplication application);

//    List<JobApplication> getAllApplicationsByCompany(Long companyId);

    List<JobApplication> getApplicationsByJob(Long jobId);

    JobApplication getApplicationById(Long id);

    List<JobApplication> getApplicationsByApplicant(String email);

    JobStatus updateApplicationStatus(Long id, ApplicationStatus status);

    void deleteApplication(Long id);

}
