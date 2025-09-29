package by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto;

import by.it_academy.jd2.Mk_jd2_111_25.account_service.core.dto.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccount {

    private String title;
    private String description;
    private BigDecimal balance;
    private AccountType type;
    private String currency;
}
