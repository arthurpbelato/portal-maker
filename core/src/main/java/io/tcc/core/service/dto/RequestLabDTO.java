package io.tcc.core.service.dto;

import io.tcc.documentcommons.model.DocumentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RequestLabDTO {
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String title;
    private String description;
    private String resources;
    private String relation;
    private List<DocumentDTO> files = new ArrayList<>();
}
