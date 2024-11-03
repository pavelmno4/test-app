package ru.pkozlov.app.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.pkozlov.app.service.security.dto.TokenRequest;
import ru.pkozlov.app.service.security.dto.TokenResponse;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final AuthenticationManager authenticationManager;
    private final JwtComponent jwtComponent;

    public TokenResponse generateToken(TokenRequest tokenRequest, Function<String, UserDetails> loadUserByEmail) {
        var email = tokenRequest.getEmail();
        var password = tokenRequest.getPassword();

        UserDetails userDetails = loadUserByEmail.apply(email);

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), password)
        );

        var token = jwtComponent.generateToken(userDetails);
        return TokenResponse.builder()
                .token(token)
                .build();
    }
}
