package io.tcc.core.model;

import io.tcc.core.model.enums.PostStatusEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "post")
@Accessors(chain = true)
public class Post implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private LocalDateTime postDate;

    @Column
    private String title;

    @Column(length = 2000)
    private String description;

    @Column
    private String externalReference;

    @Column
    private Long score = 0L;

    @Column
    private PostStatusEnum status;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Document> images = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    private List<Document> models = new ArrayList<>();

    @ManyToOne(optional = false)

    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String subject;

    @Column(columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<String> tags;

    @OneToMany(mappedBy = "post", orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PostReview> reviews = new ArrayList<>();

}
