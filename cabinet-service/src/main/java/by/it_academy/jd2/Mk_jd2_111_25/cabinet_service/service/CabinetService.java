package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.*;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserStatus;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.service.api.*;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.entity.RegistrationEntity;
import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.repository.IAuthRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CabinetService implements ICabinetService {

    private final IAuthRepository authRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenHandler tokenHandler;
    private final IMailConfirmationCodeGenerator mailConfirmationCodeGenerator;
    private final IUserService userService;
    private final IMailService mailService;

    @Override
    @Transactional
    public void register(UserRegistration userRegistration) {
        String registrationCode = mailConfirmationCodeGenerator.generate(6);
        String registrationCodeHash = encoder.encode(registrationCode);

        UserInfo userInfo = UserInfo.builder()
                .mail(userRegistration.getMail())
                .fio(userRegistration.getFio())
                .role(UserRole.USER)
                .status(UserStatus.WAITING_ACTIVATION)
                .passwordHash(encoder.encode(userRegistration.getPassword()))
                .build();

        userService.create(userInfo);
        UserCreate user = userService.getByMail(userRegistration.getMail());

        RegistrationEntity entity = RegistrationEntity.builder()
                .userUuid(user.getUuid())
                .codeHash(registrationCodeHash)
                .build();

        authRepository.save(entity);

        MailRequest mailRequest = MailRequest.builder()
                .to(userRegistration.getMail())
                .subject("Confirmation code")
                .text("This is your confirmation code for Personal finance app:" + registrationCode)
                .build();

        mailService.send(mailRequest);
    }

    @Override
    @Transactional
    public void verify(String code, @Email(message = "Invalid email format.") @NotBlank(message = "Mail must be provided.") String mail) {
        UserCreate user = userService.getByMail(mail);
        UUID uuid = user.getUuid();
        RegistrationEntity record = authRepository.findByUserUuid(uuid).orElseThrow(
                () -> new DataRetrievalFailureException("Record could not be found in registration database."));
        if (encoder.matches(code, record.getCodeHash())) {
            UserInfo userInfo = UserInfo.builder()
                    .mail(user.getMail())
                    .fio(user.getFio())
                    .role(user.getRole())
                    .status(UserStatus.ACTIVATED)
                    .passwordHash(encoder.encode(user.getPassword()))
                    .build();
            userService.update(uuid, Instant.now(), userInfo);
        }
    }

    @Override
    public String login(UserLogin userLogin) {
        UserCreate user = userService.getByMail(userLogin.getMail());
        if (!user.getStatus().equals(UserStatus.ACTIVATED)){
            throw new DisabledException("User unverified.");
        }
        encoder.matches(userLogin.getPassword(), user.getPassword());
        return tokenHandler.generate(user.getUuid());
    }

    @Override
    public User me(UUID uuid) {
        return userService.getByUuid(uuid);
    }
}
