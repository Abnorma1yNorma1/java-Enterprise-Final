package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.entity;

import by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.core.dto.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "authentication", schema = "cabinet_service")
public class RegistrationEntity {

    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "mail", unique = true)
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Column(name = "password")
    private String passwordHash;
    @Column(name = "code", nullable = false)
    private String code;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
    @Column(name = "dt_create")
    private Instant InstantOfRegistration;
}
