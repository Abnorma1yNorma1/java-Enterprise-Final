package by.it_academy.jd2.Mk_jd2_111_25.user_service.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.UserCreate;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.UserInfo;

import java.time.Instant;
import java.util.UUID;

public interface IUserService {
    void create(UserInfo userInfo);
    User findByUuid (UUID uuid);
    UserCreate findByMail (String mail);
    ResponsePage<User> get(int page, int size);
    void update(UUID uuid, Instant dtUpdate, UserInfo userInfo);
}
