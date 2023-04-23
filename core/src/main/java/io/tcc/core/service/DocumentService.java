package io.tcc.core.service;

import io.tcc.documentserver.service.dto.DocumentDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface DocumentService {
    String save(@RequestBody DocumentDTO documentDTO);

    DocumentDTO getDocument(@PathVariable("uuid") String uuid);

    void delete(@PathVariable("uuid") String uuid);
}
