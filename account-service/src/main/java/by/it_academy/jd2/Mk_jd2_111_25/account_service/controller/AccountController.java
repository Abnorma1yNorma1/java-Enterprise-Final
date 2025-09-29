package by.it_academy.jd2.Mk_jd2_111_25.account_service.controller;


import by.it_academy.jd2.Mk_jd2_111_25.account_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.Account;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.AccountOperation;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.CreateAccount;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.CreateAccountOperation;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.service.api.IAccountService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final IAccountService accountService;

    @PostMapping
    public ResponseEntity<?> createAccount(CreateAccount createAccount){
        accountService.saveAccount(createAccount);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ResponsePage<Account>> getAccounts(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                             @RequestParam(defaultValue = "20") @Min(1) int size){
        ResponsePage<Account> responsePageResponse = accountService.getAccounts(page, size);
        return new ResponseEntity<>(responsePageResponse, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Account> getAccountByUuid(@PathVariable UUID uuid) {
        Account account = accountService.getAccountByUuid(uuid);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PutMapping("{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> updateAccount(@PathVariable("uuid") UUID uuid,
                                    @PathVariable("dt_update") Instant dtUpdate,
                                    @RequestBody CreateAccount createAccount){
        accountService.updateAccount(uuid, dtUpdate, createAccount);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
