package ru.pkozlov.app.dao.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.pkozlov.app.dao.domain.EmailData_;
import ru.pkozlov.app.dao.domain.PhoneData_;
import ru.pkozlov.app.dao.domain.User;
import ru.pkozlov.app.dao.domain.User_;

import java.time.LocalDate;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    interface Specs {
        static Specification<User> byDateOfBirth(LocalDate dateOfBirth) {
            return (root, query, builder) ->
                    dateOfBirth == null ? builder.conjunction() : builder.greaterThan(root.get(User_.dateOfBirth), dateOfBirth);
        }

        static Specification<User> byName(String name) {
            return (root, query, builder) ->
                    name == null ? builder.conjunction() : builder.like(root.get(User_.name), name + "%");
        }

        static Specification<User> byPhone(String phone) {
            return (root, query, builder) ->
                    phone == null ? builder.conjunction() : builder.equal(root.join(User_.phones).get(PhoneData_.phone), phone);
        }

        static Specification<User> byEmail(String email) {
            return (root, query, builder) ->
                    email == null ? builder.conjunction() : builder.equal(root.join(User_.emails).get(EmailData_.email), email);
        }
    }
}
