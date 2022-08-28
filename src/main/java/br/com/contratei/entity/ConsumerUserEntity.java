package br.com.contratei.entity;

import br.com.contratei.dto.ConsumerUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "consumer")
public class ConsumerUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String cpf;
    private String contactNumber;

    @OneToMany(cascade = CascadeType.DETACH)
    @JoinColumn(name="consumer_id")
    private List<BugetEntity> bugets;

    public ConsumerUserEntity(ConsumerUserDto dto) {
        this.id = dto.getId();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.cpf = dto.getCpf();
        this.contactNumber = dto.getContactNumber();
    }
}
