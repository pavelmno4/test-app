package ru.pkozlov.app.service.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SearchResponseDto {
    private List<UserDto> content;
    private Integer size;
    private Integer page;
}
