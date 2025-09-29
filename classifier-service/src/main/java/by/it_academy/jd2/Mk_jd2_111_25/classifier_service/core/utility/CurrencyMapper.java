package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.utility;

import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.CreateCurrency;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.Currency;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.storage.entity.CurrencyEntity;

import java.time.Instant;

public class CurrencyMapper {

    public static CurrencyEntity toEntity(CreateCurrency dto){
        Instant creationMoment = Instant.now();
        return CurrencyEntity.builder()
                .dtCreate(creationMoment)
                .dtUpdate(creationMoment)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .build();
    }

    public static Currency toDto(CurrencyEntity entity){
        return Currency.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .build();
    }
}
