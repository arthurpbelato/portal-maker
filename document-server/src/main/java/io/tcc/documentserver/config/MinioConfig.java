package io.tcc.documentserver.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {
    private final ApplicationProperties applicationProperties;

    @Bean
    @SneakyThrows
    public MinioClient minioClient(){
        MinioClient minioClient = MinioClient.builder()
                .endpoint(applicationProperties.minio.getUrl())
                .credentials(applicationProperties.minio.getUser(), applicationProperties.minio.getPassword())
                .build();

        this.crateBucket(minioClient);

        return minioClient;
    }

    @SneakyThrows
    private void crateBucket(MinioClient minioClient){
        if(!minioClient.bucketExists(BucketExistsArgs.builder().bucket(applicationProperties.minio.getBucket()).build())){
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(applicationProperties.minio.getBucket()).build());
        }
    }
}
