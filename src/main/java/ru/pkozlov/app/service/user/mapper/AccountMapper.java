package ru.pkozlov.app.service.user.mapper;

import ru.pkozlov.app.dao.domain.Account;
import ru.pkozlov.app.service.user.dto.AccountDto;

public class AccountMapper {
    public static AccountDto asDto(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .balance(account.getBalance())
                .build();
    }
}
