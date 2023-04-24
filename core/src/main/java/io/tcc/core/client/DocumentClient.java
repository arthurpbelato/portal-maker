package io.tcc.core.client;

import io.tcc.documentcommons.model.DocumentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "document", url = "http://localhost:10001/api/document")
public interface DocumentClient {

    /**
     *
     * @return the document uuid
     */
    @PostMapping
    ResponseEntity<String> save(@RequestBody DocumentDTO documentDTO);

    @GetMapping("/{uuid}")
    ResponseEntity<DocumentDTO> getDocument(@PathVariable("uuid") String uuid);

    @DeleteMapping("/{uuid}")
    void delete(@PathVariable("uuid") String uuid);

}
