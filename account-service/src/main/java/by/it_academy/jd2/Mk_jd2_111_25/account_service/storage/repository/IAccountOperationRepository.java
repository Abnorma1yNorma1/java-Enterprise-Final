package by.it_academy.jd2.Mk_jd2_111_25.account_service.storage.repository;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.storage.entity.AccountOperationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAccountOperationRepository extends JpaRepository<AccountOperationEntity, UUID> {
}
