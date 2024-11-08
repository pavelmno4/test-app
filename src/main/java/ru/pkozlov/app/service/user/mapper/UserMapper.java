package ru.pkozlov.app.service.user.mapper;

import ru.pkozlov.app.dao.domain.User;
import ru.pkozlov.app.service.user.dto.UserDto;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto asDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .dateOfBirth(user.getDateOfBirth())
                .password(user.getPassword())
                .account(AccountMapper.asDto(user.getAccount()))
                .emails(
                        user.getEmails().stream()
                                .map(EmailDataMapper::asDto)
                                .collect(Collectors.toList())
                )
                .phones(
                        user.getPhones().stream()
                                .map(PhoneDataMapper::asDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
