package ru.pkozlov.app.service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class UserDto {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private AccountDto account;
    private List<EmailDataDto> emails;
    private List<PhoneDataDto> phones;
}
