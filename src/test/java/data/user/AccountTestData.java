package data.user;

import ru.pkozlov.app.dao.domain.Account;

import java.math.BigDecimal;

public class AccountTestData {
    public static Account createAccount(
            Long id,
            BigDecimal initialBalance,
            BigDecimal balance
    ) {
        return Account.builder()
                .id(id)
                .initialBalance(initialBalance)
                .balance(balance)
                .build();
    }
}
