package br.com.contratei.entity;

import br.com.contratei.dto.AddressDto;
import br.com.contratei.dto.CoreConsumerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String state;
    private String city;
    private String district;
    private String street;
    private String numberStreet;
    private String postCode;
    private String complement;
    private Boolean main;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private ConsumerUserEntity consumer;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private ProviderUserEntity provider;

    public void changeCoreData(AddressDto dto) {
        this.state = dto.getState();
        this.city = dto.getCity();
        this.district = dto.getDistrict();
        this.street = dto.getStreet();
        this.numberStreet = dto.getNumberStreet();
        this.postCode = dto.getPostCode();
        this.complement = dto.getComplement();
        this.main = dto.getMain();
    }

    public AddressEntity(AddressDto dto) {
        this.id = dto.getId();
        this.state = dto.getState();
        this.city = dto.getCity();
        this.district = dto.getDistrict();
        this.street = dto.getStreet();
        this.numberStreet = dto.getNumberStreet();
        this.postCode = dto.getPostCode();
        this.complement = dto.getComplement();
        this.main = dto.getMain();
        this.consumer = Objects.nonNull(dto.getConsumer()) ? new ConsumerUserEntity(dto.getConsumer()) : null;
        this.provider = Objects.nonNull(dto.getProvider()) ? new ProviderUserEntity(dto.getProvider()) : null;
    }
}
