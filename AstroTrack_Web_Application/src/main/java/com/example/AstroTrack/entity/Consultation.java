package com.example.AstroTrack.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Entity class representing a consultation.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Consultation {

    /**
     * The unique identifier of the consultation.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The date of the consultation.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date consultationDate;

    /**
     * Notes taken during the consultation.
     */
    private String notes;

    /**
     * The price of the consultation.
     */
    private Double price;

    /**
     * The due amount for the consultation.
     */
    private Double dueAmount;

    /**
     * The date of the next appointment.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextAppointment;

    /**
     * The client associated with the consultation.
     */
    @JsonBackReference
    @ManyToOne
    private Client client;

    /**
     * Indicates whether the consultation is deleted.
     */
    private boolean isDeleted;

    /**
     * Constructor for creating a Consultation entity with specific fields.
     *
     * @param consultationDate the date of the consultation
     * @param notes the notes taken during the consultation
     * @param price the price of the consultation
     * @param dueAmount the due amount for the consultation
     * @param nextAppointment the date of the next appointment
     */
    public Consultation(Date consultationDate, String notes, Double price, Double dueAmount, Date nextAppointment) {
        this.consultationDate = consultationDate;
        this.notes = notes;
        this.price = price;
        this.dueAmount = dueAmount;
        this.nextAppointment = nextAppointment;
    }
}
