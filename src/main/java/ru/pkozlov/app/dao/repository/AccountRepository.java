package ru.pkozlov.app.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pkozlov.app.dao.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
