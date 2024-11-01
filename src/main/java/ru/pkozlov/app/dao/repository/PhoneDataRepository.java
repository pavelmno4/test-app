package ru.pkozlov.app.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pkozlov.app.dao.domain.PhoneData;

import java.util.Optional;

public interface PhoneDataRepository extends JpaRepository<PhoneData, Long> {
    Optional<PhoneData> findByPhone(String phone);
}
