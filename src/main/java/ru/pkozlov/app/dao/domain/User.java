package ru.pkozlov.app.dao.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "usr")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usr_seq")
    @SequenceGenerator(name = "usr_seq", sequenceName = "usr_id_seq", allocationSize = 1)
    private Long id;

    @Column(length = 500, nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(length = 500, nullable = false)
    private String password;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Account account;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "email_data_user_id_fk"))
    private List<EmailData> emails;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "phone_data_user_id_fk"))
    private List<PhoneData> phones;
}
