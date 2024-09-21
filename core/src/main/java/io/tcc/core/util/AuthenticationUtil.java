package io.tcc.core.util;

import io.tcc.core.model.SecurityUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

import static io.tcc.core.model.enums.RoleEnum.ROLE_ADMIN;
import static io.tcc.core.model.enums.RoleEnum.ROLE_REVIEWER;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationUtil {

    private static final List<String> REVIEWER_ROLES = List.of(ROLE_ADMIN.getRole().getName(), ROLE_REVIEWER.getRole().getName());

    public static SecurityUser getLoggedUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getUsername() {
        return getLoggedUser().getUsername();
    }

    public static String getId() {
        return getLoggedUser().getUserId();
    }

    public static UUID getUuid() {
        return UUID.fromString(getLoggedUser().getUserId());
    }

    public static Boolean isReviewer() {
        return AuthenticationUtil.getLoggedUser()
                .getAuthorities()
                .stream()
                .anyMatch(role -> REVIEWER_ROLES.contains(role.toString()));
    }

}
