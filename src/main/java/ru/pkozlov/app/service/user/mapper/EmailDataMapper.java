package ru.pkozlov.app.service.user.mapper;

import ru.pkozlov.app.dao.domain.EmailData;
import ru.pkozlov.app.service.user.dto.EmailDataDto;

public class EmailDataMapper {
    public static EmailDataDto asDto(EmailData emailData) {
        return EmailDataDto.builder()
                .id(emailData.getId())
                .email(emailData.getEmail())
                .build();
    }
}
