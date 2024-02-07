package io.tcc.core.service.mapper;

import io.tcc.core.model.Role;
import io.tcc.core.service.dto.EnumDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends EntityMapper<EnumDTO, Role> {
}
