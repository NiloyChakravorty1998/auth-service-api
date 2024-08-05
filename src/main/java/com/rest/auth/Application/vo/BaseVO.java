package com.rest.auth.Application.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO {
    private String requestId;
    private String responseId;
}
