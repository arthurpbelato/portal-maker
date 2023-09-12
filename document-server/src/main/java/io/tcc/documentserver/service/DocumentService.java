package io.tcc.documentserver.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import io.tcc.documentcommons.model.DocumentDTO;
import io.tcc.documentserver.config.ApplicationProperties;
import io.tcc.documentserver.converter.DocumentDtoToObjectMetadataConverter;
import io.tcc.documentserver.converter.S3ObjectToDocumentDtoConverter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentDtoToObjectMetadataConverter dtoToObjectMetadataConverter;
    private final S3ObjectToDocumentDtoConverter s3ObjectToDocumentDtoConverter;
    private final ApplicationProperties applicationProperties;
    private final AmazonS3 client;

    @SneakyThrows
    public void save(DocumentDTO document) {
        log.debug("document-server - DocumentService#save - start");

        var file = new ByteArrayInputStream(document.getBase64().getBytes());
        var metadata = dtoToObjectMetadataConverter.convert(document);

        var putObjectRequest = new PutObjectRequest(applicationProperties.getS3().getBucketName(),
                document.getId().toString(), file, metadata);
        client.putObject(putObjectRequest);
    }

    @SneakyThrows
    public DocumentDTO getDocument(String uuid) {
        log.debug("document-server - DocumentService#getDocument - start");

        final var s3Object = client.getObject(applicationProperties.getS3().getBucketName(), uuid);
        return s3ObjectToDocumentDtoConverter.convert(s3Object);
    }

    //TODO review
    public List<DocumentDTO> getDocumentsByIds(List<String> ids){
        log.debug("document-server - DocumentService#getDocumentsByIds - start");
        return ids.stream().map(this::getDocument).toList();
    }

    @SneakyThrows
    public void delete(String uuid) {
        log.debug("document-server - DocumentService#delete - start");

        var request = new DeleteObjectRequest(applicationProperties.getS3().getBucketName(), uuid);
        client.deleteObject(request);
    }
}
