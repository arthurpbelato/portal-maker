package io.tcc.core.web;

import io.tcc.core.models.Mensagem;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("api/oauth")
public class AuthenticationTest {

    @GetMapping
    public String helloWorld() {
        return "Hello World";
    }

    @PostMapping
    public ResponseEntity<String> putString(@RequestBody Mensagem message) {
        log.info(message.getMessage());
        return new ResponseEntity<>(message.getMessage(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        log.info("Deleted o ID: [{}]", id);
        System.out.println();
    }

}
