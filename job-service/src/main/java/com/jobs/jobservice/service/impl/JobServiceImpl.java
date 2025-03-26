package com.jobs.jobservice.service.impl;
import com.jobs.jobservice.model.Job;
import com.jobs.jobservice.repository.JobRepository;
import com.jobs.jobservice.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public boolean createJob(Job job) {
        try {
         jobRepository.save(job);
         return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id){
        try{
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job upadteJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
            if (jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setTitle(upadteJob.getTitle());
                job.setDescription(upadteJob.getDescription());
                job.setMinimumSalary(upadteJob.getMinimumSalary());
                job.setMaximumSalary(upadteJob.getMaximumSalary());
                job.setEligibility(upadteJob.getEligibility());
                job.setLocation(upadteJob.getLocation());
                jobRepository.save(job);
                return true;
            }
        return false;
    }
}
