package io.tcc.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(schema = "public")
public class Role implements Serializable {

    @Id
    @Column
    private Long id;

    @Column
    private String name;

}
