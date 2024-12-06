package com.example.AstroTrack.service.Impl;

import com.example.AstroTrack.dto.ClientDto;
import com.example.AstroTrack.dto.ViewAllClientsDto;
import com.example.AstroTrack.entity.Client;
import com.example.AstroTrack.entity.Consultation;
import com.example.AstroTrack.exception.ClientNotFoundException;
import com.example.AstroTrack.mapper.ClientMapper;
import com.example.AstroTrack.repository.ClientRepository;
import com.example.AstroTrack.repository.ClientRepositoryPagination;
import com.example.AstroTrack.repository.ConsultationPaginationRepository;
import com.example.AstroTrack.repository.ConsultationRepository;
import com.example.AstroTrack.service.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service implementation for managing clients.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientMapper clientMapper;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ConsultationRepository consultationRepository;

    @Autowired
    ConsultationPaginationRepository consultationPaginationRepository;

    @Autowired
    ClientRepositoryPagination clientRepositoryPagination;

    /**
     * Creates a new client.
     *
     * @param clientDto the client data transfer object
     * @return the created client data transfer object
     */
    public ClientDto createClient(ClientDto clientDto) {
        Client client = clientMapper.mapToClient(clientDto);
        client.setDeleted(false);
        return clientMapper.mapToClientDto(clientRepository.save(client), 0.00, null);
    }

    /**
     * Creates a new client with an image.
     *
     * @param clientDto the client data transfer object
     * @param image the image file
     * @return the created client data transfer object
     * @throws IOException if an I/O error occurs
     */
    public ClientDto createClient(ClientDto clientDto, MultipartFile image) throws IOException {
        Client client = clientMapper.mapToClient(clientDto);
        client.setImage(image.getBytes());
        client.setDeleted(false);
        return clientMapper.mapToClientDto(clientRepository.save(client), 0.00, null);
    }

    /**
     * Retrieves all clients.
     *
     * @return a list of all clients
     */
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream().map(client -> clientMapper.mapToClientDto(client)).collect(Collectors.toList());
    }

    /**
     * Retrieves a paginated list of all clients, optionally sorted by name.
     *
     * @param pageNo the page number
     * @param sort the sort criteria
     * @return a list of view all clients DTOs
     */
    @Transactional
    public List<ViewAllClientsDto> viewAllClients(int pageNo, String sort) {
        PageRequest pageRequest = PageRequest.of(pageNo, 10);
        Page<Client> clientPage = clientRepositoryPagination.findAll(pageRequest);
        if (sort.equalsIgnoreCase("name")) {
            clientPage = clientRepositoryPagination.findAllSortedByName(pageRequest);
        }
        List<ViewAllClientsDto> clientsDto = clientPage.getContent().stream()
                .map(client -> clientMapper.mapToViewAllClientsDto(client))
                .collect(Collectors.toList());
        return clientsDto;
    }

    /**
     * Retrieves a paginated list of clients filtered by name.
     *
     * @param pageNo the page number
     * @param filter the name filter
     * @return a list of view all clients DTOs
     */
    @Transactional
    public List<ViewAllClientsDto> filterClients(int pageNo, String filter) {
        PageRequest pageRequest = PageRequest.of(pageNo, 10);
        Page<Client> clientPage = clientRepositoryPagination.findByName(filter, pageRequest);
        List<ViewAllClientsDto> clientsDto = clientPage.getContent().stream()
                .map(client -> clientMapper.mapToViewAllClientsDto(client))
                .collect(Collectors.toList());
        return clientsDto;
    }

    /**
     * Updates a client's information.
     *
     * @param id the client ID
     * @param clientDto the client data transfer object
     * @return the updated client data transfer object
     */
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Optional<Client> existingClient = Optional.ofNullable(clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " not found!")));
        if (existingClient.isPresent()) {
            existingClient.get().setName(clientDto.getName());
            existingClient.get().setAge(clientDto.getAge());
            existingClient.get().setDateAndTimeOfBirth(clientDto.getDateAndTimeOfBirth());
            existingClient.get().setPlaceOfBirth(clientDto.getPlaceOfBirth());
            existingClient.get().setPhoneNumber(clientDto.getPhoneNumber());
        } else {
            throw new ClientNotFoundException("Client with id " + id + " not found!");
        }
        return clientMapper.mapToClientDto(clientRepository.save(existingClient.get()), 0.00, existingClient.get().getConsultations());
    }

    /**
     * Updates a client's information with an image.
     *
     * @param id the client ID
     * @param clientDto the client data transfer object
     * @param image the image file
     * @return the updated client data transfer object
     * @throws IOException if an I/O error occurs
     */
    public ClientDto updateClient(Long id, ClientDto clientDto, MultipartFile image) throws IOException {
        Optional<Client> existingClient = Optional.ofNullable(clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " not found!")));
        if (existingClient.isPresent()) {
            existingClient.get().setName(clientDto.getName());
            existingClient.get().setAge(clientDto.getAge());
            existingClient.get().setDateAndTimeOfBirth(clientDto.getDateAndTimeOfBirth());
            existingClient.get().setPlaceOfBirth(clientDto.getPlaceOfBirth());
            existingClient.get().setPhoneNumber(clientDto.getPhoneNumber());
            existingClient.get().setImage(image.getBytes());
        } else {
            throw new ClientNotFoundException("Client with id " + id + " not found!");
        }
        return clientMapper.mapToClientDto(clientRepository.save(existingClient.get()), 0.00, existingClient.get().getConsultations());
    }

    /**
     * Deletes a client by setting the isDeleted flag to true.
     *
     * @param id the client ID
     */
    public void deleteClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty()) {
            throw new ClientNotFoundException("Client with id " + id + " not found!");
        } else {
            client.get().setDeleted(true);
        }
        clientRepository.save(client.get());
    }

    /**
     * Retrieves a client by ID with paginated consultations.
     *
     * @param id the client ID
     * @param page the page number
     * @return the client data transfer object
     */
    public ClientDto getClientById(Long id, int page) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " not found!"));
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<Consultation> consultationPage = consultationPaginationRepository.findByClientId(id, pageRequest);
        List<Consultation> consultations = consultationPage.getContent();
        Double dueAmount = consultationRepository.findDueAmountsByClientId(id).stream()
                .mapToDouble(Double::doubleValue).sum();
        ClientDto clientDto = clientMapper.mapToClientDto(client, dueAmount, consultations);
        return clientDto;
    }
}
