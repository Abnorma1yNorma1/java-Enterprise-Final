package by.it_academy.jd2.Mk_jd2_111_25.account_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.Account;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.CreateAccount;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.utility.AccountMapper;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.service.api.IAccountService;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.storage.entity.AccountEntity;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.storage.repository.IAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;

    @Override
    @Transactional
    public void saveAccount(CreateAccount createAccount) {
        AccountEntity accountEntity = AccountMapper.toEntity(createAccount);
        accountRepository.save(accountEntity);
    }

    @Override
    public ResponsePage<Account> getAccounts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Account> springPage = accountRepository.findAll(pageable).map(AccountMapper::toDto);
        return ResponsePage.fromSpringPage(springPage);
    }

    @Override
    public Account getAccountByUuid(UUID uuid) {
        AccountEntity account = accountRepository.getReferenceById(uuid);
        return AccountMapper.toDto(account);
    }

    @Override
    @Transactional
    public void updateAccount(UUID uuid, Instant dtUpdate, CreateAccount createAccount) {
        AccountEntity entity = accountRepository.findByUuid(uuid).orElseThrow(() -> new DataRetrievalFailureException("Account could not be found in account database."));
        if (!entity.getDtUpdate().equals(dtUpdate)){
            throw new IllegalStateException("Your account record is outdated.");
        }
        entity.setTitle(createAccount.getTitle());
        entity.setDescription(createAccount.getDescription());
        entity.setBalance(createAccount.getBalance());
        entity.setType(createAccount.getType());
        entity.setCurrency(createAccount.getCurrency());

        accountRepository.save(entity);
    }
}
