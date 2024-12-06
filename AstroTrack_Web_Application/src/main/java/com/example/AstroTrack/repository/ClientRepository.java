package com.example.AstroTrack.repository;

import com.example.AstroTrack.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * Repository interface for Client entities.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Finds a client by their ID, ensuring the client is not marked as deleted.
     *
     * @param id the ID of the client to find
     * @return an Optional containing the found client, or empty if no client is found
     */
    @Query(value = "SELECT * FROM client WHERE id = :id AND is_deleted = false", nativeQuery = true)
    Optional<Client> findById(Long id);
}
