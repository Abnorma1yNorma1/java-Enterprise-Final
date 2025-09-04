package by.it_academy.jd2.Mk_jd2_111_25.account_scheduler_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.account_scheduler_service.core.dto.enums.TimeUnit;
import lombok.Data;

import java.time.Instant;

@Data
public class Schedule {
    private Instant start_time;
    private Instant stop_time;
    private Instant interval;
    private TimeUnit time_unit;
}
