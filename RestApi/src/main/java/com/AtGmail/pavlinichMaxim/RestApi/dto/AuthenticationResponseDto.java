package com.AtGmail.pavlinichMaxim.RestApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponseDto {
    private String username;
    private String token;
}
