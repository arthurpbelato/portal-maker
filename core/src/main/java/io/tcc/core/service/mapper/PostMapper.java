package io.tcc.core.service.mapper;

import io.tcc.core.model.Post;
import io.tcc.core.service.dto.PostDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PostUserMapper.class, DocumentMapper.class, PostStatusEnumMapper.class})
public interface PostMapper extends EntityMapper<PostDTO, Post> {
}
