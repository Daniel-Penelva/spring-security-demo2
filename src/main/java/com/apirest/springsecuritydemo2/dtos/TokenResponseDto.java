package com.apirest.springsecuritydemo2.dtos;

import lombok.Builder;

@Builder
public record TokenResponseDto(String token, String refreshToken) {
}