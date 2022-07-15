package br.com.contratei.entity;

import br.com.contratei.dto.ConsumerUserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public ConsumerUserEntity(ConsumerUserDto usuario) {
        this.email = usuario.getEmail();
        this.password = usuario.getPassword();
        this.firstName = usuario.getFirstName();
        this.lastName = usuario.getLastName();
        this.cpf = usuario.getCpf();
        this.contactNumber = usuario.getContactNumber();
    }
}
