package com.rest.auth.Application.vo;

import com.rest.auth.Application.entity.Role;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class SignupRequestVO extends BaseVO{
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Role role;
}
