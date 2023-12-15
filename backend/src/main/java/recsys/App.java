package recsys;
import io.micronaut.runtime.Micronaut;
import recsys.model.AccountingTransactionEntity;
import recsys.model.PeriodEntity;
import recsys.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

/**
 * This class will be the entry point of your application.
 * It is responsible for initializing and starting the application.
 */
public class App {
    public static void main(String[] args) {
        var app = Micronaut
                .build(args)
                .start();

        TransactionService transactionService = app.getBean(TransactionService.class);
        transactionService.checkIfDiscrepancyOnTotalAmountExists();
        //List<PeriodEntity> fknPrint = transactionService.populatePeriodEntity(LocalDate.of(2023, 01, 01), LocalDate.of(2023, 02, 01), 1234, 123, 1);
        //List<PeriodEntity> fknPrint2 = transactionService.populatePeriodEntity(LocalDate.of(2023, 01, 01), LocalDate.of(2023, 02, 01), 1234, 123, 1);

        //System.out.println(fknPrint2);

    }
}
