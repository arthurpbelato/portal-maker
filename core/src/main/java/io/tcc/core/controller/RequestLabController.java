package io.tcc.core.controller;

import io.tcc.core.service.RequestLabServiceImpl;
import io.tcc.core.service.dto.RequestLabDTO;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/request-lab")
public class RequestLabController {

    private final RequestLabServiceImpl service;

    @PostMapping("/public/send")
    public ResponseEntity<HttpStatus> send(@RequestBody final RequestLabDTO dto) throws MessagingException {
        service.send(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
