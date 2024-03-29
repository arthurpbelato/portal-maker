package io.tcc.core.service.interfaces;

import io.tcc.documentcommons.model.DocumentDTO;
import jakarta.mail.MessagingException;

import java.util.List;

public interface EmailService {
    void send(String to, String body, String subject);


    void sendWithAttachment(final String to, final String body, final String subject, final List<DocumentDTO> attachments) throws MessagingException;
}
