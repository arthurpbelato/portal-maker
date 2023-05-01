package io.tcc.core.util;

import io.tcc.core.model.SecurityUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationUtil {

    public static SecurityUser getLoggedUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static String getUsername() {
        return getLoggedUser().getUsername();
    }

    public static String getId() {
        return getLoggedUser().getUserId();
    }

}
