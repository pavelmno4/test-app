package ru.pkozlov.app.service.mapper;

import ru.pkozlov.app.dao.domain.Account;
import ru.pkozlov.app.service.dto.AccountDto;

public class AccountMapper {
    public static AccountDto asDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .build();
    }
}
