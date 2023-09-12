package io.tcc.core.service;

import io.tcc.core.model.User;
import io.tcc.core.model.enums.RoleEnum;
import io.tcc.core.repository.UserRepository;
import io.tcc.core.service.dto.BasicUserDTO;
import io.tcc.core.service.dto.UserProfileDTO;
import io.tcc.core.service.dto.UserRegisterDTO;
import io.tcc.core.service.mapper.BasicUserMapper;
import io.tcc.core.service.mapper.UserProfileMapper;
import io.tcc.core.service.mapper.UserRegisterMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository repository;
    private final BasicUserMapper basicUserMapper;
    private final UserProfileMapper userProfileMapper;
    private final UserRegisterMapper userRegisterMapper;

    @Override
    public BasicUserDTO register(UserRegisterDTO userProfileDTO) {
        User user = userRegisterMapper.toEntity(userProfileDTO);
        user.getRoles().add(RoleEnum.ROLE_USER.getRole());
        return basicUserMapper.toDto(repository.save(user));
    }

    @Override
    public UserProfileDTO getProfile(String id) throws Exception{
        return repository.findById(UUID.fromString(id)).map(userProfileMapper::toDto).orElseThrow(Exception::new);
    }

    @Override
    public List<UserProfileDTO> getProfiles() {
        return repository.findAll().stream().map(userProfileMapper::toDto).toList();
    }
}
