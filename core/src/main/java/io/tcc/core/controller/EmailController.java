package io.tcc.core.controller;

import io.tcc.core.service.dto.BasicEmailDTO;
import io.tcc.core.service.interfaces.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/email")
public class EmailController {

    private final EmailService service;

    @GetMapping("/public")
    public ResponseEntity<HttpStatus> send(@RequestBody BasicEmailDTO emailData) {
        service.send(emailData.getTo(), emailData.getBody(),"Assunto");
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
