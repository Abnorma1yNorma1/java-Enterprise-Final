package by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserCreate {
    @NotBlank(message = "Id should be provided.")
    private UUID uuid;
    @NotBlank(message = "Date of creation should be provided.")
    private Instant dtCreate;
    @NotBlank(message = "Date of last update should be provided.")
    private Instant dtUpdate;
    @Email(message = "Appropriate mail format required")
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String password;
}
