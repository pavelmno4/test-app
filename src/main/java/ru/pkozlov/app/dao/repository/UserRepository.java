package ru.pkozlov.app.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pkozlov.app.dao.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
