package io.tcc.core.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserProfileDetailsDTO {

    private UUID id;

    private String name;

    private String email;

    private String cpf;

    private int postApprovedCount;

    private int postDeniedCount;

    private int postToReviewCount;

    private int postToEditCount;
}
