package com.tcc.documentserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "properties")
public class ApplicationProperties {
    MinioProperties minio = new MinioProperties();

    @Getter
    @Setter
    public static class MinioProperties{
        private String url;
        private String user;
        private String password;
        private String bucket;
    }
}
