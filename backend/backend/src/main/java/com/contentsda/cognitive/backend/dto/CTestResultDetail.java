package com.contentsda.cognitive.backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class CTestResultDetail {
    private int memoryScore;
    private int visuospatialScore;
    private int attentionScore;
    private int rememberScore;
    private int executeScore;
    List<LogDTO> logDTOList;
}
