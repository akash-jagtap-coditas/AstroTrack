package com.example.AstroTrack.controller;

import com.example.AstroTrack.dto.ApiResponse;
import com.example.AstroTrack.dto.ConsultationDto;
import com.example.AstroTrack.dto.FinancialReportDto;
import com.example.AstroTrack.exception.ConsultationListEmpty;
import com.example.AstroTrack.service.Impl.ConsultationServiceImpl;
import com.example.AstroTrack.utils.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Controller to handle consultation-related requests.
 */
@RestController
@RequestMapping("/astrotrack/consultation")
@Tag(name = "Consultation", description = "APIs for managing consultations")
public class ConsultationController {

    @Autowired
    ConsultationServiceImpl consultationServiceImpl;

    /**
     * Creates a new consultation for a given client.
     *
     * @param consultationDto the consultation data to create
     * @param clientId the ID of the client
     * @return a response containing the created consultation data
     */
    @Operation(summary = "Create consultation", description = "Creates a new consultation for a given client.")
    @PostMapping
    public ResponseEntity<ApiResponse<ConsultationDto>> createConsultation(@Valid @RequestBody ConsultationDto consultationDto, @RequestParam Long clientId) {
        ConsultationDto consultationDtoResponse = consultationServiceImpl.createConsultation(consultationDto, clientId);
        return ResponseUtil.success(consultationDtoResponse, "Consultation created successfully!");
    }

    /**
     * Retrieves all consultations for a given client.
     *
     * @param clientId the ID of the client
     * @return a response containing the list of consultations
     * @throws ConsultationListEmpty if the consultation list is empty
     */
    @Operation(summary = "Get all consultations", description = "Retrieves all consultations for a given client.")
    @GetMapping("/{clientId}")
    public ResponseEntity<ApiResponse<List<ConsultationDto>>> getAllConsultations(@PathVariable Long clientId) {
        List<ConsultationDto> consultationDtoList = consultationServiceImpl.getAllConsultations(clientId);
        if (consultationDtoList == null || consultationDtoList.isEmpty()) {
            throw new ConsultationListEmpty("Consultation list is empty!");
        }
        return ResponseUtil.success(consultationDtoList, "Consultation list retrieved successfully!");
    }

    /**
     * Retrieves a consultation by ID.
     *
     * @param id the ID of the consultation
     * @return a response containing the consultation data
     */
    @Operation(summary = "Get consultation by ID", description = "Retrieves a consultation by ID.")
    @GetMapping("/getConsultation/{id}")
    public ResponseEntity<ApiResponse<Optional<ConsultationDto>>> getConsultation(@PathVariable Long id) {
        ConsultationDto consultationDto = consultationServiceImpl.getConsultation(id);
        return ResponseUtil.success(Optional.ofNullable(consultationDto), "Consultation retrieved successfully!");
    }

    /**
     * Updates a consultation by ID.
     *
     * @param id the ID of the consultation to update
     * @param consultationDto the updated consultation data
     * @return a response containing the updated consultation data
     */
    @Operation(summary = "Update consultation", description = "Updates a consultation by ID.")
    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<ConsultationDto>> updateConsultation(@PathVariable Long id, @Valid @RequestBody ConsultationDto consultationDto) {
        ConsultationDto consultationDtoResponse = consultationServiceImpl.updateConsultation(id, consultationDto);
        return ResponseUtil.success(consultationDtoResponse, "Consultation updated successfully!");
    }

    /**
     * Deletes a consultation by ID.
     *
     * @param id the ID of the consultation to delete
     * @return a response confirming the deletion
     */
    @Operation(summary = "Delete consultation", description = "Deletes a consultation by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteConsultation(@PathVariable Long id) {
        consultationServiceImpl.deleteConsultation(id);
        return ResponseUtil.delete(id, "Consultation deleted successfully!");
    }

    /**
     * Retrieves the financial report for a specific date.
     *
     * @param date the date for the financial report
     * @return a response containing the financial report
     */
    @Operation(summary = "Get financial report", description = "Retrieves the financial report for a specific date.")
    @GetMapping("/getFinancialReport")
    public ResponseEntity<ApiResponse<FinancialReportDto>> getFinancialReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        FinancialReportDto financialReportDto = consultationServiceImpl.getFinancialReport(date);
        return ResponseUtil.success(financialReportDto, "Financial report retrieved successfully!");
    }
}
