package ru.pkozlov.app.service.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.pkozlov.app.dao.repository.AccountRepository;
import ru.pkozlov.app.service.exception.ValidationException;

import java.math.BigDecimal;

import static data.user.AccountTestData.createAccount;
import static org.mockito.Mockito.*;

public class AccountComponentTest {
    private final AccountRepository accountRepository = mock(AccountRepository.class, (invocation) -> invocation.getArguments()[0]);

    private final AccountComponent accountComponent = new AccountComponent(
            accountRepository
    );

    @Test
    public void successfullyTransferMoney() {
        var senderAccount = createAccount(1L, new BigDecimal("120.00"), new BigDecimal("120.00"));
        var receiverAccount = createAccount(2L, new BigDecimal("100.00"), new BigDecimal("100.00"));
        var amount = new BigDecimal("80.00");

        var result = accountComponent.transferMoney(senderAccount, receiverAccount, amount);

        Assertions.assertThat(result.getBalance()).isEqualTo(new BigDecimal("40.00"));
        Assertions.assertThat(receiverAccount.getBalance()).isEqualTo(new BigDecimal("180.00"));

        verify(accountRepository, times(1)).save(receiverAccount);
        verify(accountRepository, times(1)).save(senderAccount);
    }

    @Test
    public void notEnoughMoney() {
        var senderAccount = createAccount(1L, new BigDecimal("50.00"), new BigDecimal("50.00"));
        var receiverAccount = createAccount(2L, new BigDecimal("100.00"), new BigDecimal("100.00"));
        var amount = new BigDecimal("110.00");

        Assertions
                .assertThatCode(() -> accountComponent.transferMoney(senderAccount, receiverAccount, amount))
                .isInstanceOf(ValidationException.class)
                .hasMessage("Not enough money");

        Assertions.assertThat(senderAccount.getBalance()).isEqualTo(new BigDecimal("50.00"));
        Assertions.assertThat(receiverAccount.getBalance()).isEqualTo(new BigDecimal("100.00"));
    }
}
