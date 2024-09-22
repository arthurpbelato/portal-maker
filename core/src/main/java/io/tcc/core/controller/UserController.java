package io.tcc.core.controller;

import io.tcc.core.model.Mensagem;
import io.tcc.core.service.dto.BasicUserDTO;
import io.tcc.core.service.dto.ChangePasswordDTO;
import io.tcc.core.service.dto.EnumDTO;
import io.tcc.core.service.dto.LoggedUserDTO;
import io.tcc.core.service.dto.UserProfileDTO;
import io.tcc.core.service.dto.UserProfileDetailsDTO;
import io.tcc.core.service.interfaces.UserService;
import io.tcc.core.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/internal/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserProfileDTO> save(@RequestBody UserProfileDTO userRegisterDTO) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userRegisterDTO));
    }

    @GetMapping("/internal/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserProfileDTO> get(@PathVariable("id") String id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(service.getProfile(id));
    }

    @GetMapping("/internal/profile")
    @PreAuthorize("hasAnyRole('ROLE_REVIEWER', 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserProfileDTO> getProfileData() throws Exception {
        UserProfileDTO userProfileDTO = service.getProfile(AuthenticationUtil.getId());
        return ResponseEntity.ok(userProfileDTO);
    }

    @GetMapping("/internal/profile/details")
    @PreAuthorize("hasAnyRole('ROLE_REVIEWER', 'ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<UserProfileDetailsDTO> getProfileDetails() throws Exception {
        return ResponseEntity.ok(service.getProfileDetails());
    }

    @GetMapping("/internal/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<UserProfileDTO>> getProfiles() {
        List<UserProfileDTO> userProfileDTO = service.getProfiles();
        return ResponseEntity.ok(userProfileDTO);
    }

    @GetMapping("/internal/validate/token")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER', 'ROLE_USER')")
    public ResponseEntity<String> validateToken() {
        return ResponseEntity.ok("Valid");
    }

    @GetMapping("/internal/logged/role")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<String>> getLoggedUserRoles() {
        return ResponseEntity.ok(service.getLoggedUserRoles());
    }

    @GetMapping("/internal/role")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<EnumDTO>> listRoles() {
        return ResponseEntity.ok(service.listRoles());
    }

    @PostMapping("internal/password/update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER', 'ROLE_USER')")
    public ResponseEntity<Mensagem> update(@RequestBody ChangePasswordDTO changePasswordDTO) {
        var message = new Mensagem();
        try {
            message.setMessage(service.updatePassword(changePasswordDTO));
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            message.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(message);
        }
    }

}
