package io.tcc.documentcommons.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentDTO implements Serializable {

    private UUID uuid;

    @NotBlank
    private String base64;

    private String title;

    private String extension;

    private String size;
}