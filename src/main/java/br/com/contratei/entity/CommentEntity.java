package br.com.contratei.entity;

import br.com.contratei.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String comment;

    private Double score;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private ProviderUserEntity provider;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "consumer_id")
    private ConsumerUserEntity consumer;

    public CommentEntity(CommentDto dto) {
        this.id = dto.getId();
        this.comment = dto.getComment();
        this.score = dto.getScore();
        this.provider =  Objects.nonNull(dto.getProvider()) ? new ProviderUserEntity(dto.getProvider()) : null;
        this.consumer = Objects.nonNull(dto.getConsumer()) ? new ConsumerUserEntity(dto.getConsumer()) : null;
    }

}
