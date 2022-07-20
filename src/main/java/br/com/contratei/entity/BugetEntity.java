package br.com.contratei.entity;

import br.com.contratei.dto.BugetDto;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "buget")
public class BugetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.ORDINAL)
    private ServiceTypeEnum serviceType;
    private String description;

    public BugetEntity(BugetDto dto) {
        this.id = dto.getId();
        this.serviceType = dto.getServiceType();
        this.description = dto.getDescription();
    }
}
