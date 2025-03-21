package io.tcc.core.web;

import io.tcc.core.model.Mensagem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AuthenticationTest {

    @GetMapping("/public/hello")
    public ResponseEntity<String> helloWorld() {
        return new ResponseEntity<>("Hello World", HttpStatus.OK);
    }

    @GetMapping("/internal/oauth")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> helloUser() {
        return new ResponseEntity<>("Olá usuário!!", HttpStatus.OK);
    }

    @PostMapping("/internal/oauth")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> putString(@RequestBody Mensagem message) {
        log.info(message.getMessage());
        return new ResponseEntity<>(message.getMessage(), HttpStatus.CREATED);
    }

    @PostMapping("/internal/oauth/encode")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> encode(@RequestBody Mensagem message) {
        var encoded = new BCryptPasswordEncoder().encode(message.getMessage());
        log.info("Encoded message: {}", encoded);
        return new ResponseEntity<>(encoded, HttpStatus.CREATED);
    }

    @DeleteMapping("/internal/oauth/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable("id") Long id) {
        log.info("Deleted o ID: [{}]", id);
        System.out.println();
    }

}
