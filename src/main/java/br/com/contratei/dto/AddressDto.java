package br.com.contratei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private ConsumerUserDto consumer;
}
