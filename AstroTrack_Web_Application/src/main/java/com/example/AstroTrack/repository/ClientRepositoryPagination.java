package com.example.AstroTrack.repository;

import com.example.AstroTrack.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for paginated and sorted client entities.
 */
public interface ClientRepositoryPagination extends PagingAndSortingRepository<Client, Long> {

    /**
     * Finds all clients, sorted by name in ascending order, where is_deleted is false.
     *
     * @param pageable the pagination information
     * @return a page of clients sorted by name
     */
    @Query(value = "SELECT * FROM client WHERE is_deleted = false ORDER BY name ASC", nativeQuery = true)
    Page<Client> findAllSortedByName(Pageable pageable);

    /**
     * Finds clients by name where is_deleted is false.
     *
     * @param name the name of the client to find
     * @param pageable the pagination information
     * @return a page of clients with the specified name
     */
    @Query(value = "SELECT * FROM client WHERE LOWER(name) LIKE LOWER(CONCAT('%', :name, '%')) AND is_deleted = false", nativeQuery = true)
    Page<Client> findByName(@Param("name") String name, Pageable pageable);

    /**
     * Finds all clients where is_deleted is false.
     *
     * @param pageable the pagination information
     * @return a page of all clients
     */
    @Query(value = "SELECT * FROM client WHERE is_deleted = false", nativeQuery = true)
    Page<Client> findAll(Pageable pageable);
}
