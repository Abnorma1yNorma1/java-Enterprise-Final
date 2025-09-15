package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Override
    public UserRole getRole(UUID uuid) {
        //TODO получить из user-service
        return null;
    }
}
