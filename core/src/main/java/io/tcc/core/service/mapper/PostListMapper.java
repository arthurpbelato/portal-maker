package io.tcc.core.service.mapper;

import io.tcc.core.model.Post;
import io.tcc.core.service.dto.PostListDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PostUserMapper.class, PostStatusEnumMapper.class})
public interface PostListMapper extends EntityMapper<PostListDTO, Post>{
}
