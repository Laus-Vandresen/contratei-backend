package br.com.contratei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CoreConsumerDto {

    private String email;
    private String firstName;
    private String lastName;
    private String cpf;
    private String contactNumber;
    private byte[] profilePicture;

}
