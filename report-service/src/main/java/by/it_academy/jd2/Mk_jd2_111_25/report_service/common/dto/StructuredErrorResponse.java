package by.it_academy.jd2.Mk_jd2_111_25.report_service.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StructuredErrorResponse {
    private String logRef = "structured_error";
    private List<FieldError> errors;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FieldError{
        private String field;
        private String message;
    }
}
