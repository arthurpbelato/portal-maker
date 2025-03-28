package io.tcc.core.service;

import io.tcc.core.service.dto.RequestLabDTO;
import io.tcc.core.service.interfaces.EmailService;
import io.tcc.core.service.interfaces.RequestLabService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestLabServiceImpl implements RequestLabService {

    private final EmailService emailService;

    private static final String TEMPLATE = """
            <strong>Nome:</strong> %s
            <br>
            <strong>CPF:</strong> %s
            <br>
            <strong>Email:</strong> %s
            <br>
            <strong>Telefone:</strong> %s
            <br>
            <strong>Relação com a Instituição:</strong> %s
            <br>
            <strong>Título do projeto:</strong> %s
            <br>
            <strong>Descrição do projeto:</strong> %s
            <br>
            <strong>Recursos necessários:</strong> %s
            """;

    @Override
    public void send(final RequestLabDTO dto) throws MessagingException {
        final var body = TEMPLATE.formatted(dto.getName(),
                dto.getCpf(),
                dto.getEmail(),
                dto.getPhone(),
                dto.getRelation(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getResources());
        emailService.sendWithAttachment("tccportalmaker@gmail.com",
                body,
                "Requisição para uso do laboratório - %s".formatted(dto.getTitle()) ,
                dto.getFiles());
    }
}
