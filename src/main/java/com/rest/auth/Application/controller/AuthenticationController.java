package com.rest.auth.Application.controller;

import com.rest.auth.Application.service.AuthenticationService;
import com.rest.auth.Application.vo.SignInRequestVO;
import com.rest.auth.Application.vo.SignupRequestVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1/api")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping(path = "/register", name = "register new user")
    public ResponseEntity<Object> signUp(@RequestBody SignupRequestVO ivo){
        return new ResponseEntity<>(authService.register(ivo), HttpStatus.CREATED);
    }
    @PostMapping(path = "/authenticate", name = "login user")
    public ResponseEntity<Object> signIn(@RequestBody SignInRequestVO ivo){
        log.info("Login for : "+ ivo.getEmail());
        return new ResponseEntity<>(authService.authenticate(ivo), HttpStatus.OK);
    }
}
