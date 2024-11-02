package ru.pkozlov.app.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.pkozlov.app.service.security.dto.AuthUserDetails;
import ru.pkozlov.app.service.user.UserService;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findByUsername(username);
        return AuthUserDetails.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }

    public UserDetails loadUserByEmail(String email) {
        var user = userService.findByEmail(email);
        return AuthUserDetails.builder()
                .id(user.getId())
                .name(user.getName())
                .password(user.getPassword())
                .build();
    }
}
