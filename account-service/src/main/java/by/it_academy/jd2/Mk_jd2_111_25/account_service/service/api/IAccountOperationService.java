package by.it_academy.jd2.Mk_jd2_111_25.account_service.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.AccountOperation;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.CreateAccountOperation;

import java.time.Instant;
import java.util.UUID;

public interface IAccountOperationService {

    void addAccountOperation(UUID uuid, CreateAccountOperation createAccountOperation);
    ResponsePage<AccountOperation> getAccountOperations(UUID uuid, int page, int size);
    void updateAccountOperation(UUID uuidAccount, UUID uuidAccountOperation, Instant dtUpdate, CreateAccountOperation createAccountOperation);
    void deleteAccountOperation(UUID uuidAccount, UUID uuidAccountOperation, Instant dtUpdate, CreateAccountOperation createAccountOperation);
}
