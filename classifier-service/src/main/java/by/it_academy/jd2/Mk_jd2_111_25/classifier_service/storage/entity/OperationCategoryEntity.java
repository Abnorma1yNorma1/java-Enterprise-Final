package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.storage.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "operation_categories", schema = "classifier_service")
public class OperationCategoryEntity {

    @Id
    @Column(name = "uuid")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "dt_create", nullable = false, updatable = false)
    private Instant dtCreate;
    @Column(name = "dt_update")
    private  Instant dtUpdate;
    @Column(name = "title")
    private String title;
}
