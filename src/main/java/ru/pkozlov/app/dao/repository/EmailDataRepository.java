package ru.pkozlov.app.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pkozlov.app.dao.domain.EmailData;

public interface EmailDataRepository extends JpaRepository<EmailData, Long> {
}
