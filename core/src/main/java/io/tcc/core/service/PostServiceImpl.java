package io.tcc.core.service;

import io.tcc.core.repository.PostRepository;
import io.tcc.core.service.dto.PostDTO;
import io.tcc.core.service.interfaces.DocumentService;
import io.tcc.core.service.interfaces.PostService;
import io.tcc.core.service.mapper.PostMapper;
import io.tcc.core.util.AuthenticationUtil;
import io.tcc.documentcommons.model.DocumentDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
    private final PostMapper mapper;

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
    //TODO usar pageable
    public List<PostDTO> getByUserId() {
        return mapper.toDto(repository.findAllByUserId(AuthenticationUtil.getUuid()));
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
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }
}
