package com.rest.auth.Application.service;

import com.rest.auth.Application.vo.ResponseVO;
import com.rest.auth.Application.vo.SignInRequestVO;
import com.rest.auth.Application.vo.SignupRequestVO;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    ResponseVO register(SignupRequestVO ivo);

    ResponseVO authenticate(SignInRequestVO ivo);
}
