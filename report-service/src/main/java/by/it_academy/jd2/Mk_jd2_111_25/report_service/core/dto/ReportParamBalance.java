package by.it_academy.jd2.Mk_jd2_111_25.report_service.core.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReportParamBalance implements ReportParam {
    private List<UUID> accounts;
}
