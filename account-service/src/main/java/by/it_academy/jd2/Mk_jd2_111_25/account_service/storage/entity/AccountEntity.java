package by.it_academy.jd2.Mk_jd2_111_25.account_service.storage.entity;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts", schema = "account_service")
public class AccountEntity {

    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "dt_create", nullable = false, updatable = false)
    private Instant dtCreate;
    @Column(name = "dt_update")
    private Instant dtUpdate;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "balance")
    private BigDecimal balance;
    @Column(name = "type")
    private AccountType type;
    @Column(name = "currency")
    private String currency;

}
