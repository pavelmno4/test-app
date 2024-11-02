package ru.pkozlov.app.dao.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Getter
@Setter
@Table(
        name = "account",
        indexes = {@Index(name = "account_user_id_idx", columnList = "user_id")}
)
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_id_seq", allocationSize = 1)
    private Long id;

    @Min(value = 0)
    @Column(nullable = false, scale = 2)
    private BigDecimal initialBalance;

    @Min(value = 0)
    @Column(nullable = false, scale = 2)
    private BigDecimal balance;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "account_user_id_fk"), unique = true)
    private User user;

    public BigDecimal getBalanceUpperLimit() {
        return initialBalance.multiply(new BigDecimal("2.07")).setScale(2, RoundingMode.HALF_EVEN);
    }
}
