package by.it_academy.jd2.Mk_jd2_111_25.account_scheduler_service.core.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class Operation {
    private UUID account;
    private String description;
    private UUID category;
    private BigDecimal value;
    private UUID currency;

}
