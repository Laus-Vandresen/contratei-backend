package br.com.contratei.dto;

import br.com.contratei.entity.ProviderUserEntity;
import br.com.contratei.enuns.ActingRegionEnum;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProviderUserDto {

    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String cpf;
    private String contactNumber;
    private String description;
    private ActingRegionEnum actingRegion;
    private BigDecimal hourValue;
    private ServiceTypeEnum serviceType;
    private byte[] profilePicture;
    private byte[] backgroundImage;
    private Double score;

    public ProviderUserDto(ProviderUserEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.cpf = entity.getCpf();
        this.contactNumber = entity.getContactNumber();
        this.description = entity.getDescription();
        this.actingRegion = entity.getActingRegion();
        this.hourValue = entity.getHourValue();
        this.serviceType = entity.getServiceType();
        this.profilePicture = entity.getProfilePicture();
        this.backgroundImage = entity.getBackgroundImage();
        this.score = entity.getScore();
    }
}
