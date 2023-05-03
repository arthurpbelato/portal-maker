package io.tcc.core.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Post implements Serializable {
    private UUID id;
    private LocalDate postDate;
    private String title;
    private String description;
    private String externalReference;
    private Long score;
    private List<Document> images;
    private List<Document> model3d;
    private User user;
    private List<String> tags;
}
