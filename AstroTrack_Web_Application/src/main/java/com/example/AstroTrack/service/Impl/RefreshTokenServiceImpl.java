package com.example.AstroTrack.service.Impl;

import com.example.AstroTrack.dto.RefreshTokenRequestDto;
import com.example.AstroTrack.entity.RefreshToken;
import com.example.AstroTrack.entity.Users;
import com.example.AstroTrack.repository.RefreshTokenRepository;
import com.example.AstroTrack.repository.UserRepository;
import com.example.AstroTrack.service.RefreshTokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service implementation for managing refresh tokens.
 */
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final String SECRET = "4261656C64756E67asvjxhvsdjsjdcsajxvsyuaf2fwuqyf67e276dwutduwqfd72fed12f7fd7621cywqfd";

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    /**
     * Creates a new refresh token for a specific user.
     *
     * @param username the username of the user
     * @return an optional containing the created refresh token
     */
    public Optional<RefreshToken> createRefreshToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30 * 6))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 30 * 6));
        refreshToken.setUserInfo(userRepository.findUserByUsername(username));
        Users existingUser = userRepository.findUserByUsername(username);
        Optional<RefreshToken> existingToken = Optional.ofNullable(refreshTokenRepository.findByUserInfo(existingUser));
        if (!existingToken.isEmpty()) {
            return existingToken;
        } else {
            return Optional.of(refreshTokenRepository.save(refreshToken));
        }
    }

    /**
     * Finds a refresh token by its token string.
     *
     * @param token the token string
     * @return an optional containing the found refresh token
     */
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    /**
     * Verifies the expiration date of a refresh token.
     *
     * @param token the refresh token to verify
     * @return the verified refresh token
     * @throws RuntimeException if the token is expired
     */
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(new Date(System.currentTimeMillis())) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }

    /**
     * Deletes a refresh token.
     *
     * @param token the refresh token to delete
     * @throws RuntimeException if the token is not found
     */
    public void deleteToken(RefreshTokenRequestDto token) {
        try {
            Optional<RefreshToken> refreshToken = refreshTokenRepository.findByToken(token.getToken());
            refreshTokenRepository.delete(refreshToken.get());
        } catch (Exception e) {
            throw new RuntimeException("Token not found");
        }
    }
}
