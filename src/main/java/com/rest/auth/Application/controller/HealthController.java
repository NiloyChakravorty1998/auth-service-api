package com.rest.auth.Application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/health")
@Slf4j
public class HealthController {

    @RequestMapping(path ="", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map<String, String>> healthCheck() {
        log.info("API : /v1/health ");
        Map<String, String> response = new HashMap<>();
        response.put("status", "Up and running");
        response.put("time", LocalDateTime.now().toString());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
