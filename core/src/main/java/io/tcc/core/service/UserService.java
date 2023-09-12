package io.tcc.core.service;

import io.tcc.core.service.dto.BasicUserDTO;
import io.tcc.core.service.dto.UserProfileDTO;
import io.tcc.core.service.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {
    BasicUserDTO register(UserRegisterDTO basicUserDTO);

    UserProfileDTO getProfile(String id) throws Exception;

    List<UserProfileDTO> getProfiles();
}
