package io.tcc.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "document")
public class Document implements Serializable {

    @Id
    @Column
    private String id;

    @Column
    @Lob
    private String base64;

    @Column
    private String title;

    @Column
    private String extension;

    @Column
    private String size;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
