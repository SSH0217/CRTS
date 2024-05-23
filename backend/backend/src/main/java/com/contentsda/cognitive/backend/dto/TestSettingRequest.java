package com.contentsda.cognitive.backend.dto;

import lombok.Data;

@Data
public class TestSettingRequest {
    private Long deviceId;
    private Long patientId;
    private String testType;
}
