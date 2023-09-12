package io.tcc.core.controller;

import io.tcc.core.service.dto.PostDTO;
import io.tcc.core.service.interfaces.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post")
public class PostController {

    private final PostService service;

    @PostMapping("/internal/save")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PostDTO> save(@RequestBody PostDTO dto) {
        var saved = service.save(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/internal/user")
    public ResponseEntity<List<PostDTO>> getByUserId() {
        return ResponseEntity.ok(service.getByUserId());
    }
}
