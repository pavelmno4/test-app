package ru.pkozlov.app.service.mapper;

import org.springframework.data.domain.Page;
import ru.pkozlov.app.dao.domain.User;
import ru.pkozlov.app.service.dto.SearchResponseDto;

public class SearchResponseMapper {
    public static SearchResponseDto asDto(Page<User> page) {
        return SearchResponseDto.builder()
                .content(page.map(UserMapper::asDto).getContent())
                .size(page.getSize())
                .page(page.getNumber())
                .build();
    }
}
