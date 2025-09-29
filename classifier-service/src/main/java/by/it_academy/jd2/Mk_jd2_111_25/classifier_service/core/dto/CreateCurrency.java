package by.it_academy.jd2.Mk_jd2_111_25.classifier_service.core.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class CreateCurrency {
    private String title;
    private String description;

}
