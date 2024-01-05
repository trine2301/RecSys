package recsys.controller;

import io.micronaut.core.convert.format.Format;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import recsys.model.ComparisonEntity;
import recsys.model.PeriodEntity;
import recsys.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller to handle the HTTP requests regarding comparisons.
 *
 * @author Trine Staverløkk
 * @version 0.1
 */
@Controller("/period_comparison")
public class ComparisonController {


    private final TransactionService service;

    public ComparisonController(TransactionService service) {
        this.service = service;
    }


    /**
     * Gets the total discrepancy between total amount of bank transactions and accounting transactions.
     *
     * @param startDate The first date of the selected period
     * @param endDate The last date of the selected period
     * @return returns total discrepancy between bank transactions and accounting transactions.
     */
    @Get("/total_discrepancy")
    public double getDiscrepancy(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.getDiscrepancyAmount(startDate, endDate);
    }

    /**
     * Gets total amount for bank transactions for a given time-period.
     *
     * @param startDate The start date of the selected period. Transactions occurring on or after this date will be considered.
     * @param endDate   The end date of the selected period. Transactions occurring on or before this date will be considered.
     * @return Returns total amount for account transactions for the selected period.
     */
    @Get("/total_amount_bank")
    public double getAmountBank(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.getTotalBankSum(startDate, endDate);
    }


    /**
     * Gets total amount for account transactions for a given time-period.
     *
     * @param startDate The start date of the selected period. Transactions occurring on or after this date will be considered.
     * @param endDate   The end date of the selected period. Transactions occurring on or before this date will be considered.
     * @return Returns total amount for account transactions for the selected period.
     */
    @Get("/total_amount_accounting")
    public double getAmountAccounting(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.getTotalAccSum(startDate, endDate);
    }


    /**
     * Compares bank transactions with accounting transactions within a given period.
     *
     * @param startDate The start date of the selected period. Transactions occurring on or after this date will be considered.
     * @param endDate   The end date of the selected period. Transactions occurring on or before this date will be considered.
     * @return A list of ComparisonEntity objects representing the compared transactions. Each entity contains information about the matching or partial matching transactions.
     */
    @Get("/results")
    public List<ComparisonEntity> setComparingResults(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.compareTransactions(startDate, endDate);
    }

    /**
     * Populates PeriodEntity with data and saves it.
     *
     * @param startDate              The start date of the selected period. Transactions occurring on or after this date will be considered.
     * @param endDate                The end date of the selected period. Transactions occurring on or before this date will be considered.
     * @param accTotal               The total sums of accounting transactions.
     * @param bankTotal              The total sum of banking transactions.
     * @param totalDiscrepancyAmount The total discrepancy between the sum of transactions.
     * @return Returns a list of saved entities of type PeriodEntity.
     */
    @Post("/results_for_period")
    public List<PeriodEntity> populatedPeriodEntity(
        @Format("yyyy-MM-dd") @QueryValue LocalDate startDate,
        @Format("yyyy-MM-dd") @QueryValue LocalDate endDate,
        double bankTotal,
        double accTotal,
        double totalDiscrepancyAmount) {
        return service.populatePeriodEntity(startDate, endDate, bankTotal, accTotal, totalDiscrepancyAmount);
    }


}
