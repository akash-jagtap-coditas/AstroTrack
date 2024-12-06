package com.example.AstroTrack.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) for viewing all clients.
 */
@Getter
@Setter
@NoArgsConstructor
public class ViewAllClientsDto {

    /**
     * The unique identifier of the client.
     */
    Long id;

    /**
     * The name of the client.
     */
    String name;

    /**
     * The phone number of the client.
     */
    String phoneNumber;

    /**
     * Constructor for ViewAllClientsDto with specific fields.
     *
     * @param id the unique identifier of the client
     * @param name the name of the client
     * @param phoneNumber the phone number of the client
     */
    public ViewAllClientsDto(Long id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
