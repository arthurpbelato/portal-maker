package io.tcc.core.service.dto;

import io.tcc.core.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

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

    @NotEmpty
    private List<Role> roles;
}
