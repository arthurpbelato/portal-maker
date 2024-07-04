package io.tcc.core.service.interfaces;

import io.tcc.core.service.dto.PostDTO;
import io.tcc.core.service.dto.PostListDTO;
import io.tcc.core.service.dto.PostReviewDTO;
import io.tcc.documentcommons.model.DocumentDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostDTO save(PostDTO dto);

    List<PostListDTO> list();

    List<DocumentDTO> loadImages(UUID postId);

    List<PostListDTO> getByUserId(Integer page, Integer size);

    List<PostListDTO> listReview(Integer page, Integer size);

    PostDTO updatedStatus(UUID id, Integer newStatus);

    PostDTO getById(UUID id);

    PostDTO approve(final String id);

    PostDTO review(final String id, final PostReviewDTO postReviewDTO);

    void deleteDocument(final String id);

    Integer getReviewCount();

    List<PostListDTO> listBySubject(final String subjectId);
}
