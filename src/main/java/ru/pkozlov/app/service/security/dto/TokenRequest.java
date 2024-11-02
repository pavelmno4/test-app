package ru.pkozlov.app.service.security.dto;

import lombok.Data;

@Data
public class TokenRequest {
    private String email;
    private String password;
}
