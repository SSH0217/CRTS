package com.contentsda.cognitive.backend.entity;

import com.contentsda.cognitive.backend.entity.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestResultDTO {
    private Long id;
    private LocalDateTime testStartTime;
    private LocalDateTime testEndTime;
    private String testSubjectName;
    private ATestResultDTO aTestResult;
    private BTestResultDTO bTestResult;
    private CTestResultDTO cTestResult;

    public TestResultDTO(Long id, LocalDateTime testStartTime, LocalDateTime testEndTime, String testSubjectName,
                         ATestResultDTO aTestResult, BTestResultDTO bTestResult, CTestResultDTO cTestResult) {
        this.id = id;
        this.testStartTime = testStartTime;
        this.testEndTime = testEndTime;
        this.testSubjectName = testSubjectName;
        this.aTestResult = aTestResult;
        this.bTestResult = bTestResult;
        this.cTestResult = cTestResult;
    }
}
