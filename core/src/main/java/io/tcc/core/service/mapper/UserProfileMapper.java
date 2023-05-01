package io.tcc.core.service.mapper;

import io.tcc.core.model.User;
import io.tcc.core.service.dto.UserProfileDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper extends EntityMapper<UserProfileDTO, User> {
}
