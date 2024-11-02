package ru.pkozlov.app.service.user.mapper;

import org.springframework.data.domain.Page;
import ru.pkozlov.app.dao.domain.User;
import ru.pkozlov.app.service.user.dto.SearchResponseDto;

public class SearchResponseMapper {
    public static SearchResponseDto asDto(Page<User> page) {
        return SearchResponseDto.builder()
                .content(page.map(UserMapper::asDto).getContent())
                .size(page.getSize())
                .page(page.getNumber())
                .build();
    }
}
