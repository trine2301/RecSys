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
 * Controller to handle the HTTP requests.
 */
@Controller("/period_comparison")
public class ComparisonController {


    private final TransactionService service;

    public ComparisonController(TransactionService service) {
        this.service = service;
    }

    /**
     * Send calculation of the total discrepancy and sends
     * @param startDate
     * @param endDate
     * @return
     */
    @Get("/total_discrepancy")
    public double getDiscrepancy(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.getDiscrepancyAmount(startDate, endDate);
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Get("/total_amount_bank")
    public double getAmountBank(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.getTotalBankSum(startDate, endDate);
    }


    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Get("/total_amount_accounting")
    public double getAmountAccounting(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.getTotalAccSum(startDate, endDate);
    }


    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
    @Get("/results")
    public List<ComparisonEntity> setComparingResults(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.compareTransactions(startDate, endDate);
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @param bankTotal
     * @param accTotal
     * @param totalDiscrepancyAmount
     * @return
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
