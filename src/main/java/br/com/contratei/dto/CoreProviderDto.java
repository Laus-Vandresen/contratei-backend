package br.com.contratei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CoreProviderDto {

    private String email;
    private String firstName;
    private String lastName;
    private String cpf;
    private String contactNumber;
    private String description;
    private BigDecimal hourValue;
    private byte[] profilePicture;
    private byte[] backgroundImage;
}
