package ru.pkozlov.app.dao.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@Table(
        name = "email_data",
        indexes = { @Index(name = "email_data_user_id_idx", columnList = "user_id") }
)
public class EmailData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_data_seq")
    @SequenceGenerator(name = "email_data_seq", sequenceName = "email_data_id_seq", allocationSize = 1)
    private Long id;

    @Column(length = 200, nullable = false, unique = true)
    private String email;
}
