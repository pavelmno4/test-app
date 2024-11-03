package ru.pkozlov.app.service.user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.pkozlov.app.dao.repository.UserRepository;
import ru.pkozlov.app.service.exception.NotFoundException;

import java.math.BigDecimal;
import java.util.Optional;

import static data.user.AccountTestData.createAccount;
import static data.user.UserTestData.createUser;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final AccountComponent accountComponent = mock(AccountComponent.class);
    private final EmailDataComponent emailDataComponent = mock(EmailDataComponent.class);
    private final PhoneDataComponent phoneDataComponent = mock(PhoneDataComponent.class);

    private final UserService userService = new UserService(
            userRepository,
            accountComponent,
            emailDataComponent,
            phoneDataComponent
    );

    @Test
    public void successfullyTransferMoney() {
        var senderUser = createUser(1L, "Мартин", createAccount(1L, new BigDecimal("120.00"), new BigDecimal("120.00")));
        var receiverUser = createUser(2L, "Гриша", createAccount(2L, new BigDecimal("100.00"), new BigDecimal("100.00")));
        var amount = new BigDecimal("80.00");

        when(userRepository.findById(1L)).thenReturn(Optional.of(senderUser));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiverUser));
        when(accountComponent.transferMoney(senderUser.getAccount(), receiverUser.getAccount(), amount)).thenReturn(senderUser.getAccount());

        var result = userService.transferMoney(1L, 2L, amount);

        Assertions.assertThat(result).isNotNull();

        verify(accountComponent, times(1)).transferMoney(senderUser.getAccount(), receiverUser.getAccount(), amount);
    }

    @Test
    public void senderUserNotFound() {
        var amount = new BigDecimal("80.00");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions
                .assertThatCode(() -> userService.transferMoney(1L, 2L, amount))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("User with id 1 not found");
    }

    @Test
    public void receiverUserNotFound() {
        var senderUser = createUser(1L, "Мартин", createAccount(1L, new BigDecimal("120.00"), new BigDecimal("120.00")));
        var amount = new BigDecimal("80.00");

        when(userRepository.findById(1L)).thenReturn(Optional.of(senderUser));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());

        Assertions
                .assertThatCode(() -> userService.transferMoney(1L, 2L, amount))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("User with id 2 not found");
    }
}
