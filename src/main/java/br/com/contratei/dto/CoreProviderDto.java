package br.com.contratei.dto;

import br.com.contratei.enuns.ActingRegionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

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
    private ActingRegionEnum actingRegion;
    private byte[] profilePicture;
    private byte[] backgroundImage;
}
