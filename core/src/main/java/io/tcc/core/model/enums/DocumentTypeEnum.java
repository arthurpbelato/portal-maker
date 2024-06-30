package io.tcc.core.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum DocumentTypeEnum {
    IMAGE(0, "image"),
    MODEL(1, "model");


    private final Integer id;
    private final String description;

    public static DocumentTypeEnum getById(final Integer status) {
        return Arrays.stream(values()).filter(v -> v.getId().equals(status)).findFirst().orElse(null);
    }
}
