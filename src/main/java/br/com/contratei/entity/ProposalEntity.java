package br.com.contratei.entity;

import br.com.contratei.dto.ProposalDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "proposal")
public class ProposalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private ProviderUserEntity provider;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id")
    private BudgetEntity budget;

    private String description;
    private BigDecimal averageValue;

    public ProposalEntity(ProposalDto dto) {
        this.id = dto.getId();
        this.provider = Objects.nonNull(dto.getProvider()) ? new ProviderUserEntity(dto.getProvider()) : null;
        this.budget = Objects.nonNull(dto.getBudget()) ? new BudgetEntity(dto.getBudget()) : null;
        this.description = dto.getDescription();
        this.averageValue = dto.getAverageValue();
    }
}