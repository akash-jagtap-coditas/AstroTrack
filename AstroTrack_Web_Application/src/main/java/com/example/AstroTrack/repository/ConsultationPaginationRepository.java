package com.example.AstroTrack.repository;

import com.example.AstroTrack.entity.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for paginated and sorted consultation entities.
 */
@Repository
public interface ConsultationPaginationRepository extends PagingAndSortingRepository<Consultation, Long> {

    /**
     * Finds consultations by client ID with pagination.
     *
     * @param clientId the ID of the client whose consultations are to be found
     * @param pageable the pagination information
     * @return a page of consultations for the specified client
     */
    @Query("SELECT c FROM Consultation c WHERE c.client.id = :clientId")
    Page<Consultation> findByClientId(Long clientId, Pageable pageable);
}
