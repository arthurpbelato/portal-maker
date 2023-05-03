package io.tcc.documentserver.config.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class S3Properties {
    private String url;
    private Boolean pathStyleEnabled;
    private String bucketName;
}

