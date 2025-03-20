package io.tcc.core.repository;

import io.tcc.core.model.Post;
import io.tcc.core.model.User;
import io.tcc.core.model.enums.PostStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    Integer countByStatusInAndUser(List<PostStatusEnum> status, User user);
    List<Post> findBySubjectAndStatus(final String subjectId, final PostStatusEnum status);

    List<Post> findByStatus(final PostStatusEnum status);
}
