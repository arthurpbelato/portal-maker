package io.tcc.core.config.security;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    @SneakyThrows
    public SecurityFilterChain configure(HttpSecurity httpSecurity) {
        return httpSecurity
                .httpBasic()
                .and()
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("api/**").permitAll()
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/internal/**").authenticated()
                )
                .httpBasic(Customizer.withDefaults()).csrf().disable()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
