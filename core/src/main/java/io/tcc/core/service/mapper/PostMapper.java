package io.tcc.core.service.mapper;

import io.tcc.core.model.Post;
import io.tcc.core.service.dto.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostUserMapper.class, DocumentMapper.class, PostStatusEnumMapper.class})
public interface PostMapper extends EntityMapper<PostDTO, Post> {

    @Override
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "models", ignore = true)
    PostDTO toDto(Post post);

}
