package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserCreate;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserInfo;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserRole;

import java.time.Instant;
import java.util.UUID;

public interface IUserService {
    UserRole getRole(UUID uuid);
    void create(UserInfo userInfo);
    UserCreate getByMail(String mail);
    void update(UUID uuid, Instant dt_update, UserInfo userInfo);
    User getByUuid (UUID uuid);
    boolean adminMailExists(String mail);
}
