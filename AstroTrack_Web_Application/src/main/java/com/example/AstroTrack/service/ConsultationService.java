package com.example.AstroTrack.service;

import com.example.AstroTrack.dto.ConsultationDto;
import com.example.AstroTrack.dto.FinancialReportDto;

import java.time.LocalDate;
import java.util.List;

/**
 * Service interface for managing consultations.
 */
public interface ConsultationService {

        /**
         * Creates a new consultation for a specific client.
         *
         * @param consultationDto the consultation data transfer object
         * @param clientId the ID of the client
         * @return the created consultation data transfer object
         */
        public ConsultationDto createConsultation(ConsultationDto consultationDto, Long clientId);

        /**
         * Retrieves all consultations for a specific client.
         *
         * @param id the client ID
         * @return a list of consultation data transfer objects
         */
        public List<ConsultationDto> getAllConsultations(Long id);

        /**
         * Retrieves a specific consultation by ID.
         *
         * @param id the consultation ID
         * @return the consultation data transfer object
         */
        public ConsultationDto getConsultation(Long id);

        /**
         * Updates a specific consultation by ID.
         *
         * @param id the consultation ID
         * @param consultationDto the consultation data transfer object
         * @return the updated consultation data transfer object
         */
        public ConsultationDto updateConsultation(Long id, ConsultationDto consultationDto);

        /**
         * Deletes a specific consultation by ID.
         *
         * @param id the consultation ID
         */
        public void deleteConsultation(Long id);

        /**
         * Retrieves the financial report for a specific date.
         *
         * @param date the date for the financial report
         * @return the financial report data transfer object
         */
        public FinancialReportDto getFinancialReport(LocalDate date);
}
