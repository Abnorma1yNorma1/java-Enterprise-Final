package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.common.client.IUserClient;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.*;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserStatus;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.ICabinetService;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.IPasswordEncoder;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.entity.RegistrationEntity;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.repository.IAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CabinetService implements ICabinetService {

    private final IAuthRepository authRepository;
    private final IPasswordEncoder encoder;
    private final JwtTokenHandler tokenHandler;
    private final IUserClient userClient;

    @Override
    @Transactional
    public void register(UserRegistration userRegistration) {
        String registrationCode = MailConfirmationCodeGenerator.generate(6);
        String registrationCodeHash = encoder.hash(registrationCode);

        UserInfo userInfo = UserInfo.builder()
                .mail(userRegistration.getMail())
                .fio(userRegistration.getFio())
                .role(UserRole.USER)
                .status(UserStatus.WAITING_ACTIVATION)
                .passwordHash(encoder.hash(userRegistration.getPassword()))
                .build();

        ResponseEntity<Void> createResponse = userClient.create(userInfo);
        if (!createResponse.getStatusCode().is2xxSuccessful()){
            //TODO
        }
        ResponseEntity<UserCreate> userResponse = userClient.getByMail(userRegistration.getMail());;
        UserCreate user = userResponse.getBody();
        if (user == null){
            throw new DataRetrievalFailureException("Unable to retrieve user");
        }
        RegistrationEntity entity = RegistrationEntity.builder()
                .uuid(user.getUuid())
                .codeHash(registrationCodeHash)
                .build();
        authRepository.save(entity);
        //TODO отправка письма с кодом на почту
    }

    @Override
    @Transactional
    public void verify(String code, String mail) {

        ResponseEntity<UserCreate> getByMailResponse = userClient.getByMail(mail);
        UserCreate user = getByMailResponse.getBody();
        if (user == null){
            throw new DataRetrievalFailureException("Unable to retrieve user");
        }
            UUID uuid = user.getUuid();
            RegistrationEntity record = authRepository.findByUuid(uuid).orElseThrow(
                    () -> new DataRetrievalFailureException("Record could not be found in registration database."));
            if (encoder.match(code, record.getCodeHash())){
                UserCreate userCreate = UserCreate.builder()
                        .uuid(user.getUuid())
                        .dtCreate(user.getDtCreate())
                        .dtUpdate(user.getDtUpdate())
                        .mail(user.getMail())
                        .fio(user.getFio())
                        .role(user.getRole())
                        .status(UserStatus.ACTIVATED)
                        .password(user.getPassword())
                        .build();
                ResponseEntity<Void> updateResponse = userClient.update(uuid, Instant.now(), userCreate);
                if (!updateResponse.getStatusCode().is2xxSuccessful()) {
                    //TODO throw new IllegalStateException("User update failed with status: " + updateResponse.getStatusCode());
                }
            }
    }

    @Override
    public String login(UserLogin userLogin) {
        RegistrationEntity entity = authRepository.findByMail(userLogin.getMail()).orElseThrow(
                () -> new DataRetrievalFailureException("Mail could not be found in registration database.")
        );
        ResponseEntity<UserCreate> getByMailResponse = userClient.getByMail(userLogin.getMail());
        UserCreate user = getByMailResponse.getBody();
        if (user == null){
            throw new DataRetrievalFailureException("Unable to retrieve user");
        }
        encoder.match(userLogin.getPassword(), user.getPassword());
        return tokenHandler.generate(entity.getUuid());

    }

    @Override
    public User me(UUID uuid) {
        ResponseEntity<User> gwtByUuidResponse = userClient.getByUuid(uuid);
        User user = gwtByUuidResponse.getBody();
        if (user == null){
            throw new DataRetrievalFailureException("Unable to retrieve user");
        }
        return user;
    }
}
