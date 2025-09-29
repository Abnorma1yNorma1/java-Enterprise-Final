package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.controller;

import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.CreateCurrency;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.CreateOperationCategory;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.Currency;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.OperationCategory;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.service.api.ICurrencyService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classifier")
@RequiredArgsConstructor
public class ClassifierController {

    private final ICurrencyService currencyService;

    @PostMapping("/currency")
    public ResponseEntity<?> createCurrency(@RequestBody CreateCurrency createCurrency){
        currencyService.save(createCurrency);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<ResponsePage<Currency>> getCurrency(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                      @RequestParam(defaultValue = "20") @Min(1) int size){
        ResponsePage<Currency> responsePageResponse = currencyService.getCurrency(page, size);
        return new ResponseEntity<>(responsePageResponse, HttpStatus.OK);
    }

    @PostMapping("/operation/category")
    public ResponseEntity<?> createOperationCategory(@RequestBody CreateOperationCategory createOperationCategory){
        currencyService.save(createOperationCategory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/operation/category")
    public ResponseEntity<ResponsePage<OperationCategory>> getOperationCategory(@RequestParam(defaultValue = "0") @Min(0) int page,
                                                               @RequestParam(defaultValue = "20") @Min(1) int size){
        ResponsePage<OperationCategory> responsePageResponse = currencyService.getOperationCategory(page, size);
        return new ResponseEntity<>(responsePageResponse, HttpStatus.OK);
    }
}
