package br.com.contratei.entity;

import br.com.contratei.dto.BudgetDto;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.enuns.PriorityLevelEnum;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "budget")
public class BudgetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Enumerated(EnumType.ORDINAL)
    private BudgetStatusEnum status;
    private BigDecimal value;

    @Enumerated(EnumType.ORDINAL)
    private ServiceTypeEnum serviceType;

    @Enumerated(EnumType.ORDINAL)
    private PriorityLevelEnum priority;

    private String description;
    private LocalDate openingDate;
    private LocalDate completionDate;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private ConsumerUserEntity consumer;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private ProviderUserEntity provider;

    public BudgetEntity(BudgetDto dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.status = dto.getStatus();
        this.value = dto.getValue();
        this.serviceType = dto.getServiceType();
        this.description = dto.getDescription();
        this.priority = dto.getPriority();
        this.openingDate = dto.getOpeningDate();
        this.completionDate = dto.getCompletionDate();
        this.consumer = Objects.nonNull(dto.getConsumer()) ? new ConsumerUserEntity(dto.getConsumer()) : null;
        this.provider = Objects.nonNull(dto.getProvider()) ?  new ProviderUserEntity(dto.getProvider()) : null;
    }
}
