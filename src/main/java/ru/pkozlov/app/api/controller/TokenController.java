package ru.pkozlov.app.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pkozlov.app.service.security.AuthUserDetailsService;
import ru.pkozlov.app.service.security.TokenService;
import ru.pkozlov.app.service.security.dto.TokenRequest;
import ru.pkozlov.app.service.security.dto.TokenResponse;

@Tag(name = "Token Controller", description = "Получение jwt")
@RestController
@RequiredArgsConstructor
public class TokenController {
    private final AuthUserDetailsService userDetailsService;
    private final TokenService tokenService;

    @PostMapping("/auth/token")
    public TokenResponse createToken(
            @RequestBody TokenRequest tokenRequest
    ) {
        return tokenService.generateToken(tokenRequest, userDetailsService::loadUserByEmail);
    }
}
