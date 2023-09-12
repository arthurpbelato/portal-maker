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

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
//TODO: fix logs
public class DocumentServiceImpl implements DocumentService {

    private final DocumentMapper mapper;
    private final DocumentClient client;
    private final DocumentRepository repository;

    @Override
    public String save(DocumentDTO documentDTO) {
        DocumentDTO savedDocument = saveDto(documentDTO);
        try{
            client.save(savedDocument);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return savedDocument.getId();
    }

    @Override
    public DocumentDTO getDocument(String uuid) {
        DocumentDTO response;
        try {
            response = client.getDocument(uuid);
        } catch (Exception e){
            log.error(e.getMessage());
            response = mapper.toDto(repository.findById(UUID.fromString(uuid)).orElseGet(Document::new));
        }
        return response;
    }

    @Override
    //TODO: review
    public void delete(String uuid) {
        client.delete(uuid);
    }

    @Override
    public List<String> saveAll(List<DocumentDTO> dtoList) {
        return dtoList.stream().map(this::save).toList();
    }

    @Override
    public List<DocumentDTO> getByIds(List<String> ids) {
        return null;
    }

    public List<DocumentDTO> getById(String postId) {
//        List<DocumentDTO> response;
//        try {
//            response = client.getDocument()
//        } catch (Exception e){
//            log.error(e.getMessage());
//            response = mapper.toDto(repository.findById(UUID.fromString(uuid)).orElseGet(Document::new));
//        }
//        return response;
        return null;
    }

    private DocumentDTO saveDto(DocumentDTO documentDTO) {
        return mapper.toDto(repository.save(mapper.toEntity(documentDTO)));
    }
}
