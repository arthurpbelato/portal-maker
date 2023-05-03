package io.tcc.core.service;

import io.tcc.core.client.DocumentClient;
import io.tcc.core.model.Document;
import io.tcc.core.repository.DocumentRepository;
import io.tcc.core.service.interfaces.DocumentService;
import io.tcc.core.service.mapper.DocumentMapper;
import io.tcc.documentcommons.model.DocumentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentMapper mapper;
    private final DocumentClient client;
    private final DocumentRepository repository;

    @Override
    public String save(DocumentDTO documentDTO) {
        DocumentDTO savedDocument = saveDto(documentDTO);
        String response = client.save(savedDocument);
        return response;
    }

    @Override
    public DocumentDTO getDocument(String uuid) {
        DocumentDTO response;
        try {
            response = client.getDocument(uuid);
        } catch (Exception e){
            log.debug(e.getMessage());
            response = mapper.toDto(repository.findById(UUID.fromString(uuid)).orElseGet(Document::new));
        }
        return response;
    }

    @Override
    public void delete(String uuid) {
        client.delete(uuid);
    }

    private DocumentDTO saveDto(DocumentDTO documentDTO) {
        return mapper.toDto(repository.save(mapper.toEntity(documentDTO)));
    }
}
