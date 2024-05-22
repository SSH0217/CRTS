package com.contentsda.cognitive.backend.entity;

import lombok.Data;

@Data
public class ATestResultDTO {
    private Long id;
    private Long martMemoryTestResultId;
    private Long separateVisuospatialTestResultId;
    private Long mealAttentionTestResultId;
    private Long microwaveExecuteTestResultId;

    public ATestResultDTO(ATestResult aTestResult) {
        this.id = aTestResult.getId();
        this.martMemoryTestResultId = aTestResult.getMartMemoryTestResult() != null ? aTestResult.getMartMemoryTestResult().getId() : null;
        this.separateVisuospatialTestResultId = aTestResult.getSeparateVisuospatialTestResult() != null ? aTestResult.getSeparateVisuospatialTestResult().getId() : null;
        this.mealAttentionTestResultId = aTestResult.getMealAttentionTestResult() != null ? aTestResult.getMealAttentionTestResult().getId() : null;
        this.microwaveExecuteTestResultId = aTestResult.getMicrowaveExecuteTestResult() != null ? aTestResult.getMicrowaveExecuteTestResult().getId() : null;
    }
}
