package br.com.contratei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProposalDto {

    private int id;
    private ProviderUserDto provider;
    private BudgetDto budget;
    private String description;
    private BigDecimal averageValue;
}
