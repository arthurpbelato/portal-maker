package io.tcc.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(schema = "public")
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    @Id
    @Column
    private Long id;

    @Column
    private String name;

}
