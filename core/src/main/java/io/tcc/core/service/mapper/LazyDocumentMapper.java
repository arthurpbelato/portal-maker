package io.tcc.core.service.mapper;

import io.tcc.core.model.Document;
import io.tcc.documentcommons.model.LazyDocumentDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LazyDocumentMapper extends EntityMapper<LazyDocumentDTO, Document> {
}
