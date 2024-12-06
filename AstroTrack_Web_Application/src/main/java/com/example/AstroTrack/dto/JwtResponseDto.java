package com.example.AstroTrack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for JWT responses.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDto {

    /**
     * The JWT access token.
     */
    private String accessToken;

    /**
     * The JWT refresh token.
     */
    private String refreshToken;
}
