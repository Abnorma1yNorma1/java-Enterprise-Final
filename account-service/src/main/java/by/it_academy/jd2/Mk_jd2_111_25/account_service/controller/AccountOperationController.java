package by.it_academy.jd2.Mk_jd2_111_25.account_service.controller;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.AccountOperation;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.CreateAccountOperation;
import by.it_academy.jd2.Mk_jd2_111_25.account_service.service.api.IAccountOperationService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/account/{uuid}/operation")
@RequiredArgsConstructor
public class AccountOperationController {

    private final IAccountOperationService addAccountOperation;

    @PostMapping
    public ResponseEntity<?> addAccountOperation(@PathVariable("uuid") UUID uuid,
                                                 @RequestBody CreateAccountOperation createAccountOperation){
        addAccountOperation.addAccountOperation(uuid, createAccountOperation);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping
    public ResponseEntity<ResponsePage<AccountOperation>> getAccountOperations(@PathVariable("uuid") UUID uuid,
                                                                               @RequestParam(defaultValue = "0") @Min(0) int page,
                                                                               @RequestParam(defaultValue = "20") @Min(1) int size){
        ResponsePage<AccountOperation> responsePageResponse = addAccountOperation.getAccountOperations(uuid, page, size);
        return new ResponseEntity<>(responsePageResponse, HttpStatus.OK);
    }

    @PutMapping("/{uuid_operation}/dt_update/{dt_update}")
    public ResponseEntity<?> updateAccountOperation(@PathVariable("uuid") UUID uuidAccount,
                                                    @PathVariable("uuid_operation") UUID uuidAccountOperation,
                                                    @PathVariable("dt_update") Instant dtUpdate,
                                                    @RequestBody CreateAccountOperation createAccountOperation){
        addAccountOperation.updateAccountOperation(uuidAccount, uuidAccountOperation, dtUpdate, createAccountOperation);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{uuid_operation}/dt_update/{dt_update}")
    public ResponseEntity<?> deleteAccountOperation(@PathVariable("uuid") UUID uuidAccount,
                                                    @PathVariable("uuid_operation") UUID uuidAccountOperation,
                                                    @PathVariable("dt_update") Instant dtUpdate,
                                                    @RequestBody CreateAccountOperation createAccountOperation) {
        addAccountOperation.deleteAccountOperation(uuidAccount, uuidAccountOperation, dtUpdate, createAccountOperation);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
