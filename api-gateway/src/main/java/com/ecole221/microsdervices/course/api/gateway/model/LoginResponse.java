package com.ecole221.microsdervices.course.api.gateway.model;

import lombok.Data;

@Data
public class LoginResponse {
    private String access_token;
    private String refresh_token;
    private String expires_in;
    private String refresh_expires_in;
    private String token_type;
}
