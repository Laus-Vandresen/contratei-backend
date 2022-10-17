package br.com.contratei.dto;

import br.com.contratei.entity.ProposalEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProposalDto {

    private int id;
    private ProviderUserDto provider;
    private BudgetDto budget;
    private String description;
    private BigDecimal averageValue;
    private Boolean accepted;

    public ProposalDto(ProposalEntity entity) {
        this.id = entity.getId();
        this.provider = Objects.nonNull(entity.getProvider()) ? new ProviderUserDto(entity.getProvider()) : null;
        this.budget = Objects.nonNull(entity.getBudget()) ? new BudgetDto(entity.getBudget()): null;
        this.description = entity.getDescription();
        this.averageValue = entity.getAverageValue();
        this.accepted = entity.getAccepted();
    }
}
