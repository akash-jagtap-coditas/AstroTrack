package com.example.AstroTrack.service.Impl;

import static com.example.AstroTrack.utils.MonthUtil.*;
import static com.example.AstroTrack.mapper.ConsultationsMapper.*;
import com.example.AstroTrack.dto.ConsultationDto;
import com.example.AstroTrack.dto.FinancialReportDto;
import com.example.AstroTrack.entity.Client;
import com.example.AstroTrack.entity.Consultation;
import com.example.AstroTrack.exception.ConsultationNotFoundException;
import com.example.AstroTrack.mapper.ConsultationsMapper;
import com.example.AstroTrack.repository.ClientRepository;
import com.example.AstroTrack.repository.ConsultationRepository;
import com.example.AstroTrack.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing consultations.
 */
@Service
public class ConsultationServiceImpl implements ConsultationService {

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ConsultationsMapper consultationsMapper;

    /**
     * Creates a new consultation for a specific client.
     *
     * @param consultationDto the consultation data transfer object
     * @param clientId the ID of the client
     * @return the created consultation data transfer object
     */
    public ConsultationDto createConsultation(ConsultationDto consultationDto, Long clientId) {
        Optional<Client> existingClient = clientRepository.findById(clientId);
        Consultation consultation = mapToConsultation(consultationDto);
        consultation.setClient(existingClient.get());
        consultation.setDeleted(false);
        return consultationsMapper.mapToConsultationDto(Optional.of(consultationRepository.save(consultation)));
    }

    /**
     * Retrieves all consultations for a specific client.
     *
     * @param id the client ID
     * @return a list of consultation data transfer objects
     */
    public List<ConsultationDto> getAllConsultations(Long id) {
        Optional<Client> existingClient = clientRepository.findById(id);
        List<ConsultationDto> consultationDtoList = consultationRepository.findAllByClient(existingClient.get().getId()).stream()
                .map(consultation -> mapToConsultationDto(Optional.ofNullable(consultation)))
                .collect(Collectors.toList());
        return consultationDtoList;
    }

    /**
     * Retrieves a specific consultation by ID.
     *
     * @param id the consultation ID
     * @return the consultation data transfer object
     */
    public ConsultationDto getConsultation(Long id) {
        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ConsultationNotFoundException("Consultation with id " + id + " not found!"));
        return mapToConsultationDto(Optional.ofNullable(consultation));
    }

    /**
     * Updates a specific consultation by ID.
     *
     * @param id the consultation ID
     * @param consultationDto the consultation data transfer object
     * @return the updated consultation data transfer object
     */
    public ConsultationDto updateConsultation(Long id, ConsultationDto consultationDto) {
        Optional<Consultation> existingConsultation = consultationRepository.findById(id);
        existingConsultation.get().setNotes(consultationDto.getNotes());
        existingConsultation.get().setConsultationDate(consultationDto.getConsultationDate());
        existingConsultation.get().setPrice(consultationDto.getPrice());
        existingConsultation.get().setDueAmount(consultationDto.getDueAmount());
        existingConsultation.get().setNextAppointment(consultationDto.getNextAppointment());
        return mapToConsultationDto(Optional.of(consultationRepository.save(existingConsultation.get())));
    }

    /**
     * Deletes a specific consultation by setting the isDeleted flag to true.
     *
     * @param id the consultation ID
     */
    public void deleteConsultation(Long id) {
        Optional<Consultation> consultation = Optional.ofNullable(consultationRepository.findById(id)
                .orElseThrow(() -> new ConsultationNotFoundException("Consultation with id " + id + " not found!")));
        if (consultation.isPresent()) {
            consultation.get().setDeleted(true);
            consultationRepository.save(consultation.get());
        }
    }

    /**
     * Retrieves the financial report for a specific date.
     *
     * @param date the date for the financial report
     * @return the financial report data transfer object
     */
    public FinancialReportDto getFinancialReport(LocalDate date) {
        Month month = date.getMonth();
        int m = convertMonthNameToInt(String.valueOf(month));
        int year = date.getYear();
        Double monthlyEarning = consultationRepository.findMonthlyEarnings(m, year);
        Double totalEarning = consultationRepository.findTotalEarnings();
        Double totalDueAmount = consultationRepository.findTotalBalance();
        return new FinancialReportDto(monthlyEarning, totalEarning, totalDueAmount);
    }
}
