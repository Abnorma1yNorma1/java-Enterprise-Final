package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "authentication", schema = "cabinet_service")
public class RegistrationEntity {

    @Id
    @Column(name = "uuid")
    private UUID userUuid;
    @Column(name = "code", nullable = false)
    private String codeHash;
}
