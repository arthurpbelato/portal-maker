package io.tcc.core.service.interfaces;

import io.tcc.core.service.dto.PostDTO;
import io.tcc.core.service.dto.PostListDTO;

import java.util.List;
import java.util.UUID;

public interface PostService {
    PostDTO save(PostDTO dto);

    List<PostListDTO> getByUserId(Integer page, Integer size);

    List<PostListDTO> getAllWaitingReview(Integer page, Integer size);

    PostDTO updatedStatus(UUID id, Integer newStatus);

    PostDTO getById(UUID id);
}
