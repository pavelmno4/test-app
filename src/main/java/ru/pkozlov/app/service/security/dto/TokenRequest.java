package ru.pkozlov.app.service.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TokenRequest {
    @Schema(description = "Email", example = "petr@yandex.ru")
    private String email;
    @Schema(description = "Пароль", example = "adminnn")
    private String password;
}
