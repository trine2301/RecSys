package recsys.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import recsys.exceptions.CouldNotCompareTransactionsException;
import recsys.model.ComparisonEntity;
import recsys.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller to handle the HTTP requests.
 */
@Controller("/period_comparison")
public class TransactionController {

    private final TransactionService service;


    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @Get
    public double getDiscrepancy() {
        return service.getDiscrepancyAmount();
    }

//    @Get("/results")
//    public List<ComparisonEntity> settingResults() throws CouldNotCompareTransactionsException {
//        return service.setComparingResults();
//    }

    @Get("/results")
    public HttpResponse<?> compareTransactions(@QueryValue LocalDate startDate, @QueryValue LocalDate endDate) {
        service.compareTransactions(startDate, endDate);
        return HttpResponse.ok();
    }

}
