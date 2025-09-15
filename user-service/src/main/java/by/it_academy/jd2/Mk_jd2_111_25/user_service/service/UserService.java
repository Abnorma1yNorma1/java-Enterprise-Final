package by.it_academy.jd2.Mk_jd2_111_25.user_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.UserCreate;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.UserInfo;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.utility.UserMapper;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.service.api.IUserService;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.storage.entity.UserEntity;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.storage.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    @Override
    @Transactional
    public void create(UserInfo userInfo) {
        UserEntity userEntity = UserMapper.toEntity(userInfo);
        userRepository.save(userEntity);
    }

    @Override
    public User findByUuid(UUID uuid) {
        UserEntity user = userRepository.findById(uuid).orElseThrow(() -> new DataRetrievalFailureException("User could not be found in user database."));
        return UserMapper.toDto(user);
    }

    @Override
    public ResponsePage<User> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> springPage = userRepository.findAll(pageable).map(UserMapper::toDto);
        return ResponsePage.fromSpringPage(springPage);
    }

    @Override
    @Transactional
    public void update(UUID uuid, Instant dtUpdate, UserCreate userCreate) {
        UserEntity entity = userRepository.findById(uuid).orElseThrow(() -> new DataRetrievalFailureException("User could not be found in user database."));
        if (!entity.getDtUpdate().equals(dtUpdate)) {
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
