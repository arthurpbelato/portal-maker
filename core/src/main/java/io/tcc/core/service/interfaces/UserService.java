package io.tcc.core.service.interfaces;

import io.tcc.core.service.dto.BasicUserDTO;
import io.tcc.core.service.dto.LoggedUserDTO;
import io.tcc.core.service.dto.UserProfileDTO;
import io.tcc.core.service.dto.UserRegisterDTO;

import java.util.List;

public interface UserService {

    LoggedUserDTO login(BasicUserDTO basicUserDTO);

    BasicUserDTO register(UserRegisterDTO basicUserDTO);

    UserProfileDTO getProfile(String id) throws Exception;

    List<UserProfileDTO> getProfiles();
}
