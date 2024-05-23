package com.contentsda.cognitive.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LogDTO {
    private Long logNum;
    private LocalDateTime logTime;
    private String log;
}
