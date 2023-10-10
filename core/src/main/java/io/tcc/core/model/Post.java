package io.tcc.core.model;

import io.tcc.core.model.enums.PostStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private LocalDateTime postDate;

    @Column
    private String title;

    @Column(length = 600)
    private String description;

    @Column
    private String externalReference;

    @Column
    private Long score;

    @Column
    private PostStatusEnum status;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Document> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<Document> model3d = new ArrayList<>();

    @ManyToOne(optional = false)
    
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String subject;

//    private List<String> tags;
}
