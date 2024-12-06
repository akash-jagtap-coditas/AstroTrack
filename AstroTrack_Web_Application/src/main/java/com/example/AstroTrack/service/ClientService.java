package com.example.AstroTrack.service;

import com.example.AstroTrack.dto.ClientDto;
import com.example.AstroTrack.dto.ViewAllClientsDto;
import com.example.AstroTrack.entity.Client;

import java.util.List;

/**
 * Service interface for managing clients.
 */
public interface ClientService {

        /**
         * Creates a new client.
         *
         * @param clientDto the client data transfer object
         * @return the created client data transfer object
         */
        public ClientDto createClient(ClientDto clientDto);

        /**
         * Retrieves all clients.
         *
         * @return a list of all clients
         */
        public List<ClientDto> getAllClients();

        /**
         * Retrieves a paginated list of clients filtered by name.
         *
         * @param pageNo the page number
         * @param filter the name filter
         * @return a list of view all clients DTOs
         */
        public List<ViewAllClientsDto> filterClients(int pageNo, String filter);

        /**
         * Updates a client's information.
         *
         * @param id the client ID
         * @param clientDto the client data transfer object
         * @return the updated client data transfer object
         */
        public ClientDto updateClient(Long id, ClientDto clientDto);

        /**
         * Deletes a client by ID.
         *
         * @param id the client ID
         */
        public void deleteClient(Long id);

        /**
         * Retrieves a client by ID with paginated consultations.
         *
         * @param id the client ID
         * @param page the page number
         * @return the client data transfer object
         */
        public ClientDto getClientById(Long id, int page);
}
