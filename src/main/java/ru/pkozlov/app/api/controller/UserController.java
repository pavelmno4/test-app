package ru.pkozlov.app.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import ru.pkozlov.app.service.user.UserService;
import ru.pkozlov.app.service.user.dto.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/v1/user")
    public SearchResponseDto search(
            @PageableDefault Pageable pageable,
            SearchRequestDto searchRequest
    ) {
        return userService.searchUsers(searchRequest, pageable);
    }

    @PostMapping("/v1/user/{userId}/email")
    public UserDto addEmail(
            @PathVariable Long userId,
            @RequestBody PatchEmailDto patchEmail
    ) {
        return userService.addEmail(userId, patchEmail);
    }

    @PatchMapping("/v1/user/{userId}/email/{emailDataId}")
    public UserDto updateEmail(
            @PathVariable Long userId,
            @PathVariable Long emailDataId,
            @RequestBody PatchEmailDto patchEmail
    ) {
        return userService.updateEmail(userId, emailDataId, patchEmail);
    }

    @DeleteMapping("/v1/user/{userId}/email/{emailDataId}")
    public UserDto deleteEmail(
            @PathVariable Long userId,
            @PathVariable Long emailDataId
    ) {
        return userService.deleteEmail(userId, emailDataId);
    }

    @PostMapping("/v1/user/{userId}/phone")
    public UserDto addPhone(
            @PathVariable Long userId,
            @RequestBody PatchPhoneDto patchPhoneDto
    ) {
        return userService.addPhone(userId, patchPhoneDto);
    }

    @PatchMapping("/v1/user/{userId}/phone/{phoneDataId}")
    public UserDto updatePhone(
            @PathVariable Long userId,
            @PathVariable Long phoneDataId,
            @RequestBody PatchPhoneDto patchPhoneDto
    ) {
        return userService.updatePhone(userId, phoneDataId, patchPhoneDto);
    }

    @DeleteMapping("/v1/user/{userId}/phone/{phoneDataId}")
    public UserDto deletePhone(
            @PathVariable Long userId,
            @PathVariable Long phoneDataId
    ) {
        return userService.deletePhone(userId, phoneDataId);
    }
}
