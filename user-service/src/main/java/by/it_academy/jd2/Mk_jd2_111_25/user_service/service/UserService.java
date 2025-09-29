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
        UserEntity user = userRepository.findById(uuid).orElseThrow(() -> new DataRetrievalFailureException("User could not be found by id in user database."));
        return UserMapper.toDto(user);
    }

    @Override
    public UserCreate findByMail(String mail) {
        UserEntity entity = userRepository.findByMail(mail).orElseThrow(() -> new DataRetrievalFailureException("User could not be found by mail in user database."));
        return UserMapper.toUserCreate(entity);
    }

    @Override
    public ResponsePage<User> get(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> springPage = userRepository.findAll(pageable).map(UserMapper::toDto);
        return ResponsePage.fromSpringPage(springPage);
    }

    @Override
    @Transactional
    public void update(UUID uuid, Instant dtUpdate, UserInfo userInfo) {
        UserEntity entity = userRepository.findById(uuid).orElseThrow(() -> new DataRetrievalFailureException("User could not be found in user database."));

        boolean isStatusUpdateOnly = userInfo.getMail().equals(entity.getMail())
                && userInfo.getFio().equals(entity.getFio())
                && userInfo.getRole().equals(entity.getRole())
                && userInfo.getPassword().equals(entity.getPassword())
                && userInfo.getStatus() != entity.getStatus();

        if (!isStatusUpdateOnly && !entity.getDtUpdate().equals(dtUpdate)) {
            throw new IllegalStateException("Your user record is outdated.");
        }
        entity.setMail(userInfo.getMail());
        entity.setFio(userInfo.getFio());
        entity.setRole(userInfo.getRole());
        entity.setStatus(userInfo.getStatus());
        entity.setPassword(userInfo.getPassword());
        entity.setDtUpdate(Instant.now());
        userRepository.save(entity);
    }

    @Override
    @Transactional
    public UserCreate serviceCreate(UserInfo userInfo){
        UserEntity userEntity = UserMapper.toEntity(userInfo);
        UserEntity returned =  userRepository.save(userEntity);
        return UserMapper.toUserCreate(returned);
    }
}
