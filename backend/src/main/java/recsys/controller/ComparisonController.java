package recsys.controller;

import io.micronaut.core.convert.format.Format;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import recsys.model.ComparisonEntity;
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

    @Get("/total_discrepancy")
    public double getDiscrepancy(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.getDiscrepancyAmount(startDate, endDate);
    }

    @Get("/total_amount_bank")
    public double getAmountBank(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.getTotalBankSum(startDate, endDate);
    }

    @Get("/total_amount_accounting")
    public double getAmountAccounting(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.getTotalAccSum(startDate, endDate);
    }


    @Get("/results")
    public List<ComparisonEntity> setComparingResults(@Format("yyyy-MM-dd") @QueryValue LocalDate startDate, @Format("yyyy-MM-dd") @QueryValue LocalDate endDate) {
        return service.compareTransactions(startDate, endDate);
    }

}
