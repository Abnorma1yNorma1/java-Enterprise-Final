package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.utility;

import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.CreateOperationCategory;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.OperationCategory;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.storage.entity.OperationCategoryEntity;

import java.time.Instant;

public class OperationCategoryMapper {

    public static OperationCategoryEntity toEntity(CreateOperationCategory dto){
        Instant creationMoment = Instant.now();
        return OperationCategoryEntity.builder()
                .dtCreate(creationMoment)
                .dtUpdate(creationMoment)
                .title(dto.getTitle())
                .build();
    }

    public static OperationCategory toDto(OperationCategoryEntity entity){
        return OperationCategory.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .build();
    }
}
