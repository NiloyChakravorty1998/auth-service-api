package com.rest.auth.Application.service.impl;

import com.rest.auth.Application.dao.repository.UserEntityRepository;
import com.rest.auth.Application.entity.UserEntity;
import com.rest.auth.Application.jwt.JWTService;
import com.rest.auth.Application.service.AuthenticationService;
import com.rest.auth.Application.util.AppUtils;
import com.rest.auth.Application.vo.ResponseVO;
import com.rest.auth.Application.vo.SignInRequestVO;
import com.rest.auth.Application.vo.SignupRequestVO;
import com.rest.auth.Application.vo.UserResponseVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserEntityRepository userRepo;
    private final AppUtils utils;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public ResponseVO register(SignupRequestVO ivo) {
        UserEntity entity = utils.convertToEntity(ivo);
        if(userRepo.findByEmail(entity.getEmail()).isPresent()){
            return new ResponseVO(
                    null, new RuntimeException("User already in the database with same email")
            );
        }
        UserEntity savedEntity = userRepo.save(entity);
        String jwtToken = jwtService.generateToken(new HashMap<>(),savedEntity);
        UserResponseVO uvo = utils.convertToUserResponseVO(savedEntity);
        ResponseVO ovo = new ResponseVO(uvo,null,jwtToken);
        ovo.setResponseId(utils.generateResponseId());
        return ovo;
    }
    @Override
    public ResponseVO authenticate(SignInRequestVO ivo) {
        log.info("Authenticate service : " + ivo.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            ivo.getEmail(), ivo.getPassword()
                    )
            );
        } catch (Exception e) {
            log.error("Authentication failed for user: " + ivo.getEmail(), e);
            return new ResponseVO(
                    null, new RuntimeException("Authentication failed: " + e.getMessage())
            );
        }

        if (userRepo.findByEmail(ivo.getEmail()).isEmpty()) {
            log.info("User not found: " + ivo.getEmail());
            return new ResponseVO(
                    null, new RuntimeException("User does not exist in the database")
            );
        }
        UserEntity entity = userRepo.findByEmail(ivo.getEmail()).get();
        log.info("Generating token for: " + ivo.getEmail());
        String jwtToken = jwtService.generateToken(new HashMap<>(), entity);

        UserResponseVO uvo = utils.convertToUserResponseVO(entity);
        ResponseVO ovo = new ResponseVO(uvo, null, jwtToken);
        ovo.setResponseId(utils.generateResponseId());
        return ovo;
    }
}
