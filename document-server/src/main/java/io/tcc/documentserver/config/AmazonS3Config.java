package io.tcc.documentserver.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AmazonS3Config {
    @Bean
    public AmazonS3 amazonS3(
            @Value("${cloud.aws.s3.url}") String serviceUrl,
            @Value("${cloud.aws.s3.path-style-enabled}") Boolean pathStyleAccessEnabled,
            @Value("${cloud.aws.credential.access-key}") String accessKey,
            @Value("${cloud.aws.credential.secret-key}") String secretKey,
            @Value("${cloud.aws.region.static:null}") String region,
            @Value("${cloud.aws.s3.bucket-name}") String bucketName) {
        final AmazonS3 amazonS3 = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        serviceUrl, region))
                .withPathStyleAccessEnabled(pathStyleAccessEnabled)
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        if (!amazonS3.doesBucketExistV2(bucketName)) {
            amazonS3.createBucket(bucketName);
        }
        return amazonS3;
    }
}
