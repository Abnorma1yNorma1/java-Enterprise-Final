package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistration {
    @Email(message = "Appropriate mail format required")
    @NotBlank(message = "Mail should be provided.")
    private String mail;
    @NotBlank(message = "Fio should be provided.")
    private String fio;
    @NotBlank(message = "Password should be provided.")
    private String password;
}
