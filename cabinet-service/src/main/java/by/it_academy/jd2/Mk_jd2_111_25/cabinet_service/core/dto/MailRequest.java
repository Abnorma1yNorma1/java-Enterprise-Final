package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MailRequest {
    @Email
    @NotBlank
    private String to;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;
}
