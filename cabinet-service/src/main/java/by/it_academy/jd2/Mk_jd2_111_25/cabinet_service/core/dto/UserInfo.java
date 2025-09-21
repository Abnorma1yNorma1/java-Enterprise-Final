package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserStatus;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Email
    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String passwordHash;
}
