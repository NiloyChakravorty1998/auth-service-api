package com.rest.auth.Application.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVO extends BaseVO {
    private Object response;
    private Object exception;
    private String authToken;

    public ResponseVO(Object o, RuntimeException e) {
    }
}
