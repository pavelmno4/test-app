package ru.pkozlov.app.service.mapper;

import ru.pkozlov.app.dao.domain.EmailData;
import ru.pkozlov.app.service.dto.EmailDataDto;

public class EmailDataMapper {
    public static EmailDataDto asDto(EmailData emailData) {
        return EmailDataDto.builder()
                .id(emailData.getId())
                .email(emailData.getEmail())
                .build();
    }
}
