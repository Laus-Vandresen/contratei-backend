package br.com.contratei.dto;

import br.com.contratei.entity.ConsumerUserEntity;
import br.com.contratei.entity.ProviderUserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String cpf;
    private String contactNumber;
    private String description;
    private Double kmWorkRange;
    private BigDecimal hourValue;
    private Boolean isProvider;

    public UserDto(ConsumerUserEntity entity) {
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.cpf = entity.getCpf();
        this.contactNumber = entity.getContactNumber();
        this.isProvider = false;
    }

    public UserDto(ProviderUserEntity entity) {
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.cpf = entity.getCpf();
        this.contactNumber = entity.getContactNumber();
        this.description = entity.getDescription();
        this.kmWorkRange = entity.getKmWorkRange();
        this.hourValue = entity.getHourValue();
        this.isProvider = true;
    }
}
