package by.it_academy.jd2.Mk_jd2_111_25.audit_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.audit_service.core.dto.enums.UserRole;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@Data
@Builder
@RequiredArgsConstructor
public class UserAudit {
    private  UUID uuid;
    @Email
    private String mail;
    private String fio;
    private UserRole role;
}
