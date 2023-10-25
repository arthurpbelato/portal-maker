package io.tcc.core.controller;

import io.tcc.core.service.dto.PostDTO;
import io.tcc.core.service.dto.PostListDTO;
import io.tcc.core.service.interfaces.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<List<PostListDTO>> getByUserId(@RequestParam("page") Integer page,
                                                         @RequestParam("size") Integer size) {
        return ResponseEntity.ok(service.getByUserId(page, size));
    }

    @GetMapping("/internal/waiting-review")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<PostListDTO>> getAllWaitingReview(@RequestParam("page") Integer page,
                                                                 @RequestParam("size") Integer size) {

        return ResponseEntity.ok(service.getAllWaitingReview(page, size));
    }

    @PatchMapping("/internal/status/{id}/{status}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PostDTO> updatePostStatus(@PathVariable("id") String id, @PathVariable("status") Integer status) {
        return ResponseEntity.ok(service.updatedStatus(UUID.fromString(id), status));
    }

    @GetMapping("/internal/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PostDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getById(UUID.fromString(id)));
    }

    @PutMapping("/internal")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<PostDTO> update(PostDTO postDTO){
        return ResponseEntity.ok(service.save(postDTO));
    }
}
