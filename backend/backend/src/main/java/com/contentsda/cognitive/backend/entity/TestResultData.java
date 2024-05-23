package com.contentsda.cognitive.backend.entity;

import lombok.Data;

import java.util.List;

@Data
public class TestResultData {
    private boolean isConnected;
    private int deviceNum;
    private String deviceName;
    private int patientID;
    private String testType;
    private String startTime;
    private String endTime;
    private boolean martMemoryResult1;
    private boolean martMemoryResult2;
    private boolean martMemoryResult3;
    private int correctItem1;
    private int correctItem2;
    private int correctItem3;
    private boolean paintMemoryResult1;
    private boolean paintMemoryResult2;
    private boolean paintMemoryResult3;
    private int correctPaint1;
    private int correctPaint2;
    private int correctPaint3;
    private boolean martRememberResult;
    private int rememberCorrectItem;
    private boolean paintRememberResult;
    private int rememberCorrectPaint;
    private boolean seperateResult;
    private int seperateCount;
    private int plasticCount;
    private int canCount;
    private int glassCount;
    private int vinylCount;
    private int seperateTime;
    private boolean deskResult;
    private int deskCount;
    private int deskTime;
    private boolean deskBookResult;
    private int deskBookCount;
    private boolean deskWritingResult;
    private int deskWritingCount;
    private boolean mealResult;
    private int mealCount;
    private boolean callResult;
    private int callCount;
    private boolean microwaveResult;
    private int microwaveTime;
    private List<String> microwaveLog;
    private List<String> microwaveLogTime;
    private boolean washingMachineResult;
    private int washingMachineTime;
    private List<String> washingMachineLog;
    private List<String> washingMachineLogTime;
}
