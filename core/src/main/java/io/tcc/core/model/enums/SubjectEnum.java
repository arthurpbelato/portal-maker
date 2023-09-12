package io.tcc.core.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum SubjectEnum {

    NONE(0, "Sem Matéria"),
    MATEMATICA(1, "Matemática"),
    PORTUGUES(2, "Português"),
    BIOLOGIA(3, "Biologia"),
    QUIMICA(3, "Química"),
    FISICA(3, "Física"),
    GEOGRAFIA(3, "Geografia");

    private final Integer id;
    private final String name;

    public static SubjectEnum getByName(String name) {
        return Arrays.stream(SubjectEnum.values())
                .filter(subject -> subject.getName().equals(name)).findFirst().orElse(NONE);
    }
}
