package by.it_academy.jd2.Mk_jd2_111_25.account_service.core.utility;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.AccountOperation;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.CreateAccountOperation;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.storage.entity.AccountOperationEntity;

import java.time.Instant;

public class AccountOperationMapper {

    public static AccountOperationEntity toEntity(CreateAccountOperation dto){
        Instant creationMoment = Instant.now();
        return AccountOperationEntity.builder()
                .dtCreate(creationMoment)
                .dtUpdate(creationMoment)
                .date(dto.getDate())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .value(dto.getValue())
                .currency(dto.getCurrency())
                .build();
    };

    public static AccountOperation toDto(AccountOperationEntity entity){
        return AccountOperation.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .date(entity.getDate())
                .description(entity.getDescription())
                .category(entity.getCategory())
                .value(entity.getValue())
                .currency(entity.getCurrency())
                .build();
    }
}
