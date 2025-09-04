package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String logRef = "error";
    private String message;
}
