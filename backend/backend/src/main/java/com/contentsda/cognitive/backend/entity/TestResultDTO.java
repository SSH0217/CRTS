package com.contentsda.cognitive.backend.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestResultDTO {
    private Long id;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private String testSubjectName;

    public TestResultDTO(Long id, LocalDateTime testStartTime, LocalDateTime testEndTime, String testSubjectName) {
        this.id = id;
        this.testStartTime = testStartTime;
        this.testEndTime = testEndTime;
        this.testSubjectName = testSubjectName;
    }
}
