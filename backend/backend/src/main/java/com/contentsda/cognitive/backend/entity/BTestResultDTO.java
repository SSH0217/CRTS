package com.contentsda.cognitive.backend.entity;

import lombok.Data;

@Data
public class BTestResultDTO {
    private Long id;
    private Long paintMemoryTestResultId;
    private Long deskVisuospatialTestResultId;
    private Long callAttentionTestResultId;
    private Long washingMachineExecuteTestResultId;

    public BTestResultDTO(BTestResult bTestResult) {
        this.id = bTestResult.getId();
        this.paintMemoryTestResultId = bTestResult.getPaintMemoryTestResult() != null ? bTestResult.getPaintMemoryTestResult().getId() : null;
        this.deskVisuospatialTestResultId = bTestResult.getDeskVisuospatialTestResult() != null ? bTestResult.getDeskVisuospatialTestResult().getId() : null;
        this.callAttentionTestResultId = bTestResult.getCallAttentionTestResult() != null ? bTestResult.getCallAttentionTestResult().getId() : null;
        this.washingMachineExecuteTestResultId = bTestResult.getWashingMachineExecuteTestResult() != null ? bTestResult.getWashingMachineExecuteTestResult().getId() : null;
    }
}
