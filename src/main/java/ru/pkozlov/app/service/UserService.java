package ru.pkozlov.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pkozlov.app.dao.repository.UserRepository;
import ru.pkozlov.app.service.dto.PatchEmailDto;
import ru.pkozlov.app.service.dto.PatchPhoneDto;
import ru.pkozlov.app.service.dto.SearchRequestDto;
import ru.pkozlov.app.service.dto.UserDto;
import ru.pkozlov.app.service.exception.NotFoundException;
import ru.pkozlov.app.service.exception.ValidationException;
import ru.pkozlov.app.service.mapper.UserMapper;

import static ru.pkozlov.app.dao.repository.UserRepository.Specs.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final EmailDataComponent emailDataComponent;
    private final PhoneDataComponent phoneDataComponent;

    @Transactional
    public UserDto addEmail(Long userId, PatchEmailDto patchEmail) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        user.getEmails().add(emailDataComponent.createEmail(patchEmail));
        return UserMapper.asDto(user);
    }

    @Transactional
    public UserDto updateEmail(Long userId, Long emailDataId, PatchEmailDto patchEmail) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        user.getEmails().stream()
                .filter(emailData -> emailData.getId().equals(emailDataId))
                .findFirst()
                .ifPresentOrElse(
                        emailData -> emailDataComponent.updateEmail(emailData, patchEmail),
                        () -> { throw new NotFoundException(String.format("EmailData with id %d not found for User %d", emailDataId, userId)); }
                );

        return UserMapper.asDto(user);
    }

    @Transactional
    public UserDto deleteEmail(Long userId, Long emailDataId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        if (user.getEmails().size() == 1) throw new ValidationException("User must have as least 1 email");

        user.getEmails().stream()
                .filter(emailData -> emailData.getId().equals(emailDataId))
                .findFirst()
                .ifPresentOrElse(
                        emailDataComponent::deleteEmail,
                        () -> { throw new NotFoundException(String.format("EmailData with id %d not found for User %d", emailDataId, userId)); }
                );

        return UserMapper.asDto(user);
    }

    @Transactional
    public UserDto addPhone(Long userId, PatchPhoneDto patchPhone) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        user.getPhones().add(phoneDataComponent.createPhone(patchPhone));
        return UserMapper.asDto(user);
    }

    @Transactional
    public UserDto updatePhone(Long userId, Long phoneDataId, PatchPhoneDto patchPhone) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        user.getPhones().stream()
                .filter(phoneData -> phoneData.getId().equals(phoneDataId))
                .findFirst()
                .ifPresentOrElse(
                        phoneData -> phoneDataComponent.updatePhone(phoneData, patchPhone),
                        () -> { throw new NotFoundException(String.format("PhoneData with id %d not found for User %d", phoneDataId, userId)); }
                );

        return UserMapper.asDto(user);
    }

    @Transactional
    public UserDto deletePhone(Long userId, Long phoneDataId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("User with id %d not found", userId)));

        if (user.getPhones().size() == 1) throw new ValidationException("User must have as least 1 phone");

        user.getPhones().stream()
                .filter(phoneData -> phoneData.getId().equals(phoneDataId))
                .findFirst()
                .ifPresentOrElse(
                        phoneDataComponent::deletePhone,
                        () -> { throw new NotFoundException(String.format("PhoneData with id %d not found for User %d", phoneDataId, userId)); }
                );

        return UserMapper.asDto(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDto> searchUsers(SearchRequestDto searchRequest, Pageable pageable) {
        return userRepository.findAll(
                byDateOfBirth(searchRequest.getDateOfBirth())
                        .and(byPhone(searchRequest.getPhone()))
                        .and(byName(searchRequest.getName()))
                        .and(byEmail(searchRequest.getEmail())),
                pageable
        ).map(UserMapper::asDto);
    }
}
