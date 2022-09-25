package br.com.contratei.dto;

import br.com.contratei.entity.CommentEntity;
import br.com.contratei.entity.ProviderUserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    private Double kmWorkRange;
    private BigDecimal hourValue;
    private byte[] profilePicture;
    private byte[] backgroundImage;
    List<PhotoDto> pictures;
    private List<CommentDto> comments;

    public ProviderUserDto(ProviderUserEntity entity) {
        this.id = entity.getId();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.cpf = entity.getCpf();
        this.contactNumber = entity.getContactNumber();
        this.description = entity.getDescription();
        this.kmWorkRange = entity.getKmWorkRange();
        this.hourValue = entity.getHourValue();
        this.profilePicture = entity.getProfilePicture();
        this.backgroundImage = entity.getBackgroundImage();
        this.pictures = entity.getPictures().stream().map(PhotoDto::new).collect(Collectors.toList());
        this.comments = entity.getComments().stream().map(CommentDto::new).collect(Collectors.toList());
    }
}
