package by.it_academy.jd2.Mk_jd2_111_25.account_scheduler_service.core.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class ScheduledOperation {
    @NotNull
    private final UUID account;
    @NotNull
    private final Instant dt_create;
    @NotNull
    private final Instant dt_update;
    private Schedule schedule;
    private Operation operation;
}
