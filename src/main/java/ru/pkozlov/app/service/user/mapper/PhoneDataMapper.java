package ru.pkozlov.app.service.user.mapper;

import ru.pkozlov.app.dao.domain.PhoneData;
import ru.pkozlov.app.service.user.dto.PhoneDataDto;

public class PhoneDataMapper {
    public static PhoneDataDto asDto(PhoneData phoneData) {
        return PhoneDataDto.builder()
                .id(phoneData.getId())
                .phone(phoneData.getPhone())
                .build();
    }
}
