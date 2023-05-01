package io.tcc.core.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class UserRegisterDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email;

    @CPF
    @NotBlank
    private String cpf;

    @NotBlank
    private String password;
}
