package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistration {
    @Email
    @NotBlank
    private String mail;
    @NotBlank
    private String fio;
    @NotBlank
    private String password;
}
