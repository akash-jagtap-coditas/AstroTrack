package com.example.AstroTrack.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Entity class representing a refresh token.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    /**
     * The unique identifier of the refresh token.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * The refresh token string.
     */
    private String token;

    /**
     * The expiry date of the refresh token.
     */
    private Date expiryDate;

    /**
     * The user associated with the refresh token.
     */
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Users userInfo;
}
