package io.tcc.core.service.mapper;

import io.tcc.core.model.User;
import io.tcc.core.service.dto.BasicUserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasicUserMapper extends EntityMapper<BasicUserDTO, User>{
}
