package ru.pkozlov.app.service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountDto {
    private Long id;
    private BigDecimal balance;
}
