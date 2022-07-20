package br.com.contratei.dto;

import br.com.contratei.entity.BugetEntity;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BugetDto {

    private int id;
    private ServiceTypeEnum serviceType;
    private String description;

    public BugetDto(BugetEntity entity) {
        this.id = entity.getId();
        this.serviceType = entity.getServiceType();
        this.description = entity.getDescription();
    }
}
