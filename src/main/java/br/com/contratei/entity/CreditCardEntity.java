package br.com.contratei.entity;

import br.com.contratei.dto.ConsumerUserDto;
import br.com.contratei.dto.CreditCardDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "credit_card")
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String holder;
    private String number;
    private LocalDate validity;
    private String cvv;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private ConsumerUserEntity consumer;

    public CreditCardEntity(CreditCardDto dto) {
        this.id = dto.getId();
        this.holder = dto.getHolder();
        this.number = dto.getNumber();
        this.validity = dto.getValidity();
        this.cvv = dto.getCvv();
        this.consumer = Objects.nonNull(dto.getConsumer()) ? new ConsumerUserEntity(dto.getConsumer()) : null;
    }
}
