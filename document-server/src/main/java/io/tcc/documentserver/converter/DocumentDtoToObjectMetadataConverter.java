package io.tcc.documentserver.converter;

import com.amazonaws.services.s3.model.ObjectMetadata;
import io.tcc.documentcommons.model.DocumentDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DocumentDtoToObjectMetadataConverter implements Converter<DocumentDTO, ObjectMetadata> {

    @Override
    public ObjectMetadata convert(DocumentDTO source) {
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(source.getBase64().getBytes().length);
        objectMetadata.setContentType(source.getExtension());
        objectMetadata.addUserMetadata("filename", "%s.%s".formatted(source.getTitle(), source.getExtension()));
        objectMetadata.addUserMetadata("extension", source.getExtension());
        objectMetadata.addUserMetadata("size", source.getSize());
        return objectMetadata;
    }
}
