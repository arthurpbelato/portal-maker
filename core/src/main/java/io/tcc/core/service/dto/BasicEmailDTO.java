package io.tcc.core.service.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class BasicEmailDTO {
    private String to;
    private String body;
}
