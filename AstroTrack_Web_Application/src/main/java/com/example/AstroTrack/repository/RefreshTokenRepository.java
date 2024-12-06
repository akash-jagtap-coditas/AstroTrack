package com.example.AstroTrack.repository;

import com.example.AstroTrack.entity.RefreshToken;
import com.example.AstroTrack.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for RefreshToken entities.
 */
@Repository
public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Integer> {

    /**
     * Finds a refresh token by its token string.
     *
     * @param token the token string to search for
     * @return an Optional containing the found refresh token, or empty if no token is found
     */
    Optional<RefreshToken> findByToken(String token);

    /**
     * Finds a refresh token by user information.
     *
     * @param userInfo the user information to search for
     * @return the found refresh token
     */
    RefreshToken findByUserInfo(Users userInfo);
}
