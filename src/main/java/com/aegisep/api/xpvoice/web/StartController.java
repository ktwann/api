package com.aegisep.api.xpvoice.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class StartController {

    @GetMapping(value = "/", produces = "application/json; charset=UTF-8")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("welcome aegis open api");
    }

}
