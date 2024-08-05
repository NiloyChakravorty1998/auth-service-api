package com.rest.auth.Application.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignInRequestVO extends BaseVO{
    private String email;
    private String password;
}
