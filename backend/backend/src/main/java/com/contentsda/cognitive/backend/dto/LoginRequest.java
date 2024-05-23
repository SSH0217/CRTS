package com.contentsda.cognitive.backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String loginId;
    private String loginPw;
}
