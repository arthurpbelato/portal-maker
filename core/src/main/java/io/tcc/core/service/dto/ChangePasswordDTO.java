package io.tcc.core.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDTO {

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;

}
