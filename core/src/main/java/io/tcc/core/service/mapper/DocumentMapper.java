package io.tcc.core.service.mapper;

import io.tcc.core.model.Document;
import io.tcc.documentcommons.model.DocumentDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {

    @Override
    @Mapping(source = "post.id", target = "postId")
    DocumentDTO toDto(Document entity);

    @Override
    @InheritInverseConfiguration
    Document toEntity(DocumentDTO dto);
}
