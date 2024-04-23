package com.apirest.springsecuritydemo2.dtos;

public record TokenResponseDto(String token, String refreshToken) {
}