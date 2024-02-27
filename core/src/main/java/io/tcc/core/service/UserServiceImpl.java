package io.tcc.core.service;

import io.tcc.core.config.security.TokenService;
import io.tcc.core.model.SecurityUser;
import io.tcc.core.model.User;
import io.tcc.core.repository.RoleRepository;
import io.tcc.core.repository.UserRepository;
import io.tcc.core.service.dto.BasicUserDTO;
import io.tcc.core.service.dto.EnumDTO;
import io.tcc.core.service.dto.LoggedUserDTO;
import io.tcc.core.service.dto.UserProfileDTO;
import io.tcc.core.service.dto.UserRegisterDTO;
import io.tcc.core.service.interfaces.EmailService;
import io.tcc.core.service.interfaces.PasswordService;
import io.tcc.core.service.interfaces.UserService;
import io.tcc.core.service.mapper.RoleMapper;
import io.tcc.core.service.mapper.UserProfileMapper;
import io.tcc.core.service.mapper.UserRegisterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
//TODO: LOGS
public class UserServiceImpl implements UserService {

    private final RoleMapper roleMapper;
    private final TokenService tokenService;
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final UserProfileMapper userProfileMapper;
    private final UserRegisterMapper userRegisterMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordService passwordService;
    private final EmailService emailService;

    @Override
    public LoggedUserDTO login(BasicUserDTO basicUserDTO) {
        var user = new UsernamePasswordAuthenticationToken(basicUserDTO.getName(), basicUserDTO.getPassword());
        var auth = authenticationManager.authenticate(user);
        var response = new LoggedUserDTO();

        response.setName(((SecurityUser) auth.getPrincipal()).getUser().getName());
        response.setToken(tokenService.generateToken((SecurityUser) auth.getPrincipal()));
        return response;
    }

    @Override
    public UserProfileDTO register(UserRegisterDTO userRegisterDTO) {
        User user = userRegisterMapper.toEntity(userRegisterDTO);
        final var password = passwordService.getPassword();
        final var emailBody = """
                Seu email foi registrado no Portal Maker do IFES Campus Colatina! Você pode acessar nosso portal com
                 seu email e sua nova senha: ?
                """;
        emailService.send(userRegisterDTO.getEmail(), emailBody.formatted(password), "Portal Maker - Seu email foi registrado!");
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return userProfileMapper.toDto(repository.save(user));
    }

    @Override
    public UserProfileDTO getProfile(String id) throws Exception{
        return repository.findById(UUID.fromString(id)).map(userProfileMapper::toDto).orElseThrow(Exception::new);
    }

    @Override
    public List<UserProfileDTO> getProfiles() {
        return repository.findAll().stream().map(userProfileMapper::toDto).toList();
    }

    @Override
    public List<String> getLoggedUserRoles() {
        return SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    }

    @Override
    public List<EnumDTO> listRoles() {
        return roleMapper.toDto(roleRepository.findAll());
    }
}
