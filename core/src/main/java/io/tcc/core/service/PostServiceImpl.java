package io.tcc.core.service;

import io.tcc.core.model.Document;
import io.tcc.core.model.Post;
import io.tcc.core.model.PostReview;
import io.tcc.core.model.enums.PostStatusEnum;
import io.tcc.core.repository.DocumentRepository;
import io.tcc.core.repository.PostPageRepository;
import io.tcc.core.repository.PostRepository;
import io.tcc.core.repository.PostReviewRepository;
import io.tcc.core.service.dto.PostDTO;
import io.tcc.core.service.dto.PostListDTO;
import io.tcc.core.service.dto.PostReviewDTO;
import io.tcc.core.service.interfaces.DocumentService;
import io.tcc.core.service.interfaces.PostService;
import io.tcc.core.service.mapper.PostListMapper;
import io.tcc.core.service.mapper.PostMapper;
import io.tcc.core.service.mapper.PostReviewMapper;
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

import static io.tcc.core.model.enums.PostStatusEnum.*;
import static io.tcc.core.model.enums.RoleEnum.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
//TODO logs
public class PostServiceImpl implements PostService {

    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_MODEL = "model";
    private final DocumentService documentService;
    private final PostRepository repository;
    private final PostPageRepository pageRepository;
    private final PostMapper mapper;
    private final PostListMapper listMapper;
    private final PostReviewMapper postReviewMapper;
    private final PostReviewRepository postReviewRepository;
    private final DocumentRepository documentRepository;

    @Override
    public PostDTO save(final PostDTO dto) {
        List<DocumentDTO> images = dto.getImages();
        List<DocumentDTO> model3d = dto.getModels();
        dto.setPostDate(LocalDateTime.now());
        var savedDto = saveDto(dto);

        setDocumentId(images, savedDto, TYPE_IMAGE);
        savedDto.setImages(DocumentDTO.getInstancesByIdList(documentService.saveAll(images)));

        setDocumentId(model3d, savedDto, TYPE_MODEL);
        savedDto.setModels(DocumentDTO.getInstancesByIdList(documentService.saveAll(model3d)));

        return saveDto(savedDto);
    }

    @Override
    public List<PostListDTO> list() {
        return listMapper.toDto(repository.findAll());
    }

    @Override
    public List<DocumentDTO> loadImages(UUID postId) {
        return documentService.getByPostId(postId);
    }

    @Override
    public List<PostListDTO> getByUserId(Integer page, Integer size) {
        return listMapper.toDto(pageRepository.findAllByUserId(AuthenticationUtil.getUuid(), PageRequest.of(page, size)));
    }

    @Override
    public List<PostListDTO> listReview(final Integer page, final Integer size) {
        if (AuthenticationUtil.getLoggedUser()
                .getAuthorities()
                .stream()
                .anyMatch(role -> role.toString().equals(ROLE_ADMIN.getRole().getName()))) {
            return listMapper.toDto(pageRepository.findAllByStatus(WAITING_REVIEW,
                    PageRequest.of(page, size, Sort.by("postDate").ascending())));
        }
        return listMapper.toDto(pageRepository.findAllByUserAndStatusIn(AuthenticationUtil.getLoggedUser().getUser(),
                List.of(WAITING_EDIT, WAITING_REVIEW),
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

    @Override
    public PostDTO approve(final String id) {
        final var post = repository.findById(UUID.fromString(id)).orElseThrow(RuntimeException::new);
        post.setStatus(APPROVED);
        final var result = repository.save(post);
        return mapper.toDto(result);
    }

    @Override
    public PostDTO review(final String id, final PostReviewDTO postReviewDTO) {
        final var postReview = new PostReview();
        final var postId = UUID.fromString(id);
        final var post = repository.findById(postId).orElse(null);
        if (post == null) {
            throw new RuntimeException("Post not found");
        }
        post.setStatus(WAITING_EDIT);
        postReview.setPost(post)
                .setReviewNote(postReviewDTO.getReviewNote())
                .setSolved(false);
        repository.save(post);
        postReviewRepository.save(postReview);
        return mapper.toDto(post);
    }

    @Override
    public void deleteDocument(String id) {
        documentRepository.delete(new Document().setId(id));
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
        post.setStatus(WAITING_REVIEW);
        return mapper.toDto(repository.save(post));
    }
}
