package ru.pkozlov.app.service.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailDataDto {
    private Long id;
    private String email;
}
