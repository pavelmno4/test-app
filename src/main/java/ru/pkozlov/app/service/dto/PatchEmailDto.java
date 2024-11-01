package ru.pkozlov.app.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatchEmailDto {
    private String email;
}
