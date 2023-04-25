package io.tcc.core.service.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicUserDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
