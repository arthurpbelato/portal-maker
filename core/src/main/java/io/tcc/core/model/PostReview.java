package io.tcc.core.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "post_review")
@Accessors(chain = true)
public class PostReview implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String reviewNote;

    @Column
    private Boolean solved;

    @ManyToOne
    private Post post;
}

