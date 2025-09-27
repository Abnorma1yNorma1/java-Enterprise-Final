package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.common.client.IUserClient;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserCreate;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserInfo;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
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

    @Override
    public void create(UserInfo userInfo){
        ResponseEntity<?> response = userClient.create(userInfo);
        if (!response.getStatusCode().is2xxSuccessful()){
            throw new DataRetrievalFailureException("Request to create user failed");
        }
    }

    @Override
    public UserCreate getByMail(String mail){
        ResponseEntity<UserCreate> createResponse = userClient.getByMail(mail);
        UserCreate userCreate = createResponse.getBody();
        if (userCreate == null){
            throw new DataRetrievalFailureException("Unable to retrieve user by mail");
        }
        return userCreate;
    }

    @Override
    public void update(UUID uuid, Instant dt_update, UserCreate userCreate){
        ResponseEntity<Void> updateResponse = userClient.update(uuid, Instant.now(), userCreate);
        if (!updateResponse.getStatusCode().is2xxSuccessful()) {
            throw new DataIntegrityViolationException("User update failed with status: " + updateResponse.getStatusCode());
        }
    }

    @Override
    public User getByUuid (UUID uuid){
        ResponseEntity<User> getByUuidResponse = userClient.getByUuid(uuid);
        User user = getByUuidResponse.getBody();
        if (user == null) {
            throw new DataRetrievalFailureException("Unable to retrieve user by uuid");
        }
        return user;
    }
}
