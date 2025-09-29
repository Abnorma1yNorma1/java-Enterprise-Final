package by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.enums.AccountType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class Account {

    @NotNull
    private UUID uuid;
    @NotNull
    private Instant dtCreate;
    @NotNull
    private Instant dtUpdate;
    private String title;
    private String description;
    private BigDecimal balance;
    private AccountType type;
    private String currency;

}
