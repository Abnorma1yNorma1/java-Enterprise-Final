package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserLogin;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.UserRegistration;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserStatus;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.ICabinetService;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.IPasswordEncoder;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.entity.RegistrationEntity;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.repository.IAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
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

    @Override
    @Transactional
    public void register(UserRegistration userRegistration) {
        String registrationCode = encoder.hash(userRegistration.getPassword());
        RegistrationEntity entity = RegistrationEntity.builder()
                .mail(userRegistration.getMail())
                .fio(userRegistration.getFio())
                .passwordHash(registrationCode)
                .code(MailConfirmationCodeGenerator.generate(6))
                .status(UserStatus.WAITING_ACTIVATION)
                .InstantOfRegistration(Instant.now())
                .build();
        authRepository.save(entity);
        //TODO отправка письма с кодом на почту
    }

    @Override
    @Transactional
    public void verify(String code, String mail) {
        RegistrationEntity entity = authRepository.findByMail(mail).orElseThrow(
                () -> new DataRetrievalFailureException("Mail could not be found in registration database.")
        );
        encoder.match(code, entity.getPasswordHash());
        //TODO пометить состояние как "прошел" и послать сообщение в gateway?
    }

    @Override
    public String login(UserLogin userLogin) {
        RegistrationEntity entity = authRepository.findByMail(userLogin.getMail()).orElseThrow(
                () -> new DataRetrievalFailureException("Mail could not be found in registration database.")
        );
        encoder.match(userLogin.getPassword(), entity.getPasswordHash());
        return tokenHandler.generate(entity.getUuid());

    }

    @Override
    public User me(UUID uuid) {
        //TODO обратиться к user-service за данными юзера
        return null;
    }
}
