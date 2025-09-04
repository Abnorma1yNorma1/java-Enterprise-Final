package by.it_academy.jd2.Mk_jd2_111_25.user_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.UserCreate;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.utility.UserMapper;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.service.api.IUserService;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.storage.entity.UserEntity;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.storage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public void create(UserCreate userCreate) {
        UserEntity userEntity = UserMapper.toEntity(userCreate);
        userRepository.save(userEntity);
    }

    @Override
    public User findByUuid(UUID uuid) {
        Optional<UserEntity> user = userRepository.findById(uuid);
        return user.map(UserMapper::toDto).orElse(null);
    }

    @Override
    public ResponsePage<User> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> springPage = userRepository.findAll(pageable).map(UserMapper::toDto);
        return ResponsePage.fromSpringPage(springPage);

    }

    @Override
    public void update(UUID uuid, Instant dtUpdate, UserCreate userCreate) {
        UserEntity entity = userRepository.findById(uuid).orElseThrow();
        if (!entity.getDtUpdate().equals(dtUpdate)){
            throw new IllegalStateException("");
        }
        entity.setMail(userCreate.getMail());
        entity.setMail(userCreate.getMail());
        entity.setFio(userCreate.getFio());
        entity.setRole(userCreate.getRole());
        entity.setStatus(userCreate.getStatus());
        userRepository.save(entity);
    }
}
