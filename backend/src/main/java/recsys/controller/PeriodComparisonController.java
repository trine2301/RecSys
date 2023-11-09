package recsys.controller;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import recsys.repository.PeriodRepository;

/**
 * TODO
 */
@Controller("/period_comparison")
public class PeriodComparisonController {

    private final PeriodRepository periodRepository;


    public PeriodComparisonController(PeriodRepository repository) {
        periodRepository = repository;
    }


    @Get
    public PeriodRepository getPeriodComparisonRepository() {
        return periodRepository;
    }
}

