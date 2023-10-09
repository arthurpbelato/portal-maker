package io.tcc.documentserver.controller;

import io.tcc.documentcommons.model.DocumentDTO;
import io.tcc.documentserver.service.DocumentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public ResponseEntity<String> save(@RequestBody @Valid DocumentDTO documentDTO) {
        log.debug("document-server - DocumentController#save - start");
        documentService.save(documentDTO);
        return ResponseEntity.ok(documentDTO.getId().toString());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable("uuid") @NotBlank String uuid) {
        log.debug("document-server - DocumentController#getDocument - start");
        return ResponseEntity.ok(documentService.getDocument(uuid));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DocumentDTO>> getDocuments(@RequestParam("ids") List<String> ids) {
        log.debug("document-server - DocumentController#getDocuments - start");
        return ResponseEntity.ok(documentService.getDocumentsByIds(ids));
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable("uuid") @NotBlank String uuid) {
        log.debug("document-server - DocumentController#delete - start");
        documentService.delete(uuid);
    }
}
