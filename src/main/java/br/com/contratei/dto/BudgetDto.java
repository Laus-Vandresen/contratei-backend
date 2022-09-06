package br.com.contratei.dto;

import br.com.contratei.entity.BudgetEntity;
import br.com.contratei.enuns.BudgetStatusEnum;
import br.com.contratei.enuns.PriorityLevelEnum;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.security.Provider;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BudgetDto {

    private int id;
    private String title;
    private BudgetStatusEnum status;
    private BigDecimal value;
    private PriorityLevelEnum priority;
    private ServiceTypeEnum serviceType;
    private String description;
    private LocalDate openingDate;
    private LocalDate completionDate;
    private ConsumerUserDto consumer;
    private ProviderUserDto provider;

    public BudgetDto(BudgetEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.status = entity.getStatus();
        this.value = entity.getValue();
        this.serviceType = entity.getServiceType();
        this.priority = entity.getPriority();
        this.description = entity.getDescription();
        this.openingDate = entity.getOpeningDate();
        this.completionDate = entity.getCompletionDate();
        this.consumer = Objects.nonNull(entity.getConsumer()) ? new ConsumerUserDto(entity.getConsumer()) : null;
        this.provider = Objects.nonNull(entity.getProvider()) ? new ProviderUserDto(entity.getProvider()) : null;
    }
}
