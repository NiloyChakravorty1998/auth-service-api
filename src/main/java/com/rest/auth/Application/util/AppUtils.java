package com.rest.auth.Application.util;

import com.rest.auth.Application.entity.Role;
import com.rest.auth.Application.entity.UserEntity;
import com.rest.auth.Application.vo.SignupRequestVO;
import com.rest.auth.Application.vo.UserResponseVO;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AppUtils {
    private final PasswordEncoder pwEncoder;

    public  UserEntity convertToEntity(SignupRequestVO vo) {
        UserEntity entity = new UserEntity();
        entity.setEmail(vo.getEmail());
        entity.setFirstName(vo.getFirstName());
        entity.setLastName(vo.getLastName());
        entity.setUserName(vo.getUserName());
        entity.setRole(Role.USER);
        entity.setPassword(pwEncoder.encode(
                vo.getPassword()
                )
        );
        return entity;
    }
    public  UserResponseVO convertToUserResponseVO(UserEntity entity) {
        UserResponseVO ovo = new UserResponseVO();
        ovo.setEmail(entity.getEmail());
        ovo.setUserId(entity.getUserId());
        ovo.setFirstName(entity.getFirstName());
        ovo.setLastName(entity.getLastName());
        ovo.setUserName(entity.getUserName());
        ovo.setRole(Role.USER);
        return ovo;
    }

    public  String generateResponseId() {
        UUID id = new UUID(4 ,4);
        return "res" +id.toString();
    }
}

