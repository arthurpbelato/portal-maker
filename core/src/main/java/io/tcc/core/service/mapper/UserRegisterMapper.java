package io.tcc.core.service.mapper;

import io.tcc.core.model.User;
import io.tcc.core.service.dto.UserRegisterDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRegisterMapper extends EntityMapper<UserRegisterDTO, User> {
}
