package io.tcc.documentserver.config;

import io.tcc.documentserver.config.properties.CredentialsProperties;
import io.tcc.documentserver.config.properties.S3Properties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cloud.aws")
public class ApplicationProperties {
    private CredentialsProperties credential = new CredentialsProperties();
    private S3Properties s3 = new S3Properties();
}




