package io.tcc.documentserver.converter;

import com.amazonaws.services.s3.model.S3Object;
import io.tcc.documentcommons.model.DocumentDTO;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class S3ObjectToDocumentDtoConverter implements Converter<S3Object, DocumentDTO> {

    @Override
    @SneakyThrows
    public DocumentDTO convert(S3Object source) {
        return DocumentDTO.builder()
                .uuid(UUID.fromString(source.getKey()))
                .base64(IOUtils.toString(source.getObjectContent(), StandardCharsets.UTF_8))
                .extension(source.getObjectMetadata().getUserMetaDataOf("extension"))
                .title(source.getObjectMetadata().getUserMetaDataOf("filename"))
                .size(source.getObjectMetadata().getUserMetaDataOf("size"))
                .build();
    }
}
