package com.example.AstroTrack.repository;

import com.example.AstroTrack.entity.Client;
import com.example.AstroTrack.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Consultation entities.
 */
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    /**
     * Finds all consultations for a specific client, ensuring the client is not marked as deleted.
     *
     * @param clientId the ID of the client whose consultations are to be found
     * @return a list of consultations for the specified client
     */
    @Query(value = "SELECT * FROM consultation WHERE client_id = :clientId AND client_id IN (SELECT id FROM client WHERE is_deleted = false)", nativeQuery = true)
    List<Consultation> findAllByClient(@Param("clientId") Long clientId);

    /**
     * Finds all due amounts for a specific client, ensuring the client is not marked as deleted.
     *
     * @param clientId the ID of the client whose due amounts are to be found
     * @return a list of due amounts for the specified client
     */
    @Query(value = "SELECT c.due_amount FROM consultation c WHERE c.client_id = :clientId AND c.client_id IN (SELECT id FROM client WHERE is_deleted = false)", nativeQuery = true)
    List<Double> findDueAmountsByClientId(@Param("clientId") Long clientId);

    /**
     * Finds the total earnings from all consultations, ensuring clients are not marked as deleted.
     *
     * @return the total earnings from all consultations
     */
    @Query(value = "SELECT SUM(c.price) FROM consultation c WHERE c.client_id IN (SELECT id FROM client WHERE is_deleted = false)", nativeQuery = true)
    Double findTotalEarnings();

    /**
     * Finds the total balance from all consultations, ensuring clients are not marked as deleted.
     *
     * @return the total balance from all consultations
     */
    @Query(value = "SELECT SUM(c.due_amount) FROM consultation c WHERE c.client_id IN (SELECT id FROM client WHERE is_deleted = false)", nativeQuery = true)
    Double findTotalBalance();

    /**
     * Finds the monthly earnings for a specific month and year from all consultations, ensuring clients are not marked as deleted.
     *
     * @param month the month to find earnings for
     * @param year the year to find earnings for
     * @return the monthly earnings for the specified month and year
     */
    @Query(value = "SELECT SUM(c.price) FROM consultation c WHERE EXTRACT(MONTH FROM c.consultation_date) = :month AND EXTRACT(YEAR FROM c.consultation_date) = :year AND c.client_id IN (SELECT id FROM client WHERE is_deleted = false)", nativeQuery = true)
    Double findMonthlyEarnings(@Param("month") int month, @Param("year") int year);

    /**
     * Finds a consultation by its ID, ensuring it is not marked as deleted.
     *
     * @param id the ID of the consultation to find
     * @return an Optional containing the found consultation, or empty if no consultation is found
     */
    @Query(value = "SELECT * FROM consultation WHERE id = :id AND is_deleted = false", nativeQuery = true)
    Optional<Consultation> findById(@Param("id") Long id);
}
