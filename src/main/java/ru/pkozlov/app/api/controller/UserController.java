package ru.pkozlov.app.api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.pkozlov.app.api.dto.MoneyTransferRequest;
import ru.pkozlov.app.service.security.dto.AuthUserDetails;
import ru.pkozlov.app.service.user.UserService;
import ru.pkozlov.app.service.user.dto.*;

@Tag(name = "User Controller", description = "Ресурсы пользователя")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/v1/user")
    public SearchResponseDto search(
            @ParameterObject @PageableDefault Pageable pageable,
            @ParameterObject SearchRequestDto searchRequest
    ) {
        return userService.searchUsers(searchRequest, pageable);
    }

    @PostMapping("/v1/user/{userId}/transfer-money")
    public UserDto transferMoney(
            @AuthenticationPrincipal AuthUserDetails sender,
            @RequestBody MoneyTransferRequest moneyTransferRequest
    ) {
        return userService.transferMoney(
                sender.getId(),
                moneyTransferRequest.getReceiverUserId(),
                moneyTransferRequest.getAmount()
        );
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
