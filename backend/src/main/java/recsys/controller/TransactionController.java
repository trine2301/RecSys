package recsys.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import recsys.model.TransactionEntity;
import recsys.model.DifferenceEntity;
import recsys.service.TransactionService;

/**
 * Controller to handle the HTTP requests.
 * All is phind. Refactor.
 */
@Controller("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Post("/compare")
    public HttpResponse<DifferenceEntity> compare(TransactionEntity transaction1, TransactionEntity transaction2) {
        DifferenceEntity difference = transactionService.compare(transaction1, transaction2);
        return HttpResponse.ok(difference);
    }
}

