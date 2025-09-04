package by.it_academy.jd2.Mk_jd2_111_25.report_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.report_service.core.dto.enums.ReportType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class Report {
    @NotNull
    private final UUID uuid;
    @NotNull
    private final Instant dt_create;
    @NotNull
    private final Instant dt_update;
    private String status;
    private ReportType type;
    private final String description;

}
