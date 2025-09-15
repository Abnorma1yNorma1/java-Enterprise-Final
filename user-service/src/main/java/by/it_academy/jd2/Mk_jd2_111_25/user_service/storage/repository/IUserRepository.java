package by.it_academy.jd2.Mk_jd2_111_25.user_service.storage.repository;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.storage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByMail(String mail);
}
