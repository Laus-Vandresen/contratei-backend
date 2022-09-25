package br.com.contratei.entity;

import br.com.contratei.dto.PhotoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "photo")
public class PhotoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private ProviderUserEntity provider;

    private byte[] picture;

    public PhotoEntity(PhotoDto photoDto) {
        this.id = photoDto.getId();
        this.provider = Objects.nonNull(photoDto.getProvider()) ?  new ProviderUserEntity(photoDto.getProvider()) : null;
        this.picture = photoDto.getPicture();
    }
}
