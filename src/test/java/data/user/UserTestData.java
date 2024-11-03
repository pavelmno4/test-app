package data.user;

import ru.pkozlov.app.dao.domain.Account;
import ru.pkozlov.app.dao.domain.EmailData;
import ru.pkozlov.app.dao.domain.PhoneData;
import ru.pkozlov.app.dao.domain.User;

import java.time.LocalDate;
import java.util.List;

public class UserTestData {
    public static User createUser(
            Long id,
            String name,
            Account account
    ) {
        return createUser(
                id,
                name,
                LocalDate.parse("1987-11-24"),
                "1234",
                account,
                List.of(),
                List.of()
        );
    }

    public static User createUser(
            Long id,
            String name,
            LocalDate dateOfBirth,
            String password,
            Account account,
            List<EmailData> emails,
            List<PhoneData> phones
    ) {
        return User.builder()
                .id(id)
                .name(name)
                .dateOfBirth(dateOfBirth)
                .password(password)
                .account(account)
                .emails(emails)
                .phones(phones)
                .build();
    }
}
