package io.tcc.core.service;

import io.tcc.core.model.enums.PostStatusEnum;
import io.tcc.core.repository.PostPageRepository;
import io.tcc.core.repository.PostRepository;
import io.tcc.core.service.dto.PostDTO;
import io.tcc.core.service.dto.PostListDTO;
import io.tcc.core.service.interfaces.DocumentService;
import io.tcc.core.service.interfaces.PostService;
import io.tcc.core.service.mapper.PostListMapper;
import io.tcc.core.service.mapper.PostMapper;
import io.tcc.core.util.AuthenticationUtil;
import io.tcc.documentcommons.model.DocumentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
//TODO logs
public class PostServiceImpl implements PostService {

    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_3D = "3d";
    private final DocumentService documentService;
    private final PostRepository repository;
    private final PostPageRepository pageRepository;
    private final PostMapper mapper;
    private final PostListMapper listMapper;

    @Override
    public PostDTO save(PostDTO dto) {
        List<DocumentDTO> images = dto.getImages();
        List<DocumentDTO> model3d = dto.getModel3d();
        dto.setPostDate(LocalDateTime.now());
        var savedDto = saveDto(dto);

        setDocumentId(images, savedDto, TYPE_IMAGE);
        savedDto.setImages(DocumentDTO.getInstancesByIdList(documentService.saveAll(images)));

        setDocumentId(model3d, savedDto, TYPE_3D);
        savedDto.setModel3d(DocumentDTO.getInstancesByIdList(documentService.saveAll(model3d)));

        return saveDto(savedDto);
    }

    @Override
    public List<PostListDTO> getByUserId(Integer page, Integer size) {
        return listMapper.toDto(pageRepository.findAllByUserId(AuthenticationUtil.getUuid(), PageRequest.of(page, size)));
    }

    @Override
    public List<PostListDTO> getAllWaitingReview(Integer page, Integer size) {
        return listMapper.toDto(pageRepository.findAllByStatus(PostStatusEnum.WAITING_REVIEW,
                PageRequest.of(page, size, Sort.by("postDate").ascending())));
    }

    @Override
    public PostDTO updatedStatus(UUID id, Integer newStatus) {
        var post = repository.findById(id).orElseThrow(RuntimeException::new);
        post.setStatus(PostStatusEnum.getById(newStatus));
        return mapper.toDto(repository.save(post));
    }

    @Override
    public PostDTO getById(UUID id) {
        var post = repository.findById(id).orElseThrow(RuntimeException::new);
        return mapper.toDto(post);
    }

    private static void setDocumentId(List<DocumentDTO> documentDTOList, PostDTO savedDto, String type) {
        var counter = new AtomicInteger(0);
        documentDTOList.forEach(dto -> {
            dto.setId(buildDocumentId(savedDto, type, counter.getAndIncrement()));
            dto.setPostId(savedDto.getId());
        });
    }

    private static String buildDocumentId(PostDTO savedDto, String type, Integer counter) {
        return "%s::%s%d".formatted(savedDto.getId().toString(), type, counter);
    }

    private PostDTO saveDto(PostDTO dto) {
        var post = mapper.toEntity(dto);
        post.setStatus(PostStatusEnum.WAITING_REVIEW);
        return mapper.toDto(repository.save(post));
    }
}
