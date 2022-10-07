package br.com.contratei.dto;

import br.com.contratei.entity.ConsumerUserEntity;
import br.com.contratei.entity.ProviderUserEntity;
import br.com.contratei.enuns.ActingRegionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {

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
    private Boolean isProvider;
    private byte[] profilePicture;
    private byte[] backgroundImage;

    public UserDto(ConsumerUserEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.cpf = entity.getCpf();
        this.contactNumber = entity.getContactNumber();
        this.isProvider = false;
        this.profilePicture = entity.getProfilePicture();
    }

    public UserDto(ProviderUserEntity entity) {
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
        this.isProvider = true;
        this.profilePicture = entity.getProfilePicture();
        this.backgroundImage = entity.getBackgroundImage();
    }
}
