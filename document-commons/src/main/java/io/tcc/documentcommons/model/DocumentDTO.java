package io.tcc.documentcommons.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDTO implements Serializable {

    private String id;

    @NotBlank
    private String base64;

    private String title;

    private String extension;

    private String size;

    private UUID postId;


    public DocumentDTO(String id) {
        this.id = id;
    }

    public static List<DocumentDTO> getInstancesByIdList(List<String> ids){
        return ids.stream().map(DocumentDTO::new).toList();
    }
}