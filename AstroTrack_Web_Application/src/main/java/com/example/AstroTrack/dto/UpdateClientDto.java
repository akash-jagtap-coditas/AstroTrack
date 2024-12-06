package com.example.AstroTrack.dto;

import com.example.AstroTrack.entity.Consultation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Data Transfer Object (DTO) for updating Client information.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientDto {

    /**
     * The unique identifier of the client.
     */
    private Long id;

    /**
     * The name of the client.
     */
    @NotNull(message = "Name cannot be null")
    private String name;

    /**
     * The date and time of birth of the client.
     */
    @NotNull(message = "Date and time of birth cannot be null")
    private Date dateAndTimeOfBirth;

    /**
     * The age of the client.
     */
    @NotNull(message = "Age cannot be null")
    private int age;

    /**
     * The place of birth of the client.
     */
    @NotNull(message = "Place of birth cannot be null")
    private String placeOfBirth;

    /**
     * The phone number of the client.
     */
    @NotNull(message = "Phone number cannot be null")
    @Size(min = 10, max = 10, message = "Phone number should be of 10 digits")
    private String phoneNumber;

    /**
     * The image of the client.
     */
    private byte[] image;

    /**
     * The list of consultations for the client.
     */
    private List<Consultation> consultations;

    /**
     * Constructor for UpdateClientDto with specific fields.
     *
     * @param id the unique identifier of the client
     * @param name the name of the client
     * @param age the age of the client
     * @param dateOfBirth the date and time of birth of the client
     * @param placeOfBirth the place of birth of the client
     * @param phoneNumber the phone number of the client
     * @param image the image of the client
     */
    public UpdateClientDto(Long id, String name, int age, Date dateOfBirth, String placeOfBirth, String phoneNumber, byte[] image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dateAndTimeOfBirth = dateOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    /**
     * Constructor for UpdateClientDto with specific fields.
     *
     * @param id the unique identifier of the client
     * @param name the name of the client
     * @param age the age of the client
     * @param dateAndTimeOfBirth the date and time of birth of the client
     * @param placeOfBirth the place of birth of the client
     * @param phoneNumber the phone number of the client
     */
    public UpdateClientDto(Long id, String name, int age, Date dateAndTimeOfBirth, String placeOfBirth, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dateAndTimeOfBirth = dateAndTimeOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.phoneNumber = phoneNumber;
    }
}
