package io.tcc.core.service.interfaces;

import io.tcc.core.service.dto.BasicUserDTO;
import io.tcc.core.service.dto.ChangePasswordDTO;
import io.tcc.core.service.dto.EnumDTO;
import io.tcc.core.service.dto.LoggedUserDTO;
import io.tcc.core.service.dto.UserProfileDTO;
import io.tcc.core.service.dto.UserProfileDetailsDTO;

import java.util.List;

public interface UserService {

    LoggedUserDTO login(BasicUserDTO basicUserDTO);

    UserProfileDTO save(UserProfileDTO basicUserDTO) throws Exception;

    UserProfileDTO getProfile(String id) throws Exception;

    UserProfileDetailsDTO getProfileDetails() throws Exception;

    List<UserProfileDTO> getProfiles();

    List<String> getLoggedUserRoles();

    List<EnumDTO> listRoles();

    String updatePassword(ChangePasswordDTO changePasswordDTO) throws Exception;
}
