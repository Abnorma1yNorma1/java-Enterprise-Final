package by.it_academy.jd2.Mk_jd2_111_25.audit_service.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.audit_service.core.dto.enums.UserRole;

import java.util.UUID;

public interface IUserService {
    UserRole getRole(UUID uuid);
}
