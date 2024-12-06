package com.example.AstroTrack.controller;

import com.example.AstroTrack.dto.ApiResponse;
import com.example.AstroTrack.dto.ClientDto;
import com.example.AstroTrack.dto.ViewAllClientsDto;
import com.example.AstroTrack.service.Impl.ClientServiceImpl;
import com.example.AstroTrack.utils.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Controller to handle client-related requests.
 */
@RestController
@RequestMapping("/astrotrack/client")
@Tag(name = "Client", description = "APIs for managing clients")
public class ClientController {

    @Autowired
    ClientServiceImpl clientServiceImpl;

    @Autowired
    private Validator validator;

    /**
     * Retrieves a client by ID with pagination for consultations.
     *
     * @param id the ID of the client to retrieve
     * @param pageNo the page number for pagination
     * @return a response containing the client data
     */
    @Operation(summary = "Get client by ID with consultation", description = "Retrieves a client by ID with its consultation with pagination.")
    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse<ClientDto>> getClient(@PathVariable Long id, @RequestParam int pageNo) {
        ClientDto clientDto = clientServiceImpl.getClientById(id, pageNo);
        return ResponseUtil.success(clientDto, "Client with id " + id + " retrieved successfully!");
    }

    /**
     * Creates a new client.
     *
     * @param clientData the client data as a JSON string
     * @param image the client's image file
     * @return a response containing the created client data
     * @throws IOException if an input-output error occurs
     */
    @Operation(summary = "Create client", description = "Creates a new client.")
    @PostMapping
    public ResponseEntity<ApiResponse<ClientDto>> createClient(@Valid @RequestParam("client") String clientData, @RequestParam("image") MultipartFile image) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClientDto clientDto = objectMapper.readValue(clientData, ClientDto.class);

        Set<ConstraintViolation<ClientDto>> violations = validator.validate(clientDto);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<ClientDto> violation : violations) {
                sb.append(violation.getMessage()).append("; ");
            }
            return ResponseUtil.error(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        ClientDto clientDtoResponse = clientServiceImpl.createClient(clientDto, image);
        return ResponseUtil.success(clientDtoResponse, "Client created successfully!");
    }

    /**
     * Retrieves the image of a client by ID.
     *
     * @param id the ID of the client
     * @return a response containing the client's image as a byte array
     */
    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getClientImage(@PathVariable Long id) {
        ClientDto clientOptional = clientServiceImpl.getClientById(id, 0);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + clientOptional.getName() + "-image.jpg\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(clientOptional.getImage());
    }

    /**
     * Retrieves a paginated and sorted list of all clients.
     *
     * @param pageNo the page number for pagination
     * @param sort the sorting criteria
     * @return a response containing the paginated and sorted list of clients
     */
    @Operation(summary = "View all clients", description = "Retrieves a paginated and sorted list of all clients.")
    @GetMapping("/viewAllClients/{pageNo}")
    public ResponseEntity<ApiResponse<List<ViewAllClientsDto>>> viewAllClients(@PathVariable int pageNo, @RequestParam String sort) {
        List<ViewAllClientsDto> viewAllClientsDtos = clientServiceImpl.viewAllClients(pageNo, sort);
        return ResponseUtil.success(viewAllClientsDtos, "All clients retrieved successfully");
    }

    /**
     * Filters clients based on the provided criteria.
     *
     * @param pageNo the page number for pagination
     * @param filterName the filter criteria
     * @return a response containing the filtered list of clients
     */
    @Operation(summary = "Filter clients", description = "Filters clients based on the provided criteria.")
    @GetMapping("/filter")
    public ResponseEntity<ApiResponse<List<ViewAllClientsDto>>> filterClients(@RequestParam int pageNo, @RequestParam String filterName) {
        List<ViewAllClientsDto> viewAllClientsDtos = clientServiceImpl.filterClients(pageNo, filterName);
        return ResponseUtil.success(viewAllClientsDtos, "Clients filtered successfully!");
    }

    /**
     * Updates a client by ID.
     *
     * @param clientData the client data as a JSON string
     * @param image the client's image file
     * @param id the ID of the client to update
     * @return a response containing the updated client data
     * @throws IOException if an input-output error occurs
     */
    @Operation(summary = "Update client", description = "Updates a client by ID.")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ClientDto>> updateClient(@RequestParam("client") String clientData, @RequestParam("image") MultipartFile image, @PathVariable Long id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ClientDto clientDto = objectMapper.readValue(clientData, ClientDto.class);

        Set<ConstraintViolation<ClientDto>> violations = validator.validate(clientDto);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<ClientDto> violation : violations) {
                sb.append(violation.getMessage()).append("/n ");
            }
            return ResponseUtil.error(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        ClientDto clientDtoResponse = clientServiceImpl.updateClient(id, clientDto, image);
        return ResponseUtil.success(clientDtoResponse, "Client updated successfully!");
    }

    /**
     * Deletes a client by ID.
     *
     * @param id the ID of the client to delete
     * @return a response confirming the deletion
     */
    @Operation(summary = "Delete client", description = "Deletes a client by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Long>> deleteClient(@PathVariable Long id) {
        clientServiceImpl.deleteClient(id);
        return ResponseUtil.delete(id, "Client with id " + id + " deleted successfully!");
    }
}
