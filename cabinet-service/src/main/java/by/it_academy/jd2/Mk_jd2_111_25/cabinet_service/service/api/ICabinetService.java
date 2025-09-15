package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserLogin;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserRegistration;

import java.util.UUID;

public interface ICabinetService {
    void register(UserRegistration userRegistration);
    void verify(String code, String mail);
    String login(UserLogin userLogin);
    User me(UUID uuid);
}
