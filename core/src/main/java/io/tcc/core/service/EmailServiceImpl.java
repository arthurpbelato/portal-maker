package io.tcc.core.service;

import io.tcc.core.service.interfaces.EmailService;
import io.tcc.documentcommons.model.DocumentDTO;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.*;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;


    @Override
    public void send(String to, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("tccportalmake@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        sender.send(message);
    }

    @Override
    public void sendWithAttachment( final String to, final String body, final String subject, final List<DocumentDTO> attachments) throws MessagingException {

        final Multipart multipart = new MimeMultipart();
        final var message = sender.createMimeMessage();

        attachments.forEach(attachment-> {
            final MimeBodyPart filePart = new PreencodedMimeBodyPart("base64");
            try {
                filePart.setContent(attachment.getBase64(), "application/*");
                filePart.setFileName(attachment.getTitle());
                multipart.addBodyPart(filePart);
            } catch (final MessagingException e) {
                throw new RuntimeException(e);
            }
        });

        final var bodyPart = new MimeBodyPart();
        bodyPart.setContent(body, "text/html");

        multipart.addBodyPart(bodyPart);

        message.setFrom("tccportalmaker@gmail.com");
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(multipart);


        sender.send(message);
    }
}
