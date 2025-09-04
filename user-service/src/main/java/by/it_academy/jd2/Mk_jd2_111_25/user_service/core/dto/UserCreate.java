package by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.enums.UserStatus;
import jakarta.validation.constraints.Email;
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
    @NotNull
    private final UUID uuid;
    @NotNull
    private final Instant dtCreate;
    @NotNull
    private final Instant dtUpdate;
    @Email
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String password;
}
