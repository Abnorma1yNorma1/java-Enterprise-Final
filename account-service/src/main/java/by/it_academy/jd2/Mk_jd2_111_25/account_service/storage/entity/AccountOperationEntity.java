package by.it_academy.jd2.Mk_jd2_111_25.account_service.storage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account_operations", schema = "account_operation_service")
public class AccountOperationEntity {

    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "dt_create", nullable = false, updatable = false)
    private Instant dtCreate;
    @Column(name = "dt_update")
    private Instant dtUpdate;
    @Column(name = "date")
    private Instant date;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private UUID category;
    @Column(name = "dt_update")
    private BigDecimal value;
    @Column(name = "currency")
    private UUID currency;
}
