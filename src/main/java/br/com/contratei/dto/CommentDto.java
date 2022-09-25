package br.com.contratei.dto;

import br.com.contratei.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CommentDto {

    private int id;
    private String comment;
    private Double score;
    private ProviderUserDto provider;
    private ConsumerUserDto consumer;

    public CommentDto(CommentEntity entity) {
        this.id = entity.getId();
        this.comment = entity.getComment();
        this.score = entity.getScore();
        this.provider = Objects.nonNull(entity.getProvider()) ? new ProviderUserDto(entity.getProvider()) : null;
        this.consumer = Objects.nonNull(entity.getConsumer()) ? new ConsumerUserDto(entity.getConsumer()) : null;
    }
}
