package io.tcc.core.service;

import io.tcc.documentserver.service.dto.DocumentDTO;
import io.tcc.core.client.DocumentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService{

    private final DocumentClient client;

    @Override
    public String save(DocumentDTO documentDTO) {
        ResponseEntity<String> response = client.save(documentDTO);
        return response.getBody();
    }

    @Override
    public DocumentDTO getDocument(String uuid) {
        ResponseEntity<DocumentDTO> response = client.getDocument(uuid);
        return response.getBody();
    }

    @Override
    public void delete(String uuid) {
        client.delete(uuid);
    }
}
