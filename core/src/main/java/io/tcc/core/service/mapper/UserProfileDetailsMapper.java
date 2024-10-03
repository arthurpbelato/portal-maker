package io.tcc.core.service.mapper;

import io.tcc.core.model.User;
import io.tcc.core.service.dto.UserProfileDetailsDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileDetailsMapper extends EntityMapper<UserProfileDetailsDTO, User> {
}
