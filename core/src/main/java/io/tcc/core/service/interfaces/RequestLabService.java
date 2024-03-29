package io.tcc.core.service.interfaces;

import io.tcc.core.service.dto.RequestLabDTO;
import jakarta.mail.MessagingException;

public interface RequestLabService {
    void send(RequestLabDTO dto) throws MessagingException;
}
