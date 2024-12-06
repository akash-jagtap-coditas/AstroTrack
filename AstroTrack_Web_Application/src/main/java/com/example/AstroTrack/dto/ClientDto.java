package com.example.AstroTrack.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Data Transfer Object for Client.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    /**
     * The unique identifier of the client.
     */
    Long id;

    /**
     * The name of the client.
     */
    @NotNull(message = "Name cannot be null")
    String name;

    /**
     * The date and time of birth of the client.
     */

    @NotNull(message = "Date and time of birth cannot be null")
    Date dateAndTimeOfBirth;

    /**
     * The age of the client.
     */
    @NotNull(message = "Age cannot be null")
    int age;

    /**
     * The place of birth of the client.
     */
    @NotNull(message = "Place of birth cannot be null")
    String placeOfBirth;

    /**
     * The phone number of the client.
     */
    @NotNull(message = "Phone number cannot be null")
    @Size(min = 10, max = 10, message = "Phone number should be of 10 digits.")
    String phoneNumber;

    /**
     * The image of the client.
     */
    private byte[] image;

    /**
     * The list of consultations for the client.
     */
    List<ConsultationDto> consultations;

    /**
     * The balance amount for the client.
     */
    Double balance;

    /**
     * Constructor for ClientDto with specific fields.
     *
     * @param id the unique identifier of the client
     * @param name the name of the client
     * @param age the age of the client
     * @param dateAndTimeOfBirth the date and time of birth of the client
     * @param placeOfBirth the place of birth of the client
     * @param phoneNumber the phone number of the client
     * @param image the image of the client
     * @param consultations the list of consultations for the client
     * @param balance the balance amount for the client
     */
    public ClientDto(Long id, String name, int age, Date dateAndTimeOfBirth, String placeOfBirth, String phoneNumber, byte[] image, List<ConsultationDto> consultations, Double balance) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dateAndTimeOfBirth = dateAndTimeOfBirth;
        this.placeOfBirth = placeOfBirth;
        this.phoneNumber = phoneNumber;
        this.image = image;
        this.balance = balance;
        this.consultations = consultations;
    }
}
