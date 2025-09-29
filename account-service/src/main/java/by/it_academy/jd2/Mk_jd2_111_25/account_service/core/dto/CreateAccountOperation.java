package by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto;

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
public class CreateAccountOperation {

    private Instant date;
    private String description;
    private UUID category;
    private BigDecimal value;
    private UUID currency;
}
