package by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto;

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
public class AccountOperation {
    @NotNull
    private UUID uuid;
    @NotNull
    private Instant dtCreate;
    @NotNull
    private Instant dtUpdate;
    private Instant date;
    private String description;
    private UUID category;
    private BigDecimal value;
    private UUID currency;

}
