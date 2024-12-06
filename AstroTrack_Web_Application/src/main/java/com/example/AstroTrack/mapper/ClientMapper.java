package com.example.AstroTrack.mapper;

import static com.example.AstroTrack.mapper.ConsultationsMapper.*;
import com.example.AstroTrack.dto.ClientDto;
import com.example.AstroTrack.dto.ConsultationDto;
import com.example.AstroTrack.dto.UpdateClientDto;
import com.example.AstroTrack.dto.ViewAllClientsDto;
import com.example.AstroTrack.entity.Client;
import com.example.AstroTrack.entity.Consultation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Mapper class for converting between Client entities and DTOs.
 */
@Component
public class ClientMapper {

    /**
     * Maps a Client entity to a ClientDto.
     *
     * @param client the Client entity to map
     * @param dueAmount the due amount to set in the ClientDto
     * @param consultations the list of consultations to set in the ClientDto
     * @return the mapped ClientDto
     */
    public ClientDto mapToClientDto(Client client, Double dueAmount, List<Consultation> consultations) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setDateAndTimeOfBirth(client.getDateAndTimeOfBirth());
        clientDto.setAge(client.getAge());
        clientDto.setPlaceOfBirth(client.getPlaceOfBirth());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setImage(client.getImage());
        clientDto.setBalance(dueAmount);
        if (client.getConsultations() != null && !client.getConsultations().isEmpty()) {
            List<ConsultationDto> consultationDtoList = consultations.stream()
                    .map(consultation -> mapToConsultationDto(Optional.ofNullable(consultation)))
                    .collect(Collectors.toList());
            clientDto.setConsultations(consultationDtoList);
        }
        return clientDto;
    }

    /**
     * Maps a Client entity to a ClientDto.
     *
     * @param client the Client entity to map
     * @return the mapped ClientDto
     */
    public ClientDto mapToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setDateAndTimeOfBirth(client.getDateAndTimeOfBirth());
        clientDto.setAge(client.getAge());
        clientDto.setPlaceOfBirth(client.getPlaceOfBirth());
        clientDto.setPhoneNumber(client.getPhoneNumber());
        clientDto.setImage(client.getImage());
        return clientDto;
    }

    /**
     * Maps a ClientDto to a Client entity.
     *
     * @param clientDto the ClientDto to map
     * @return the mapped Client entity
     */
    public Client mapToClient(ClientDto clientDto) {
        return new Client(clientDto.getName(), clientDto.getAge(), clientDto.getDateAndTimeOfBirth(),
                clientDto.getPlaceOfBirth(), clientDto.getPhoneNumber(), clientDto.getImage());
    }

    /**
     * Maps a Client entity to a ViewAllClientsDto.
     *
     * @param client the Client entity to map
     * @return the mapped ViewAllClientsDto
     */
    public ViewAllClientsDto mapToViewAllClientsDto(Client client) {
        return new ViewAllClientsDto(client.getId(), client.getName(), client.getPhoneNumber());
    }

    /**
     * Maps a Client entity to an UpdateClientDto.
     *
     * @param client the Client entity to map
     * @return the mapped UpdateClientDto
     */
    private UpdateClientDto mapToUpdateClientDto(Client client) {
        return new UpdateClientDto(client.getId(), client.getName(), client.getAge(),
                client.getDateAndTimeOfBirth(), client.getPlaceOfBirth(), client.getPhoneNumber(), client.getImage());
    }
}
