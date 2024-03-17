package io.tcc.core.service.dto;

import io.tcc.documentcommons.model.DocumentDTO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostDTO {
    private UUID id;
    private LocalDateTime postDate;
    private String title;
    private String description;
    private String externalReference;
    private Long score;
    private List<DocumentDTO> images = new LinkedList<>();
    private List<DocumentDTO> models = new ArrayList<>();
    private PostUserDTO user;
    private String subject;
    private Integer status;
    private List<String> tags = new ArrayList<>();
}
