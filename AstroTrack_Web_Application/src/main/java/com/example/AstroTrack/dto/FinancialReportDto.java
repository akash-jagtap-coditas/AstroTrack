package com.example.AstroTrack.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data Transfer Object for Financial Report.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinancialReportDto {

    /**
     * The total earnings for the month.
     */
    Double monthlyEarning;

    /**
     * The total earnings.
     */
    Double totalEarning;

    /**
     * The total due amount.
     */
    Double totalDueAmount;
}
