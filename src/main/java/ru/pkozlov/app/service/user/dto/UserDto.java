package ru.pkozlov.app.service.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private String password;
    private AccountDto account;
    private List<EmailDataDto> emails;
    private List<PhoneDataDto> phones;
}
