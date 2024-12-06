package com.example.AstroTrack.dto;

import com.example.AstroTrack.entity.Client;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Data Transfer Object for Consultation.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDto {

    /**
     * The unique identifier of the consultation.
     */
    Long id;

    /**
     * The date of the consultation.
     */
    @NotNull(message = "Consultation date cannot be null")
    Date consultationDate;

    /**
     * Notes taken during the consultation.
     */
    @NotNull(message = "Notes cannot be null")
    String notes;

    /**
     * The price of the consultation.
     */
    @NotNull(message = "Price cannot be null")
    Double price;

    /**
     * The due amount for the consultation.
     */
    @NotNull(message = "Due amount cannot be null")
    Double dueAmount;

    /**
     * The date of the next appointment.
     */
    Date nextAppointment;
}
