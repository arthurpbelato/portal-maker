package io.tcc.core.service.mapper;

import io.tcc.core.model.PostReview;
import io.tcc.core.service.dto.PostReviewDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostReviewMapper extends EntityMapper<PostReviewDTO, PostReview> {
}
