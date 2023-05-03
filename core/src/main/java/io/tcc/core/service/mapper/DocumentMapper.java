package io.tcc.core.service.mapper;

import io.tcc.core.model.Document;
import io.tcc.documentcommons.model.DocumentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper extends EntityMapper<DocumentDTO, Document> {
}
