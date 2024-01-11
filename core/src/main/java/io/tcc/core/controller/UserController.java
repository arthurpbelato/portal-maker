package io.tcc.core.controller;

import io.tcc.core.config.security.TokenService;
import io.tcc.core.service.dto.BasicUserDTO;
import io.tcc.core.service.dto.LoggedUserDTO;
import io.tcc.core.service.dto.UserProfileDTO;
import io.tcc.core.service.dto.UserRegisterDTO;
import io.tcc.core.service.interfaces.UserService;
import io.tcc.core.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/public/login")
    public ResponseEntity<LoggedUserDTO> login(@RequestBody BasicUserDTO basicUserDTO) {
        try {
            return new ResponseEntity<>(service.login(basicUserDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/public/register")
    public ResponseEntity<BasicUserDTO> register(@RequestBody UserRegisterDTO userProfileDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.register(userProfileDTO));
    }

    @GetMapping("/internal/profile")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<UserProfileDTO> getProfileData() throws Exception {
        UserProfileDTO userProfileDTO = service.getProfile(AuthenticationUtil.getId());
        return ResponseEntity.ok(userProfileDTO);
    }

    @GetMapping("/internal/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserProfileDTO>> getProfiles() {
        List<UserProfileDTO> userProfileDTO = service.getProfiles();
        return ResponseEntity.ok(userProfileDTO);
    }

    @GetMapping("/internal/validate/token")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> validateToken() {
        return ResponseEntity.ok("Valid");
    }

}
