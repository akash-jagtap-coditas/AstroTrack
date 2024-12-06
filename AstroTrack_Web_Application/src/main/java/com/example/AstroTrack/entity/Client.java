package com.example.AstroTrack.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * Entity class representing a client.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    /**
     * The unique identifier of the client.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the client.
     */
    private String name;

    /**
     * The date and time of birth of the client.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAndTimeOfBirth;

    /**
     * The age of the client.
     */
    private int age;

    /**
     * The place of birth of the client.
     */
    private String placeOfBirth;

    /**
     * The phone number of the client.
     */
    private String phoneNumber;

    /**
     * The image of the client.
     */
    @Lob
    private byte[] image;

    /**
     * Indicates whether the client is deleted.
     */
    private boolean isDeleted;

    /**
     * The list of consultations for the client.
     */
    @JsonManagedReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Consultation> consultations;

    /**
     * Constructor for creating a Client entity with specific fields.
     *
     * @param name the name of the client
     * @param age the age of the client
     * @param dateAndTimeOfBirth the date and time of birth of the client
     * @param placeOfBirth the place of birth of the client
     * @param phoneNumber the phone number of the client
     * @param image the image of the client
     */
    public Client(String name, int age, Date dateAndTimeOfBirth, String placeOfBirth, String phoneNumber, byte[] image) {
        this.name = name;
        this.dateAndTimeOfBirth = dateAndTimeOfBirth;
        this.age = age;
        this.placeOfBirth = placeOfBirth;
        this.phoneNumber = phoneNumber;
        this.image = image;
    }

    /**
     * Getter for isDeleted field.
     *
     * @return true if the client is deleted, false otherwise
     */
    public boolean getIsDeleted() {
        return isDeleted;
    }
}
