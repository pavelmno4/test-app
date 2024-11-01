package ru.pkozlov.app.service.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchRequestDto {
    private LocalDate dateOfBirth;
    private String phone;
    private String name;
    private String email;
}
