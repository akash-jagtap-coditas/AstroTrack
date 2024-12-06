package com.example.AstroTrack.controller;

import com.example.AstroTrack.dto.AuthRequestDto;
import com.example.AstroTrack.dto.JwtResponseDto;
import com.example.AstroTrack.dto.RefreshTokenDto;
import com.example.AstroTrack.dto.RefreshTokenRequestDto;
import com.example.AstroTrack.entity.RefreshToken;
import com.example.AstroTrack.service.Impl.JwtServiceImpl;
import com.example.AstroTrack.service.Impl.RefreshTokenServiceImpl;
import com.example.AstroTrack.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Controller to handle authentication requests.
 */
@RestController
@RequestMapping("/astrotrack/auth")
@Tag(name = "Authentication", description = "APIs for user authentication and token management")
public class AuthController {

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenServiceImpl refreshTokenServiceImpl;

    @Autowired
    private TokenService tokenService;

    /**
     * Authenticates the user and generates a JWT token.
     *
     * @param authRequestDTO the authentication request containing username and password
     * @return a response containing the JWT access token and refresh token
     * @throws UsernameNotFoundException if the authentication request is invalid
     */
    @Operation(summary = "Authenticate user", description = "Authenticates the user and generates a JWT token.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    public JwtResponseDto authenticateAndGetToken(@RequestBody AuthRequestDto authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        Optional<RefreshToken> refreshToken = refreshTokenServiceImpl.createRefreshToken(authRequestDTO.getUsername());

        if (authentication.isAuthenticated()) {
            return JwtResponseDto.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                    .refreshToken(refreshToken.get().getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("Invalid user request..!!");
        }
    }

    /**
     * Generates a new access token using the provided refresh token.
     *
     * @param refreshTokenRequestDTO the refresh token request containing the refresh token
     * @return a response containing the new access token
     * @throws RuntimeException if the refresh token is not found in the database
     */
    @Operation(summary = "Refresh access token", description = "Generates a new access token using the provided refresh token.")
    @PostMapping("/refreshToken")
    public RefreshTokenDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO) {
        return refreshTokenServiceImpl.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenServiceImpl::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    return new RefreshTokenDto(accessToken);
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
    }

    /**
     * Logs out the user by deleting the given refresh token.
     *
     * @param refreshTokenRequestDTO the refresh token to delete
     * @return a response indicating the logout was successful
     */
    @Operation(summary = "Logout user", description = "Logs out the user by deleting the given refresh token.")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequestDto refreshTokenRequestDTO) {
        try {
            refreshTokenServiceImpl.deleteToken(refreshTokenRequestDTO);
            return ResponseEntity.ok("Logout successful. Refresh Token deleted.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid token.");
        }
    }
}
