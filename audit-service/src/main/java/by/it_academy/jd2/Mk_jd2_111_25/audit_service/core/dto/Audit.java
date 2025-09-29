package by.it_academy.jd2.Mk_jd2_111_25.audit_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.audit_service.core.dto.enums.EssenceType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@RequiredArgsConstructor
public class Audit {
    @NotNull
    private final UUID uuid;
    @NotNull
    private final Instant dt_create;
    private UserAudit userAudit;
    private String text;
    private EssenceType type;
    private UUID id;
}
