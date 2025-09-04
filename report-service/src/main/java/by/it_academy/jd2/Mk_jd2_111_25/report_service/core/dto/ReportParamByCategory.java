package by.it_academy.jd2.Mk_jd2_111_25.report_service.core.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class ReportParamByCategory {
    private List<UUID> accounts;
    private LocalDate from;
    private LocalDate to;
    private List<UUID> categories;
}
