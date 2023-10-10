package io.tcc.core.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum PostStatusEnum {

    WAITING_REVIEW(0, "Aguardando revisão"),
    WAITING_EDIT(1, "Aguardando edição"),
    APPROVED(2, "Aprovado"),
    DENIED(3, "Negado");


    private final Integer id;
    private final String description;

    public static PostStatusEnum getById(Integer status) {
        return Arrays.stream(values()).filter(v -> v.getId().equals(status)).findFirst().orElse(null);
    }
}
