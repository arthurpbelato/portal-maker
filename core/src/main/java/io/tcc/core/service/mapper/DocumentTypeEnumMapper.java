package io.tcc.core.service.mapper;

import io.tcc.core.model.enums.DocumentTypeEnum;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentTypeEnumMapper extends EntityMapper<Integer, DocumentTypeEnum> {
    @Override
    default DocumentTypeEnum toEntity(Integer dto) {
        return DocumentTypeEnum.getById(dto);
    }

    @Override
    default Integer toDto(DocumentTypeEnum entity) {
        return entity.getId();
    }
}