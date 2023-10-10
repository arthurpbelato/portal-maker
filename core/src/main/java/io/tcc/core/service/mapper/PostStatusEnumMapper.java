package io.tcc.core.service.mapper;

import io.tcc.core.model.enums.PostStatusEnum;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostStatusEnumMapper extends EntityMapper<Integer, PostStatusEnum> {
    @Override
    default PostStatusEnum toEntity(Integer dto) {
        return PostStatusEnum.getById(dto);
    }

    @Override
    default Integer toDto(PostStatusEnum entity) {
        return entity.getId();
    }
}
