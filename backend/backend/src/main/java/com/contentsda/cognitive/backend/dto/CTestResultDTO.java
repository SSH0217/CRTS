package com.contentsda.cognitive.backend.dto;

import com.contentsda.cognitive.backend.entity.CTestResult;
import com.contentsda.cognitive.backend.entity.ExecuteLog;
import lombok.Data;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CTestResultDTO {
    private Long id;
    private Integer memoryScore;
    private Integer visuospatialScore;
    private Integer attentionScore;
    private Integer executeScore;
    private Integer rememberScore;
    private List<Long> executeLogIds;

    public CTestResultDTO(CTestResult cTestResult) {
        this.id = cTestResult.getId();
        this.memoryScore = cTestResult.getMemoryScore();
        this.visuospatialScore = cTestResult.getVisuospatialScore();
        this.attentionScore = cTestResult.getAttentionScore();
        this.executeScore = cTestResult.getExecuteScore();
        this.rememberScore = cTestResult.getRememberScore();
        this.executeLogIds = cTestResult.getExecuteLogList() != null ? cTestResult.getExecuteLogList().stream().map(ExecuteLog::getId).collect(Collectors.toList()) : null;
    }
}
