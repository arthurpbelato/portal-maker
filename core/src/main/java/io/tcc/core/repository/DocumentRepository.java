package io.tcc.core.repository;

import io.tcc.core.model.Document;
import io.tcc.core.model.enums.DocumentTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DocumentRepository extends JpaRepository<Document, UUID> {

    List<Document> findAllByPostId(UUID postId);

    List<Document> findAllByPostIdAndType(final UUID postId, final DocumentTypeEnum type);

}
