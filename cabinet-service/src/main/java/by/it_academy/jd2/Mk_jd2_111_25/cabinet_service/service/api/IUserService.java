package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserCreate;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserInfo;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserRole;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.util.UUID;

public interface IUserService {
    UserRole getRole(UUID uuid);
    void create(UserInfo userInfo);
    UserCreate getByMail(String mail);
    public void update(UUID uuid, Instant dt_update, UserCreate userCreate);
    public User getByUuid (UUID uuid);
}
