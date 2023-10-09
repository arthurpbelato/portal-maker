package io.tcc.core.service.interfaces;

import io.tcc.core.service.dto.PostDTO;

import java.util.List;

public interface PostService {
    PostDTO save(PostDTO dto);

    List<PostDTO> getByUserId();
}
