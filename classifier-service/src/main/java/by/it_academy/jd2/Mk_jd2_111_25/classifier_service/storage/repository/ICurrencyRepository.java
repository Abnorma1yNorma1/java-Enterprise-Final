package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.storage.repository;

import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.storage.entity.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ICurrencyRepository extends JpaRepository<CurrencyEntity, UUID> {
}
