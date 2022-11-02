package br.com.contratei.dto;

import br.com.contratei.entity.CreditCardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreditCardDto {

    private int id;
    private String holder;
    private String number;
    private LocalDate validity;
    private String cvv;
    private ConsumerUserDto consumer;

    public CreditCardDto(CreditCardEntity entity) {
        this.id = entity.getId();
        this.holder = entity.getHolder();
        this.number = entity.getNumber();
        this.validity = entity.getValidity();
        this.cvv = entity.getCvv();
        this.consumer = Objects.nonNull(entity.getConsumer()) ? new ConsumerUserDto(entity.getConsumer()) : null;
    }
}
