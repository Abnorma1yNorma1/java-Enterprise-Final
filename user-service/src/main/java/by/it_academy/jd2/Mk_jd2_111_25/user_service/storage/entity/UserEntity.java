package by.it_academy.jd2.Mk_jd2_111_25.user_service.storage.entity;

import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.enums.UserRole;
import by.it_academy.jd2.Mk_jd2_111_25.user_service.core.dto.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Builder
@Table(name = "users", schema = "user_service")
public class UserEntity {

    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private  UUID uuid;
    @Column(name = "dt_create", nullable = false, updatable = false)
    private  Instant dtCreate;
    @Column(name = "dt_update")
    private  Instant dtUpdate;
    @Column(name = "mail", unique = true)
    private String mail;
    @Column(name = "fio")
    private String fio;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status;
    @Column(name = "password")
    private String password;
}
