package ru.pkozlov.app.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.pkozlov.app.dao.repository.AccountRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

import static org.springframework.transaction.annotation.Isolation.SERIALIZABLE;

@Component
@RequiredArgsConstructor
public class AccountComponent {
    private final AccountRepository accountRepository;

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
