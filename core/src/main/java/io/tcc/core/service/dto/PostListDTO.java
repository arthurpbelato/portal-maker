package io.tcc.core.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PostListDTO {

    private UUID id;
    private LocalDateTime postDate;
    private String title;
    private String description;
    private Long score;
    private PostUserDTO user;
    private String subject;
    private Integer status;
    private List<String> tags;

}
