package br.com.contratei.entity;

import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "provider")
public class ProviderUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String cpf;
    private String contactNumber;
    private String description;
    private Double kmWorkRange;
    private BigDecimal hourValue;
    @Enumerated(EnumType.ORDINAL)
    private ServiceTypeEnum serviceType;

    public ProviderUserEntity(ProviderUserDto usuario) {
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.firstName = usuario.getFirstName();
        this.lastName = usuario.getLastName();
        this.cpf = usuario.getCpf();
        this.contactNumber = usuario.getContactNumber();
        this.description = usuario.getDescription();
        this.kmWorkRange = usuario.getKmWorkRange();
        this.hourValue = usuario.getHourValue();
    }
}
