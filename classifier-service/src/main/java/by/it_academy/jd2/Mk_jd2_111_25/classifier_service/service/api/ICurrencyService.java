package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.service.api;

import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.common.dto.ResponsePage;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.CreateCurrency;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.CreateOperationCategory;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.Currency;
import by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto.OperationCategory;

public interface ICurrencyService {
    void save(CreateCurrency createCurrency);
    void save(CreateOperationCategory createOperationCategory);
    ResponsePage<Currency> getCurrency(int page, int size);
    ResponsePage<OperationCategory> getOperationCategory(int page, int size);
}
