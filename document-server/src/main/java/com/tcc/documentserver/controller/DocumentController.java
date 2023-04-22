package com.tcc.documentserver.controller;

import com.tcc.documentserver.service.DocumentService;
import com.tcc.documentserver.service.dto.DocumentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
public class DocumentController {
    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody DocumentDTO documentDTO){
        documentService.save(documentDTO);
        return ResponseEntity.ok(documentDTO.getUuid());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable("uuid") String uuid){
        return ResponseEntity.ok(documentService.getDocument(uuid));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable("uuid") String uuid){
        documentService.delete(uuid);
    }
}
