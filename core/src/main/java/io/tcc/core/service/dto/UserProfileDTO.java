package io.tcc.core.service.dto;

import io.tcc.core.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserProfileDTO {

    private UUID id;

    private String name;

    private String email;

    private String cpf;

    private List<Role> roles;
}
