package io.tcc.core.service.interfaces;

public interface EmailService {
    void send(String to, String body, String subject);
}
