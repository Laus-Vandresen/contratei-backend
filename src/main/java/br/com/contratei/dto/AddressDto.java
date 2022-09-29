package br.com.contratei.dto;

import br.com.contratei.entity.AddressEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressDto {

    private int id;
    private String state;
    private String city;
    private String district;
    private String street;
    private String numberStreet;
    private String postCode;
    private String complement;
    private Boolean main;
    private ConsumerUserDto consumer;
    private ProviderUserDto provider;

    public AddressDto(AddressEntity entity) {
        this.id = entity.getId();
        this.state = entity.getState();
        this.city = entity.getCity();
        this.district = entity.getDistrict();
        this.street = entity.getStreet();
        this.numberStreet = entity.getNumberStreet();
        this.postCode = entity.getPostCode();
        this.complement = entity.getComplement();
        this.main = entity.getMain();
        this.consumer = Objects.nonNull(entity.getConsumer()) ? new ConsumerUserDto(entity.getConsumer()) : null;
        this.provider = Objects.nonNull(entity.getProvider()) ? new ProviderUserDto(entity.getProvider()) : null;
    }
}
