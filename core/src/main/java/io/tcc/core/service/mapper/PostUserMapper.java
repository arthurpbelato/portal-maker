package io.tcc.core.service.mapper;

import io.tcc.core.model.User;
import io.tcc.core.service.dto.PostUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostUserMapper extends EntityMapper<PostUserDTO, User> {
}
