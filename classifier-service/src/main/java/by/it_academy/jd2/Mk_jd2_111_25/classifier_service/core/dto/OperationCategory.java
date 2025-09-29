package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class OperationCategory {
    @NotNull
    private final UUID uuid;
    @NotNull
    private final Instant dtCreate;
    @NotNull
    private final Instant dtUpdate;
    private String title;

}
