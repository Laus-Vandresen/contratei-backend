package br.com.contratei.entity;

import br.com.contratei.dto.ProviderUserDto;
import br.com.contratei.enuns.ServiceTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    public void updateScore(Double score) {
        this.score = score;
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
        this.kmWorkRange = usuario.getKmWorkRange();
        this.hourValue = usuario.getHourValue();
        this.profilePicture = usuario.getProfilePicture();
        this.backgroundImage = usuario.getBackgroundImage();
        this.pictures = usuario.getPictures().stream().map(PhotoEntity::new).collect(Collectors.toList());
        this.comments = usuario.getComments().stream().map(CommentEntity::new).collect(Collectors.toList());
    }
}
