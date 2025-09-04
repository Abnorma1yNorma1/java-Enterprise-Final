package by.it_academy.jd2.Mk_jd2_111_25.account_operation_service.core.dto;

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
public class Operation {
    @NotNull
    private final UUID uuid;
    @NotNull
    private final Instant dt_create;
    @NotNull
    private final Instant dt_update;
    private Instant date;
    private String description;
    private UUID category;
    private BigDecimal value;
    private UUID currency;

}
