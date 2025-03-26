package com.jobs.jobservice.service;

import com.jobs.jobservice.model.Job;
import java.util.List;

public interface JobService {
    List<Job> getAllJobs();
    boolean createJob(Job job);
    Job getJobById(Long id);
    boolean deleteJobById(Long id);
    boolean updateJob(Long id, Job upadteJob);
}
