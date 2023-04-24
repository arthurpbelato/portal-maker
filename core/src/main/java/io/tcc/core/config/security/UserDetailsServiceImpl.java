package io.tcc.core.config.security;

import io.tcc.core.models.SecurityUser;
import io.tcc.core.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByName(username).map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Usuário %s não encontrado", username)));
    }

}
