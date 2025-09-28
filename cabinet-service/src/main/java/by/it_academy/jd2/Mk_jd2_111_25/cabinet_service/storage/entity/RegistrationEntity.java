package by.it_academy.jd2.Mk_jd2_111_25.cabinet_service.storage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authentication", schema = "cabinet_service")
public class RegistrationEntity implements Persistable<UUID> {

    @Id
    @Column(name = "uuid")
    private UUID userUuid;
    @Column(name = "code", nullable = false)
    private String codeHash;
    @Transient
    private boolean isNew = true;

    @PrePersist
    @PostLoad
    void trackNotNew() {
        this.isNew = false;
    }

    @Override
    public UUID getId() {
        return this.userUuid;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }
}
