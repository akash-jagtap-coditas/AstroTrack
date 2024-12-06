package com.example.AstroTrack.mapper;

import com.example.AstroTrack.dto.ConsultationDto;
import com.example.AstroTrack.entity.Consultation;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Mapper class for converting between Consultation entities and DTOs.
 */
@Component
public class ConsultationsMapper {

    /**
     * Maps a Consultation entity to a ConsultationDto.
     *
     * @param consultation the Consultation entity to map
     * @return the mapped ConsultationDto
     */
    public static ConsultationDto mapToConsultationDto(Optional<Consultation> consultation) {
        return new ConsultationDto(
                consultation.get().getId(),
                consultation.get().getConsultationDate(),
                consultation.get().getNotes(),
                consultation.get().getPrice(),
                consultation.get().getDueAmount(),
                consultation.get().getNextAppointment()
        );
    }

    /**
     * Maps a ConsultationDto to a Consultation entity.
     *
     * @param consultationDto the ConsultationDto to map
     * @return the mapped Consultation entity
     */
    public static Consultation mapToConsultation(ConsultationDto consultationDto) {
        return new Consultation(
                consultationDto.getConsultationDate(),
                consultationDto.getNotes(),
                consultationDto.getPrice(),
                consultationDto.getDueAmount(),
                consultationDto.getNextAppointment()
        );
    }
}
