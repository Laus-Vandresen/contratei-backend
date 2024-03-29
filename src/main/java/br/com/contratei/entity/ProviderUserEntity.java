package br.com.contratei.entity;

import br.com.contratei.dto.CoreProviderDto;
import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ActingRegionEnum;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
    @Enumerated(EnumType.ORDINAL)
    private ActingRegionEnum actingRegion;
    private BigDecimal hourValue;
    @Enumerated(EnumType.ORDINAL)
    private ServiceTypeEnum serviceType;
    private byte[] profilePicture;
    private byte[] backgroundImage;
    private Double score;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name="provider_id")
    private List<BudgetEntity> budgets;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name="provider_id")
    private List<PhotoEntity> pictures;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name="provider_id")
    private List<CommentEntity> comments;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name="provider_id")
    private List<AddressEntity> address;

    public void updateScore(Double score) {
        this.score = score;
    }

    public void changeCoreData(CoreProviderDto dto) {
        this.email = dto.getEmail();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.cpf = dto.getCpf();
        this.contactNumber = dto.getContactNumber();
        this.description = dto.getDescription();
        this.hourValue = dto.getHourValue();
        this.actingRegion = dto.getActingRegion();
        this.profilePicture = dto.getProfilePicture();
        this.backgroundImage = dto.getBackgroundImage();
    }

    public ProviderUserEntity(ProviderUserDto usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.firstName = usuario.getFirstName();
        this.lastName = usuario.getLastName();
        this.cpf = usuario.getCpf();
        this.contactNumber = usuario.getContactNumber();
        this.description = usuario.getDescription();
        this.actingRegion = usuario.getActingRegion();
        this.hourValue = usuario.getHourValue();
        this.serviceType = usuario.getServiceType();
        this.profilePicture = usuario.getProfilePicture();
        this.backgroundImage = usuario.getBackgroundImage();
    }
}
