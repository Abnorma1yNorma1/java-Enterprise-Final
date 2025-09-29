package by.it_academy.jd2.Mk_jd2_111_25.audit_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.audit_service.common.controller.client.IUserClient;
import by.it_academy.jd2.Mk_jd2_111_25.audit_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.audit_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.audit_service.service.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final IUserClient userClient;

    @Override
    public UserRole getRole(UUID uuid) {
        User user = userClient.getByUuid(uuid).getBody();
        if (user == null){
            throw new DataRetrievalFailureException("Unable to retrieve user");
        }
        return user.getRole();
    }

}
