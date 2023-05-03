package io.tcc.core.controller;

import io.tcc.core.service.interfaces.DocumentService;
import io.tcc.documentcommons.model.DocumentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("api/file")
@RestController
public class FileController {

    private final DocumentService client;

    @PostMapping("/internal/save")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<String> save(@RequestBody DocumentDTO dto){
        return  ResponseEntity.ok(client.save(dto));
    }

    @GetMapping("/internal/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    ResponseEntity<DocumentDTO> get(@PathVariable("id") String dto){
        return  ResponseEntity.ok(client.getDocument(dto));
    }


}
