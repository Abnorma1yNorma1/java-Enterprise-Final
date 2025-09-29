package by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
public class User {
    @NotBlank
    private UUID uuid;
    @NotBlank
    private Instant dtCreate;
    @NotBlank
    private Instant dtUpdate;
    @NotBlank
    @Email
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
}
