package ru.pkozlov.app.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pkozlov.app.dao.domain.PhoneData;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
}
