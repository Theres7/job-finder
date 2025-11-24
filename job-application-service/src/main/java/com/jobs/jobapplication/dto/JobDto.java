package com.jobs.jobapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobDto {
    private Long id;
    private String title;
    private String description;
    private String company;
    private String location;
    private JobStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

