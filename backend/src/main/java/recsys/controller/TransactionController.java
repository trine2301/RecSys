package recsys.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import recsys.model.ComparisonEntity;
import recsys.service.TransactionService;

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

    @Get("/results")
    public List<ComparisonEntity> checkIfAccTransHaveAMatchingBankTrans() {
        return service.setComparingResults();
    }
}

