package com.contentsda.cognitive.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestResultDetailDTO {
    private boolean memoryResult1;
    private boolean memoryResult2;
    private boolean memoryResult3;
    private int correctItem1;
    private int correctItem2;
    private int correctItem3;
    private boolean rememberResult;
    private int rememberCorrectItem;

    private boolean visuospatialResult;
    private int visuospatialCount;
    private int visuospatialOptionCount1;
    private int visuospatialOptionCount2;
    private int visuospatialOptionCount3;
    private int visuospatialOptionCount4;
    private int visuospatialTime;

    private boolean attentionResult;
    private int attentionCount;

    private boolean executeResult;
    private int executeTime;
    List<LogDTO> logDTOList;
}
