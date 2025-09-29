package by.it_academy.jd2.Mk_jd2_111_25.account_service.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.Account;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.CreateAccount;

import java.time.Instant;
import java.util.UUID;

public interface IAccountService {
    void saveAccount(CreateAccount createAccount);
    ResponsePage<Account> getAccounts(int page,int size);
    Account getAccountByUuid(UUID uuid);
    void updateAccount(UUID uuid, Instant dtUpdate, CreateAccount createAccount);

}
