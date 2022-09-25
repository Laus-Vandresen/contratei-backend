package br.com.contratei.dto;

import br.com.contratei.entity.PhotoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PhotoDto {

    private int id;
    private ProviderUserDto provider;
    private byte[] picture;

    public PhotoDto(PhotoEntity entity) {
       this.id = entity.getId();
       this.provider = Objects.nonNull(entity.getProvider()) ? new ProviderUserDto(entity.getProvider()) : null;
       this.picture = entity.getPicture();
    }
}
