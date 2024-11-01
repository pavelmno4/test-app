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
        name = "phone_data",
        indexes = { @Index(name = "phone_data_user_id_idx", columnList = "user_id") }
)
public class PhoneData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "phone_data_seq")
    @SequenceGenerator(name = "phone_data_seq", sequenceName = "phone_data_id_seq", allocationSize = 1)
    private Long id;

    @Column(length = 13, nullable = false, unique = true)
    private String phone;
}
