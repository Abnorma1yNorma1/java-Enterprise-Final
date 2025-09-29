package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.service;

import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.CreateCurrency;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.CreateOperationCategory;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.Currency;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.OperationCategory;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.utility.CurrencyMapper;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.utility.OperationCategoryMapper;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.service.api.ICurrencyService;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.storage.entity.CurrencyEntity;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.storage.entity.OperationCategoryEntity;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.storage.repository.ICurrencyRepository;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.storage.repository.IOperationCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyRepository currencyRepository;
    private final IOperationCategoryRepository operationCategoryRepository;

    @Override
    public void save(CreateCurrency createCurrency) {
        CurrencyEntity currencyEntity = CurrencyMapper.toEntity(createCurrency);
        currencyRepository.save(currencyEntity);
    }

    @Override
    public void save(CreateOperationCategory createOperationCategory) {
        OperationCategoryEntity operationCategoryEntity = OperationCategoryMapper.toEntity(createOperationCategory);
        operationCategoryRepository.save(operationCategoryEntity);
    }

    @Override
    public ResponsePage<Currency> getCurrency(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Currency> springPage = currencyRepository.findAll(pageable).map(CurrencyMapper::toDto);
        return ResponsePage.fromSpringPage(springPage);
    }

    @Override
    public ResponsePage<OperationCategory> getOperationCategory(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<OperationCategory> springPage = operationCategoryRepository.findAll(pageable).map(OperationCategoryMapper::toDto);
        return ResponsePage.fromSpringPage(springPage);
    }
}