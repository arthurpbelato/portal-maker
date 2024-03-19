package io.tcc.core.service.interfaces;

import io.tcc.documentcommons.model.DocumentDTO;

import java.util.List;
import java.util.UUID;

public interface DocumentService {
    String save(DocumentDTO documentDTO);

    DocumentDTO getDocument(String uuid);

    void delete(String uuid);

    List<String> saveAll(List<DocumentDTO> dtoList);

    List<DocumentDTO> getByIds(List<String> ids);

    List<DocumentDTO> getByPostId(UUID postId);
}
