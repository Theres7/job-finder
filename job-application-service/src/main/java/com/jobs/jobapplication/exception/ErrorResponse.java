package com.jobs.jobapplication.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private int status;
    private String message;
    private String path;

    @CreationTimestamp
    private LocalDateTime timestamp;

    public ErrorResponse(int value, String message, String description) {
        this.status = value;
        this.message = message;
        this.path = description;
    }

}

