package com.tcc.documentserver.service;

import com.tcc.documentserver.config.ApplicationProperties;
import com.tcc.documentserver.service.dto.DocumentDTO;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final ApplicationProperties applicationProperties;

    private final MinioClient client;

    @SneakyThrows
    public void save(DocumentDTO document){
        client.putObject(PutObjectArgs.builder()
                .contentType(StandardCharsets.UTF_8.toString())
                .stream(new ByteArrayInputStream(document.getBase64().getBytes()),document.getBase64().getBytes().length,0)
                .object(document.getUuid())
                .bucket(applicationProperties.getMinio().getBucket()).build());
    }

    @SneakyThrows
    public DocumentDTO getDocument(String uuid){
        GetObjectResponse file = client.getObject(GetObjectArgs.builder()
                .bucket(applicationProperties.getMinio().getBucket())
                .object(uuid).build());
        return new DocumentDTO(uuid, IOUtils.toString(file,StandardCharsets.UTF_8));
    }

    @SneakyThrows
    public void delete(String uuid){
        client.removeObject(RemoveObjectArgs.builder().bucket(applicationProperties.getMinio().getBucket()).object(uuid).build());
    }
}
