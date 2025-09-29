package by.it_academy.jd2.Mk_jd2_111_25.user_service.core.utility;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.User;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.UserCreate;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.UserInfo;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.storage.entity.UserEntity;

import java.time.Instant;
import java.util.UUID;

public class UserMapper {

    public static UserEntity toEntity(UserCreate dto) {
        return UserEntity.builder()
                .uuid(dto.getUuid())
                .dtCreate(dto.getDtCreate())
                .dtUpdate(dto.getDtUpdate())
                .mail(dto.getMail())
                .fio(dto.getFio())
                .role(dto.getRole())
                .status(dto.getStatus())
                .password(dto.getPassword())
                .build();
    }

    public static UserEntity toEntity(UserInfo dto) {
        Instant creationMoment = Instant.now();
        return UserEntity.builder()
                .dtCreate(creationMoment)
                .dtUpdate(creationMoment)
                .mail(dto.getMail())
                .fio(dto.getFio())
                .role(dto.getRole())
                .status(dto.getStatus())
                .password(dto.getPassword())
                .build();
    }

    public static User toDto(UserEntity entity) {
        return User.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .mail(entity.getMail())
                .fio(entity.getFio())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build();
    }

    public static UserCreate toUserCreate(UserEntity entity){
        return UserCreate.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .mail(entity.getMail())
                .fio(entity.getFio())
                .role(entity.getRole())
                .status(entity.getStatus())
                .password(entity.getPassword())
                .build();
    }
}

