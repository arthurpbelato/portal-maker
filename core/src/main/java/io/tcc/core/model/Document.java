package io.tcc.core.model;

import io.tcc.core.model.enums.DocumentTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "document")
@Accessors(chain = true)
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

    @Column
    private DocumentTypeEnum type;

    public Document (String id, String title) {
        this.id = id;
        this.title = title;
    }

}
