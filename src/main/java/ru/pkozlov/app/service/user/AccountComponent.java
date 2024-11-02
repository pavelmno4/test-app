package ru.pkozlov.app.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.pkozlov.app.dao.domain.Account;
import ru.pkozlov.app.dao.repository.AccountRepository;
import ru.pkozlov.app.service.exception.ValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Component
@RequiredArgsConstructor
public class AccountComponent {
    private final AccountRepository accountRepository;

    @Transactional(isolation = SERIALIZABLE)
    public Account transferMoney(Account senderAccount, Account receiverAccount, BigDecimal amount) {
        var senderCurrentBalance = senderAccount.getBalance();
        var receiverCurrentBalance = receiverAccount.getBalance();

        if (senderCurrentBalance.subtract(amount).compareTo(BigDecimal.ZERO) < 0)
            throw new ValidationException("Not enough money");

        senderAccount.setBalance(senderCurrentBalance.subtract(amount));
        receiverAccount.setBalance(receiverCurrentBalance.add(amount));

        accountRepository.save(receiverAccount);
        return accountRepository.save(senderAccount);
    }

    @Scheduled(initialDelay = 30 * 1000, fixedDelay = 30 * 1000)
    @Transactional(isolation = SERIALIZABLE)
    public void increaseBalance() {
        var updatedAccounts = accountRepository.findAll().stream()
                .peek(account -> {
                    var balanceUpperLimit = account.getBalanceUpperLimit();
                    var currentBalance = account.getBalance();
                    var updatedBalance = currentBalance.multiply(new BigDecimal("1.1")).setScale(2, RoundingMode.HALF_EVEN);

                    updatedBalance = (updatedBalance.compareTo(balanceUpperLimit) > 0) ? balanceUpperLimit : updatedBalance;

                    account.setBalance(updatedBalance);
                })
                .collect(Collectors.toList());
        accountRepository.saveAll(updatedAccounts);
    }
}
