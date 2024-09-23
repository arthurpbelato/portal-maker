package io.tcc.core.repository;

import io.tcc.core.model.Document;
import io.tcc.core.model.enums.DocumentTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, String> {

    @Query("select new io.tcc.core.model.Document(d.id, d.title) from Document d where d.post.id = ?1 and d.type = 1")
    List<Document> findAllLazyModelsByPostId(UUID postId);

    @Query("""
    select d from Document d where d.post.id = ?1 and d.type = 0
    """)
    List<Document> findAllImagesByPostId(UUID postId);

    @Query("""
    select d from Document d where d.post.id = ?1 and d.type = 1
    """)
    List<Document> findAllModelsByPostId(UUID postId);

    List<Document> findAllByPostIdAndType(final UUID postId, final DocumentTypeEnum type);

}
