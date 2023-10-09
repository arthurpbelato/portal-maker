package io.tcc.core.client;

import io.tcc.documentcommons.model.DocumentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "document", url = "http://localhost:8081/api/document")
public interface DocumentClient {

    /**
     *
     * @return the document uuid
     */
    @PostMapping
    String save(@RequestBody DocumentDTO documentDTO);

    @GetMapping("/{uuid}")
    DocumentDTO getDocument(@PathVariable("uuid") String uuid);

    @DeleteMapping("/{uuid}")
    void delete(@PathVariable("uuid") String uuid);

    @GetMapping("/list")
    List<DocumentDTO> getDocuments(@RequestParam("ids") List<String> ids);

}
