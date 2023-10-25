package io.tcc.core.repository;

import io.tcc.core.model.Post;
import io.tcc.core.model.enums.PostStatusEnum;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface PostPageRepository extends PagingAndSortingRepository<Post, UUID> {
    List<Post> findAllByStatus(PostStatusEnum status, PageRequest page);

    List<Post> findAllByUserId(UUID id, PageRequest page);

}
