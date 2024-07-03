package io.tcc.core.model.enums;

import io.tcc.core.model.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RoleEnum {

    ROLE_ADMIN(new Role(1L, "ROLE_ADMIN")),
    ROLE_USER(new Role(2L, "ROLE_USER")),
    ROLE_REVIEWER(new Role(3L, "ROLE_REVIEWER"));

    private final Role Role;

}
