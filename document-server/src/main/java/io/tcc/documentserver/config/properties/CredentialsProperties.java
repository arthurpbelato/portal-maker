package io.tcc.documentserver.config.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialsProperties {
    private String accessKey;
    private String secretKey;
}