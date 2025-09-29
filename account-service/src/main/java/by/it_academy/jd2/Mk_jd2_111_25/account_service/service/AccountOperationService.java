package by.it_academy.jd2.Mk_jd2_111_25.account_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.AccountOperation;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.CreateAccountOperation;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.service.api.IAccountOperationService;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.storage.repository.IAccountOperationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountOperationService implements IAccountOperationService {

    private final IAccountOperationRepository accountOperationRepository;

    @Override
    @Transactional
    public void addAccountOperation(UUID uuid, CreateAccountOperation createAccountOperation) {

    }

    @Override
    public ResponsePage<AccountOperation> getAccountOperations(UUID uuid, int page, int size) {
        return null;
    }

    @Override
    public void updateAccountOperation(UUID uuidAccount, UUID uuidAccountOperation, Instant dtUpdate, CreateAccountOperation createAccountOperation) {

    }

    @Override
    public void deleteAccountOperation(UUID uuidAccount, UUID uuidAccountOperation, Instant dtUpdate, CreateAccountOperation createAccountOperation) {

    }
}
