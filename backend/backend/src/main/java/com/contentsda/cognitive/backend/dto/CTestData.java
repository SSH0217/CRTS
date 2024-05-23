package com.contentsda.cognitive.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CTestData {
    private boolean isConnected;
    private int deviceNum;
    private String deviceName;
    private int patientID;
    private String patientName;
    private String testType;

    // 전체 테스트 시작 시간
    private String startTime;
    // 전체 테스트 완료 시간
    private String endTime;

    private int memoryScore;
    private int visuospatialScore;
    private List<String> visuospatialLog;
    private List<String> visuospatialLogTime;
    private int rememberScore;
    private int attentionScore;
    private List<String> attentionLog;
    private List<String> attentionLogTime;
    private int executeScore;
    private int executeLeftCount;
    private int executeRightCount;
    private List<String> executeLog;
    private List<String> executeLogTime;
}
