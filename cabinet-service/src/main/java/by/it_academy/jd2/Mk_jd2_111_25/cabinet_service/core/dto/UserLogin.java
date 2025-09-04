package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLogin {
    @Email
    private String mail;
    private String password;
}
