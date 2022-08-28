package br.com.contratei.entity;

import br.com.contratei.dto.BugetDto;
import br.com.contratei.enuns.BugetStatusEnum;
import br.com.contratei.enuns.PriorityLevelEnum;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "buget")
public class BugetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Enumerated(EnumType.ORDINAL)
    private BugetStatusEnum status;
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

    public BugetEntity(BugetDto dto) {
        this.id = dto.getId();
        this.title = dto.getTitle();
        this.status = dto.getStatus();
        this.value = dto.getValue();
        this.serviceType = dto.getServiceType();
        this.description = dto.getDescription();
        this.priority = dto.getPriority();
        this.openingDate = dto.getOpeningDate();
        this.completionDate = dto.getCompletionDate();
        this.consumer = new ConsumerUserEntity(dto.getConsumer());
    }
}
