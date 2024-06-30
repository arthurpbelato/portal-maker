package io.tcc.core.repository;

import io.tcc.core.model.PostReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostReviewRepository extends JpaRepository<PostReview, UUID> {
}
