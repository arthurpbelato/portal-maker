package io.tcc.core.controller;

import io.tcc.core.service.dto.PostDTO;
import io.tcc.core.service.dto.PostListDTO;
import io.tcc.core.service.dto.PostReviewDTO;
import io.tcc.core.service.dto.PostUserDTO;
import io.tcc.core.service.interfaces.PostService;
import io.tcc.core.util.AuthenticationUtil;
import io.tcc.documentcommons.model.DocumentDTO;
import io.tcc.documentcommons.model.LazyDocumentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/post")
public class PostController {

    private final PostService service;

    @GetMapping("/public/list")
    public ResponseEntity<List<PostListDTO>> list() {
        return ResponseEntity.ok(service.list());
    }

    @GetMapping("/public/images/{postId}")
    public ResponseEntity<List<DocumentDTO>> loadImages(@PathVariable UUID postId) {
        return ResponseEntity.ok(service.loadImages(postId));
    }

    @GetMapping("/public/models/{postId}")
    public ResponseEntity<List<DocumentDTO>> loadModels(@PathVariable UUID postId) {
        return ResponseEntity.ok(service.loadModels(postId));
    }

    @GetMapping("/public/models/lazy/{postId}")
    public ResponseEntity<List<LazyDocumentDTO>> lazyLoadModels(@PathVariable UUID postId) {
        return ResponseEntity.ok(service.lazyLoadModels(postId));
    }

    @PostMapping("/internal/save")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER', 'ROLE_USER')")
    public ResponseEntity<PostDTO> save(@RequestBody PostDTO dto) {
        final var user = new PostUserDTO().setId(AuthenticationUtil.getUuid());
        dto.setUser(user);
        final var saved = service.save(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/internal/user")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER', 'ROLE_USER')")
    public ResponseEntity<List<PostListDTO>> getByUserId(@RequestParam("page") Integer page,
                                                         @RequestParam("size") Integer size) {
        return ResponseEntity.ok(service.getByUserId(page, size));
    }

    @GetMapping("/internal/list/review")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER', 'ROLE_USER')")
    public ResponseEntity<List<PostListDTO>> listReview(@RequestParam("page") Integer page,
                                                        @RequestParam("size") Integer size) {

        return ResponseEntity.ok(service.listReview(page, size));
    }

    @PatchMapping("/internal/status/{id}/{status}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER', 'ROLE_USER')")
    public ResponseEntity<PostDTO> updatePostStatus(@PathVariable("id") String id, @PathVariable("status") Integer status) {
        return ResponseEntity.ok(service.updatedStatus(UUID.fromString(id), status));
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<PostDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getById(UUID.fromString(id)));
    }

    @PutMapping("/internal")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER', 'ROLE_USER')")
    public ResponseEntity<PostDTO> update(PostDTO postDTO){
        return ResponseEntity.ok(service.save(postDTO));
    }

    @PatchMapping("/internal/approve/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER')")
    public ResponseEntity<PostDTO> approve(@PathVariable("id") final String id) {
        return ResponseEntity.ok(service.approve(id));
    }

    @PostMapping("/internal/review/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER')")
    public ResponseEntity<PostDTO> review(@PathVariable("id") final String id,
                                          @RequestBody final PostReviewDTO postReviewDTO) {
        return ResponseEntity.ok(service.review(id, postReviewDTO));
    }

    @DeleteMapping("/internal/document/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER', 'ROLE_USER')")
    public ResponseEntity<Void> delete(@PathVariable("id") final String id) {
        service.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/internal/list/review/count")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_REVIEWER', 'ROLE_USER')")
    public ResponseEntity<Integer> getReviewCount() {
        return ResponseEntity.ok(service.getReviewCount());
    }

    @GetMapping("/public/list/subject/{subjectId}")
    public ResponseEntity<List<PostListDTO>> listBySubject(@PathVariable("subjectId") final String subjectId) {
        return ResponseEntity.ok(service.listBySubject(subjectId));
    }

    @GetMapping("/public/models/download/{id}")
    public ResponseEntity<DocumentDTO> downloadModel(@PathVariable("id") final String id) {
        return ResponseEntity.ok(service.downloadModel(id));
    }
}
