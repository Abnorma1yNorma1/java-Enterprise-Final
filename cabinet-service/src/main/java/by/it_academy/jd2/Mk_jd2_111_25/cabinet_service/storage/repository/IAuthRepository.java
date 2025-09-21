package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.repository;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IAuthRepository extends JpaRepository<RegistrationEntity, UUID> {
    Optional<RegistrationEntity> findByMail(String mail);
    Optional<RegistrationEntity> findByUuid(UUID uuid);
}
