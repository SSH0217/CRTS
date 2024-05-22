package com.contentsda.cognitive.backend.entity;

import lombok.Data;

@Data
public class LoginRequest {
    private String loginId;
    private String loginPw;
}
