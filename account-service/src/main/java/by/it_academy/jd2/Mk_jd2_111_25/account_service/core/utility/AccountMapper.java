package by.it_academy.jd2.Mk_jd2_111_25.account_service.core.utility;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.Account;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.CreateAccount;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.storage.entity.AccountEntity;

import java.time.Instant;

public class AccountMapper {


    public static AccountEntity toEntity(CreateAccount dto){
        Instant creationMoment = Instant.now();
        return AccountEntity.builder()
                .dtCreate(creationMoment)
                .dtUpdate(creationMoment)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .balance(dto.getBalance())
                .type(dto.getType())
                .currency(dto.getCurrency())
                .build();
    };

    public static Account toDto(AccountEntity entity){
        return Account.builder()
                .uuid(entity.getUuid())
                .dtCreate(entity.getDtCreate())
                .dtUpdate(entity.getDtUpdate())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .balance(entity.getBalance())
                .type(entity.getType())
                .currency(entity.getCurrency())
                .build();
    }


}
