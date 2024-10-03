package io.tcc.core.service;

import io.tcc.core.config.security.TokenService;
import io.tcc.core.model.SecurityUser;
import io.tcc.core.model.User;
import io.tcc.core.model.enums.PostStatusEnum;
import io.tcc.core.repository.RoleRepository;
import io.tcc.core.repository.UserRepository;
import io.tcc.core.service.dto.BasicUserDTO;
import io.tcc.core.service.dto.ChangePasswordDTO;
import io.tcc.core.service.dto.EnumDTO;
import io.tcc.core.service.dto.LoggedUserDTO;
import io.tcc.core.service.dto.UserProfileDTO;
import io.tcc.core.service.dto.UserProfileDetailsDTO;
import io.tcc.core.service.interfaces.EmailService;
import io.tcc.core.service.interfaces.PasswordService;
import io.tcc.core.service.interfaces.PostService;
import io.tcc.core.service.interfaces.UserService;
import io.tcc.core.service.mapper.RoleMapper;
import io.tcc.core.service.mapper.UserProfileDetailsMapper;
import io.tcc.core.service.mapper.UserProfileMapper;
import io.tcc.core.util.AuthenticationUtil;
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
import java.util.Objects;
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
    private final UserProfileDetailsMapper userProfileDetailsMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordService passwordService;
    private final EmailService emailService;
    private final PostService postService;

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
    public UserProfileDTO save(UserProfileDTO userProfileDTO) throws Exception {
        User user = userProfileMapper.toEntity(userProfileDTO);

        if (user.getId() == null) { //FIXME ??? devemos enviar uma nova senha caso o admin altere o email??
            user.setPassword(generatePasswordAndSendByEmail(user));
        } else {
            var userFromDb = repository.findById(userProfileDTO.getId()).orElseThrow(Exception::new);
            user.setPassword(userFromDb.getPassword());
        }

        return userProfileMapper.toDto(repository.save(user));
    }

    @Override
    public UserProfileDTO getProfile(String id) throws Exception {
        return repository.findById(UUID.fromString(id)).map(userProfileMapper::toDto).orElseThrow(Exception::new);
    }

    @Override //FIXME rever a forma de preencher os counts
    public UserProfileDetailsDTO getProfileDetails() throws Exception {
        var userDetails = repository.findById(AuthenticationUtil.getUuid()).map(userProfileDetailsMapper::toDto).orElseThrow(Exception::new);

        var posts = postService.getByUserId();

        userDetails.setPostApprovedCount(posts.stream().filter(post -> Objects.equals(post.getStatus(), PostStatusEnum.APPROVED.getId())).toList().size());
        userDetails.setPostDeniedCount(posts.stream().filter(post -> Objects.equals(post.getStatus(), PostStatusEnum.DENIED.getId())).toList().size());
        userDetails.setPostToEditCount(posts.stream().filter(post -> Objects.equals(post.getStatus(), PostStatusEnum.WAITING_REVIEW.getId())).toList().size());
        userDetails.setPostToReviewCount(posts.stream().filter(post -> Objects.equals(post.getStatus(), PostStatusEnum.WAITING_EDIT.getId())).toList().size());

        return userDetails;
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

    @Override
    public String updatePassword(ChangePasswordDTO changePasswordDTO) throws Exception {
        var user = repository.findById(AuthenticationUtil.getUuid()).orElseThrow(Exception::new);

        if (new BCryptPasswordEncoder().matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            if (changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
                user.setPassword(new BCryptPasswordEncoder().encode(changePasswordDTO.getNewPassword()));
                repository.save(user);
                return "Alteração realizada com sucesso!";
            } else {
                throw new Exception("As novas senhas são diferentes");
            }
        } else {
            throw new Exception("A senha atual apresentada está incorreta");
        }
    }

    private String generatePasswordAndSendByEmail(User user) {
        final var password = passwordService.getPassword();
        final var emailBody = """
                Seu email foi registrado no Portal Maker do IFES Campus Colatina! Você pode acessar nosso portal com
                 seu email e sua nova senha: %s
                """;
        emailService.send(user.getEmail(), emailBody.formatted(password), "Portal Maker - Seu email foi registrado!");
       return new BCryptPasswordEncoder().encode(password);
    }

}
