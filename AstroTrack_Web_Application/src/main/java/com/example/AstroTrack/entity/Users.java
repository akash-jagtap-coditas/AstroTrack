package com.example.AstroTrack.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class representing a user.
 */
@Getter
@Setter
@Entity
public class Users {

    /**
     * The unique identifier of the user.
     */
    @Id
    private Long id;

    /**
     * The username of the user.
     */
    private String username;

    /**
     * The password of the user.
     */
    private String password;
}
