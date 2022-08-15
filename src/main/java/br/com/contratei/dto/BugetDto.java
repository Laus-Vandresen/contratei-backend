package br.com.contratei.dto;

import br.com.contratei.entity.BugetEntity;
import br.com.contratei.enuns.BugetStatusEnum;
import br.com.contratei.enuns.PriorityLevelEnum;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BugetDto {

    private int id;
    private String title;
    private BugetStatusEnum status;
    private BigDecimal value;
    private PriorityLevelEnum priority;
    private ServiceTypeEnum serviceType;
    private String description;
    private LocalDate openingDate;
    private LocalDate completionDate;

    public BugetDto(BugetEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.status = entity.getStatus();
        this.value = entity.getValue();
        this.serviceType = entity.getServiceType();
        this.priority = entity.getPriority();
        this.description = entity.getDescription();
        this.openingDate = entity.getOpeningDate();
        this.completionDate = entity.getCompletionDate();
    }
}
